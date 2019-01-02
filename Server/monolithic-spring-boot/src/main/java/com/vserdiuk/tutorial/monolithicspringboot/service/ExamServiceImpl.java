package com.vserdiuk.tutorial.monolithicspringboot.service;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import com.vserdiuk.tutorial.monolithicspringboot.model.projection.StatisticsProjection;
import com.vserdiuk.tutorial.monolithicspringboot.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Statistics> getStatistics() {
        List<StatisticsProjection> statisticsProjectionList = examRepository.getStatistics();
        List<Statistics> statistics = statisticsProjectionList.stream().map(s -> modelMapper.map(s, Statistics.class)).collect(Collectors.toList());
        return statistics;
    }
}
