package org.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FootballController extends AbstractController {

	@GetMapping("/fixtures")
	public String getFixtures(final Model model,
			@RequestParam(name = "timeFrame", defaultValue = "7") final int timeFrame) {
//		final Fixtures fixtures = footballOperations.getFixtures(timeFrame);
//		model.addAttribute("fixtures", fixtures.getFixtures());

		return "fixtures";
	}

	@GetMapping("/fixture/{id}")
	public String getFixture(final Model model, @PathVariable final long id) {
//		final FixtureWithH2H fixtureWithH2H = footballOperations.getFixture(id);
//		model.addAttribute("fixtureWithH2H", fixtureWithH2H);

		return "fixture";
	}
}
