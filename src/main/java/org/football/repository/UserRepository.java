package org.football.repository;

import java.util.Optional;

import org.football.persistance.user.User;

public interface UserRepository extends CustomJpaRepository<User> {

	Optional<User> findOneByEmail(String email);

}
