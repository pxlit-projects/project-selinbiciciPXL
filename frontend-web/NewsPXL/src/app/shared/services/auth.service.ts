import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { UserRequest } from '../models/userrequest.model';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private users: User[]= [
     { id: 1, username: 'milan', name: 'Milan Dewaele', password: 'ed123', role: 'editor' },
     { id: 2, username: 'tom', name: 'Tom Jacobs', password: 'us123', role: 'user' }
    ]

    login({ username, password }: UserRequest): boolean {
      const user = this.users.find(u => u.username === username);
  
      if (user && user.password === password) {
        localStorage.setItem('userId', user.id.toString());
        localStorage.setItem('username', user.username);
        localStorage.setItem('role', user.role); // Sla ook de rol op, handig voor rolgebaseerde toegang
        return true; // Login succesvol
      }
  
      return false; // Fout bij login
    }

    logout(): void {
      localStorage.removeItem('userId');
      localStorage.removeItem('username');
      localStorage.removeItem('role');
    }
  
    getUserById(id: number): User | undefined {
      return this.users.find(u => u.id === id);
    }
  
    getLoggedInUser(): User | null {
      const userId = localStorage.getItem('userId');
      if (userId) {
        return this.getUserById(Number(userId)) || null;
      }
      return null;
    }
  
    isAuthenticated(): boolean {
      return localStorage.getItem('userId') !== null;
    }
  
    getRole(): string | null {
      return localStorage.getItem('role');
    }
}
 

