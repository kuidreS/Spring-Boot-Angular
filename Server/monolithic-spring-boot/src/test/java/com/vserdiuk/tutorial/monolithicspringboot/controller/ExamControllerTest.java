package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vserdiuk.tutorial.monolithicspringboot.model.ECTS;
import com.vserdiuk.tutorial.monolithicspringboot.model.Exam;
import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import com.vserdiuk.tutorial.monolithicspringboot.repository.ExamRepository;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StudentRepository;
import com.vserdiuk.tutorial.monolithicspringboot.repository.SubjectRepository;
import com.vserdiuk.tutorial.monolithicspringboot.service.ExamService;
import com.vserdiuk.tutorial.monolithicspringboot.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @TestConfiguration
    static class ExamControllerTestContextConfiguration {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExamService examService;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private ExamRepository examRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    public void createTest() throws Exception {
        Student student = new Student();
        student.setFirstName("Mason");
        student.setLastName("Johnson");

        Subject subject = new Subject();
        subject.setName("Math");

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudent(student);
        exam.setEcts(ECTS.A);

        String examJson = objectMapper.writeValueAsString(exam);

        MockHttpServletResponse response = mockMvc.perform(post("/monolith/exam/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(examJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}