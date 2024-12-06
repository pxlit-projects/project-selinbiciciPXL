import { Component, OnInit } from '@angular/core';
import { Post } from '../../shared/models/post.model';
import { PostService } from '../../shared/services/post.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-post',
  imports: [CommonModule],
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
//Je kunt gepubliceerde posts ophalen en weergeven in een lijst.
export class PostComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostService, public authService: AuthService) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
