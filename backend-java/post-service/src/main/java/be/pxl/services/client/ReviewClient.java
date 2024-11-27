package be.pxl.services.client;


import be.pxl.services.controller.RejectRequest;
import be.pxl.services.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name= "review-service")
public interface ReviewClient {

    @GetMapping("/api/review/view/{postId}")
    Post viewPost(@PathVariable("postId") Long postId);

    @PostMapping("/api/review/approve/{postId}")
    Post approvePost(@PathVariable("postId") Long postId);

    @PostMapping("/api/review/reject/{postId}")
    Post rejectPost(@PathVariable("postId") Long postId, @RequestBody RejectRequest rejectRequest);

    @GetMapping("/api/review/approved")
    List<Post> getApprovedPosts();

    @GetMapping("/api/review/rejected")
    List<Post> getRejectedPosts();



}
