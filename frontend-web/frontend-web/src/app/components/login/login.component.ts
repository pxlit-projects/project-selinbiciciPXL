import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [MatCardModule, MatInputModule, MatButtonModule, FormsModule, ReactiveFormsModule],
})
export class LoginComponent {
  email = '';
  password = '';
  loginError = false;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    const success = this.authService.login(this.email, this.password);
    if (success) {
      const role = this.authService.getRole();
      if (role === 'redacteur') {
        this.router.navigate(['/posts/new']);
      } else if (role === 'gebruiker') {
        this.router.navigate(['/posts/published']);
      }
    } else {
      this.loginError = true;
    }
  }
}

