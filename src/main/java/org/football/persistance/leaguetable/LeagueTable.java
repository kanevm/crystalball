package org.football.persistance.leaguetable;

import java.util.ArrayList;
import java.util.List;

import org.football.persistance.meta.Resource;

public class LeagueTable extends Resource {
	private String leagueCaption;
	private Integer matchday;
	private List<Standing> standing = new ArrayList<>();

	public String getLeagueCaption() {
		return leagueCaption;
	}

	public void setLeagueCaption(String leagueCaption) {
		this.leagueCaption = leagueCaption;
	}

	public Integer getMatchday() {
		return matchday;
	}

	public void setMatchday(Integer matchday) {
		this.matchday = matchday;
	}

	public List<Standing> getStanding() {
		return standing;
	}

	public void setStanding(List<Standing> standing) {
		this.standing = standing;
	}
}
