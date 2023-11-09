package net.croz.owasp.badexample.repository;

import net.croz.owasp.badexample.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AuthUser> getAuthUserByUsername(String username) {
        final String query = "SELECT * FROM auth_user WHERE username = '" + username + "'";

        final AuthUser authUser = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(AuthUser.class));

        return Optional.ofNullable(authUser);
    }

    public void updateAuthPassword(AuthUser authUser) {
        final String query = "UPDATE auth_user SET password = ? WHERE id = ?";
        jdbcTemplate.update(query, authUser.getPassword(), authUser.getId());
    }

}
