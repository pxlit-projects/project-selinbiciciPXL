package be.pxl.services.services;

import be.pxl.services.client.ReviewClient;
import be.pxl.services.controller.RejectRequest;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final ReviewClient reviewClient;

    public Post

    // Create a new post and return the created post as a PostDTO
    @Override
    public PostDTO createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        post.setStatus(PostStatus.DRAFT); // Setting default status

        post.setCreatedDate(LocalDateTime.now());

        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost); // Return as DTO
    }

    // Update an existing post and return the updated post as PostDTO
    @Override
    public PostDTO updatePost(Long id, PostRequest postRequest) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isEmpty()) {
            throw new RuntimeException("Post not found");
        }
        Post post = existingPost.get();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());

        Post updatedPost = postRepository.save(post);
        return new PostDTO(updatedPost); // Return updated post as DTO
    }

    // Get all published posts and return them as a list of PostDTO
    @Override
    public List<PostDTO> getPublishedPosts() {
        List<Post> publishedPosts = postRepository.findByStatus(PostStatus.PUBLISHED);
        return publishedPosts.stream()
                .map(PostDTO::new) // Convert each post to PostDTO
                .collect(Collectors.toList());
    }

    // Publish a post and request approval from ReviewService
    @Override
    public PostDTO publishPost(Long id) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isEmpty()) {
            throw new RuntimeException("Post not found");
        }
        Post post = existingPost.get();
        post.setStatus(PostStatus.PUBLISHED);

        // Save locally
        Post publishedPost = postRepository.save(post);

        return new PostDTO(publishedPost); // Return the published post as DTO
    }

    // Filter posts
    public List<Post> filterPosts(PostFilterRequest postFilterRequest) {
        String author = postFilterRequest.getAuthor();
        String title = postFilterRequest.getTitle();
        LocalDateTime date = postFilterRequest.getCreationDate();

        if (date != null) {
            return postRepository.findByStatusAndDate(PostStatus.PUBLISHED, date);
        } else if (author != null && !author.isEmpty()) {
            return postRepository.findByStatusAndAuthorContainingIgnoreCase(PostStatus.PUBLISHED, author);
        } else if (title != null && !title.isEmpty()) {
            return postRepository.findByStatusAndTitleContainingIgnoreCase(PostStatus.PUBLISHED, title);
        }
        return postRepository.findByStatus(PostStatus.PUBLISHED);

    }

    // USERSTORY 2: Methode om een post als concept op te slaan
    public PostDTO createDraftPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        post.setStatus(PostStatus.DRAFT); // Status instellen als DRAFT (concept)
        post.setCreatedDate(LocalDateTime.now());

        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost);
    }

}
