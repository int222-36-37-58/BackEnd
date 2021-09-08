package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.Order;
import int222.project.repositories.OrderJpaRepository;
import int222.project.repositories.UserJpaRepositories;

@RestController
public class OrderRestController {
	@Autowired
	OrderJpaRepository orderRepo;
	@Autowired
	UserJpaRepositories userRepo;
	@GetMapping("/getallorder")
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}
	@GetMapping("/getuserorder/{id}")
	public List<Order> getUserOrder(@PathVariable int id){
		return orderRepo.findByUser(userRepo.findById(id).get());
	}
	@PostMapping("/addorder")
	public Order addOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}
}
