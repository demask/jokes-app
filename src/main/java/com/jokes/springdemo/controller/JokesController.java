package com.jokes.springdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jokes.springdemo.dao.JokesDao;
import com.jokes.springdemo.entity.Category;
import com.jokes.springdemo.entity.Joke;
import com.jokes.springdemo.entity.JokeHelp;
import com.jokes.springdemo.entity.ModelClass;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/toJokes")
public class JokesController {

	@Autowired
	private JokesDao jokesDAO;

//	add an initbinder.. to convert trim input strings
//	remove leading and trailing whitespace
//	resolve issue for validation
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/listJokes")
	public String listJokes(Model theModel) {

//		 get jokes from the service
		List<Joke> theJokes = jokesDAO.getJokes();

//		// add the jokes to the model
		theModel.addAttribute("jokes", theJokes);

		return "list-jokes";
	}

	@GetMapping("/likeJoke")
	public String likeJoke(@RequestParam("jokeId") int jokeId) {

		jokesDAO.likeJoke(jokeId);

		return "redirect:/toJokes/listJokes";
	}

	@GetMapping("/dislikeJoke")
	public String dislikeJoke(@RequestParam("jokeId") int jokeId) {
		
		jokesDAO.dislikeJoke(jokeId);

		return "redirect:/toJokes/listJokes";
	}

	@GetMapping("/showJokeForm")
	public String showFormForUpdate(Model theModel) {

		JokeHelp theJokeHelp = new JokeHelp("");

		theModel.addAttribute("joke", theJokeHelp);

		return "joke-form";
	}

	@PostMapping("/saveJoke")
	public String saveJoke(@Valid @ModelAttribute("joke") JokeHelp theJokeHelp, 
			BindingResult theBindingResult, Model theModel) {

		if (theBindingResult.hasErrors()) {
			return "joke-form";
		} else {
			Joke joke = new Joke(theJokeHelp.getContent());
			joke.setCategory(new Category(theJokeHelp.getCategory()));
			jokesDAO.saveJoke(joke);
			return "redirect:/toJokes/listJokes";
		}

	}

}
