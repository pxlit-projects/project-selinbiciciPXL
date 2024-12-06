import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../../shared/services/post.service';

@Component({
  selector: 'app-edit-post',
  standalone: true,
  imports: [MatCardModule, MatInputModule, MatButtonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './edit-post.component.html',
  styleUrl: './edit-post.component.css'
})
export class EditPostComponent implements OnInit{

  post: any = {
    title: '',
    content: '',
    author: '',
    status: '',
  };

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
     const postId = this.route.snapshot.params['id'];
    this.postService.getPostById(postId).subscribe((data) => {
      this.post = data;
    });
  }

  onSubmit() {
    const postId = this.route.snapshot.params['id'];
    this.postService.editPost(postId, this.post).subscribe(() => {
      alert('Post updated successfully!');
      this.router.navigate(['/posts/published']);
    });
  }

}
