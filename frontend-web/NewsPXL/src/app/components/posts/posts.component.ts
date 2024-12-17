import { Component, inject, Input, OnInit } from '@angular/core';
import { PostResponse } from '../../shared/models/postresponse.model';
import { PostService } from '../../shared/services/post.service';
import { RouterLink } from '@angular/router';
import { AsyncPipe, CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

import { Filter } from '../../shared/models/filter.model';
import { Post } from '../../shared/models/post.model';
import { FilterPostsComponent } from "../filter-posts/filter-posts.component";
import { PostItemComponent } from "../post-item/post-item.component";

@Component({
  selector: 'app-posts',
  standalone:true,
  imports: [CommonModule, FilterPostsComponent, PostItemComponent, AsyncPipe],
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.css'
})
export class PostsComponent implements OnInit {
  postService: PostService = inject(PostService);
  filteredData$!: Observable<Post[]>;
 

  ngOnInit(): void {
    this.fetchData(); 
 
  }

  handleFilter(filter: Filter){
    this.filteredData$ = this.postService.filterPosts(filter);
  }

  fetchData(): void {
    this.filteredData$ = this.postService.getPosts();
  }
}
