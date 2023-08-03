package com.example.dma.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "writer")
    private String writer;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}