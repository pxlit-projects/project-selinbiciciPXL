import { Component, OnInit } from '@angular/core';
import { Post } from '../../shared/models/post.model';
import { PostService } from '../../shared/services/post.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-post',
  imports: [CommonModule],
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
//Je kunt gepubliceerde posts ophalen en weergeven in een lijst.
export class PostComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostService, public authService: AuthService) {}


  ngOnInit(): void {
    this.postService.getPublishedPosts().subscribe((data) => {
      this.posts = data;
    });
  }

  editPost(postId: number): void {
    if (postId !== undefined) {
      // Alleen doorgaan als postId gedefinieerd is
      console.log('Edit Post', postId);
      // Verwijs naar een bewerkingspagina of doe iets anders
    } else {
      console.warn('Invalid post ID');
    }
  }
}
