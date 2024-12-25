import { NgFor, NgIf } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ReviewRequest } from '../../shared/models/reviewrequest.model';
import { ReviewService } from '../../shared/services/review.service';
import { PostDTO } from '../../shared/models/postdto.model';
import { PostService } from '../../shared/services/post.service';
import { PostResponse } from '../../shared/models/postresponse.model';
import { ReviewResponse } from '../../shared/models/reviewresponse.model';

@Component({
  selector: 'app-approved-review',
  imports: [NgFor],
  templateUrl: './approved-review.component.html',
  styleUrl: './approved-review.component.css'
})
export class ApprovedReviewComponent implements OnInit {
  
  approvedReviews: ReviewResponse[] = [];
  reviewService = inject(ReviewService);
  postService = inject(PostService);
  selectedPost: PostDTO [] = [];
  publishPost?: PostDTO;
  userRole = 'editor';
  post: PostResponse | null = null;

  

  ngOnInit(): void {
    this.loadApprovedReviews();
  }


  loadApprovedReviews(): void {
    this.postService.getApprovedPosts().subscribe({
      next: (post) => {
        this.selectedPost = post;
      },
      error: (err) => {
        console.error('Er is een fout opgetreden:', err);  // Foutafhandeling
      }
    });
  }

   // Functie om een post te publiceren
   publishReview(id: number): void {
    this.postService.publishPost(id, this.userRole).subscribe({
      next: (post) => {
        this.publishPost = post;
        alert(`Post ${post.title} is gepubliseerd!`);

      },
    error: (error) => {
      console.error('Fout bij publiceren:', error);
    }
    });
  }





  

}
