import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PostRequest } from '../models/postrequest.model';
import { PostResponse } from '../models/postresponse.model';
import { PostDTO } from '../models/postdto.model';
import { Post } from '../models/post.model';
import { Filter } from '../models/filter.model';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = environment.apiUrl + '/post/api/posts';

  constructor(private http: HttpClient) {}

  getAllPosts(): Observable<PostResponse[]> {
    return this.http.get<PostResponse[]>(`${this.apiUrl}`);
  }

  // Haal een post op op basis van ID
  getPostById(id: number): Observable<PostResponse> {
    return this.http.get<PostResponse>(`${this.apiUrl}/${id}`);
  }

  // Methode om een nieuwe post te maken
  createPost(postRequest: PostRequest, userRole: string ): Observable<PostDTO> {
    const headers = new HttpHeaders().set('userRole', userRole).set('Content-Type', 'application/json');
    return this.http.post<PostDTO>(`${this.apiUrl}`, postRequest, { headers });
  }

  editPost(id: number, postRequest: PostRequest): Observable<PostDTO> {
    return this.http.put<PostDTO>(`${this.apiUrl}/${id}`, postRequest);
  }


  submitPost(id: number, userRole: string): Observable<PostDTO> {
    const headers = new HttpHeaders().set('userRole', userRole).set('Content-Type', 'application/json');
    return this.http.post<PostDTO>(`${this.apiUrl}/${id}/submit`, {}, { headers });
  }

  publishPost(id: number, userRole: string): Observable<PostDTO> {
    const headers = new HttpHeaders().set('userRole', userRole).set('Content-Type', 'application/json');
    return this.http.put<PostDTO>(`${this.apiUrl}/${id}/publish`, {}, { headers } );
  }


  getPublishedPosts(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(`${this.apiUrl}/published`);
  }

  getSubmittedPosts(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(`${this.apiUrl}/submitted`);
  }

  getApprovedPosts(): Observable<PostDTO[]> {
    return this.http.get<PostDTO[]>(`${this.apiUrl}/approved`);
  }

   // Haal post met reviews op via id
   getPostWithReviews(id: number): Observable<PostResponse> {
    return this.http.get<PostResponse>(`${this.apiUrl}/${id}/with-reviews`);
  }

  filterPosts(filter: Filter): Observable<Post[]> {
    return this.getAllPosts().pipe(
      map((posts: Post[]) => posts.filter(post => this.isPostMatchingFilter(post, filter)))
    );
  }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.apiUrl);
  }

  public isPostMatchingFilter(post: Post, filter: Filter): boolean {

    // Check voor content
   const matchesContent = filter.content 
    ? (post.content ?? '').toLowerCase().includes(filter.content.toLowerCase()) 
    : true;

    // Check voor author
   const matchesAuthor = filter.author 
    ? (post.author ?? '').toLowerCase().includes(filter.author.toLowerCase()) 
    : true;

   const matchesCreatedAt = filter.createdDate === ""? true : this.checkDate(new Date(post.createdDate), new Date(filter.createdDate));


    return matchesContent && matchesAuthor && matchesCreatedAt;
  }

  public checkDate(postDate: Date, filterDate: Date): boolean {
    
    // Vergelijk dag, maand en jaar
    const matchesDay = postDate.getDate() === filterDate.getDate();
    const matchesMonth = postDate.getMonth() === filterDate.getMonth();
    const matchesYear = postDate.getFullYear() === filterDate.getFullYear();
  
    // Retourneer het resultaat van de vergelijking
    return matchesDay && matchesMonth && matchesYear;
    
  }

  
}

