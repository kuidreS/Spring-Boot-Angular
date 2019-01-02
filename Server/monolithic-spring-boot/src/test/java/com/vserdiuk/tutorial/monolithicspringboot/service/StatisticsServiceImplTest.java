package com.vserdiuk.tutorial.monolithicspringboot.service;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import com.vserdiuk.tutorial.monolithicspringboot.repository.ExamRepository;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StatisticsRepository;
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
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatisticsServiceImplTest {

    @TestConfiguration
    static class StatisticsServiceImplTestContextConfiguration {
        @Bean
        public StatisticsService statisticsService() {
            return new StatisticsServiceImpl();
        }
    }

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void updateStatistics() {
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

        Statistics updatedStatistics = new Statistics();
        updatedStatistics.setStudents(11);
        updatedStatistics.setSubject("Math");
        updatedStatistics.setA(4);
        updatedStatistics.setB(2);
        updatedStatistics.setC(2);
        updatedStatistics.setD(1);
        updatedStatistics.setE(1);
        updatedStatistics.setF(1);

        statisticsService.updateStatistics(updatedStatistics);

        Statistics expectedStatistics = statisticsRepository.findBySubject("Math");

        assertThat(updatedStatistics.getA()).isEqualTo(expectedStatistics.getA());
        assertThat(updatedStatistics.getB()).isEqualTo(expectedStatistics.getB());
        assertThat(updatedStatistics.getC()).isEqualTo(expectedStatistics.getC());
        assertThat(updatedStatistics.getD()).isEqualTo(expectedStatistics.getD());
        assertThat(updatedStatistics.getE()).isEqualTo(expectedStatistics.getE());
        assertThat(updatedStatistics.getF()).isEqualTo(expectedStatistics.getF());
    }
}