package com.patrick.mongodbspring.config;

import com.patrick.mongodbspring.domain.Post;
import com.patrick.mongodbspring.domain.User;
import com.patrick.mongodbspring.dto.AuthorDTO;
import com.patrick.mongodbspring.dto.CommentDTO;
import com.patrick.mongodbspring.repository.PostRepository;
import com.patrick.mongodbspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("16/07/2022"), "Minha nova vida", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("17/07/2022"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        postRepository.saveAll(List.of(post1, post2));

        maria.getPosts().addAll(List.of(post1, post2));
        userRepository.save(maria);

        CommentDTO c1 = new CommentDTO("Que bacana!", sdf.parse("18/08/2022"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Parabéns maria!", sdf.parse("19/08/2022"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Maravilha!", sdf.parse("18/08/2022"), new AuthorDTO(alex));

        post1.getComments().addAll(List.of(c1, c2));
        post2.getComments().addAll(List.of(c3));

        postRepository.saveAll(List.of(post1, post2));
    }
}
