package com.oopsmails.springboot.template.inmemorydb.hsql;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.CommentRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.dao.PostRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.Comment;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataBaseApplication implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataBaseApplication.class, args);
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
