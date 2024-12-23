import { Component, inject, OnInit } from '@angular/core';
import { PostDTO } from '../../shared/models/postdto.model';
import { PostService } from '../../shared/services/post.service';
import { ReviewService } from '../../shared/services/review.service';
import { ReviewResponse } from '../../shared/models/reviewresponse.model';
import { ReviewRequest } from '../../shared/models/reviewrequest.model';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-review-post',
  imports: [NgIf, NgFor, FormsModule ],
  templateUrl: './review-post.component.html',
  styleUrl: './review-post.component.css'
})
export class ReviewPostComponent implements OnInit {
 
  submittedPosts: PostDTO[] = [];
  userRole = 'editor'; // Zet dit afhankelijk van de ingelogde gebruiker
  postService: PostService = inject(PostService);
  reviewService: ReviewService = inject(ReviewService);
  selectedPostReviews: ReviewResponse[] = [];
  request: ReviewRequest [] =[];
  selectedPostId?: number;
  rejectingPostId: number | null = null; // ID van de post die wordt afgewezen
  rejectReason: string = ''; // Reden voor afwijzing


  ngOnInit(): void {
    this.fetchSubmittedPosts();
  }

   /**
   * Haal alle ingediende posts op.
   */
   fetchSubmittedPosts(): void {
    this.postService.getSubmittedPosts().subscribe({
      next: (posts) => (this.submittedPosts = posts),
      error: (err) => alert('Fout bij ophalen van posts: ' + err.message)
    });
  }


/**
   * Keur een review goed.
   */
  approve(postId: number): void {
 
    /*submitPost(postId: number): void {
      this.postService.submitPost(postId, this.userRole).subscribe({
        next: (post) => {
          this.selectedPost = post;
          alert(`Post ${post.title} is ingediend!`);
        },
        error: (err) => {
          alert('Er is iets misgegaan: ' + err.message);
        }
      });
    }*/
  }

   /**
   * Wijs een review af.
   */
   reject(postId: number): void {
    if (!this.rejectReason.trim()) {
      alert('Geef een reden voor de afwijzing.');
      return;
    }
    // Verzend de reden samen met de post-ID
    const reason = this.rejectReason.trim();
    const request: ReviewRequest = {
      postId, 
      content: '',
      author: ''
    };
    this.reviewService.rejectReview(request).subscribe({
      next: () => {
        this.selectedPostReviews = this.selectedPostReviews.filter((review) => review.id !== postId);
        this.rejectingPostId = null; // Reset de afwijzing
        this.rejectReason = ''; // Reset de reden
        alert('Post afgewezen met reden: ' + reason);
      },
      error: (err) => alert('Fout bij afwijzen: ' + err.message)
    });
  }

   /**
   * Toggle de zichtbaarheid van het invoerveld voor afwijzen.
   */
   toggleRejectReason(postId: number): void {
    if (this.rejectingPostId === postId) {
      // Verberg de reden als dezelfde post opnieuw wordt geklikt
      this.rejectingPostId = null;
      this.rejectReason = ''; // Reset de reden
    } else {
      // Toon de reden als een andere post wordt geklikt
      this.rejectingPostId = postId;
    }
  }
}
 

