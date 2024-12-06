import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import { PostRequest } from '../models/post-request.model';


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

  
}

  
