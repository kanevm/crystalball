package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.GAME_FORM_ATTR;

import org.football.form.validator.GameFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = GameController.class)
public class GameControllerAdvice {

	@Autowired
	private GameFormValidator gameFormValidator;

	@InitBinder(GAME_FORM_ATTR)
	public void initBinder(final WebDataBinder binder) {
		binder.addValidators(gameFormValidator);
	}
}
