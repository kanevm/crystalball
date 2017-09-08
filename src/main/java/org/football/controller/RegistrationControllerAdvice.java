package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.REGISTER_FORM_ATTR;

import org.football.form.validator.RegisterFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = RegistrationController.class)
public class RegistrationControllerAdvice {

	@Autowired
	private RegisterFormValidator registerFormValidator;

	@InitBinder(REGISTER_FORM_ATTR)
	public void initBinder(final WebDataBinder binder) {
		binder.addValidators(registerFormValidator);
	}
}
