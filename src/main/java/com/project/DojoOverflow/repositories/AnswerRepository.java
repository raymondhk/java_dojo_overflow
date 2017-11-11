package com.project.DojoOverflow.repositories;

import com.project.DojoOverflow.models.Answer;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface AnswerRepository extends CrudRepository<Answer,Long>{
}
