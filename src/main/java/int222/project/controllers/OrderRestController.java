package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.UserOrder;
import int222.project.repositories.UserOrderJpaRepository;
import int222.project.repositories.UserJpaRepositories;

@RestController
public class OrderRestController {
	@Autowired
	UserOrderJpaRepository orderRepo;
	@Autowired
	UserJpaRepositories userRepo;
	@GetMapping("/getallorder")
	public List<UserOrder> getAllOrder() {
		return orderRepo.findAll();
	}
	@GetMapping("/getuserorder/{id}")
	public List<UserOrder> getUserOrder(@PathVariable int id){
		return orderRepo.findByUser(userRepo.findById(id).get());
	}
	@PostMapping("/addorder")
	public UserOrder addOrder(@RequestBody UserOrder order) {
		return orderRepo.save(order);
	}
}
