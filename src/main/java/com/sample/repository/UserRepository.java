package com.sample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.data.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
