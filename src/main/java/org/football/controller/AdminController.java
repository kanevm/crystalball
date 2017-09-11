package org.football.controller;

import static org.football.controller.ControllerAttributeConstants.GAMES_ATTR;
import static org.football.controller.ControllerAttributeConstants.USERS_ATTR;
import static org.football.controller.ControllerUrlConstants.ADMIN_URL;
import static org.football.controller.ControllerUrlConstants.GAME_URL;
import static org.football.controller.ControllerUrlConstants.USER_URL;
import static org.football.controller.ControllerViewConstants.AdminPage;

import org.football.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(ADMIN_URL)
public class AdminController extends AbstractController {

	@Autowired
	private GameService gameService;

	@GetMapping
	public String getAdminPage(final Model model) {
		model.addAttribute(USERS_ATTR, userService.getAllUsers());
		model.addAttribute(GAMES_ATTR, gameService.getGames());

		return AdminPage;
	}

	@PostMapping(GAME_URL)
	public String deleteGame(@RequestParam final long id) {
		gameService.deleteGame(id);

		return REDIRECT_PREFIX + ADMIN_URL;
	}

	@PostMapping(USER_URL)
	public String deleteUser(@RequestParam final long id) {
		userService.deleteUser(id);

		return REDIRECT_PREFIX + ADMIN_URL;
	}
}
