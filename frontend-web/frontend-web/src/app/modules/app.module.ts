import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 

// Importeren van Angular Material-modules
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';

// Importeren van je componenten
 // Je standalone AppComponent

import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from '../app.component';
@NgModule({
  declarations: [AppComponent],

  imports: [
    BrowserModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule
  ],
  providers: [],
})
export class AppModule { }

// Hier gebruik je bootstrapApplication om de app te starten
bootstrapApplication(AppComponent);
