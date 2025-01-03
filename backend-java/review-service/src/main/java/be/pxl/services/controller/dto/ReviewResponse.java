package be.pxl.services.controller.dto;

import be.pxl.services.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long postId;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private ReviewStatus reviewStatus;
}
