package be.pxl.services.repository;

import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStatus(PostStatus status);

    @Query("SELECT p FROM Post p WHERE " +
     "(:content IS NULL OR p.content LIKE %:content%) AND " +
     "(:author IS NULL OR p.author = :author) AND " +
     "(:creationDate IS NULL OR CAST(p.createdDate AS date) = :creationDate)") //Als je alleen de datum wilt vergelijken en niet de tijd:
    List<Post> filterPosts(@Param("content") String content,
                           @Param("author") String author,
                           @Param("creationDate") LocalDate creationDate);
}

