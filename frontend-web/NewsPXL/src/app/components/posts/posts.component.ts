import { Component, OnInit } from '@angular/core';
import { PostResponse } from '../../shared/models/postresponse.model';
import { PostService } from '../../shared/services/post.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-posts',
  standalone:true,
  imports:[RouterLink, CommonModule],
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.css'
})
export class PostsComponent implements OnInit {
  posts: PostResponse[] = [];

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService.getAllPosts().subscribe((data) => {
      this.posts = data;
    });
  }
}
