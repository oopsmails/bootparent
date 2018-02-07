package com.oopsmails.spring.jpa.controller;

import com.oopsmails.spring.jpa.entity.Tag;
import com.oopsmails.spring.jpa.repository.TagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(name = "get all tags", method = RequestMethod.GET, value = "/tags")
    List<Tag> findAllTags() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return tagRepository.findAll(sort);
    }
}
