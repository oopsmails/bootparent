package com.oopsmails.spring.jpa.controller;

import com.oopsmails.spring.jpa.entity.Post;
import com.oopsmails.spring.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @RequestMapping(name = "get all posts", method = RequestMethod.GET, value = "/posts")
    List<Post> findAllPosts() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return postRepository.findAll(sort);
    }
}
