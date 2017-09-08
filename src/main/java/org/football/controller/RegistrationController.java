package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.REGISTER_FORM_ATTR;
import static org.football.controller.ControllerUrlConstants.HOME_URL;
import static org.football.controller.ControllerUrlConstants.REGISTER_URL;
import static org.football.controller.ControllerViewConstants.RegisterPage;

import javax.validation.Valid;

import org.football.form.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(REGISTER_URL)
public class RegistrationController extends AbstractController {

	@GetMapping
	public String getUserCreatePage(final Model model) {
		prepareRegisterPage(model, new RegisterForm());

		return RegisterPage;
	}

	@PostMapping
	public String handleUserCreateForm(@Valid @ModelAttribute(REGISTER_FORM_ATTR) final RegisterForm registerForm,
			final BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			prepareRegisterPage(model, registerForm);

			return RegisterPage;
		}

		userService.create(registerForm);

		return REDIRECT_PREFIX + HOME_URL;
	}

	private void prepareRegisterPage(final Model model, final RegisterForm registerForm) {
		model.addAttribute(REGISTER_FORM_ATTR, registerForm);
	}
}