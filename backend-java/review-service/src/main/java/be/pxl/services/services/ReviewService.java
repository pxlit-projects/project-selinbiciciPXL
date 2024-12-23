package be.pxl.services.services;


import be.pxl.services.client.PostClient;
import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.Post;
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
    private final PostClient postClient;


    @Override
    public List<ReviewResponse> findReviewsByPostId(Long postId) {
        // Haal de post op via PostClient
        Post post = postClient.getPostById(postId);

        // Haal reviews op uit de database en converteer naar ReviewResponse
        return reviewRepository.findReviewByPostId(postId)
                .stream()
                .map(review -> mapToReviewResponse(review, post))
                .toList();
    }

    @Override
    public void approve(ReviewRequest reviewRequest) {

        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .author(reviewRequest.getAuthor())
                .content(reviewRequest.getContent())
                .statusType(StatusType.APPROVED)
                .createdDate(LocalDateTime.now())
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
                .createdDate(LocalDateTime.now())
                .build();

        // Sla de review op in de database
        reviewRepository.save(review);

        // Voeg de reden en de opmerkingen toe (mogelijk in een logboek of database)
       // post.setRejectionReason(reason);
        //post.setRejectionComment(comment);
    }

    private ReviewResponse mapToReviewResponse(Review review, Post post) {
        return ReviewResponse.builder()
                .Id(review.getId())
                .postId(post.getId())
                .content(review.getContent())
                .statusType(review.getStatusType())
                .createdDate(review.getCreatedDate())
                .author(review.getAuthor())
                .build();
    }
}
