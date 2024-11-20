package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.dto.PostRequest;

public interface IPostService {

    PostDTO createPost(PostRequest postRequest);
}
