package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;

import java.util.List;

public interface IPostService {

    PostDTO createPost(PostRequest postRequest);

    // Update an existing post and return the updated post as PostDTO
    PostDTO updatePost(Long id, PostRequest postRequest);

    // Get all published posts and return them as a list of PostDTO
    List<PostDTO> getPublishedPosts();

    // Publish a post and return the published post as a PostDTO
    PostDTO publishPost(Long id);

    // USERSTORY 2: Methode om een post als concept op te slaan
    PostDTO createDraftPost(PostRequest postRequest);

    List<Post> filterPosts(PostFilterRequest postFilterRequest);
}
