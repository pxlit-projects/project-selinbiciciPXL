import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface PostRequest {
  title: string;
  content: string;
  author: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = 'http://localhost:8081/api/posts';

  constructor(private http: HttpClient) {}

  createPost(post: PostRequest): Observable<any> {
    return this.http.post(this.apiUrl, post);
  }
}
