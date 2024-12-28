package be.pxl.services.controller;

import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.Review;
import be.pxl.services.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByPostId(@PathVariable Long postId) {
        return reviewService.getReviewsByPostId(postId);
    }

    // Endpoints voor goedkeuren en afkeuren van posts
    @PutMapping("/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approvePost(@RequestBody ReviewRequest reviewRequest) {
        reviewService.approvePost(reviewRequest);
    }

    @PutMapping("/reject")
    @ResponseStatus(HttpStatus.OK)
    public void rejectPost(@RequestBody ReviewRequest reviewRequest) {
        reviewService.rejectPost(reviewRequest);
    }







}
