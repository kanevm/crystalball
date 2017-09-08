package org.football.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.football.service.UserService;
import org.football.form.RegisterForm;
import org.football.persistance.user.Role;
import org.football.persistance.user.User;
import org.football.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String ANONYMOUS = "anonymous";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User getCurrentUser() {
		final Optional<Authentication> authentication = Optional
				.ofNullable(SecurityContextHolder.getContext().getAuthentication());

		return authentication
				.map(Authentication::getPrincipal)
				.filter(CurrentUser.class::isInstance)
				.map(CurrentUser.class::cast)
				.map(CurrentUser::getUser)
				.orElse(getAnonymousUser());
	}

	@Override
	public Optional<User> getUserById(final long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(final String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User create(final RegisterForm form) {
		final User user = convert(form);

		return userRepository.save(user);
	}

	protected User convert(final RegisterForm form) {
		final User user = new User();
		user.setEmail(form.getEmail());
		user.setPasswordHash(bCryptPasswordEncoder.encode(form.getPassword()));
		user.setRole(form.getRole());
		user.setName(form.getName());

		return user;
	}

	private static User getAnonymousUser() {
		final User anonymous = new User();
		anonymous.setEmail(ANONYMOUS);
		anonymous.setName(ANONYMOUS);
		anonymous.setRole(Role.ANONYMOUS);

		return anonymous;
	}
}
