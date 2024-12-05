package be.pxl.services.repository;

import be.pxl.services.domain.Post;
import be.pxl.services.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStatus(PostStatus status);

    // @Query("SELECT p FROM Post p WHERE " +
// "(:content IS NULL OR p.content LIKE %:content%) AND " +
// "(:author IS NULL OR p.author = :author) AND " +
// "(:creationDate IS NULL OR FUNCTION('DATE', p.createdDate) = :creationDate)")
// List<Post> filterPosts(@Param("content") String content,
//                        @Param("author") String author,
//                        @Param("creationDate") LocalDate creationDate);
}

