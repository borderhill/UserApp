package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.data.User;

@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public Optional<User> findByUserName(String userName);

    public Optional<User> findByEmail(String email);

}
