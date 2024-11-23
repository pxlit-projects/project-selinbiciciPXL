import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import { PostRequest } from '../models/post-request.model';
import { PostFilterRequest } from '../models/post-filter-request.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = environment.apiUrl + 'post/api/post';

  constructor(private http: HttpClient) {}

  // USER STORY 1: Create a new post
  createPost(postRequest: PostRequest): Observable<Post> {
    return this.http.post<Post>(this.apiUrl, postRequest);
  }

  // USER STORY 2: Create a draft post
  createDraftPost(postRequest: PostRequest): Observable<Post> {
    return this.http.post<Post>(`${this.apiUrl}/draft`, postRequest);
  }

  // USER STORY 3: Update an existing post
  updatePost(id: number, postRequest: PostRequest): Observable<Post> {
    return this.http.put<Post>(`${this.apiUrl}/${id}`, postRequest);
  }

  // USER STORY 4: Get all published posts
  getPublishedPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.apiUrl);
  }

  // USER STORY 4 (partial update): Publish a post
  publishPost(id: number): Observable<Post> {
    return this.http.patch<Post>(`${this.apiUrl}/${id}/publish`, null);
  }

  // USER STORY 5: Filter posts
  filterPosts(filterRequest: PostFilterRequest): Observable<Post[]> {
    return this.http.post<Post[]>(`${this.apiUrl}/filter`, filterRequest);
  }
}

  
