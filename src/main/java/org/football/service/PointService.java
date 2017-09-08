package org.football.service;

import org.football.persistance.game.Game;
import org.football.persistance.user.User;

public interface PointService {

	void initPoints(User user, int points);

	void addPoints(User user, int points);

	void removePoints(User user, int points);

	int retrievePoints(User user);

	int retrievePoints(Game game);
}
