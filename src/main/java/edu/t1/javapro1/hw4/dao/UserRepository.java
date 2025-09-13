package edu.t1.javapro1.hw4.dao;

import edu.t1.javapro1.hw4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
