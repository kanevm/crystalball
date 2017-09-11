package org.football.controller;

public interface ControllerUrlConstants {

	String ADMIN_URL = "/admin";
	String USER_URL = "/user";
	String GAME_URL = "/game";
	String GAMES_URL = "/games";
	String START_URL = "/start";
	String PREDICT_URL = "/predict";
	String SUMMARY_URL = "/summary";
	String START_GAME_URL = GAME_URL + START_URL;
	String PREDICT_GAME_URL = GAME_URL + PREDICT_URL;
	String HOME_URL = "/";
	String LOGIN_URL = "/login";
	String REGISTER_URL = "/register";
}
