package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.User;
import int222.project.repositories.UserJpaRepositories;

@RestController

public class UserController {
	@Autowired
	UserJpaRepositories userRepo;

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userRepo.findAll();
	};

	@GetMapping("/user/{id}")
	public User getAllUser(@PathVariable int id) {
		return userRepo.findById(id).get();
	};

	@PostMapping("/register")
	public User postUser(@RequestBody User user) {// orrequestBody
		return userRepo.save(user);
	}

	@PutMapping("/edituser")
	public User editUser(@RequestBody User user) {
		User userOld = userRepo.findById(user.getUserId()).get();
		userOld.setUserName(user.getUserName());
		userOld.setPassword(user.getPassword());
		userOld.setAddress(user.getAddress());
		userOld.setTel(user.getTel());
		userOld.setFullName(user.getFullName());
		userOld.setRole(user.getRole());
		return userRepo.save(userOld);
	}

	@DeleteMapping("use/delete/{id}") // change name
	public String deleteUser(@PathVariable int id) {
		userRepo.deleteById(id);
		return "delete success";
	}
	@GetMapping("user/thisuser")
	public String currentUser(Authentication authen) {
		return authen.getName();
	}
//	@Transactional
//	public UserDetails loadUserBysUserName(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByUserName(username)
//				.orElseThrow(() -> new UsernameNotFoundException("no username ni" + username));
//		return UserPrinciple.build(user);
//	}
}
