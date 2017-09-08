package org.football.persistance.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.football.persistance.prediction.Prediction;
import org.football.persistance.user.User;

@Entity
@Table(name = "games")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long competitionId;

	@Column(nullable = false)
	private String competitionCaption;

	@Column(nullable = false)
	private Integer competitionMatchday;

	@ManyToMany
	private List<User> users = new ArrayList<>();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GameType gameType;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GameStatus gameStatus;

	@OneToMany(mappedBy = "game", cascade = { CascadeType.ALL })
	private List<Prediction> predictions = new ArrayList<>();
	
	@Column(nullable = false)
	private Date startedTime;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(final GameType gameType) {
		this.gameType = gameType;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(final GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public List<Prediction> getPredictions() {
		return predictions;
	}

	public void setPredictions(final List<Prediction> predictions) {
		this.predictions = predictions;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(final Long competitionId) {
		this.competitionId = competitionId;
	}

	public Date getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(final Date startedTime) {
		this.startedTime = startedTime;
	}

	public String getCompetitionCaption() {
		return competitionCaption;
	}

	public void setCompetitionCaption(final String competitionCaption) {
		this.competitionCaption = competitionCaption;
	}

	public Integer getCompetitionMatchday() {
		return competitionMatchday;
	}

	public void setCompetitionMatchday(final Integer competitionMatchday) {
		this.competitionMatchday = competitionMatchday;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", competitionId=" + competitionId + ", users=" + users + ", gameType=" + gameType
				+ ", gameStatus=" + gameStatus + ", predictions=" + predictions + "]";
	}
}
