import { Component, inject, OnInit } from '@angular/core';
import { PostResponse } from '../../shared/models/postresponse.model';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive} from '@angular/router';
import { PostService } from '../../shared/services/post.service';
import { AsyncPipe, CommonModule } from '@angular/common';
import { AuthService } from '../../shared/services/auth.service';
import { ReviewService } from '../../shared/services/review.service';
import { ReviewResponse } from '../../shared/models/reviewresponse.model';
import { Observable } from 'rxjs';
import { Post } from '../../shared/models/post.model';
import { PostDTO } from '../../shared/models/postdto.model';

@Component({
  selector: 'app-post-details',
  standalone:true,
   imports:[CommonModule, RouterLink,],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit {

  post: PostResponse | null = null;
  route: ActivatedRoute = inject(ActivatedRoute);
  postService: PostService = inject(PostService);
  authService: AuthService = inject(AuthService);
  router: Router = inject(Router);
  id = Number(this.route.snapshot.paramMap.get('id'));
  posts: PostDTO[] = [];
  selectedPost?: PostDTO;
  userRole = 'editor'; // Zet dit afhankelijk van de ingelogde gebruiker



  ngOnInit(): void {
    //const id = Number(this.route.snapshot.paramMap.get('id'));

    //haal post details op
    this.postService.getPostById(this.id).subscribe((data) => {
      this.post = data;
    });

  }

  canEdit():boolean{
    return this.authService.getRole()=== 'editor';
  }

  submitPost(postId: number): void {
    this.postService.submitPost(postId, this.userRole).subscribe({
      next: (post) => {
        this.selectedPost = post;
        alert(`Post ${post.title} is ingediend!`);
      },
      error: (err) => {
        alert('Er is iets misgegaan: ' + err.message);
      }
    });
  }
}
