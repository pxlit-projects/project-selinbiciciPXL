package be.pxl.services.services;


import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.repository.PostRepository;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    //create and post
    @Override
    public PostDTO createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        post.setCreatedDate(LocalDateTime.now());

        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost);
    }

}
