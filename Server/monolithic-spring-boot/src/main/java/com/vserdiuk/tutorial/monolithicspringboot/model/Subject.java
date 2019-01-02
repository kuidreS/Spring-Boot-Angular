package com.vserdiuk.tutorial.monolithicspringboot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    @ApiModelProperty(notes = "The database generated subject ID")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The subject name")
    private String name;
}
