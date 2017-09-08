package org.football.service;

import java.util.List;

import org.football.form.GameForm;
import org.football.persistance.game.Game;
import org.football.persistance.user.User;

public interface GameService {

	Game startGame(GameForm gameForm);

	List<Game> getGamesForUser(User user);

	List<Game> getGamesForCurrentUser();

	Game getGame(long id);

}
