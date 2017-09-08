package org.football.service;

import java.util.List;

import org.football.form.PredictionFormEntry;
import org.football.persistance.game.Game;
import org.football.persistance.prediction.Prediction;
import org.football.persistance.user.User;

public interface PredictionService {

	Prediction createPrediction(Prediction prediction);

	Prediction evaluatePrediction(Prediction prediction);

	Prediction alterPrediction(Prediction prediction);

	void deletePrediction(Prediction prediction);

	List<Prediction> createPredictions(List<PredictionFormEntry> predictionEntries);

	List<Prediction> getPredictions(Game game, User user);

	List<Prediction> getPredictions(Game game);

}
