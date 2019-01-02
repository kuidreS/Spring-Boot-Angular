package com.vserdiuk.tutorial.monolithicspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "statistics_id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "students")
    private int students;

    @Column(name = "a")
    private int a;

    @Column(name = "b")
    private int b;

    @Column(name = "c")
    private int c;

    @Column(name = "d")
    private int d;

    @Column(name = "e")
    private int e;

    @Column(name = "f")
    private int f;
}
