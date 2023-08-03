package com.example.dma.Controller;

import com.example.dma.Auth.SessionManger;
import com.example.dma.DTO.PostDTO;
import com.example.dma.Domain.User;
import com.example.dma.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final SessionManger sessionManger;

    @GetMapping(value = "/post")
    public PostDTO post(@RequestParam(value = "number") Long number) {
        return postService.getPost(number);
    }

    @PostMapping(value = "/post/create")
    public void createPost(@RequestBody HashMap<String, Object> postInfo, HttpServletRequest request) {
        postService.createPost(postInfo, request);
    }
}
