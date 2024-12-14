import { Component } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports:[RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private authService: AuthService, private router: Router) {}

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);  // Redirect naar login na logout
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated(); // Check of gebruiker is ingelogd
  }

  getRole(): string | null {
    return this.authService.getRole(); // Ophalen van de rol van de gebruiker
  }
}
