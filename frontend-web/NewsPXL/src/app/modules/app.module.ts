import { NgModule } from '@angular/core';
import { AppComponent } from '../app.component';
import { BrowserModule } from '@angular/platform-browser';
import { AddPostComponent } from '../components/add-post/add-post.component';
import { LoginComponent } from '../components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
    declarations: [
      AppComponent,
      AddPostComponent,
      LoginComponent,
    
 
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule
    ],

    bootstrap: [AppComponent]
  })
  export class AppModule { }