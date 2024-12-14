
import { LoginComponent } from './components/login/login.component';

import { CreatePostComponent } from './components/create-post/create-post.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { EditPostComponent } from './components/edit-post/edit-post.component';
import { PostsComponent } from './components/posts/posts.component';
import { PublishedPostsComponent } from './components/published-posts/published-posts.component';
import { FilterPostsComponent } from './components/filter-posts/filter-posts.component';
import { AuthguardService } from './shared/services/authguard.service';
import { Routes } from '@angular/router';


export const routes: Routes = [
  { path: 'posts', component: PostsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'details/:id', component: PostDetailsComponent, canActivate:[AuthguardService]},
  { path: 'create', component: CreatePostComponent, canActivate:[AuthguardService]},
  { path: 'edit/:id', component: EditPostComponent, canActivate:[AuthguardService]},
  { path: 'published', component: PublishedPostsComponent }, // Route voor getPublishedPosts
  { path: 'filter', component: FilterPostsComponent }, // Route voor filterPosts
  { path: '', redirectTo: '/login', pathMatch: 'full' },

 
];