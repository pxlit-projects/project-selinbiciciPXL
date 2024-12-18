import { Component, inject, OnInit } from '@angular/core';
import { PostRequest } from '../../shared/models/postrequest.model';
import { ActivatedRoute, Router} from '@angular/router';
import { PostService } from '../../shared/services/post.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { PostDTO } from '../../shared/models/postdto.model';
import { AsyncPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-post',
  standalone: true,
  imports:[FormsModule,CommonModule, ReactiveFormsModule, AsyncPipe],
  templateUrl: './edit-post.component.html',
  styleUrl: './edit-post.component.css'
})
export class EditPostComponent implements OnInit {

  route: ActivatedRoute = inject(ActivatedRoute);
  id: number= this.route.snapshot.params['id'];
  postService: PostService = inject(PostService);
  post$: Observable<PostDTO> = this.postService.getPostById(this.id)

  fb: FormBuilder = inject(FormBuilder);
  router: Router = inject(Router);

  postForm: FormGroup = this.fb.group({
    content: ['', Validators.required],
  })


  ngOnInit(): void {
    this.post$.subscribe({
      next:(post) => {
        this.postForm.patchValue({
          content: post.content,
        });
      }
    })
  }

  cancelEdit(): void {
    this.router.navigate(['/posts']); // Navigeer terug naar de lijst met posts
  }

    // Haal de huidige gegevens van de post op
    

  OnSubmit(){
    const editPost: PostRequest ={
      ...this.postForm.value
    };

    // Verstuur de wijziging naar de backend
    this.postService.editPost(this.id, editPost).subscribe({
      next:() => {
        this.postForm.reset();
        // Na succesvolle wijziging navigeren we naar de detailpagina van de post
        this.router.navigate(['/posts/' + this.id]);
      },
      error: (err) => {
        console.error('Fout bij het opslaan van de post:', err);
      }
    });

  }
}

