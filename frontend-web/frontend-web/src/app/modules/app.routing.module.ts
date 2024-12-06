import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../components/login/login.component";
import { NgModule } from "@angular/core";
import { PostComponent } from "../components/post/post.component";


const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'posts', component: PostComponent }
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}

