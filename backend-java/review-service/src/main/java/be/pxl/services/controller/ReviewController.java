package be.pxl.services.controller;

import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //client in post
    @GetMapping( "post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByPostId(@PathVariable Long postId) {
        return reviewService.findReviewsByPostId(postId);

    }

    // postmapping /review/approve
    // postmapping /review/reject

    @PostMapping("/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approveReview(@RequestBody ReviewRequest reviewRequest){
        reviewService.approve(reviewRequest);
    }

    @PostMapping("/reject")
    @ResponseStatus(HttpStatus.OK)
    public void rejectReview(@RequestBody ReviewRequest reviewRequest){
        reviewService.reject(reviewRequest);
    }




}
