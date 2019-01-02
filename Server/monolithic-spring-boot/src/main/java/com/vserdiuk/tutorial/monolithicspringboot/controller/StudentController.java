package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.vserdiuk.tutorial.monolithicspringboot.dto.StudentDto;
import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import com.vserdiuk.tutorial.monolithicspringboot.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/monolith/student")
@Api(value = "student", description = "Student CRUD operations")
public class StudentController {

    final static Logger logger = Logger.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Create a student")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create (@RequestBody StudentDto studentDto) {
        Student student = convertDtoToEntity(studentDto);
        studentRepository.save(student);
    }

    @ApiOperation(value = "Get a list of all students", response = List.class)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @ApiOperation(value = "Find students by first name and last name", response = List.class)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Student> getByFirstNameAndLastName(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @ApiOperation(value = "Delete student by ID")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
    }

    @ApiOperation(value = "Delete all students")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @ApiOperation(value = "Update student")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update (@RequestBody Student student) {
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        studentRepository.save(updatedStudent);
    }

    private Student convertDtoToEntity(StudentDto studentDto) {
        Student student = null;
        try {
            student = modelMapper.map(studentDto, Student.class);
        } catch (MappingException e) {
            logger.error(e);
        }
        return student;
    }

}
