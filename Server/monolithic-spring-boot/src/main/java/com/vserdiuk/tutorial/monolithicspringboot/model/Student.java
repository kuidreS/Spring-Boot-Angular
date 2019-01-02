package com.vserdiuk.tutorial.monolithicspringboot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    @ApiModelProperty(notes = "The database generated student ID")
    private Long id;

    @Column(name = "first_name")
    @ApiModelProperty(notes = "Student first name")
    private String firstName;

    @Column(name = "last_name")
    @ApiModelProperty(notes = "Student last name")
    private String lastName;
}
