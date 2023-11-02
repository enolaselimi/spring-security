package com.joan.security.repository;

import com.joan.security.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     User findUserDTOById(Long id);
     User findUserDTOByLogin(String login);
     User save(User user);
}
