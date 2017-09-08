package org.football.service.impl;

import org.football.service.UserService;
import org.football.persistance.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userService.getUserByEmail(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email=%s was not found", username)));

		return convert(user);
	}

	protected CurrentUser convert(final User user) {
		return new CurrentUser(user);
	}
}
