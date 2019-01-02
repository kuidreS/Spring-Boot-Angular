package com.vserdiuk.tutorial.monolithicspringboot.service;

import com.vserdiuk.tutorial.monolithicspringboot.model.*;
import com.vserdiuk.tutorial.monolithicspringboot.repository.ExamRepository;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StudentRepository;
import com.vserdiuk.tutorial.monolithicspringboot.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExamServiceImplTest {

    @TestConfiguration
    static class ExamServiceImplTestContextConfiguration {
        @Bean
        public ExamService examService() {
            return new ExamServiceImpl();
        }
    }

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ExamService examService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void getStatistics() {
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

        Iterable<Statistics> statisticsIterable = examService.getStatistics();
        assertThat(statisticsIterable).isNotEmpty();
    }
}