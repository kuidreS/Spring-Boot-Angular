package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vserdiuk.tutorial.monolithicspringboot.dto.StudentDto;
import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StudentRepository;
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
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

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
    private StudentRepository studentRepository;

    @Test
    public void createTest() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Mason");
        studentDto.setLastName("Johnson");

        String studentDtoJson = objectMapper.writeValueAsString(studentDto);

        MockHttpServletResponse response = mockMvc.perform(post("/monolith/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getAllTest() throws Exception {
        Student student1 = new Student();
        student1.setFirstName("Mason");
        student1.setLastName("Johnson");

        Student student2 = new Student();
        student2.setFirstName("Matthew");
        student2.setLastName("Williams");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        given(studentRepository.findAll()).willReturn(students);

        MockHttpServletResponse response = mockMvc.perform(get("/monolith/student/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(student1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(student1.getLastName())))
                .andExpect(jsonPath("$[1].firstName", is(student2.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(student2.getLastName())))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String studentsJson = objectMapper.writeValueAsString(students);
        assertThat(response.getContentAsString()).isEqualTo(studentsJson);
    }

    @Test
    public void deleteTest() throws Exception {
        Student student = new Student();
        student.setId(1l);
        student.setFirstName("Mason");
        student.setLastName("Johnson");

        given(studentRepository.findById(1l)).willReturn(java.util.Optional.ofNullable(student));

        MockHttpServletResponse response = mockMvc.perform(delete("/monolith/student/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(studentRepository.findAll()).isEmpty();
    }

    @Test
    public void deleteAll() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/monolith/student/deleteAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(studentRepository.findAll()).isEmpty();
    }
}