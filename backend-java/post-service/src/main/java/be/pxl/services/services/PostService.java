package be.pxl.services.services;

import be.pxl.services.client.ReviewClient;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import be.pxl.services.domain.Review;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final ReviewClient reviewClient;

    private final PostRepository postRepository;
    //Logback zal standaard berichten op debug level registreren.
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    // Create a new post
    @Override
    public PostDTO createPost(PostRequest postRequest, String userRole) {
        logger.info("Create new post");

        // Controleer of de gebruiker de juiste rol heeft voordat je verder gaat
        checkIfEditorRole(userRole);

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(postRequest.getAuthor())
                .createdDate(LocalDateTime.now())
                .status(postRequest.getStatus() != null ? postRequest.getStatus() : PostStatus.DRAFT)
                .build();


        Post savedPost = postRepository.save(post);
        logger.info("Post created with ID: {}", savedPost.getId());
        return mapToPostDTO(savedPost); // Return as DTO
    }

    // Edit an existing post
    @Override
    public PostDTO editPost(Long id, PostRequest postRequest, String userRole) {
        logger.info("Editing post with ID: {}", id);

        // Controleer of de gebruiker de juiste rol heeft voordat je verder gaat
        checkIfEditorRole(userRole);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with ID " + id + " not found"));


        post.setContent(postRequest.getContent());

        Post updatedPost = postRepository.save(post);

        logger.info("Post with ID: {} updated", updatedPost.getId());
        return mapToPostDTO(updatedPost); // Return updated post as DTO
    }

    // Retrieve all posts
    public List<PostResponse> getAllPost() {
        logger.info("Retrieving all posts");
        return postRepository.findAll()
                .stream()
                .map(this::mapToPostResponse)
                .toList();
    }



    // Retrieve a specific post by ID
    @Override
    public PostResponse getPostById(Long id) {
        logger.info("Retrieving post with ID: {}", id);
        return postRepository.findById(id)
                .map(this::mapToPostResponse)
                .orElse(null);
    }

    // Retrieve all published posts
    @Override
    public List<PostDTO> getPublishedPosts() {
        logger.info("Retrieving all published posts");
        List<Post> publishedPosts = postRepository.findByStatus(PostStatus.PUBLISHED);
        return publishedPosts.stream()
                .map(this::mapToPostDTO) // Convert each post to PostDTO
                .toList();
    }

    @Override
    public List<PostDTO> getSubmittedPosts() {
        return postRepository.findByStatus(PostStatus.SUBMITTED)
                .stream()
                .map(this:: mapToPostDTO)
                .toList();
    }

    @Override
    public PostResponse findPostByIdWithReviews(Long id) {
        //zoek post op id
        PostResponse postResponse = postRepository.findById(id)
                .map(this::mapToPostResponse).orElseThrow(() -> new ResourceNotFoundException("Post with ID " + id + " not found"));

        List<ReviewResponse> reviews = reviewClient.getReviewsByPostId(id)
                .stream().map(this::mapToReviewResponse).toList();

        postResponse.setReviews(reviews);
        return postResponse;

    }

    @Override
    public PostDTO publishPost(Long id, String userRole) {

        // Controleer of de gebruiker de juiste rol heeft voordat je verder gaat
        checkIfEditorRole(userRole);

        // Haal de post op basis van id
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Controleer of de post is goedgekeurd en kan worden gepubliceerd
        if (post.getStatus() != PostStatus.SUBMITTED) {
            throw new IllegalStateException("Post must be submitted before publishing");
        }

        // Zet de status van de post naar "PUBLISHED"
        post.setStatus(PostStatus.PUBLISHED);

        // Sla de post op
        postRepository.save(post);
        // Retourneer de PostDTO na de update
        return mapToPostDTO(post);
    }

    @Override
    public PostDTO submitPost(Long id, String userRole) {
        // Controleer of de gebruiker de juiste rol heeft voordat je verder gaat
        checkIfEditorRole(userRole);

        // Haal de post op basis van id
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Zet de status van de post naar "SUBMITTED"
        post.setStatus(PostStatus.SUBMITTED);

        // Sla de post op
        postRepository.save(post);
        // Retourneer de PostDTO na de update
        return mapToPostDTO(post);


    }

    public void checkIfEditorRole(String userRole) {
        // Controleer of de gebruiker de juiste rol heeft
        if (!"editor".equalsIgnoreCase(userRole)) {
            throw new RuntimeException("You do not have permission to create a post.");
        }
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

    // Map a Post to a PostResponse
    private PostResponse mapToPostResponse(Post post) {
        logger.debug("Mapping Post with ID: {} to PostResponse", post.getId());
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdDate(post.getCreatedDate())
                .status(post.getStatus())
                .build();
    }

    // Map a Post to a PostDTO
    private PostDTO mapToPostDTO(Post post) {
        logger.debug("Mapping Post with ID: {} to PostDTO", post.getId());
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdDate(post.getCreatedDate())
                .status(post.getStatus())
                .build();
    }

}
