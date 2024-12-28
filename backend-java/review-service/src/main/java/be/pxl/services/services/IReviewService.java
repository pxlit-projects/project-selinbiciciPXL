package be.pxl.services.services;

import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.Review;

import java.util.List;

public interface IReviewService{

    List<ReviewResponse> getReviewsByPostId(Long postId);

    void approvePost(ReviewRequest reviewRequest);

    void rejectPost(ReviewRequest reviewRequest);

}
