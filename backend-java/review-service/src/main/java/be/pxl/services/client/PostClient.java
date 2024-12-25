package be.pxl.services.client;


import be.pxl.services.controller.dto.PostRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "post-service", url ="http://localhost:8082/api/reviews")
public interface PostClient {

    @PostMapping("/{id}/approve")
    PostRequest approvePost(@PathVariable("id") Long id , @RequestBody PostRequest postRequest);
}
