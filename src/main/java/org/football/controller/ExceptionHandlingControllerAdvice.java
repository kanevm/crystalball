package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.CURRENT_USER_ATTR;
import static org.football.controller.ControllerAttributeConstants.EXCEPTION_ATTR;
import static org.football.controller.ControllerAttributeConstants.URL_ATTR;
import static org.football.controller.ControllerViewConstants.ErrorPage;

import javax.servlet.http.HttpServletRequest;

import org.football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

	@Autowired
	protected UserService userService;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(final HttpServletRequest req, final Exception e) throws Exception {

		final ModelAndView mav = new ModelAndView();
		mav.addObject(EXCEPTION_ATTR, e);
		mav.addObject(URL_ATTR, req.getRequestURL());
		mav.addObject(CURRENT_USER_ATTR, userService.getCurrentUser());
		mav.setViewName(ErrorPage);

		return mav;
	}
}
