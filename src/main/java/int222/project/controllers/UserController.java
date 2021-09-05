package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.User;
import int222.project.repositories.UserJpaRepositories;


@CrossOrigin(origins = "http://52.187.120.198:8080")
@RestController

public class UserController {
	@Autowired
	UserJpaRepositories userRepo;

	@GetMapping("/setest")
	public List<User> getAllUser() {
		return userRepo.findAll();
	};

//	@Transactional
//	public UserDetails loadUserBysUserName(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByUserName(username)
//				.orElseThrow(() -> new UsernameNotFoundException("no username ni" + username));
//		return UserPrinciple.build(user);
//	}
}
