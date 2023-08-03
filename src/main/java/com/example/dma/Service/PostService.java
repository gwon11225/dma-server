package com.example.dma.Service;

import com.example.dma.DTO.PostDTO;
import com.example.dma.Domain.User;
import com.example.dma.Repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.dma.Auth.SessionManger;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SessionManger sessionManger;

    public PostDTO getPost(Long number) {
        return new PostDTO(postRepository.findPost((number - 1) * 10), postRepository.getNumber());
    }

    @Transactional
    public void createPost(HashMap<String, Object> postInfo, HttpServletRequest request) {
        User userInfo = (User) sessionManger.getSession(request);
        postRepository.createPost(userInfo.getName(), (String) postInfo.get("title"), (String) postInfo.get("content"));
    }
}