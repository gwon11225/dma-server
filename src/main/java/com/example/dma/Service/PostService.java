package com.example.dma.Service;

import com.example.dma.DTO.PostDTO;
import com.example.dma.Repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDTO getPost(HashMap<String, Object> info) {
        return new PostDTO(postRepository.findPost(Long.valueOf((Integer) info.get("number"))), postRepository.getNumber());
    }

    @Transactional
    public void createPost(HashMap<String, Object> postInfo) {
        postRepository.createPost((String) postInfo.get("writer"), (String) postInfo.get("title"), (String) postInfo.get("content"));
    }
}