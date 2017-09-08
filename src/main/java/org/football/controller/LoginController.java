package org.football.controller;
import static org.football.controller.ControllerAttributeConstants.ERROR_ATTR;
import static org.football.controller.ControllerUrlConstants.LOGIN_URL;
import static org.football.controller.ControllerViewConstants.LoginPage;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(LOGIN_URL)
public class LoginController extends AbstractController {

	@GetMapping
	public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView(LoginPage, ERROR_ATTR, error);
	}
}
