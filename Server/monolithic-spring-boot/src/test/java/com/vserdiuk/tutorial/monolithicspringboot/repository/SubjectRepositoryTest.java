package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
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
public class SubjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void getTest() {
        Subject subject = new Subject();
        subject.setName("Math");
        entityManager.persist(subject);

        Subject foundSubject = subjectRepository.findById(subject.getId()).get();

        assertThat(foundSubject).isEqualTo(subject);
    }

    @Test
    public void findAllTest() {
        Iterable<Subject> subjects = subjectRepository.findAll();
        assertThat(subjects).isEmpty();
    }

    @Test
    public void saveTest() {
        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Iterable<Subject> subjects = subjectRepository.findAll();
        assertThat(subjects).hasSize(1);
    }

    @Test
    public void saveAllTest() {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setName("Biology");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjectRepository.saveAll(subjects);

        Iterable<Subject> allSubjects = subjectRepository.findAll();
        assertThat(subjects).hasSize(2);
    }

    @Test
    public void deleteAllTest() {
        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        subjectRepository.deleteAll();

        assertThat(subjectRepository.findAll()).isEmpty();
    }

}