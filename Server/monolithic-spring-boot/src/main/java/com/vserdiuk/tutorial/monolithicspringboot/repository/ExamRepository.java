package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Exam;
import com.vserdiuk.tutorial.monolithicspringboot.model.projection.StatisticsProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    @Query(value = "select subjects.name as subject, count(exams.student_id) as students, " +
            "sum(case when exams.ects = 'A' then 1 else 0 end) as A, " +
            "sum(case when exams.ects = 'B' then 1 else 0 end) as B, " +
            "sum(case when exams.ects = 'C' then 1 else 0 end) as C, " +
            "sum(case when exams.ects = 'D' then 1 else 0 end) as D, " +
            "sum(case when exams.ects = 'E' then 1 else 0 end) as E, " +
            "sum(case when exams.ects = 'F' then 1 else 0 end) as F " +
            "from exams " +
            "inner join subjects " +
            "on exams.subject_id = subjects.subject_id " +
            "group by subjects.name",
            nativeQuery = true)
    List<StatisticsProjection> getStatistics();
}
