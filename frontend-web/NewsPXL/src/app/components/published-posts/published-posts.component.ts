import { Component } from '@angular/core';
import { PostService } from '../../shared/services/post.service';
import { PostDTO } from '../../shared/models/postdto.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-published-posts',
  standalone: true,
  imports:[CommonModule],
  templateUrl: './published-posts.component.html',
  styleUrl: './published-posts.component.css'
})
export class PublishedPostsComponent {

  publishedPosts: PostDTO[] = [];

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService.getPublishedPosts().subscribe((data) => {
      this.publishedPosts = data; // Vul de lijst met gepubliceerde posts
    });
  }
}
