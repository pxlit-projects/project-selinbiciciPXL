import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { PostService } from '../../shared/services/post.service';


@Component({
  selector: 'app-add-post',
  standalone: false,
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.css'
})
export class AddPostComponent {
  title: string = '';
  content: string = '';
  status: string = 'DRAFT';

  constructor(private postService: PostService, private router: Router) {}

  savePost() {
    const post = { title: this.title, content: this.content, author: 'editor', status: this.status };
    this.postService.createPost(post).subscribe(() => {
      this.router.navigate(['/posts']);
    });
  }
}
