package org.football.persistance.leaguetable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.football.persistance.competition.Competition;
import org.football.persistance.meta.Resource;

@Entity
@Table(name = "standings")
public class Standing extends Resource {
	@Id
	private String teamName;
	private Integer position;
	private String crestURI;
	private Integer playedGames;
	private Integer points;
	private Integer goals;
	private Integer goalsAgainst;
	private Integer goalDifference;
	private Integer wins;
	private Integer draws;
	private Integer losses;
	@ManyToOne(optional = false)
	private Competition competition;

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCrestURI() {
		return crestURI;
	}

	public void setCrestURI(String crestURI) {
		this.crestURI = crestURI;
	}

	public Integer getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(Integer playedGames) {
		this.playedGames = playedGames;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(Integer goals) {
		this.goals = goals;
	}

	public Integer getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(Integer goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public Integer getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(Integer goalDifference) {
		this.goalDifference = goalDifference;
	}

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Integer getDraws() {
		return draws;
	}

	public void setDraws(Integer draws) {
		this.draws = draws;
	}

	public Integer getLosses() {
		return losses;
	}

	public void setLosses(Integer losses) {
		this.losses = losses;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
}
