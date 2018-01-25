package com.oopsmails.springboot.template.inmemorydb.hsql.dao;

import com.oopsmails.springboot.template.inmemorydb.hsql.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
