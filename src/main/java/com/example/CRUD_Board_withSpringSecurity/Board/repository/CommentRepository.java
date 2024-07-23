package com.example.CRUD_Board_withSpringSecurity.Board.repository;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByPostId(Long PostId);
    public Optional<Comment> findById(Long id);
}
