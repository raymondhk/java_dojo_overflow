package com.project.DojoOverflow.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.DojoOverflow.models.Answer;
import com.project.DojoOverflow.services.DojoService;

@Controller
public class Answers{
	private final DojoService dojo;

	public Answers(DojoService dojo){
		this.dojo = dojo;
	}
	
	@PostMapping("/questions/{id}")
	public String addAnswer(@PathVariable Long id, @RequestParam(value="answer") String answer, RedirectAttributes redirectA) {
		if(answer.isEmpty()) {
			redirectA.addFlashAttribute("errors", "Answer must be entered!");
		} else {
			Answer newAnswer = new Answer(answer, dojo.getQuestionById(id));
			dojo.createAnswer(newAnswer);
		}
		return "redirect:/questions/"+id;
	}	
}
