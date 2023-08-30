package com.example.dma.Domain;

import com.example.dma.DTO.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PostData {
    private List<Post> posts;
    private Long number;

    public PostData(List<Post> posts, Long number) {
        this.posts = posts;
        this.number = number;
    }
}
