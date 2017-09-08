package org.football.persistance.competition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.football.persistance.fixture.Fixture;
import org.football.persistance.leaguetable.Standing;
import org.football.persistance.meta.Resource;

@Entity
@Table(name = "competitions")
public class Competition extends Resource {

	@Id
	private Long id;
	private String caption;
	private String league;
	private String year;
	private Integer currentMatchday;
	private Integer numberOfMatchdays;
	private Integer numberOfTeams;
	private Integer numberOfGames;
	private Date lastUpdated;
	@OrderBy("date")
	@OneToMany(mappedBy = "competition", cascade = { CascadeType.ALL })
	private List<Fixture> fixtures = new ArrayList<>();
	@OrderBy("position")
	@OneToMany(mappedBy = "competition", cascade = { CascadeType.ALL })
	private List<Standing> standings = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public Integer getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(Integer numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}

	public Integer getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Integer numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Integer getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", caption=" + caption + ", league=" + league + ", year=" + year
				+ ", currentMatchday=" + currentMatchday + ", numberOfMatchdays=" + numberOfMatchdays
				+ ", numberOfTeams=" + numberOfTeams + ", numberOfGames=" + numberOfGames + ", lastUpdated="
				+ lastUpdated + "]";
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}
}
