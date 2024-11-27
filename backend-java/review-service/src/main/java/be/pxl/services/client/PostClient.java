package be.pxl.services.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name= "post-service")
public class PostClient {

}
