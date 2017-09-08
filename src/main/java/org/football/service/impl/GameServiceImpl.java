package org.football.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.football.form.GameForm;
import org.football.persistance.competition.Competition;
import org.football.persistance.game.Game;
import org.football.persistance.game.GameStatus;
import org.football.persistance.game.GameType;
import org.football.persistance.user.User;
import org.football.repository.CompetitionRepository;
import org.football.repository.GameRepository;
import org.football.service.GameService;
import org.football.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public Game startGame(final GameForm gameForm) {
		final Game game = new Game();
		final Long competitionId = Long.valueOf(gameForm.getCompetitionId());
		final Competition competition = competitionRepository.findOne(competitionId);

		final List<User> users = gameForm.getUserIds().stream()
				.map(userService::getUserByEmail)
				.map(Optional::get)
				.collect(Collectors.toList());
		users.add(userService.getCurrentUser());

		game.setCompetitionId(competitionId);
		game.setCompetitionCaption(competition.getCaption());
		game.setCompetitionMatchday(competition.getCurrentMatchday());
		game.setGameStatus(GameStatus.IN_PROGRESS);
		game.setGameType(GameType.PRIVATE);
		game.setUsers(users);
		game.setStartedTime(new Date());

		return gameRepository.saveAndFlush(game);
	}

	@Override
	public List<Game> getGamesForUser(final User user) {
		return user.getGames();
	}

	@Override
	public List<Game> getGamesForCurrentUser() {
		return getGamesForUser(userService.getCurrentUser());
	}

	@Override
	public Game getGame(long id) {
		return gameRepository.findOne(id);
	}
}
