import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports:[RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  showNavbar: boolean = true; // Standaard toont de navbar
  private authService = inject(AuthService);
  private router = inject(Router);
  

  ngOnInit(): void {
  
  }

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