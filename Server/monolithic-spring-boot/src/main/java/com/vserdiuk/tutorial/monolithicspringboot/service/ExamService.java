package com.vserdiuk.tutorial.monolithicspringboot.service;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;

import java.util.List;

public interface ExamService {

    List<Statistics> getStatistics();
}
