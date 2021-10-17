package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.exceptions.AllException;
import int222.project.exceptions.ExceptionResponse;
import int222.project.models.Comment;
import int222.project.repositories.CommentJpaRepository;
import int222.project.repositories.ProductsJpaRepository;
import int222.project.repositories.UserJpaRepositories;

@RestController
public class CommentRestController {
	@Autowired
	CommentJpaRepository commentJpaRepository;
	@Autowired
	ProductsJpaRepository productRepo;
	@Autowired
	UserJpaRepositories userRepo;
	@GetMapping("/allcomments")
	public List<Comment> getAllComment() {
		return commentJpaRepository.findAll();
	}

	@GetMapping("product/{productId}/comment")
	public List<Comment> getComment(@PathVariable int productId) {
		return commentJpaRepository.findByProduct(productRepo.findById(productId).get());
		// page and null attribute of user left
	}
	@PostMapping("/user/addcomment")
	public Comment addCommment(@RequestBody Comment comment,Authentication authen) {
//		comment.setProduct(productRepo.findById(id).get());
		if(comment.getUser().getUserName().equals(authen.getName())) {
			throw new AllException(ExceptionResponse.ERROR_CODE.USER_NOT_MATCH, "please post your comment" );
		}
//		comment.setUser(userRepo.findById().get());
		return commentJpaRepository.save(comment);
		
	}
	@PutMapping("/user/editcomment")
	public Comment editComment(@RequestBody Comment comment) {
		Comment c = commentJpaRepository.findById(comment.getCommentId()).get();
		c.setContent(comment.getContent());
		c.setProduct(comment.getProduct());
		c.setUser(comment.getUser());
		return commentJpaRepository.save(c);
	}
	@DeleteMapping("/user/commentdelete/{id}")
	public String deleteComment(@PathVariable int id) {
		commentJpaRepository.deleteById(id);
		return "delete success";
	}
	
	
}
