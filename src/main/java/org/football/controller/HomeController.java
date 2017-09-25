package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.COMPETITIONS_ATTR;
import static org.football.controller.ControllerUrlConstants.HOME_URL;
import static org.football.controller.ControllerViewConstants.HomePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.football.persistance.competition.Competition;
import org.football.persistance.fixture.Fixture;
import org.football.repository.CompetitionRepository;
import org.football.repository.FixtureRepository;
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
	@Autowired
	private FixtureRepository fixtureRepository;

	@GetMapping
	public String getHomePage(final Model model) {

		final Map<Competition, List<Fixture>> competitionCurrentFixtures = new HashMap<>();

		competitionRepository.findAll()
				.forEach(competition -> competitionCurrentFixtures.put(competition,
						fixtureRepository.findByCompetitionIdAndMatchdayOrderByDate(competition.getId(), competition.getCurrentMatchday())));

		model.addAttribute(COMPETITIONS_ATTR, competitionCurrentFixtures);

		return HomePage;
	}
}
