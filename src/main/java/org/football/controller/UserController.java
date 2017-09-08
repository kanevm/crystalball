package org.football.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.football.form.RegisterForm;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends AbstractController {

	@GetMapping("/users")
	public ModelAndView getUsersPage() {
		return new ModelAndView("users", "users", userService.getAllUsers());
	}

	@GetMapping("/user/{id}")
	public ModelAndView getUserPage(@PathVariable Long id) {
		return new ModelAndView("user", "user", userService.getUserById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
	}

	@GetMapping("/user/create")
	public ModelAndView getUserCreatePage() {
		return new ModelAndView("user_create", "form", new RegisterForm());
	}

	@PostMapping("/user/create")
	public String handleUserCreateForm(@Valid @ModelAttribute("form") RegisterForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user_create";
		}
		try {
			userService.create(form);
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("email.exists", "Email already exists");
			return "user_create";
		}
		return "redirect:/users";
	}
}
