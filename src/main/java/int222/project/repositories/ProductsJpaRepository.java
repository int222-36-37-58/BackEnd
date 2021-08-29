package int222.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int222.project.models.Product;

public interface ProductsJpaRepository extends JpaRepository<Product, Integer> {
	Product findByName(String name);
}
