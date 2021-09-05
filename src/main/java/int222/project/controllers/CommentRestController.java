package int222.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public List<Comment> getAllComment(){
	return commentJpaRepository.findAll();
}
@GetMapping("/{productId}/comment")
public List<Comment> getComment(@PathVariable int productId){
	return commentJpaRepository.findByProduct(productRepo.findById(productId).get());
	// page and null attribute of user left 
}
}
