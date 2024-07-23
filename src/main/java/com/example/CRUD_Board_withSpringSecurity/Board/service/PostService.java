package com.example.CRUD_Board_withSpringSecurity.Board.service;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Post;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.PostModifyRequest;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.PostRequest;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.PostResponse;
import com.example.CRUD_Board_withSpringSecurity.Board.repository.PostRepository;
import com.example.CRUD_Board_withSpringSecurity.Common.exception.DataNotFoundException;
import com.example.CRUD_Board_withSpringSecurity.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.CRUD_Board_withSpringSecurity.Board.dto.BoardDtoConverter.toResponse;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService; //memberRepo로 교체

    public List<Post> findAll()
    {
        return postRepository.findAll();
    }

    public Post savePost(PostRequest postRequest) throws DataNotFoundException
    {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setWriter(memberService.getMember(postRequest.getWriter()));
        return postRepository.save(post);
    }

    public List<PostResponse> getPostResponseList()
    {
        List<Post> postList = postRepository.findAll();
        List<PostResponse> postResponseList = new ArrayList<>();
        for (Post post : postList) {
            postResponseList.add(toResponse(post));
        }
        return postResponseList;
    }




    public PostResponse findById(Long id)
    {
        return toResponse(postRepository.findById(id).get());
    }

    public Post modify(PostModifyRequest postModifyRequest)
    {
        Post post = postRepository.findById(postModifyRequest.getId()).get();
        post.setTitle(postModifyRequest.getTitle());
        post.setContent(postModifyRequest.getContent());
        postRepository.save(post);

        return post;
    }

    public void delete(Long id)
    {
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }
}
