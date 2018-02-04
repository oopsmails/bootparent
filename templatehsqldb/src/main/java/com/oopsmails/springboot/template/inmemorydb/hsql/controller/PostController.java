package com.oopsmails.springboot.template.inmemorydb.hsql.controller;

import com.oopsmails.springboot.template.inmemorydb.hsql.dao.PostRepository;
import com.oopsmails.springboot.template.inmemorydb.hsql.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
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
        return postRepository.findAll();
    }
}
