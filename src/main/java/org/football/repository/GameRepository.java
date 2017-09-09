package org.football.repository;

import java.util.List;

import org.football.persistance.game.Game;
import org.football.persistance.game.GameStatus;

public interface GameRepository extends CustomJpaRepository<Game> {

	List<Game> findByGameStatus(GameStatus gameStatus);

}
