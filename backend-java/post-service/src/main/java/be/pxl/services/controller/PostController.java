package be.pxl.services.controller;


import be.pxl.services.client.ReviewServiceClient;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import be.pxl.services.domain.Review;
import be.pxl.services.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


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
    public PostDTO createPost(@Valid @RequestBody PostRequest postRequest, @RequestHeader("userRole") String userRole){
        //logger.info("Received request to create post: {}", postRequest);

        return postService.createPost(postRequest, userRole);
    }

    //US1: Retrieve all posts (including drafts)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> findAll(){
        return postService.getAllPost();
    }

    //US3: Update a post
    @PutMapping("/{id}")
    public PostDTO editPost(@PathVariable Long id, @RequestBody PostRequest postRequest, String userRole) {
        return postService.editPost(id, postRequest, userRole);
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

    //US4: Retrieve all published posts
    @GetMapping("/submitted")
    public List<PostDTO> getSubmittedPosts() {
        return postService.getSubmittedPosts();
    }


    //USERSTORY 4
    //gedeeltedelijke updates
    // Methode om een post te publiceren
    @PutMapping("/{id}/publish")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO publishPost(@PathVariable Long id, @RequestHeader("userRole") String userRole) {
        // Log de binnenkomst van het verzoek
        logger.info("Publish request ontvangen voor post met id: {} door gebruiker met rol: {}", id, userRole);

        return postService.publishPost(id, userRole);
    }

    //userstory7
    //Als redacteur wil ik ingediende posts kunnen bekijken en goedkeuren of afwijzen, zodat alleen goedgekeurde content wordt gepubliceerd.
    //get submitted
    @PostMapping("/{id}/submit")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO submitPost(@PathVariable Long id, @RequestHeader String userRole) {
        return postService.submitPost(id, userRole);
    }


   /* @GetMapping("/approved")
    public ResponseEntity<List<PostDTO>> getApprovedPosts() {
        List<PostDTO> approvedPosts = postService.getApprovedPosts();
        return ResponseEntity.ok(approvedPosts);
    }*/

    //Haal een post op inclusief reviews:
    //
    //GET http://localhost:8081/api/posts/1/reviews

   /* @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsForPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getReviewsForPost(id));
    }*/

    //userstory8
    //Als redacteur wil ik een melding ontvangen wanneer een post goedgekeurd of afgewezen is,
    // zodat ik weet of het gepubliceerd kan worden of moet worden herzien.
    //get findbyidwithreview

    @GetMapping("/{id}/with-reviews")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostByIdWithReviews(@PathVariable Long id) {
        return postService.findPostByIdWithReviews(id);
    }



    //Werk de status van een post bij:
    //
    //PUT http://localhost:8081/api/posts/1/status?status=APPROVED
   /* @PutMapping("/{id}/status")
    public ResponseEntity<Void> updatePostStatus(@PathVariable Long id, @RequestParam PostStatus status) {
        postService.updatePostStatus(id, status);
        return ResponseEntity.ok().build();
    }*/



   // userstory11
    //get findbyidwithcomments

}
