package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.ECTS;
import com.vserdiuk.tutorial.monolithicspringboot.model.Exam;
import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import com.vserdiuk.tutorial.monolithicspringboot.model.projection.StatisticsProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExamRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void getTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        entityManager.persist(student);

        Subject subject = new Subject();
        subject.setName("Math");
        entityManager.persist(subject);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);
        entityManager.persist(exam);

        Exam foundExam = examRepository.findById(exam.getId()).get();

        assertThat(foundExam).isEqualTo(exam);
    }

    @Test
    public void findAllTest() {
        Iterable<Exam> exams = examRepository.findAll();
        assertThat(exams).isEmpty();
    }

    @Test
    public void saveTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);
        examRepository.save(exam);

        Iterable<Exam> allExams = examRepository.findAll();
        assertThat(allExams).hasSize(1);
    }

    @Test
    public void saveAllTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);

        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        examRepository.saveAll(exams);

        Iterable<Exam> allExams = examRepository.findAll();
        assertThat(allExams).hasSize(1);
    }

    @Test
    public void deleteAllTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);
        examRepository.save(exam);

        examRepository.deleteAll();

        assertThat(examRepository.findAll()).isEmpty();
    }

    @Test
    public void getStatistics() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);
        examRepository.save(exam);

        List<StatisticsProjection> statisticsProjections = examRepository.getStatistics();

        assertThat(statisticsProjections).isNotEmpty();
    }
}