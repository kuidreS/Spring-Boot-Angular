package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vserdiuk.tutorial.monolithicspringboot.dto.SubjectDto;
import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import com.vserdiuk.tutorial.monolithicspringboot.repository.SubjectRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    @TestConfiguration
    static class StudentControllerTestContextConfiguration {
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
    private SubjectRepository subjectRepository;

    @Test
    public void createTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Math");

        String subjectJson = objectMapper.writeValueAsString(subjectDto);

        MockHttpServletResponse response = mockMvc.perform(post("/monolith/subject/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(subjectJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getAllTest() throws Exception {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setName("Biology");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        given(subjectRepository.findAll()).willReturn(subjects);

        MockHttpServletResponse response = mockMvc.perform(get("/monolith/subject/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(subject1.getName())))
                .andExpect(jsonPath("$[1].name", is(subject2.getName())))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String studentsJson = objectMapper.writeValueAsString(subjects);
        assertThat(response.getContentAsString()).isEqualTo(studentsJson);
    }

    @Test
    public void deleteTest() throws Exception {
        Subject subject = new Subject();
        subject.setId(1l);
        subject.setName("Math");

        given(subjectRepository.findById(1l)).willReturn(java.util.Optional.ofNullable(subject));

        MockHttpServletResponse response = mockMvc.perform(delete("/monolith/subject/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(subjectRepository.findAll()).isEmpty();
    }

    @Test
    public void deleteAllTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/monolith/subject/deleteAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(subjectRepository.findAll()).isEmpty();
    }
}