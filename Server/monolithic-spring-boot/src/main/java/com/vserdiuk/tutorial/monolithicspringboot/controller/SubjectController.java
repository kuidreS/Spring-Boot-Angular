package com.vserdiuk.tutorial.monolithicspringboot.controller;

import com.vserdiuk.tutorial.monolithicspringboot.dto.SubjectDto;
import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import com.vserdiuk.tutorial.monolithicspringboot.repository.SubjectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monolith/subject")
@Api(value = "subject", description = "Subject CRUD operations")
public class SubjectController {

    final static Logger logger = Logger.getLogger(SubjectController.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Create a subject")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create (@RequestBody SubjectDto subjectDto) {
        Subject subject = convertDtoToEntity(subjectDto);
        subjectRepository.save(subject);
    }

    @ApiOperation(value = "Get all subjects", response = List.class)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Subject> getAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @ApiOperation(value = "Delete a subject by ID")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        subjectRepository.deleteById(id);
    }

    @ApiOperation(value = "Delete all subjects")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll() {
        subjectRepository.deleteAll();
    }

    @ApiOperation(value = "Update subject")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update (@RequestBody Subject subject) {
        Subject updatedSubject = subjectRepository.findById(subject.getId()).get();
        updatedSubject.setName(subject.getName());
        subjectRepository.save(updatedSubject);
    }

    @ApiOperation(value = "Find students by first name and last name", response = List.class)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Subject getByName(@RequestParam("name") String name) {
        return subjectRepository.findByName(name);
    }

    private Subject convertDtoToEntity(SubjectDto subjectDto) {
        Subject subject = null;
        try {
            subject = modelMapper.map(subjectDto, Subject.class);
        } catch (MappingException e) {
            logger.error(e);
        }
        return subject;
    }
}
