import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  public router =inject(Router);
  title = 'news-app';

  shouldShowNavbar(): boolean {
    // Verberg de navbar op specifieke routes
    const hiddenRoutes = ['/pageNotAllowed', '/login'];
    return !hiddenRoutes.includes(this.router.url);
  }
}
