package be.pxl.services.controller;


import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    //ENDPOINT POST
    //@PostMapping createPost US1 US2
    //@GetMapping findAll US1
    //@PutMapping edit US3
    //@GetMapping findbyid US3
    //@GetMApping published US4
    //@GetMapping submitted US7
    //@GetMapping /id/withreview  US8
    //@GetMapping /id/withcomments findbyidwithcomments US11


    //US1,US2: create a new post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@Valid @RequestBody PostRequest postRequest){
        //log.info("Received request to create post: {}", postRequest);
        return postService.createPost(postRequest);
    }

    //US1: Retrieve all posts (including drafts)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> findAll(){
        return postService.getAllPost();
    }

    //US3: Update a post
    @PutMapping("/{id}")
    public PostDTO editPost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        return postService.editPost(id, postRequest);
    }

    //US3: Retrieve a post by ID
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse findById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    //US4: Retrieve all published posts
    @GetMapping("/published")
    public List<PostDTO> getPublishedPosts() {
        return postService.getPublishedPosts();
    }

    // US5: Filter posts based on content, author, or date
    /*@GetMapping("/filter")
    public ResponseEntity<List<PostResponse>> filterPosts(@RequestBody PostFilterRequest filterRequest) {
        List<PostResponse> filteredPosts = postService.filterPosts(filterRequest);
        return ResponseEntity.ok(filteredPosts);
    }*/

    //USERSTORY 4
    //gedeeltedelijke updates
    /*@PatchMapping("/{id}/publish")
    public PostDTO publishPost(@PathVariable Long id) {
        return postService.publishPost(id);
    }*/

    //USERSTORY 5
  /*  @PostMapping("/filter")
    public List<Post> filterPosts(@RequestBody PostFilterRequest postFilterRequest) {
        return postService.filterPosts(postFilterRequest);
    }*/

    //userstory7
    //get submitted

    //userstory8
    //get findbyidwithreview

   // userstory11
    //get findbyidwithcomments

}
