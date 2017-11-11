package com.project.DojoOverflow.services;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.DojoOverflow.models.Answer;
import com.project.DojoOverflow.models.Question;
import com.project.DojoOverflow.models.Tag;
import com.project.DojoOverflow.repositories.AnswerRepository;
import com.project.DojoOverflow.repositories.QuestionRepository;
import com.project.DojoOverflow.repositories.TagRepository;

@Service
public class DojoService {
	private QuestionRepository questionRepo;
	private TagRepository tagRepo;
	private AnswerRepository answerRepo;
		
	public DojoService(QuestionRepository questionRepo, TagRepository tagRepo, AnswerRepository answerRepo){
		this.questionRepo = questionRepo;
		this.tagRepo = tagRepo;
		this.answerRepo = answerRepo;
	}

	public void createAnswer(Answer answer) {
		answerRepo.save(answer);
	}
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}
	public void deleteQuestion(Long id) {
		questionRepo.delete(id);
	}
	public Question createQuestion(String question) {
		Question q = new Question();
		q.setQuestion(question);
		this.questionRepo.save(q);
		return q;
	}
	public Tag getTagById(Long id) {
		return tagRepo.findOne(id);
	}
	public Question getQuestionById(Long id) {
		return questionRepo.findOne(id);
	}
	public Question updateQuestion(Question question) {
		return questionRepo.save(question);
	}
	public List<Tag> createTags(List<String> tags, Question question) {
		for(String tag : tags) {
			Tag t = new Tag();
			if(tagRepo.findBySubject(tag) == null){
				t.setSubject(tag);
				this.tagRepo.save(t);
				question.getTags().add(t);
				questionRepo.save(question);
			} else {
				t = tagRepo.findBySubject(tag);
				question.getTags().add(t);
				questionRepo.save(question);
			}
		}
		return question.getTags();
	}
	
	// Crud methods to act on services go here.
}
