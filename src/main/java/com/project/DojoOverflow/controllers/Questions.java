package com.project.DojoOverflow.controllers;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.DojoOverflow.models.Question;
import com.project.DojoOverflow.models.Tag;
import com.project.DojoOverflow.services.DojoService;

@Controller
public class Questions{
	private final DojoService dojo;

	public Questions(DojoService dojo){
		this.dojo = dojo;
	}
	
	@RequestMapping("/questions")
	public String questions(Model model){
		model.addAttribute("questions", dojo.getAllQuestions());
		return "showQuestions";
	}
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("error") String error, @ModelAttribute("errors") String errors, @ModelAttribute("success") String success){
		return "addQuestion";
	}
	@PostMapping("/questions/new")
	public String addQuestion(@RequestParam(value="question") String quest, @RequestParam(value="tag") String t, RedirectAttributes redirectA){
		List<String> tags = Arrays.asList(t.replaceAll("\\s", "").split(","));
		// System.out.println(tags.size());
		if(quest.isEmpty()) {
			redirectA.addFlashAttribute("errors", "Question must be entered!");
		} 
		else if (t.isEmpty()) {
			redirectA.addFlashAttribute("error", "Tag must be entered!");
		}
		else if(tags.size() > 3) {
			redirectA.addFlashAttribute("error", "You can only enter 3 tags!");
		}  
		else {
			System.out.println("works");
			redirectA.addFlashAttribute("success", "Success!!");
			Question question = dojo.createQuestion(quest);
			List<Tag> tagsList = dojo.createTags(tags, question);
		}
		return "redirect:/questions/new";
	}
	@RequestMapping("/questions/delete/{id}")
	public String deleteQuestion(@PathVariable("id") Long id) {
		dojo.deleteQuestion(id);
		return "redirect:/questions";
	}
	@RequestMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model, @ModelAttribute("errors") String errors) {
		model.addAttribute("question", dojo.getQuestionById(id));
		return "showOneQuestion";
	}
}
