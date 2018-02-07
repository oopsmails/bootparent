package com.oopsmails.spring.jpa.controller;

import com.oopsmails.spring.jpa.entity.Comment;
import com.oopsmails.spring.jpa.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(name = "get all posts", method = RequestMethod.GET, value = "/comments")
    List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
}
