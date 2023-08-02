package com.example.dma.DTO;

import com.example.dma.Domain.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PostDTO {
    private List<Post> posts;
    private Long number;

    public PostDTO(List<Post> posts, Long number) {
        this.posts = posts;
        this.number = number;
    }
}
