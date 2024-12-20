package be.pxl.services.domain;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long Id;
    private Long postId;

    private String author;
    private String content;
    private LocalDateTime createdAt;
    private String statusType;

}
