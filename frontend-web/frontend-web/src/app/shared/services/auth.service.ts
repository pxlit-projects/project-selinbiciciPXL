import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn = false;
  private userRole: 'redacteur' | 'gebruiker' | null = null;

  constructor(private router: Router) {}

  login(email: string, password: string): boolean {
    // Dummy logic for login, replace with actual API call
    if (email === 'redacteur@example.com' && password === 'password') {
      this.loggedIn = true;
      this.userRole = 'redacteur';
      return true;
    } else if (email === 'gebruiker@example.com' && password === 'password') {
      this.loggedIn = true;
      this.userRole = 'gebruiker';
      return true;
    } else {
      return false;
    }
  }

  logout() {
    this.loggedIn = false;
    this.userRole = null;
    this.router.navigate(['/auth/login']);
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  getRole(): 'redacteur' | 'gebruiker' | null {
    return this.userRole;
  }
}
