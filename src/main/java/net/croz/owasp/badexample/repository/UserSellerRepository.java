package net.croz.owasp.badexample.repository;


import net.croz.owasp.badexample.entity.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSellerRepository extends JpaRepository<UserSeller, Long> {

}
