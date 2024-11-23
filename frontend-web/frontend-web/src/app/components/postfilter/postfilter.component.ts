import { Component, OnInit } from '@angular/core';
import { PostService } from '../../shared/services/post.service';
import { Post } from '../../shared/models/post.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PostFilterRequest } from '../../shared/models/post-filter-request.model';

@Component({
  selector: 'app-postfilter',
  imports: [FormsModule, CommonModule],
  templateUrl: './postfilter.component.html',
  styleUrl: './postfilter.component.css'
})
export class PostfilterComponent implements OnInit {
    // Alle posts
  posts: Post[] = [];
  // Gefilterde resultaten
  filteredPosts: Post[] = [];

  // Filter-aanvraag object
  filterRequest: PostFilterRequest = {
    author: '',
    title: '',
    date: '',
  };

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.loadPublishedPosts();  // Load the posts when the component is initialized
  }
  // Haalt alle gepubliceerde posts op
  loadPublishedPosts(): void {
    this.postService.getPublishedPosts().subscribe({
      next: (posts) => {
        this.posts = posts;
        this.filteredPosts = posts; // Toon standaard alle gepubliceerde posts
      },
      error: (err) => console.error('Error loading posts:', err),
    });
  }
 

 // Pas filters toe
 applyFilter(): void {
  this.postService.filterPosts(this.filterRequest).subscribe({
    next: (posts) => {
      this.filteredPosts = posts; // Toon gefilterde resultaten
    },
    error: (err) => console.error('Error filtering posts:', err),
  });
}
}
