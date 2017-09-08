package org.football.persistance.fixture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.football.persistance.competition.Competition;
import org.football.persistance.meta.Resource;
import org.football.persistance.prediction.Prediction;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Client side representation of Fixture data from the REST service.
 */
@Entity
@Table(name = "fixtures")
public class Fixture extends Resource {

	@Id
	private Long id;
	private Date date;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(nullable = false)
	private Integer matchday;
	@Column(nullable = false)
	private String homeTeamName, awayTeamName;
	private Short goalsHomeTeam, goalsAwayTeam;
	@ManyToOne(optional = false)
	private Competition competition;
	@OneToMany(mappedBy = "fixture", cascade = { CascadeType.ALL })
	private List<Prediction> predictions = new ArrayList<>();
	
	@JsonInclude
	@Transient
	private Result result;


	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(final String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(final String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Integer getMatchday() {
		return matchday;
	}

	public void setMatchday(final Integer matchday) {
		this.matchday = matchday;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Fixture [date=" + date + ", status=" + status + ", matchday=" + matchday + ", homeTeamName="
				+ homeTeamName + ", awayTeamName=" + awayTeamName + ", links=" + getLinks().toString() + "]";
	}

	public Short getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(Short goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public Short getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(Short goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
		this.goalsHomeTeam = result.getGoalsHomeTeam();
		this.goalsAwayTeam = result.getGoalsAwayTeam();
		
		final String selfHref = getLinks().get("self").getHref();
		this.id = Long.valueOf("" + selfHref.subSequence(selfHref.lastIndexOf("/") + 1, selfHref.length()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
}
