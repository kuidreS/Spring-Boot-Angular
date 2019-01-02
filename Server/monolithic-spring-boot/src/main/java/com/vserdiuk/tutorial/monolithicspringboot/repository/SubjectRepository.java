package com.vserdiuk.tutorial.monolithicspringboot.repository;

import com.vserdiuk.tutorial.monolithicspringboot.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    Subject findByName(String name);

}
