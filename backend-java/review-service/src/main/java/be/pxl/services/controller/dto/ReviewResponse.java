package be.pxl.services.controller.dto;

import be.pxl.services.domain.StatusType;
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
    private Long Id;
    private Long postId;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private StatusType statusType;
}
