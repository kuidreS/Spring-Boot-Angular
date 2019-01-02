package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

}
