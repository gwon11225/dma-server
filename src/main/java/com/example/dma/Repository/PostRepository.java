package com.example.dma.Repository;

import com.example.dma.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post WHERE :number <= id < (:number + 10)", nativeQuery = true)
    List<Post> findPost(Long number);

    @Query(value = "SELECT COUNT(*) FROM post", nativeQuery = true)
    Long getNumber();

    @Modifying
    @Query(value = "INSERT INTO post (writer, title, content) VALUES (:writer, :title, :content)", nativeQuery = true)
    void createPost(@Param("writer") String writer, @Param("title") String title, @Param("content") String content);
}
