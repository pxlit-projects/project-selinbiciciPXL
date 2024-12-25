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



  

    // Goedkeuren van een post
    approvePost(id: number): Observable<PostRequest> {
      return this.http.post<PostRequest>(`${this.apiUrl}/${id}/approve`, {});
    }





 




}
