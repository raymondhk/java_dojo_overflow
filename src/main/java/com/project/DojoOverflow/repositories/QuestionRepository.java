package com.project.DojoOverflow.repositories;

import com.project.DojoOverflow.models.Question;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface QuestionRepository extends CrudRepository<Question,Long>{
    List<Question> findAll();
}
