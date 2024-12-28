import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';
import { ReviewResponse } from '../models/reviewresponse.model';
import { ReviewRequest } from '../models/reviewrequest.model';
import { PostRequest } from '../models/postrequest.model';
import { PostDTO } from '../models/postdto.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = environment.apiUrl + '/review/api/reviews';

  constructor(private http: HttpClient) {}

  // Haal de reviews op voor een specifieke post
  getReviewsByPostId(postId: number): Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`${this.apiUrl}/${postId}`);
  }

  approvePost(postId: number, author: string): Observable<any> {
    const body = { postId, author};
    return this.http.put(`${this.apiUrl}/approve`, body);
  }

  rejectPost(postId: number, author:string): Observable<any>{
    const body = {postId, author};
    return this.http.put(`${this.apiUrl}/reject`, body);
  }











 




}
