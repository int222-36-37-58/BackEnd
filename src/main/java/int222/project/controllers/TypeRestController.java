package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.Type;
import int222.project.repositories.TypeJpaRepository;

//@CrossOrigin(origins = "http://52.187.120.198:8080")
@RestController
public class TypeRestController {
	@Autowired
	TypeJpaRepository typeJpaRepository;

	@GetMapping("/brands")
	public List<Type> getAllBrand() {
		return typeJpaRepository.findAll();
	};

	@GetMapping("/brand/{brandId}")
	public Type getBrand(@PathVariable int typeId) {
		return typeJpaRepository.findById(typeId).orElse(null);
	};

}
