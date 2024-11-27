package be.pxl.services.controller;

import be.pxl.services.domain.Post;
import be.pxl.services.services.ReviewService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Bekijk de details van een specifieke post
    @GetMapping("/view/{postId}")
    public Post viewPost(@PathVariable Long postId) {
        return reviewService.viewPost(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // Keur een specifieke post goed
    @PostMapping("/approve/{postId}")
    public Post approvePost(@PathVariable Long postId) {
        return reviewService.approvePost(postId);
    }

    // Wijs een specifieke post af met een opmerking
    @PostMapping("/reject/{postId}")
    public Post rejectPost(@PathVariable Long postId, @RequestBody RejectRequest rejectRequest) {
        return reviewService.rejectPost(postId, rejectRequest.getComment());
    }

    // Haal alle goedgekeurde posts op
    @GetMapping("/approved")
    public List<Post> getApprovedPosts() {
        return reviewService.getApprovedPosts();
    }

    // Haal alle afgewezen posts op
    @GetMapping("/rejected")
    public List<Post> getRejectedPosts() {
        return reviewService.getRejectedPosts();
    }


}
