package com.bartosztanski.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.userservice.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
	void delete(VerificationToken verificationToken);

}
