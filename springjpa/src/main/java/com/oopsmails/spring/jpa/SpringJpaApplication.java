package com.oopsmails.spring.jpa;

import com.oopsmails.spring.jpa.entity.Comment;
import com.oopsmails.spring.jpa.entity.Post;
import com.oopsmails.spring.jpa.repository.CommentRepository;
import com.oopsmails.spring.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup Database tables
        commentRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();

        // ======================================

        Post post = new Post("Hibernate One-To-Many Mapping Example",
                "Learn how to use one to many mapping in hibernate",
                "Entire Post Content with sample code");

        Comment comment1 = new Comment("Great Post!");
        comment1.setPost(post);

        Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
        comment2.setPost(post);

        post.getComments().add(comment1);
        post.getComments().add(comment2);

        postRepository.save(post);

        // ======================================

    }
}
