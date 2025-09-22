package edu.t1.javapro1.hw4.persistence.dao;

import edu.t1.javapro1.hw4.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
