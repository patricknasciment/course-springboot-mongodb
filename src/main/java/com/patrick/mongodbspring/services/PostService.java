package com.patrick.mongodbspring.services;

import com.patrick.mongodbspring.domain.Post;
import com.patrick.mongodbspring.domain.User;
import com.patrick.mongodbspring.dto.UserDTO;
import com.patrick.mongodbspring.repository.PostRepository;
import com.patrick.mongodbspring.repository.UserRepository;
import com.patrick.mongodbspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
