import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';
import { ReviewResponse } from '../models/reviewresponse.model';
import { ReviewRequest } from '../models/reviewrequest.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = environment.apiUrl + '/review/api/reviews';

  constructor(private http: HttpClient) {}

  /**
   * Haalt reviews op basis van een post ID.
   */
  getReviewsByPostId(postId: number): Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`${this.apiUrl}/post/${postId}`);
  }

  /**
   * Goedkeuren van een review.
   */
  approveReview(reviewRequest: ReviewRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/approve`, reviewRequest);
  }

  /**
   * Afwijzen van een review.
   */
  rejectReview(reviewRequest: ReviewRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/reject`, reviewRequest);
  }


}
