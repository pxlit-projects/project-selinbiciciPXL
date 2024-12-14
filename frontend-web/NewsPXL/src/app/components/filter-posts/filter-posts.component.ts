import { Component } from '@angular/core';
import { PostResponse } from '../../shared/models/postresponse.model';
import { PostService } from '../../shared/services/post.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-posts',
  standalone: true,
 imports:[FormsModule,CommonModule],
  templateUrl: './filter-posts.component.html',
  styleUrl: './filter-posts.component.css'
})
export class FilterPostsComponent {
  filteredPosts: PostResponse[] = [];
  filterParams = {
    content: '',
    author: '',
    creationDate: '', // Format: 'YYYY-MM-DD'
  };

  constructor(private postService: PostService) {}

  applyFilters(): void {
    this.postService.filterPosts(this.filterParams).subscribe((data) => {
      this.filteredPosts = data; // Vul de gefilterde posts
    });
  }
}
