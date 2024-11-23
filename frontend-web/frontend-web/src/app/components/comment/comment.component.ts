import { Component, OnInit } from '@angular/core';
import { CommentService } from '../../shared/services/comment.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-comment',
  imports: [FormsModule],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent implements OnInit {
  postId: number | undefined;
  comments: any[] = [];
  newComment: string = '';
  editCommentId: number | null = null;
  editCommentContent: string = '';

  constructor(
    private commentService: CommentService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.postId = +this.route.snapshot.paramMap.get('postId')!;
    this.loadComments();
  }

  loadComments(): void {
    this.commentService.getCommentsByPost(this.postId!).subscribe((comments) => {
      this.comments = comments;
    });
  }

  addComment(): void {
    if (this.newComment.trim()) {
      // Voeg comment toe
      this.commentService.addComment(1, 1, this.newComment).subscribe((comment) => {
        this.comments.push(comment);
        this.newComment = ''; // Reset het commentaar
      });
    }
  }

  editComment(commentId: number, content: string): void {
    this.editCommentId = commentId;
    this.editCommentContent = content;
  }

  updateComment(): void {
    if (this.editCommentId !== null) {
      this.commentService.updateComment(this.editCommentId, 1, this.editCommentContent).subscribe((updatedComment) => {
        this.loadComments(); // Herlaad de reacties
        this.editCommentId = null; // Reset
      });
    }
  }

  deleteComment(commentId: number): void {
    if (confirm('Are you sure you want to delete this comment?')) {
      this.commentService.deleteComment(commentId, 1).subscribe(() => {
        this.comments = this.comments.filter((comment) => comment.id !== commentId);
      });
    }
  }
}
