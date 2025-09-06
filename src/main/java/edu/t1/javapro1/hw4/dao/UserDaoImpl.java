package edu.t1.javapro1.hw4.dao;

import edu.t1.javapro1.hw4.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (username) VALUES (?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, user.getUsername());
        user.setId(id);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new User(rs.getLong("id"), rs.getString("username")), id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, username FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User(rs.getLong("id"), rs.getString("username"));
            return user;
        });
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}
