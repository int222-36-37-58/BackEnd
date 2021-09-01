
package int222.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import int222.project.models.User;
import int222.project.repositories.UserJpaRepositories;
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserJpaRepositories userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(username);
		
		user.orElseThrow(()->new UsernameNotFoundException("not found:" + username));
		return user.map(MyUserDetails::new).get() ;
		
//		return new MyUserDetails(user.get());
	}

}
