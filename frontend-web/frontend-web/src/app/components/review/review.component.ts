import { Component, OnInit } from '@angular/core';
import { Post } from '../../shared/models/post.model';
import { ReviewService } from '../../shared/services/review.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-review',
  imports: [FormsModule],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  post: Post | null = null;
  isRejection: boolean = false;
  rejectionComment: string = '';
  
  constructor(private reviewService: ReviewService, private router: Router) {}
 
  ngOnInit(): void {
    const postId = 1;  // Simuleer post ID (bijv. van de router)
    this.reviewService.viewPost(postId).subscribe((post) => {
      this.post = post;
    });
  }

  approvePost(postId?: number): void {
    if (postId) {
      this.reviewService.approvePost(postId).subscribe((updatedPost) => {
        console.log('Post approved:', updatedPost);
        this.router.navigate(['/posts']);
      });
    }
  }
  
  rejectPost(postId?: number): void {
    if (postId) {
      this.isRejection = true;
    }
  }
  
  submitRejection(postId?: number): void {
    if (postId && this.rejectionComment) {
      // Als postId en rejectionComment gedefinieerd zijn, roep de rejectPost service aan
      this.reviewService.rejectPost(postId, this.rejectionComment).subscribe((updatedPost) => {
        console.log('Post rejected:', updatedPost);
        this.router.navigate(['/posts']);
      });
    } else {
      // Geef een waarschuwing als postId of rejectionComment ontbreekt
      console.warn('Post ID or rejection comment is missing');
    }
  }

}
