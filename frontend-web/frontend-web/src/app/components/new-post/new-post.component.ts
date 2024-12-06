import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PostService } from '../../shared/services/post.service';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-new-post',
  standalone: true, 
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css'],
  imports: [MatCardModule, MatInputModule, MatButtonModule, FormsModule, ReactiveFormsModule]
})
export class NewPostComponent {
  post = {
    title: '',
    content: '',
    author: '',
    status: 'DRAFT',
  };

  constructor(private postService: PostService, private router: Router) {}

  // Publiceer de post
  onSubmit() {
    this.post.status = 'PUBLISHED';
    this.postService.createPost(this.post).subscribe(() => {
      alert('Post published successfully!');
      this.router.navigate(['/posts/published']);
    });
  }

  // Sla de post op als concept
  saveDraft() {
    this.post.status = 'DRAFT';
    this.postService.createPost(this.post).subscribe(() => {
      alert('Draft saved successfully!');
      this.router.navigate(['/posts/drafts']);
    });
  }
}

