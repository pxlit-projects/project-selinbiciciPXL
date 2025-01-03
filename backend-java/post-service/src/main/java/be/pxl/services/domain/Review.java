package be.pxl.services.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private Long id;
    private Long postId;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    //@Column(name = "status")
    private String reviewStatus; // "APPROVED", "REJECTED"

}
