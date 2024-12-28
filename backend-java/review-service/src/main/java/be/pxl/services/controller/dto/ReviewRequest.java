package be.pxl.services.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //server leest data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    private String content;
    private String author;
    private Long postId;
    private String userRole;


}
