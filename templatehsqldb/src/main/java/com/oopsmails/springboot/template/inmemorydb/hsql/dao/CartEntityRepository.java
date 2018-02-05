package com.oopsmails.springboot.template.inmemorydb.hsql.dao;

import com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntityRepository extends JpaRepository<CartEntity, Integer> {

    List<CartEntity> findAll();

    List<CartEntity> findByUserId(int userId);

}
