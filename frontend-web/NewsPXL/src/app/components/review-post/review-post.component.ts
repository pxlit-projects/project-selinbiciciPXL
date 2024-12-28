import { Component, inject, OnInit } from '@angular/core';
import { PostDTO } from '../../shared/models/postdto.model';
import { PostService } from '../../shared/services/post.service';
import { ReviewService } from '../../shared/services/review.service';
import { ReviewResponse } from '../../shared/models/reviewresponse.model';
import { ReviewRequest } from '../../shared/models/reviewrequest.model';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Post } from '../../shared/models/post.model';
import { Review } from '../../shared/models/review.model';

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
          // Filter posts met statusReview "APPROVED" uit
          this.submittedPosts = posts.filter(post => 
            !post.reviews || post.reviews.every((review: Review) => review.statusReview !== 'APPROVED')
          );
      },
      error: (err) => {
        console.error('Error fetching posts:', err);
        alert('Fout bij ophalen van posts');
      }
    });
  }


  approvePost(postId: number, author: string): void {
    this.reviewService.approvePost(postId, author).subscribe({
      next: () => {
        alert('Post approved!');
        // Haal de post opnieuw op om de bijgewerkte status op te halen
        this.updatePostStatus(postId);
      },
      error: (err) => {
        console.error('Error approving post:', err);
        alert('Fout bij het goedkeuren van de post.');
      }
    });
  }
  updatePostStatus(postId: number): void {
    this.postService.getPostWithReviews(postId).subscribe({
      next: (post) => {
        const index = this.submittedPosts.findIndex(p => p.id === postId);
        if (index > -1) {
          this.submittedPosts[index].status = post.reviews[0]?.statusReview || 'UNKNOWN';
        }
      },
      error: (err) => {
        console.error('Error fetching updated post status:', err);
      }
    });
   
  }

    // Rechthebbende controle, bijvoorbeeld als je alleen editor de post kunt goedkeuren
    /*isAllowedToApprove(post: PostDTO): boolean {
      return this.userRole === 'editor' && !this.postApprovalStatus[post.id];
    }*/
   

   // Functie voor het afwijzen van een post
   onReject(postId: number, author: string) {
    this.reviewService.rejectPost(postId, author).subscribe({
      next: (response) => {
        console.log('Post afgewezen:', response);
        alert('Post afgewezen!');

       // this.fetchSubmittedPosts(); // Vernieuw de lijst van posts na afwijzing
      },
      error: (err) => {
        console.error('Fout bij afwijzen van post:', err);
        alert('Er is een fout opgetreden bij het afwijzen van de post.');
      },
    });
  }

}




  

 

