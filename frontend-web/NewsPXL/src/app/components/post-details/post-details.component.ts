import { Component, inject, OnInit } from '@angular/core';
import { PostResponse } from '../../shared/models/postresponse.model';
import { ActivatedRoute, RouterLink} from '@angular/router';
import { PostService } from '../../shared/services/post.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-post-details',
  standalone:true,
   imports:[CommonModule, RouterLink],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit {

  post: PostResponse | null = null;

  private route = inject(ActivatedRoute);
  private postService = inject(PostService);
  private authService = inject(AuthService);


  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.postService.getPostById(id).subscribe((data) => {
      this.post = data;
    });
  }


  canEdit():boolean{
    return this.authService.getRole()=== 'editor';
  }



 
}
