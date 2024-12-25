import { Component, inject, OnInit } from '@angular/core';
import { PostDTO } from '../../shared/models/postdto.model';
import { PostService } from '../../shared/services/post.service';
import { ReviewService } from '../../shared/services/review.service';
import { ReviewResponse } from '../../shared/models/reviewresponse.model';
import { ReviewRequest } from '../../shared/models/reviewrequest.model';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Post } from '../../shared/models/post.model';

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



  ngOnInit(): void {
    this.fetchSubmittedPosts();
  }

  // Haal de posts op die goedgekeurd moeten worden
  fetchSubmittedPosts(): void {
    this.postService.getSubmittedPosts().subscribe({
      next: (posts) => {
        this.submittedPosts = posts;
      },
      error: (err) => {
        console.error('Error fetching posts:', err);
        alert('Fout bij ophalen van posts');
      }
    });
  }


/**
   * Keur een review goed.
   */
  // Goedkeuren van een post
  approvePost(id: number): void {
    this.reviewService.approvePost(id).subscribe({
      next: () => {
        // Zoek de post en update de status lokaal
        const post = this.submittedPosts.find(p => p.id === id);
        if (post) {
          post.status = 'approved';
        }
      },
      error: (error) => {
        console.error('Er is een fout opgetreden bij het goedkeuren van de post:', error);
      }
    });
      
  }
}



  

 

