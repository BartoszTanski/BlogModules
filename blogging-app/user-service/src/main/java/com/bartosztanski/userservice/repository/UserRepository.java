package com.bartosztanski.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.userservice.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
