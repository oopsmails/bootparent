package com.oopsmails.springboot.template.inmemorydb.hsql.dao;

import com.oopsmails.springboot.template.inmemorydb.hsql.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
