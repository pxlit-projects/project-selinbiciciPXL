
import { LoginComponent } from './components/login/login.component';

import { CreatePostComponent } from './components/create-post/create-post.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { EditPostComponent } from './components/edit-post/edit-post.component';
import { PostsComponent } from './components/posts/posts.component';
import { PublishedPostsComponent } from './components/published-posts/published-posts.component';
import { FilterPostsComponent } from './components/filter-posts/filter-posts.component';
import { authGuard } from './shared/services/auth.guard';
import { Routes } from '@angular/router';
import { PageNotAllowedComponent } from './components/page-not-allowed/page-not-allowed.component';
import { confirmLeaveGuard } from './shared/services/confirm-leave.guard';
import { ReviewPostComponent } from './components/review-post/review-post.component';


export const routes: Routes = [
  { path: 'posts', component: PostsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'posts/:id', component: PostDetailsComponent},
  { path: 'create', component: CreatePostComponent, canActivate:[authGuard], data: { role: 'editor' }, canDeactivate: [confirmLeaveGuard]},
  { path: 'edit/:id', component: EditPostComponent, canActivate:[authGuard], data: { role: 'editor' }, canDeactivate: [confirmLeaveGuard]},
  { path: 'published', component: PublishedPostsComponent}, // Route voor getPublishedPosts
  { path: 'filter', component: FilterPostsComponent },
  { path: 'submitted', component: ReviewPostComponent }, // Route voor filterPosts
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: PageNotAllowedComponent},
  { path: 'pageNotAllowed', component: PageNotAllowedComponent},



 
];