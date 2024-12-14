import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PostRequest } from '../models/postrequest.model';
import { PostResponse } from '../models/postresponse.model';
import { PostDTO } from '../models/postdto.model';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = environment.apiUrl + '/post/api/posts';

  constructor(private http: HttpClient) {}

  getAllPosts(): Observable<PostResponse[]> {
    return this.http.get<PostResponse[]>(`${this.apiUrl}`);
  }

  getPostById(id: number): Observable<PostResponse> {
    return this.http.get<PostResponse>(`${this.apiUrl}/${id}`);
  }

  // Methode om een nieuwe post te maken
  createPost(postRequest: PostRequest): Observable<PostDTO> {
    return this.http.post<PostDTO>(`${this.apiUrl}`, postRequest);
  }

  editPost(id: number, postRequest: PostRequest): Observable<PostDTO> {
    return this.http.put<PostDTO>(`${this.apiUrl}/${id}`, postRequest);
  }

  getPublishedPosts(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(`${this.apiUrl}/published`);
  }

  filterPosts(filterParams: { content?: string; author?: string; creationDate?: string }): Observable<PostResponse[]> {
    return this.http.get<PostResponse[]>(`${this.apiUrl}/filter`, { params: filterParams });
  }
}

