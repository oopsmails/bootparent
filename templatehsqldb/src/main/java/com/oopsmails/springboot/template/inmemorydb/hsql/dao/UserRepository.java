package com.oopsmails.springboot.template.inmemorydb.hsql.dao;

import com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findAll();

    List<UserEntity> findByUserName(String userName);

    List<UserEntity> findDistinctByCartsNotNull();

    List<UserEntity> findDistinctByUserIdAndCartsIsNotNull(int userId);
}
