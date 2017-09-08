package org.football.repository;

import java.util.List;

import org.football.persistance.fixture.Status;
import org.football.persistance.game.Game;
import org.football.persistance.prediction.Prediction;
import org.football.persistance.user.User;

public interface PredictionRepository extends CustomJpaRepository<Prediction> {

	List<Prediction> findByGameAndUser(Game game, User user);

	List<Prediction> findByPointsAndFixtureStatus(Short points, Status status);

}
