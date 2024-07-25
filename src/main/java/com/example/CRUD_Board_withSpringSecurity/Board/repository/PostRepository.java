package com.example.CRUD_Board_withSpringSecurity.Board.repository;
import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
