package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.request.PostFilterRequest;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.Post;

import java.util.List;

public interface IPostService {

    PostDTO createPost(PostRequest postRequest);

    List<PostResponse> getAllPost();

    // Update an existing post and return the updated post as PostDTO
    PostDTO editPost(Long id, PostRequest postRequest);

    PostResponse getPostById(Long id);

    // Get all published posts and return them as a list of PostDTO
    List<PostDTO> getPublishedPosts();


}
