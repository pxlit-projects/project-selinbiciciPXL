package be.pxl.services.controller.dto;


import be.pxl.services.domain.PostStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author; //userid
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private List<ReviewResponse> reviews;
   // private List<ReviewResponse> reviews;
    /*@Column(nullable = true)
    private String rejectionComment;  // Commentaar voor afwijzing*/

   /* @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // Reacties op de post

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;*/
}
