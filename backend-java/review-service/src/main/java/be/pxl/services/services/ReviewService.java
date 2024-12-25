package be.pxl.services.services;




import be.pxl.services.controller.dto.PostRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.domain.PostStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {



     /*@Override
    public void approve(ReviewRequest reviewRequest) {
        Post post = postRepository.getReferenceById(reviewRequest.getPostId());
        post.setStatus(PostStatus.APPROVED);
        postRepository.save(post);
        Review review = Review.builder()
                .postId(reviewRequest.getPostId())
                .author(reviewRequest.getAuthor())
                .content(reviewRequest.getContent())
                .statusType(StatusType.APPROVED)
                .createdDate(LocalDateTime.now())
                .build();

        // Sla de review op in de database
        reviewRepository.save(review);
    }*/






   /* @Override
    public List<ReviewResponse> findReviewsByPostId(Long postId) {


        // Haal reviews op uit de database en converteer naar ReviewResponse
        return reviewRepository.findReviewByPostId(postId)
                .stream()
                .map(this::mapToReviewResponse)
                .toList();
    }*/



    /*@Override
    public List<ReviewResponse> getApprovedPosts() {
        return reviewRepository.findByStatusType(PostStatus.APPROVED)
                .stream()
                .map(this:: mapToReviewResponse)
                .toList();
    }*/

    /*private ReviewRequest mapToReviewRequest(Review review) {
        return ReviewRequest.builder()
                .author(review.getAuthor())
                .content(review.getContent())
                .postId(review.getPostId())
                .build();
    }*/

   /* @Override
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
    }*/



    /*private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .Id(review.getId())
                .postId(review.getId())
                .content(review.getContent())
                .statusType(review.getStatusType())
                .createdDate(review.getCreatedDate())
                .author(review.getAuthor())
                .build();
    }*/
}
