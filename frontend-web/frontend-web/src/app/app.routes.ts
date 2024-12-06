import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EditPostComponent } from './components/edit-post/edit-post.component';
import { PublishedPostsComponent } from './components/published-posts/published-posts.component';
import { NewPostComponent } from './components/new-post/new-post.component';
import { AuthGuard } from './shared/services/auth.guard';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'posts/edit/:id', component: EditPostComponent },
    { path: 'posts/published', component: PublishedPostsComponent }, // Public
    { path: 'posts/new', component: NewPostComponent, canActivate: [AuthGuard], data: { role: 'redacteur' } 
    },
    
    { path: 'auth/login', component: LoginComponent }, 
    { path: '**', redirectTo: 'auth/login' },

];
