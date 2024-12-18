package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    //Logback zal standaard berichten op debug level registreren.
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    // Create a new post
    @Override
    public PostDTO createPost(PostRequest postRequest) {
        logger.info("Create new post");
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

    // Edit an existing post
    @Override
    public PostDTO editPost(Long id, PostRequest postRequest) {
        logger.info("Editing post with ID: {}", id);
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


}
