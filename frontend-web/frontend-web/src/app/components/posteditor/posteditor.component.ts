import { Component, OnInit } from '@angular/core';
import { PostService } from '../../shared/services/post.service';
import { Post } from '../../shared/models/post.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-posteditor',
  imports: [CommonModule, FormsModule], //FormsModule = Two-Way Data Binding van Angula
  templateUrl: './posteditor.component.html',
  styleUrl: './posteditor.component.css'
})
export class PosteditorComponent implements OnInit{

  post: Post | null = null;

  constructor(private postService: PostService) {}

  ngOnInit(): void {
     // Simuleer ophalen van post (vervang ID door dynamisch ophalen via router bijv.)
     this.postService.getPublishedPosts().subscribe(posts => {
      this.post = posts[0]; // Eerste post om te bewerken
    });
  }

  savePost(): void {
    if (this.post && this.post.id) {
      this.postService.updatePost(this.post.id, this.post).subscribe(updated => {
        console.log('Post updated:', updated);
      });
    }
  }

}
