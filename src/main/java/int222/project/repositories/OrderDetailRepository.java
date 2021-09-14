package int222.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int222.project.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
