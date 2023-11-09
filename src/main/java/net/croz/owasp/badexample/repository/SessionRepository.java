package net.croz.owasp.badexample.repository;

import net.croz.owasp.badexample.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM session WHERE session.valid_until < CURRENT_DATE", nativeQuery = true)
    void invalidateSessions();

    @Query(value = "SELECT * FROM session WHERE session.id = :sessionId AND session.valid_until > CURRENT_DATE", nativeQuery = true)
    Optional<Session> findValidById(@Param("sessionId") Long id);
}
