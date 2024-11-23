import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = environment.apiUrl + 'comment/api/comment';

  constructor(private http: HttpClient) { }

    // Comment toevoegen
    addComment(postId: number, userId: number, content: string): Observable<Comment> {
      return this.http.post<Comment>(`${this.apiUrl}/post/${postId}/user/${userId}`, content);
    }
  
    // Reacties van een post ophalen
    getCommentsByPost(postId: number): Observable<Comment[]> {
      return this.http.get<Comment[]>(`${this.apiUrl}/post/${postId}`);
    }

    // Reactie bewerken
  updateComment(commentId: number, userId: number, newContent: string): Observable<Comment> {
    return this.http.put<Comment>(`${this.apiUrl}/${commentId}/user/${userId}`, newContent);
  }

  // Reactie verwijderen
  deleteComment(commentId: number, userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${commentId}/user/${userId}`);
  }
}
