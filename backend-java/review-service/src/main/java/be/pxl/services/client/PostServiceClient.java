package be.pxl.services.client;

import be.pxl.services.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "post-service", url = "http://localhost:8081/api/posts")
public interface PostServiceClient {

    @GetMapping("/{id}")
    Post getPostById(@PathVariable String id);



}
