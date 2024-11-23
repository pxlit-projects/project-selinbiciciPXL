import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = environment.apiUrl + 'review/api/review';

  constructor(private http: HttpClient) { }

   // Haal een post op
   viewPost(postId: number): Observable<Post> {
    return this.http.get<Post>(`${this.apiUrl}/view/${postId}`);
  }

  // Goedkeuren van een post
  approvePost(postId: number): Observable<Post> {
    return this.http.post<Post>(`${this.apiUrl}/approve/${postId}`, {});
  }

  // Afwijzen van een post met commentaar
  rejectPost(postId: number, comment: string): Observable<Post> {
    return this.http.post<Post>(`${this.apiUrl}/reject/${postId}`, { comment });
  }

  // Haal goedgekeurde posts op
  getApprovedPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/approved`);
  }

  // Haal afgewezen posts op
  getRejectedPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/rejected`);
  }
}

