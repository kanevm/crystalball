package org.football.service;

import java.util.Collection;
import java.util.Optional;

import org.football.form.RegisterForm;
import org.football.persistance.user.User;

public interface UserService {
	
	User getCurrentUser();

	Optional<User> getUserById(long id);

	Optional<User> getUserByEmail(String email);

	Collection<User> getAllUsers();

	User create(RegisterForm form);
}
