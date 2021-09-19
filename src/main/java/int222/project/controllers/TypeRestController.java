package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.Type;
import int222.project.repositories.TypeJpaRepository;

@RestController
public class TypeRestController {
	@Autowired
	TypeJpaRepository typeJpaRepository;

	@GetMapping("/types")
	public List<Type> getAllBrand() {
		return typeJpaRepository.findAll();
	};

	@GetMapping("/type/{typeId}")
	public Type getBrand(@PathVariable int typeId) {
		return typeJpaRepository.findById(typeId).orElse(null);
	};
	@PostMapping("/addtype")
	public Type addType(@RequestBody Type type) {
		return typeJpaRepository.save(type);
	}
	@DeleteMapping("/deletetype/{id}")
	public String deleteType(@PathVariable int id) {
		typeJpaRepository.deleteById(id);
		return "delete success" ;
	}
	@PutMapping("edittype/{id}")
	public Type editeType(@RequestBody Type type ) {
		Type old = typeJpaRepository.findById(type.getTypeId()).get();
		old.setName(type.getName());
		return typeJpaRepository.save(old);
	}
	
}
