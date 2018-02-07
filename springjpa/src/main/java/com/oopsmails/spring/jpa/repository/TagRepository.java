package com.oopsmails.spring.jpa.repository;


import com.oopsmails.spring.jpa.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
