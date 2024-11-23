package be.pxl.services.services;


import be.pxl.services.domain.Comment;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.User;
import be.pxl.services.exception.CommentNotFoundException;
import be.pxl.services.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment addComment(Long postId, Long userId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(new Post(postId));  // Aangenomen dat je een Post-object hebt
        comment.setUser(new User(userId));  // Aangenomen dat je een User-object hebt
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment updateComment(Long commentId, Long userId, String newContent) throws CommentNotFoundException {
        Optional<Comment> optionalComment = commentRepository.findByIdAndUserId(commentId, userId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(newContent);
            return commentRepository.save(comment);
        } else {
            throw new CommentNotFoundException("Comment not found or not owned by user");
        }
    }

    public void deleteComment(Long commentId, Long userId) throws CommentNotFoundException {
        Optional<Comment> optionalComment = commentRepository.findByIdAndUserId(commentId, userId);
        if (optionalComment.isPresent()) {
            commentRepository.delete(optionalComment.get());
        } else {
            throw new CommentNotFoundException("Comment not found or not owned by user");
        }
    }
}
