package com.vserdiuk.tutorial.monolithicspringboot.model.projection;

import com.vserdiuk.tutorial.monolithicspringboot.model.Statistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "statisticsProjections",
        types = { Statistics.class })
public interface StatisticsProjection {
    @Value("#{target.subject}")
    String getSubject();

    @Value("#{target.students}")
    int getStudents();

    @Value("#{target.a}")
    int getA();

    @Value("#{target.b}")
    int getB();

    @Value("#{target.c}")
    int getC();

    @Value("#{target.d}")
    int getD();

    @Value("#{target.e}")
    int getE();

    @Value("#{target.f}")
    int getF();
}
