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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;



    // Create a new post
    @Override
    public PostDTO createPost(PostRequest postRequest) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(postRequest.getAuthor())
                .createdDate(LocalDateTime.now())
                .status(postRequest.getStatus() != null ? postRequest.getStatus() : PostStatus.DRAFT)
                .build();

        Post savedPost = postRepository.save(post);
        return mapToPostDTO(savedPost); // Return as DTO
    }

    // Map a Post to a PostDTO
    private PostDTO mapToPostDTO(Post post) {
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
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with ID " + id + " not found"));

        //post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
       // post.setAuthor(postRequest.getAuthor());
        //post.setStatus(postRequest.getStatus());

        Post updatedPost = postRepository.save(post);
        return mapToPostDTO(updatedPost); // Return updated post as DTO
    }

    // Retrieve all posts
    public List<PostResponse> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToPostResponse)
                .toList();
    }

    // Map a Post to a PostResponse
    private PostResponse mapToPostResponse(Post post) {
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
        return postRepository.findById(id)
                .map(this::mapToPostResponse)
                .orElse(null);
    }

    // Retrieve all published posts
    @Override
    public List<PostDTO> getPublishedPosts() {
        List<Post> publishedPosts = postRepository.findByStatus(PostStatus.PUBLISHED);
        return publishedPosts.stream()
                .map(this::mapToPostDTO) // Convert each post to PostDTO
                .toList();
    }

    // US5: Filter posts based on content, author, or date
    //filtert alleen op datum niet op tijd
    @Override
    public List<PostResponse> filterPosts(PostFilterRequest postFilterRequest) {
        //log.info("Filter parameters: content={}, author={}, creationDate={}",
        //postFilterRequest.getContent(),
                //postFilterRequest.getAuthor(),
               // postFilterRequest.getCreationDate());
        String content = postFilterRequest.getContent();
        String author = postFilterRequest.getAuthor();
        LocalDate creationDate = postFilterRequest.getCreationDate();

        // Haal gefilterde posts op vanuit repository
        List<Post> filteredPosts = postRepository.filterPosts(content, author, creationDate);

        // Converteer naar PostResponse en retourneer
        return filteredPosts.stream()
                .map(this::mapToPostResponse)
                .toList();

    }


}
