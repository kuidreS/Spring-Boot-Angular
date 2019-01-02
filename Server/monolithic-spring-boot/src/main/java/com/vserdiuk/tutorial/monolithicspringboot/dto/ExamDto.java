package com.vserdiuk.tutorial.monolithicspringboot.dto;

import com.vserdiuk.tutorial.monolithicspringboot.model.ECTS;
import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import lombok.Data;

@Data
public class ExamDto {
    private Subject subject;
    private Student student;
    private ECTS ects;
}
