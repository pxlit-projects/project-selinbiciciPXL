package be.pxl.services.controller;


import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //USERSTORY 1
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@RequestBody PostRequest postRequest){
        return postService.createPost(postRequest);
    }

    // USERSTORY 2
    @PostMapping("/draft")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createDraftPost(@RequestBody PostRequest postRequest) {
        return postService.createDraftPost(postRequest);
    }

    //USERSTORY 3
    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        return postService.updatePost(id, postRequest);
    }

    //USERSTORY 4
    @GetMapping
    public List<PostDTO> getPublishedPosts() {
        return postService.getPublishedPosts();
    }

    //USERSTORY 4
    //gedeeltedelijke updates
    @PatchMapping("/{id}/publish")
    public PostDTO publishPost(@PathVariable Long id) {
        return postService.publishPost(id);
    }

    //USERSTORY 5
    @PostMapping("/filter")
    public List<Post> filterPosts(@RequestBody PostFilterRequest postFilterRequest) {
        return postService.filterPosts(postFilterRequest);
    }

}
