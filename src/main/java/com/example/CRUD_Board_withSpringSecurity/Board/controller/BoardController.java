package com.example.CRUD_Board_withSpringSecurity.Board.controller;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Comment;
import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Post;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.*;
import com.example.CRUD_Board_withSpringSecurity.Board.repository.PostRepository;
import com.example.CRUD_Board_withSpringSecurity.Board.service.CommentService;
import com.example.CRUD_Board_withSpringSecurity.Board.service.PostService;
import com.example.CRUD_Board_withSpringSecurity.Common.exception.DataNotFoundException;
import com.example.CRUD_Board_withSpringSecurity.Member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final PostService postService;
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping("post/new")
    public String newPost(PostRequest postRequest)
    {
        return "Board/postForm";
    }

    @PostMapping("post/new")
    public String newPost(@Valid PostRequest postRequest, BindingResult bindingResult, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            return "Board/postForm";
        }

        try{
            postRequest.setWriter(principal.getName());
            postService.savePost(postRequest);
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("Error occured","Error occured");
            return "Board/postForm";
        }
        return "redirect:/post/list";
    }

    @GetMapping("post/list")
    public String postList(Model model)
    {
        List<PostResponse> postResponseList = postService.getPostResponseList();
        model.addAttribute("postList",postResponseList);
        return "Board/list";
    }

    @GetMapping("post/{id}")
    public String postDetail(@PathVariable Long id, Model model, Principal principal)
    {
        PostResponse postResponse = postService.findById(id);
        model.addAttribute("post",postResponse);

        boolean isAuthor = memberService.isAuthor(principal, postResponse);
        boolean isAdmin = memberService.isAdmin(principal);

        String currentId;
        if(principal != null) {currentId = principal.getName();}
        else {currentId = null;}

        model.addAttribute("isAuthor", isAuthor);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("currentId",currentId);

        // 플래시 속성을 가져와서 모델에 추가
        if (model.containsAttribute("modifyComment")) {
            model.addAttribute("modifyComment", model.getAttribute("modifyComment"));
        }

        return "Board/postDetail";
    }

    @GetMapping("post/{id}/modify")
    public String postModify(@PathVariable Long id, Model model, Principal principal)
    {
        PostResponse postResponse = postService.findById(id);
        if(!memberService.isAuthor(principal, postResponse) && !memberService.isAdmin(principal))
        {
            return "redirect:/post/"+id;
        }

        model.addAttribute("post",postService.findById(id));
        return "Board/postModifyForm";
    }

    @PostMapping("post/{id}/modify")
    public String postModify(@PathVariable Long id, @Valid PostModifyRequest postModifyRequest, BindingResult bindingResult, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            return "Board/postModifyForm";
        }
        postModifyRequest.setId(id);
        postService.modify(postModifyRequest);
        return "redirect:/post/"+id;
    }

    @GetMapping("post/{id}/delete")
    public String postDelete(@PathVariable Long id, Principal principal)
    {
        PostResponse postResponse = postService.findById(id);
        if(!memberService.isAuthor(principal, postResponse) && !memberService.isAdmin(principal))
        {
            return "redirect:/post/"+id;
        }
        postService.delete(id);
        return "redirect:/post/list";
    }


    @PostMapping("post/{id}/comment/new")
    public String newComment(@PathVariable Long id, @Valid CommentRequest commentRequest, BindingResult bindingResult,
                             Principal principal) throws DataNotFoundException
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/post/" + id;
        }
        commentRequest.setPostId(id);
        commentRequest.setWriter(principal.getName());
        commentService.save(commentRequest);
        return "redirect:/post/" + id;
    }

    @GetMapping("post/{post_id}/comment/{comment_id}/modify")
    public String commentModify(@PathVariable Long post_id, @PathVariable Long comment_id, Principal principal, RedirectAttributes redirectAttributes)
    {
        //사용자 검열

        redirectAttributes.addFlashAttribute("modifyComment", comment_id);
        return "redirect:/post/"+post_id;
    }

    @PostMapping("post/{post_id}/comment/{comment_id}/modify")
    public String commentModify(@PathVariable Long post_id, @PathVariable Long comment_id, @Valid CommentModifyRequest commentModifyRequest, BindingResult bindingResult, Principal principal)
    {
        commentModifyRequest.setId(comment_id);
        commentService.modify(commentModifyRequest);
        return "redirect:/post/"+post_id;
    }

    @GetMapping("post/{post_id}/comment/{comment_id}/delete")
    public String commentDelete(@PathVariable Long post_id, @PathVariable Long comment_id, Principal principal)
    {
        //사용자검열
        commentService.delete(comment_id);
        return "redirect:/post/"+post_id;
    }

}
