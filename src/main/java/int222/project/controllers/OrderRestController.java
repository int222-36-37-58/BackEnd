package int222.project.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.exceptions.AllException;
import int222.project.exceptions.ExceptionResponse;
import int222.project.models.OrderDetail;
import int222.project.models.Product;
import int222.project.models.User;
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
	@GetMapping("/admin/getallorder")
	public List<UserOrder> getAllOrder() {
		return orderRepo.findAll();
	}
	@GetMapping("/user/getuserorder/{id}")
	public List<UserOrder> getUserOrder(@PathVariable int id,Authentication authen){
		User user= userRepo.findById(id).get();
		if(!user.getUserName().equals(authen.getName())) {
			throw new AllException(ExceptionResponse.ERROR_CODE.USER_NOT_MATCH, "please get your order" );
			
		}
		
		return orderRepo.findByUser(user);
	}
	@PostMapping("/user/addorder")
	public UserOrder addOrder(@RequestBody UserOrder order,Authentication authen) {
	List<OrderDetail> od =order.getOrderDetail();
	int q = 0;
	Product p;
	UserOrder uo= orderRepo.save(order);
	if(!uo.getUser().getUserName().equals(authen.getName())) {
		throw new AllException(ExceptionResponse.ERROR_CODE.USER_NOT_MATCH, "please post your order" );
	}
	for (int i = 0; i < od.size(); i++) {
		OrderDetail orderDetail= od.get(i);
		orderDetail.setUserOrder(uo);
		orderDetailRepo.save(orderDetail);
		p = productRepo.findById(od.get(i).getProduct().getProductId()).get();
		q = p.getQuantity()-od.get(i).getQuantity();
	if (q < 0 ) {
		throw new AllException(ExceptionResponse.ERROR_CODE.OUT_OF_STOCK,
				p.getName()+"quantity is zero please wait to fill this product");
	} else	 p.setQuantity(q);
		productRepo.save(p);
	}
		return orderRepo.findById(uo.getUserOrderId()).get();
	}
	@GetMapping("/seller/order/{seller}")
	public List<OrderDetail> getSellerOrder(@PathVariable String seller){
		User u = userRepo.findByUserName(seller).get();
		List<Product> p = productRepo.findByUser(u);
		List<OrderDetail> od = null;
		for (int i = 0; i < p.size(); i++) {
		od.addAll(	orderDetailRepo.findByProduct(p.get(i)) );
		}
		return od;
	}
}
