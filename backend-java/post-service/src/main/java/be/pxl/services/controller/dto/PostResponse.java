package be.pxl.services.controller.dto;


import be.pxl.services.domain.PostStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author; //userid
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    /*@Column(nullable = true)
    private String rejectionComment;  // Commentaar voor afwijzing*/

   /* @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // Reacties op de post

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;*/
}
