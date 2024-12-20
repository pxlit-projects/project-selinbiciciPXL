package be.pxl.services.services;

import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;

import java.util.List;

public interface IReviewService{

    List<ReviewResponse> findReviewsByPostId(Long postId);

    void approve(ReviewRequest reviewRequest);

    void reject(ReviewRequest reviewRequest);

}
