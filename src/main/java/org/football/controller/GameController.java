package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.*;
import static org.football.controller.ControllerViewConstants.*;
import static org.football.controller.ControllerUrlConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.football.form.GameForm;
import org.football.form.PredictionForm;
import org.football.form.PredictionFormEntry;
import org.football.persistance.competition.Competition;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.game.Game;
import org.football.persistance.prediction.Prediction;
import org.football.repository.CompetitionRepository;
import org.football.repository.FixtureRepository;
import org.football.service.GameService;
import org.football.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController extends AbstractController {
	
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private FixtureRepository fixtureRepository;
	@Autowired
	private GameService gameService;
	@Autowired
	private PredictionService predictionService;

	@GetMapping(START_GAME_URL)
	public String startGamePage(final Model model) {
		prepareStartGamePage(model, new GameForm());

		return StartGamePage;
	}

	@PostMapping(START_GAME_URL)
	public String handleGameCreation(@Valid @ModelAttribute(GAME_FORM_ATTR) final GameForm gameForm,
			final BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			prepareStartGamePage(model, gameForm);
			
			return StartGamePage;
		}

		final Game game = gameService.startGame(gameForm);

		return REDIRECT_PREFIX + GAME_URL + "/" + game.getId();
	}
	
	@GetMapping(GAME_URL + "/{id}")
	public String getGamePage(final Model model, @PathVariable final long id) {
		final Game game = gameService.getGame(id);
		final Competition competition = competitionRepository.findOne(game.getCompetitionId());
		final List<Fixture> fixtures = fixtureRepository.findByCompetitionIdAndMatchdayOrderByDate(game.getCompetitionId(), game.getCompetitionMatchday());
		
		model.addAttribute(GAME_ATTR, game);
		model.addAttribute(FIXTURES_ATTR, fixtures);
		model.addAttribute(COMPETITION_ATTR, competition);
		model.addAttribute(PREDICTION_FORM_ATTR, initializePredictionForm(game, fixtures));
		
		return GamePage;
	}

	@PostMapping(PREDICT_GAME_URL)
	public String handlePredictions(@Valid @ModelAttribute(PREDICTION_FORM_ATTR) final PredictionForm predictionForm,
			final BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			return REDIRECT_PREFIX + GAMES_URL;
		}

		predictionService.createPredictions(predictionForm.getPredictions());

		return REDIRECT_PREFIX + GAMES_URL;
	}
	
	@GetMapping(GAMES_URL)
	public String getGamesPage(final Model model) {

		model.addAttribute(GAMES_ATTR, gameService.getGamesForCurrentUser());

		return GamesPage;
	}
	
	private void prepareStartGamePage(final Model model, final GameForm gameForm) {
		model.addAttribute(COMPETITIONS_ATTR, competitionRepository.findAll());

		model.addAttribute(USERS_ATTR,
				userService.getAllUsers().stream()
						.map(user -> user.getEmail())
						.filter(email -> !email.equalsIgnoreCase(getCurrentUser().getEmail()))
						.collect(Collectors.toList()));

		model.addAttribute(GAME_FORM_ATTR, gameForm);
	}
	
	private PredictionForm initializePredictionForm(final Game game, final List<Fixture> fixtures) {
		final List<PredictionFormEntry> predictionEntries = new ArrayList<>();
		final List<Prediction> predictions = predictionService.getPredictions(game);
		
		if (CollectionUtils.isEmpty(predictions)) {
			fixtures.forEach(fixture -> {
				final PredictionFormEntry predictionEntry = new PredictionFormEntry();
				predictionEntry.setUserId(getCurrentUser().getId());
				predictionEntry.setFixtureId(fixture.getId());
				predictionEntry.setGameId(game.getId());
				predictionEntry.setFixture(fixture);

				predictionEntries.add(predictionEntry);
			});
		} else {
			predictions.forEach(prediction -> {
				final PredictionFormEntry predictionEntry = new PredictionFormEntry();
				predictionEntry.setUserId(prediction.getUser().getId());
				predictionEntry.setFixtureId(prediction.getFixture().getId());
				predictionEntry.setGameId(prediction.getGame().getId());
				predictionEntry.setFixture(prediction.getFixture());
				predictionEntry.setGoalsHomeTeam(prediction.getGoalsHomeTeam());
				predictionEntry.setGoalsAwayTeam(prediction.getGoalsAwayTeam());
				predictionEntry.setId(prediction.getId());

				predictionEntries.add(predictionEntry);
			});
		}

		final PredictionForm predictionForm = new PredictionForm();
		predictionForm.setPredictions(predictionEntries);

		return predictionForm;
	}
}
