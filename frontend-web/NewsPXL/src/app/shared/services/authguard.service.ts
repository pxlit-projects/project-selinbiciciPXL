import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true; // Gebruiker is ingelogd, toegang is toegestaan
    } else {
      // Gebruiker is niet ingelogd, doorverwijzen naar loginpagina
      this.router.navigate(['/login']);
      return false;
    }
  }

  
}
