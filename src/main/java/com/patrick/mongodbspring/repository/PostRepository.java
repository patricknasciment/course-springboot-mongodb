package com.patrick.mongodbspring.repository;

import com.patrick.mongodbspring.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
