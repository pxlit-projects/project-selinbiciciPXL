import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userRole: 'EDITOR' | 'USER' = 'USER';

  login(role: 'EDITOR' | 'USER'): void {
    this.userRole = role;
  }

  getRole(): 'EDITOR' | 'USER' {
    return this.userRole;
  }

  isEditor(): boolean {
    return this.userRole === 'EDITOR';
  }

   // Laad rol bij init
   loadRoleFromStorage(): void {
    const role = localStorage.getItem('role');
    if (role) {
      this.userRole = role as 'EDITOR' | 'USER';
    }
  }

  // Uitloggen
  logout(): void {
    this.userRole = 'USER';
    localStorage.removeItem('role');
  }
}
