package int222.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import int222.project.models.Comment;
import int222.project.models.Product;

public interface CommentJpaRepository extends JpaRepository<Comment, Integer>{
 List<Comment> findByProduct(Product product);
}
