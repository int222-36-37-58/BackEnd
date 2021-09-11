package int222.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import int222.project.models.User;
import int222.project.models.UserOrder;

public interface UserOrderJpaRepository extends JpaRepository<UserOrder, Integer> {
List<UserOrder> findByUser(User user);

}
