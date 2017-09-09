package org.football.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.football.form.PredictionFormEntry;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.fixture.Status;
import org.football.persistance.game.Game;
import org.football.persistance.prediction.Prediction;
import org.football.persistance.user.User;
import org.football.repository.FixtureRepository;
import org.football.repository.GameRepository;
import org.football.repository.PredictionRepository;
import org.football.repository.UserRepository;
import org.football.service.PredictionService;
import org.football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService {

	@Autowired
	private PredictionRepository predictionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private FixtureRepository fixtureRepository;

	@Override
	public Prediction evaluatePrediction(final Prediction prediction) {
		final Fixture fixture = prediction.getFixture();
		final Short actualGoalsHomeTeam = fixture.getGoalsHomeTeam();
		final Short actualGoalsAwayTeam = fixture.getGoalsAwayTeam();
		
		if (fixture.getStatus() != Status.FINISHED || actualGoalsHomeTeam == null
				|| actualGoalsAwayTeam == null) {
			throw new IllegalStateException("Unable to evaluate prediction with id " + prediction.getId()
					+ " for fixture with id " + fixture.getId());
		}
		
		final Short predictedGoalsHomeTeam = prediction.getGoalsHomeTeam();
		final Short predictedGoalsAwayTeam = prediction.getGoalsAwayTeam();
		
		final Short points = evaluate(actualGoalsHomeTeam, actualGoalsAwayTeam, predictedGoalsHomeTeam, predictedGoalsAwayTeam);
		prediction.setPoints(points);
		
		System.out.println("actualGoalsHomeTeam" + actualGoalsHomeTeam);
		System.out.println("actualGoalsAwayTeam" + actualGoalsAwayTeam);
		System.out.println("predictedGoalsHomeTeam" + predictedGoalsHomeTeam);
		System.out.println("predictedGoalsAwayTeam" + predictedGoalsAwayTeam);
		System.out.println("points" + points);
		
		return predictionRepository.save(prediction);
	}

	protected Short evaluate(final Short actualGoalsHomeTeam, final Short actualGoalsAwayTeam,
			final Short predictedGoalsHomeTeam, final Short predictedGoalsAwayTeam) {
		
		// Fixture started before the user could make a prediction
		if (predictedGoalsHomeTeam == null || predictedGoalsAwayTeam == null) {
			return 0;
		}
		
		// Correct score
		if (actualGoalsHomeTeam.equals(predictedGoalsHomeTeam) && actualGoalsAwayTeam.equals(predictedGoalsAwayTeam)) {
			return 4;
		}
		
		// Draw
		final int actualGoalDifference = actualGoalsHomeTeam - actualGoalsAwayTeam;
		final int predictedGoalDifference = predictedGoalsHomeTeam - predictedGoalsAwayTeam;
		if (actualGoalDifference == 0 && predictedGoalDifference == 0) {
			return 3;
		}
		
		// Goal difference
		if (actualGoalDifference == predictedGoalDifference) {
			return 2;
		}
		
		// Winner
		final int multipliedGoalDifference = actualGoalDifference * predictedGoalDifference;
		if (multipliedGoalDifference > 0) {
			return 1;
		}
		
		return 0;
	}

	@Override
	public List<Prediction> createPredictions(final List<PredictionFormEntry> predictionEntries) {

		final List<Prediction> predictions = predictionEntries.stream()
				.map(this::convert)
				.collect(Collectors.toList());

		return predictionRepository.save(predictions);
	}

	@Override
	public List<Prediction> getPredictions(final Game game, final User user) {
		return predictionRepository.findByGameAndUser(game, user);
	}

	@Override
	public List<Prediction> getPredictions(final Game game) {
		return getPredictions(game, userService.getCurrentUser());
	}

	protected Prediction convert(final PredictionFormEntry entry) {
		final Prediction prediction = new Prediction();
		prediction.setGame(gameRepository.findOne(entry.getGameId()));
		prediction.setFixture(fixtureRepository.findOne(entry.getFixtureId()));
		prediction.setUser(userRepository.findOne(entry.getUserId()));
		prediction.setGoalsHomeTeam(entry.getGoalsHomeTeam());
		prediction.setGoalsAwayTeam(entry.getGoalsAwayTeam());
		prediction.setId(entry.getId());

		return prediction;
	}
}
