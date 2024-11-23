package be.pxl.services.repository;

import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status);

    //filter userstory 5
    List<Post> findByStatusAndAuthorContainingIgnoreCase(PostStatus status, String author);
    List<Post> findByStatusAndTitleContainingIgnoreCase(PostStatus status, String title);
    List<Post> findByStatusAndDate(PostStatus status, LocalDateTime creationDate);
}

