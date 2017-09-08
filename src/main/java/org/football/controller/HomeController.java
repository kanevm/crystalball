package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.COMPETITIONS_ATTR;
import static org.football.controller.ControllerUrlConstants.HOME_URL;
import static org.football.controller.ControllerViewConstants.HomePage;

import org.football.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(HOME_URL)
public class HomeController extends AbstractController {

	@Autowired
	private CompetitionRepository competitionRepository;

	@GetMapping
	public String getHomePage(final Model model) {
		model.addAttribute(COMPETITIONS_ATTR, competitionRepository.findAll());

		return HomePage;
	}
}
