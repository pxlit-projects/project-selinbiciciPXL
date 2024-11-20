package be.pxl.services.controller;

import be.pxl.services.domain.Post;
import be.pxl.services.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }
}
