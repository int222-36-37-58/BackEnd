package int222.project.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.exceptions.AllException;
import int222.project.exceptions.ExceptionResponse;
import int222.project.models.OrderDetail;
import int222.project.models.Product;
import int222.project.models.UserOrder;
import int222.project.repositories.UserOrderJpaRepository;
import int222.project.repositories.OrderDetailRepository;
import int222.project.repositories.ProductsJpaRepository;
import int222.project.repositories.UserJpaRepositories;

@RestController
public class OrderRestController {
	@Autowired
	UserOrderJpaRepository orderRepo;
	@Autowired
	UserJpaRepositories userRepo;
	@Autowired
	ProductsJpaRepository productRepo;
	@Autowired
	OrderDetailRepository orderDetailRepo;
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
	List<OrderDetail> od =order.getOrderDetails();
	int q = 0;
	Product p;
	UserOrder uo= orderRepo.save(order);
	for (int i = 0; i < od.size(); i++) {
		OrderDetail orderDetail= od.get(i);
		orderDetail.setUserOrder(uo);
		orderDetailRepo.save(orderDetail);
		p = productRepo.findById(od.get(i).getProduct().getProductId()).get();
		q = p.getQuantity()-od.get(i).getProduct().getQuantity();
	if (q < 0 ) {
		throw new AllException(ExceptionResponse.ERROR_CODE.DOES_NOT_FIND_ID,
				"id: {" + "wait to write exception" + "} Does not fine Id!!");
	} else	 p.setQuantity(q);
		productRepo.save(p);
	}
		return orderRepo.findById(uo.getUserOrderId()).get();
	}
	
}
