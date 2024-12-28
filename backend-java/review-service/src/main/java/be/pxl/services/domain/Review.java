package be.pxl.services.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="review")
@Data
@Builder
@NoArgsConstructor //geen constuctor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private String content;
    private String author;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus; // "APPROVED", "REJECTED"


}
