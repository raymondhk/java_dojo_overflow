package com.project.DojoOverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.DojoOverflow.models.Tag;

@Repository 												
public interface TagRepository extends CrudRepository<Tag,Long>{
	Tag findBySubject(String subject);
}
