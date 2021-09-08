package int222.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import int222.project.models.Order;
import int222.project.models.User;

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
List<Order> findByUser(User user);
}
