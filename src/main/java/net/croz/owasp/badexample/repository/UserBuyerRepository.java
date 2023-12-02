package net.croz.owasp.badexample.repository;


import net.croz.owasp.badexample.entity.UserBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuyerRepository extends JpaRepository<UserBuyer, Long> {

}
