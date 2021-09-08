package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int222.project.models.Comment;
import int222.project.repositories.CommentJpaRepository;
import int222.project.repositories.ProductsJpaRepository;

@RestController
public class CommentRestController {
	@Autowired
	CommentJpaRepository commentJpaRepository;
	@Autowired
	ProductsJpaRepository productRepo;

	@GetMapping("/allcomments")
	public List<Comment> getAllComment() {
		return commentJpaRepository.findAll();
	}

	@GetMapping("/{productId}/comment")
	public List<Comment> getComment(@PathVariable int productId) {
		return commentJpaRepository.findByProduct(productRepo.findById(productId).get());
		// page and null attribute of user left
	}
	@PostMapping("/addcomment")
	public Comment addCommment(@RequestBody Comment comment) {
		return commentJpaRepository.save(comment);
	}
	@PutMapping("editcomment")
	public Comment editComment(@RequestBody Comment comment) {
		Comment c = commentJpaRepository.findById(comment.getCommentId()).get();
		c.setContent(comment.getContent());
		c.setProduct(comment.getProduct());
		c.setUser(comment.getUser());
		return commentJpaRepository.save(c);
	}
}