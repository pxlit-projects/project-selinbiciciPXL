import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-login',
  standalone:true,
  imports:[CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup; // Formulier voor login
  isLoading = false; // Voor het tonen van de laadstatus

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}

    ngOnInit(): void {
      this.loginForm = this.fb.group({
        username: ['', [Validators.required]],
        password: ['', [Validators.required]]
      });
    }
  
    onSubmit(): void {
      if (this.loginForm.invalid) {
        return;
      }
  
      const { username, password } = this.loginForm.value;
      this.isLoading = true;
  
      if (this.authService.login({ username, password })) {
        this.isLoading = false;
        this.router.navigate(['/posts']); // Naar posts pagina na succesvol inloggen
      } else {
        this.isLoading = false;
        alert('Inloggen mislukt! Controleer je gebruikersnaam en wachtwoord.');
      }
    }

}


