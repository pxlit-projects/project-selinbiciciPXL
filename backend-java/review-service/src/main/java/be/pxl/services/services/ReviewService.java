package be.pxl.services.services;




import be.pxl.services.client.PostServiceClient;
import be.pxl.services.controller.dto.ReviewRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.Review;
import be.pxl.services.domain.ReviewStatus;
import be.pxl.services.repository.ReviewRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final PostServiceClient postServiceClient;
    //logger


    @Override
    public List<ReviewResponse> getReviewsByPostId(Long postId) {

        getPostByIdFromClient(postId);


        // Hier kan je eventueel de post-informatie loggen of verder verwerken
        // logger.info("Fetched post: {}", post);

        return reviewRepository.findReviewByPostId(postId)
                .stream()
                .map(this::mapToReviewResponse)
                .toList();
    }

    @Override
    public void approvePost(ReviewRequest reviewRequest) {

        getPostByIdFromClient(reviewRequest.getPostId());

     


        //log
        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .content(reviewRequest.getContent())
                .author(reviewRequest.getAuthor())
                .createdDate(LocalDateTime.now())
                .reviewStatus(ReviewStatus.APPROVED)
                .build();

        reviewRepository.save(review);



    }

    // Nieuwe methode om de postinformatie op te halen
    private void getPostByIdFromClient(Long postId) {
        postServiceClient.getPostById(postId.toString());
    }

    @Override
    public void rejectPost(ReviewRequest reviewRequest) {

        getPostByIdFromClient(reviewRequest.getPostId());

        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .content("")
                .author(reviewRequest.getAuthor())
                .createdDate(LocalDateTime.now())
                .reviewStatus(ReviewStatus.REJECTED)
                .build();

        reviewRepository.save(review);

    }

    private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .postId(review.getPostId())
                .author(review.getAuthor())
                .content(review.getContent())
                .createdDate(review.getCreatedDate())
                .reviewStatus(review.getReviewStatus())
                .build();
    }
}
