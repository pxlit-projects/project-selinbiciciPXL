package be.pxl.services.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="posts")
@Data
@Builder
@NoArgsConstructor //geen constuctor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author; //userid
    //@Column(name = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

   /* @Column(name = "updated_date")
    private LocalDateTime updatedDate;*/
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    /*@Column(nullable = true)
    private String rejectionComment;  // Commentaar voor afwijzing*/

   /* @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // Reacties op de post

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;*/ //Beslissing op de post

    //userid
}
