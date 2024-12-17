import { Component, inject, Input } from '@angular/core';
import { Post } from '../../shared/models/post.model';
import { CommonModule} from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

//Dit component zal verantwoordelijk zijn voor het weergeven van één bericht (post).

@Component({
  selector: 'app-post-item',
  standalone:true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './post-item.component.html',
  styleUrl: './post-item.component.css'
})
export class PostItemComponent {
  @Input() post!: Post; // Ontvangt de Post object van de bovenliggende component

  getDetails(): void{
    console.log(this.post);
  }

}


