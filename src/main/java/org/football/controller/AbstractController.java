package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.CURRENT_USER_ATTR;
import static org.football.controller.ControllerAttributeConstants.TIME_ATTR;

import java.util.Date;

import org.football.persistance.user.User;
import org.football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractController {

	protected static final String REDIRECT_PREFIX = "redirect:";

	@Autowired
	protected UserService userService;

	@ModelAttribute(CURRENT_USER_ATTR)
	protected User getCurrentUser() {
		return userService.getCurrentUser();
	}

	@ModelAttribute(TIME_ATTR)
	public Date getTime() {
		return new Date();
	}
}
