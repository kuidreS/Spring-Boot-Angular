package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.vserdiuk.tutorial.monolithicspringboot.dto.ExamDto;
import com.vserdiuk.tutorial.monolithicspringboot.model.Exam;
import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import com.vserdiuk.tutorial.monolithicspringboot.repository.ExamRepository;
import com.vserdiuk.tutorial.monolithicspringboot.service.ExamService;
import com.vserdiuk.tutorial.monolithicspringboot.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/monolith/exam")
@Api(value = "exam", description = "Exam CRUD operations")
public class ExamController {

    final static Logger logger = Logger.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Create an exam")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create (@RequestBody ExamDto examDto) {
        Exam exam = convertDtoToEntity(examDto);
        examRepository.save(exam);
        List<Statistics> statistics = examService.getStatistics();
        for (Statistics s : statistics) {
            statisticsService.updateStatistics(s);
        }
    }

    private Exam convertDtoToEntity(ExamDto examDto) {
        Exam exam = null;
        try {
            exam = modelMapper.map(examDto, Exam.class);
        } catch (MappingException e) {
            logger.error(e);
        }
        return exam;
    }

}
