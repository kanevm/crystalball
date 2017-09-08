package org.football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.football.service.UserService;
import org.football.form.RegisterForm;
import org.football.persistance.user.User;
import org.football.restapi.service.FootballOperations;
import org.football.restapi.service.impl.FootballOperationsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableScheduling
@SpringBootApplication
public class App implements CommandLineRunner {

	@Value("${football.api.baseUri}")
	private String footballApiBaseUri;

	@Value("${football.api.version}")
	private String footballApiVersion;

	@Value("#{'${football.api.supportedCompetitionIds}'.split(',')}")
	private List<String> supportedCompetitionIds = new ArrayList<>();

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(final String... arg0) throws Exception {

		final Optional<User> kani = userService.getUserByEmail("kani@abv.bg");
		if (!kani.isPresent()) {
			final RegisterForm form = new RegisterForm();
			form.setEmail("kani@abv.bg");
			form.setName("Kani Kanev");
			form.setPassword("123123");
			form.setPasswordRepeated("123123");

			userService.create(form);
			System.out.println("kani created");
		}

		final Optional<User> mkanev = userService.getUserByEmail("mkanev_@abv.bg");
		if (!mkanev.isPresent()) {
			final RegisterForm form = new RegisterForm();
			form.setEmail("mkanev_@abv.bg");
			form.setName("Martin Kanev");
			form.setPassword("123123");
			form.setPasswordRepeated("123123");

			userService.create(form);
			System.out.println("mkanev created");
		}

		final Optional<User> joro = userService.getUserByEmail("joro@abv.bg");
		if (!joro.isPresent()) {
			final RegisterForm form = new RegisterForm();
			form.setEmail("joro@abv.bg");
			form.setName("Joro Jorkata");
			form.setPassword("123123");
			form.setPasswordRepeated("123123");

			userService.create(form);
			System.out.println("joro created");
		}
	}

	@Bean
	public FootballOperations getFootballOperations() {
		return new FootballOperationsImpl(this.footballApiBaseUri.concat(this.footballApiVersion));
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public List<String> supportedCompetitionIds() {
		return this.supportedCompetitionIds;
	}

	@Bean
	public Map<Integer, String> supportedCompetitions() {
		final Map<Integer, String> competitions = new HashMap<>();
		competitions.put(445, "Premier League 2017/18");
		competitions.put(452, "1. Bundesliga 2017/18");
		competitions.put(455, "Primera Division 2017");
		competitions.put(456, "Serie A 2017/18");

		return competitions;
	}
}
