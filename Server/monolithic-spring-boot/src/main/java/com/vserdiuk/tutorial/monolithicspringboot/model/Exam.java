package com.vserdiuk.tutorial.monolithicspringboot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    @ApiModelProperty(notes = "The database generated exam ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @ApiModelProperty(notes = "Subject ID - the subject which is connected to current exam")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ApiModelProperty(notes = "Student ID - the student ID who is passing the exam")
    private Student student;

    @Column(name = "ECTS")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "ECTS (European Credit Transfer and Accumulation System) " +
            "- the grade the student received on the exam")
    private ECTS ects;
}
