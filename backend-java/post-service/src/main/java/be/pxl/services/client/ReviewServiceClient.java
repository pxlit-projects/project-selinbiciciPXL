package be.pxl.services.client;

import be.pxl.services.domain.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review-service", url = "http://localhost:8082/api/reviews")
public interface ReviewServiceClient {

    @GetMapping("/post/{postId}")
    List<Review> getReviewsByPostId(@PathVariable Long postId);
}
