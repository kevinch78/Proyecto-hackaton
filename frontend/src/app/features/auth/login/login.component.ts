import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service'; // SOLUCIÓN 1: Ajuste de ruta (Asumiendo que es '../../../')
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  template: `
    <div class="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div class="w-full max-w-md bg-white p-8 rounded-xl shadow-2xl space-y-6">
        <h1 class="text-3xl font-extrabold text-gray-900 text-center mb-6">
          Iniciar Sesión
        </h1>

        <form [formGroup]="loginForm" (ngSubmit)="onSubmit()" class="space-y-4">
          <!-- Campo de Usuario/Email -->
          <div>
            <label for="email" class="block text-sm font-medium text-gray-700">
              Usuario/Email
            </label>
            <input
              id="email"
              type="text"
              formControlName="email"
              placeholder="Tu nombre de usuario o email"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
            <div
              *ngIf="
                loginForm.get('email')?.invalid &&
                loginForm.get('email')?.touched
              "
              class="text-red-600 text-xs mt-1"
            >
              Usuario/Email es requerido.
            </div>
          </div>

          <!-- Campo de Contraseña -->
          <div>
            <label
              for="password"
              class="block text-sm font-medium text-gray-700"
            >
              Contraseña
            </label>
            <input
              id="password"
              type="password"
              formControlName="password"
              placeholder="Mínimo 6 caracteres"
              class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
            <div
              *ngIf="
                loginForm.get('password')?.invalid &&
                loginForm.get('password')?.touched
              "
              class="text-red-600 text-xs mt-1"
            >
              Contraseña es requerida (mínimo 6 caracteres).
            </div>
          </div>

          <!-- Mensaje de Error (si existe) -->
          <div
            *ngIf="errorMessage()"
            class="p-3 bg-red-100 text-red-700 border border-red-200 rounded-md text-sm"
          >
            {{ errorMessage() }}
          </div>

          <!-- Botón de Enviar -->
          <button
            type="submit"
            [disabled]="loginForm.invalid || loading()"
            class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition duration-150 ease-in-out disabled:opacity-50"
          >
            <span *ngIf="!loading()">Iniciar Sesión</span>
            <span *ngIf="loading()" class="flex items-center">
              Cargando...
              <svg
                class="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
              >
                <circle
                  class="opacity-25"
                  cx="12"
                  cy="12"
                  r="10"
                  stroke="currentColor"
                  stroke-width="4"
                ></circle>
                <path
                  class="opacity-75"
                  fill="currentColor"
                  d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
                ></path>
              </svg>
            </span>
          </button>
        </form>

        <p class="text-center text-sm text-gray-600">
          ¿No tienes una cuenta?
          <a
            [routerLink]="['/register']"
            class="font-medium text-indigo-600 hover:text-indigo-500"
            >Regístrate aquí</a
          >
        </p>
      </div>
    </div>
  `,
})
export class LoginComponent implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  private readonly fb = inject(FormBuilder);

  loginForm!: FormGroup;
  loading = signal(false);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading.set(true);
    this.errorMessage.set(null);

    // SOLUCIÓN 2: Desestructuración segura y uso de `email`
    const { email, password } = this.loginForm.value;

    this.authService.login({ email: email, password: password }).subscribe({
      next: () => {
        // Redirección manejada por el AuthService con el `tap`
        this.loading.set(false);
      },
      // SOLUCIÓN 3: Tipificación del error como HttpErrorResponse
      error: (err: HttpErrorResponse) => {
        console.error('Login error:', err);
        let msg = 'Error al iniciar sesión. Inténtalo de nuevo.';
        if (err.status === 401) {
          msg = 'Credenciales inválidas. Verifica tu usuario y contraseña.';
        } else if (err.error?.message) {
          msg = err.error.message;
        }
        this.errorMessage.set(msg);
        this.loading.set(false);
      },
    });
  }
}
