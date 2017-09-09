package org.football.persistance.prediction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.football.persistance.fixture.Fixture;
import org.football.persistance.game.Game;
import org.football.persistance.user.User;

@Entity
@Table(name = "predictions")
public class Prediction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private User user;

	@ManyToOne(optional = false)
	private Game game;

	@ManyToOne(optional = false)
	private Fixture fixture;

	@Column(nullable = true)
	private Short points, goalsHomeTeam, goalsAwayTeam;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(final Game game) {
		this.game = game;
	}

	public Short getPoints() {
		return points;
	}

	public void setPoints(final Short points) {
		this.points = points;
	}

	public Short getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(final Short goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public Short getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(final Short goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}
}
