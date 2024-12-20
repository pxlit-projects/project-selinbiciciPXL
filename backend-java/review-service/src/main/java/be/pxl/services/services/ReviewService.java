package be.pxl.services.services;


import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.Review;
import be.pxl.services.domain.StatusType;
import be.pxl.services.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    public List<ReviewResponse> findReviewsByPostId(Long postId) {
        // Haal reviews op uit de database en converteer naar ReviewResponse
        return reviewRepository.findReviewByPostId(postId).stream().map(this::mapToReviewResponse).toList();
    }

    @Override
    public void approve(ReviewRequest reviewRequest) {

        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .author(reviewRequest.getAuthor())
                .content(reviewRequest.getContent())
                .statusType(StatusType.APPROVED)
                .createdAt(LocalDateTime.now())
                .build();

        // Sla de review op in de database
        reviewRepository.save(review);
    }

    @Override
    public void reject(ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .author(reviewRequest.getAuthor())
                .content(reviewRequest.getContent())
                .statusType(StatusType.REJECTED)
                .createdAt(LocalDateTime.now())
                .build();

        // Sla de review op in de database
        reviewRepository.save(review);
    }

    private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .Id(review.getId())
                .postId(review.getPostId())
                .content(review.getContent())
                .statusType(review.getStatusType())
                .createdAt(review.getCreatedAt())
                .author(review.getAuthor())
                .build();
    }
}
