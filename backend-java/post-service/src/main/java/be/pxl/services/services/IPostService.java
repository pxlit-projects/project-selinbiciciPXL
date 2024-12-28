package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostResponse;
import be.pxl.services.controller.request.PostRequest;
import be.pxl.services.domain.PostStatus;

import java.util.List;

public interface IPostService {

    PostDTO createPost(PostRequest postRequest, String userRole);

    List<PostResponse> getAllPost();

    // Update an existing post and return the updated post as PostDTO
    PostDTO editPost(Long id, PostRequest postRequest, String userRole);

    PostResponse getPostById(Long id);

    // Get all published posts and return them as a list of PostDTO
    List<PostDTO> getPublishedPosts();

    //PostResponse findPostByIdWithReviews(Long id);


    PostDTO publishPost(Long id, String userRole);

    PostDTO submitPost(Long id, String userRole);

    List<PostDTO> getSubmittedPosts();


    //void approvePost(Long id, PostRequest postRequest);

    //List<PostDTO> getApprovedPosts();

    PostResponse findPostByIdWithReviews(Long id);
}
