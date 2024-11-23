package be.pxl.services.controller;

import be.pxl.services.domain.Comment;
import be.pxl.services.exception.CommentNotFoundException;
import be.pxl.services.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @PathVariable Long userId,
                                              @RequestBody String content) {
        Comment comment = commentService.addComment(postId, userId, content);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}/user/{userId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId,
                                                 @PathVariable Long userId,
                                                 @RequestBody String newContent) throws CommentNotFoundException {
        Comment updatedComment = commentService.updateComment(commentId, userId, newContent);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @PathVariable Long userId) throws CommentNotFoundException {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
