import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn:'root',
})

export class authGuard implements CanActivate {

private authService = inject(AuthService);
private router = inject(Router);

canActivate(
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): boolean {
  // Controleer of de gebruiker is ingelogd
  if (this.authService.isAuthenticated()) {
    // Optioneel: Controleer of de gebruiker een specifieke rol heeft
    const requiredRole = route.data['role'];
    if (requiredRole && this.authService.getRole() !== requiredRole) {
      // Als de gebruiker geen toegang heeft tot deze rol, ga dan naar een foutpagina of een andere route
      this.router.navigate(['/pageNotAllowed']);
      return false;
    }
    
    return true; // Gebruiker is ingelogd en heeft toegang
  } else {
    // Als de gebruiker niet is ingelogd, stuur deze door naar de loginpagina
    this.router.navigate(['/login']);
    return false; // Geen toegang
  }
}




}

