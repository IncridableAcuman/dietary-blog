package com.diet.backend.repository;

import com.diet.backend.entity.Token;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, ObjectId> {
}
