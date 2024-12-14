import { Component, EventEmitter, inject, Output } from '@angular/core';
import { PostService } from '../../shared/services/post.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Post } from '../../shared/models/post.model';
import { PostRequest } from '../../shared/models/postrequest.model';
import { PostDTO } from '../../shared/models/postdto.model';

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports:[FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent {
  postService: PostService = inject(PostService);
  router: Router = inject(Router);
  fb: FormBuilder = inject(FormBuilder);
  

  postForm:  FormGroup = this.fb.group({
    title: ['', Validators.required],
    content: ['', [Validators.required]],
    author: ['', [Validators.required]],
    
  });
  onSubmit() {
    const newPost: PostRequest = {
      ...this.postForm.value,    
    };

    // Verzenden naar de server via PostService
    this.postService.createPost(newPost).subscribe(() => {
        this.postForm.reset();
        this.router.navigate(['/posts']);
      });
   }
}





