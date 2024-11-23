import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../components/login/login.component";
import { NgModule } from "@angular/core";
import { PostComponent } from "../components/post/post.component";
import { PosteditorComponent } from "../components/posteditor/posteditor.component";
import { PostfilterComponent } from "../components/postfilter/postfilter.component";
import { ReviewComponent } from "../components/review/review.component";

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'posts', component: PostComponent },
    { path: 'posts/edit', component: PosteditorComponent },
    { path: 'posts/filter', component: PostfilterComponent },
    { path: 'posts/review/:id', component: ReviewComponent },
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}

