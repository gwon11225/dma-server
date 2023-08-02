package com.example.dma.Controller;

import com.example.dma.DTO.PostDTO;
import com.example.dma.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(value = "/post")
    public PostDTO post(@RequestBody HashMap<String, Object> info) {
        return postService.getPost(info);
    }

    @PostMapping(value = "/post/create")
    public void createPost(@RequestBody HashMap<String, Object> postInfo) {
        postService.createPost(postInfo);
    }
}
