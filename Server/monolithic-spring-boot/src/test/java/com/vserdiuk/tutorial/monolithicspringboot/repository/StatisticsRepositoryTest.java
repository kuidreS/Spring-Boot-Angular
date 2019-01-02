package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatisticsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    public void findBySubject() {
        Statistics statistics = new Statistics();
        statistics.setStudents(10);
        statistics.setSubject("Math");
        statistics.setA(3);
        statistics.setB(2);
        statistics.setC(2);
        statistics.setD(1);
        statistics.setE(1);
        statistics.setF(1);
        entityManager.persist(statistics);

        Statistics foundStatistics = statisticsRepository.findBySubject("Math");

        assertThat(foundStatistics).isEqualTo(statistics);
    }
}