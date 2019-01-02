package com.vserdiuk.tutorial.monolithicspringboot.service;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public void updateStatistics(Statistics s) {
        Statistics statistics = statisticsRepository.findBySubject(s.getSubject());
        if (statistics != null) {
            statistics.setStudents(s.getStudents());
            statistics.setA(s.getA());
            statistics.setB(s.getB());
            statistics.setC(s.getC());
            statistics.setD(s.getD());
            statistics.setE(s.getE());
            statistics.setF(s.getF());
            statisticsRepository.save(statistics);
        } else {
            statisticsRepository.save(s);
        }
    }

}
