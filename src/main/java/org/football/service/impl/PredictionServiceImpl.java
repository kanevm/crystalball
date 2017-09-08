package org.football.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.football.form.PredictionFormEntry;
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
	public Prediction createPrediction(Prediction prediction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prediction evaluatePrediction(Prediction prediction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prediction alterPrediction(Prediction prediction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrediction(Prediction prediction) {
		// TODO Auto-generated method stub

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
