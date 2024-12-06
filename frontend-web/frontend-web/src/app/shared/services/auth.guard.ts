import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const role = route.data['role'];
    const userHasRole = this.checkUserRole(role); // Dit moet je controleren

    console.log('User role check:', role, 'User has role:', userHasRole);

    if (userHasRole) {
      return true;  // De gebruiker heeft de juiste rol
    } else {
      this.router.navigate(['/auth/login']); // Of een andere foutpagina
      return false;
    }
  }

  checkUserRole(role: string): boolean {
    // Stel een eenvoudige mockfunctie in om de rol van de gebruiker te controleren
    return role === 'redacteur'; // Hier kun je je eigen logica voor rolverificatie toevoegen
  }
}
