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

    login({ username, password }: UserRequest): User | null {
      const user = this.users.find(u => u.username === username);
  
      return user && user.password === password ? user : null;
    }

    getUserById(id: number): User | undefined {
      return this.users.find(u => u.id === id);
    }
  





 
}
