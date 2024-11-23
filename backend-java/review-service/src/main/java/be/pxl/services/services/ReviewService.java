package be.pxl.services.services;

import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService{

    private final PostRepository postRepository;

    // Bekijk ingediende post
    public Optional<Post> viewPost(Long postId) {
        return postRepository.findById(postId);
    }

    // Goedkeuren van een post
    public Post approvePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setStatus(PostStatus.PUBLISHED);  // Zet de status naar gepubliceerd
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    // Afwijzen van een post met een opmerking
    public Post rejectPost(Long postId, String comment) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setStatus(PostStatus.REJECTED);  // Zet de status naar afgewezen
            post.setRejectionComment(comment);  // Zet de afwijzingsopmerking
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    // Optioneel: methoden voor het ophalen van goedgekeurde of afgewezen posts
    public List<Post> getApprovedPosts() {
        return postRepository.findByStatus(PostStatus.PUBLISHED);
    }

    public List<Post> getRejectedPosts() {
        return postRepository.findByStatus(PostStatus.REJECTED);
    }




}
