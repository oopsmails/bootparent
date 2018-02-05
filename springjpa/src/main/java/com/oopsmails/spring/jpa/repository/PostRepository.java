package com.oopsmails.spring.jpa.repository;

import com.oopsmails.spring.jpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("select post from Post post fetch join post.comments comments where post.id = :postId")
//    Post findPostOrderByCommentId(@Param("postId") Long postId);
}
