package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
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
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void getTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        entityManager.persist(student);

        Student foundStudent = studentRepository.findById(student.getId()).get();

        assertThat(foundStudent).isEqualTo(student);
    }

    @Test
    public void getAllTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        entityManager.persist(student);

        Iterable<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(1);
    }

    @Test
    public void saveTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        studentRepository.save(student);

        Iterable<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(1);
    }

    @Test
    public void saveAllTest() {
        Student student1 = new Student();
        student1.setFirstName("Mason");
        student1.setLastName("Johnson");

        Student student2 = new Student();
        student2.setFirstName("Matthew");
        student2.setLastName("Williams");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentRepository.saveAll(students);

        Iterable<Student> allStudents = studentRepository.findAll();
        assertThat(allStudents).hasSize(2);
    }

    @Test
    public void deleteAllTest() {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");
        entityManager.persist(student);

        studentRepository.deleteAll();

        assertThat(studentRepository.findAll()).isEmpty();
    }

}