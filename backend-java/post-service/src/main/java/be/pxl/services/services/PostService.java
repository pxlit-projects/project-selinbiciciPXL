package be.pxl.services.services;


import be.pxl.services.domain.Post;
import be.pxl.services.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post createPost(Post post) {
        post.setCreatedDate(LocalDateTime.now());
        return postRepository.save(post);
    }
}
