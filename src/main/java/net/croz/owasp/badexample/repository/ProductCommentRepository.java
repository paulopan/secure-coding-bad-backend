package net.croz.owasp.badexample.repository;

import net.croz.owasp.badexample.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

}
