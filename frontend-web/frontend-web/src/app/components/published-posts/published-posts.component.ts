import { Component, OnInit } from '@angular/core';
import { PostService } from '../../shared/services/post.service';
import { MatCardModule } from '@angular/material/card';
import { Post } from '../../shared/models/post.model';

@Component({
  selector: 'app-published-posts',
  imports: [MatCardModule],
  standalone: true,
  templateUrl: './published-posts.component.html',
  styleUrl: './published-posts.component.css'
})
export class PublishedPostsComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostService) {}

  ngOnInit() {
    this.postService.getPublishedPosts().subscribe((data: Post[]) => {
      this.posts = data;
    });
  }
}
