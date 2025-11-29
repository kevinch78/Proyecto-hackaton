import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  template: `
    <div class="min-h-screen bg-slate-950 flex items-center justify-center p-4">
      <div
        class="max-w-md w-full bg-slate-900 rounded-2xl shadow-2xl border border-slate-800 p-8"
      >
        <div class="text-center mb-8">
          <h1 class="text-3xl font-bold text-white tracking-wider mb-2">
            RECRUITER<span class="text-emerald-500">.AI</span>
          </h1>
          <p class="text-slate-400">Crea tu cuenta nueva</p>
        </div>

        <form
          [formGroup]="registerForm"
          (ngSubmit)="onSubmit()"
          class="space-y-4"
        >
          <div class="grid grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-slate-300 mb-2"
                >Nombre</label
              >
              <input
                type="text"
                formControlName="firstName"
                class="w-full bg-slate-950 border border-slate-800 rounded-lg px-4 py-3 text-slate-200 focus:outline-none focus:border-emerald-500 focus:ring-1 focus:ring-emerald-500 transition-colors"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-slate-300 mb-2"
                >Apellido</label
              >
              <input
                type="text"
                formControlName="lastName"
                class="w-full bg-slate-950 border border-slate-800 rounded-lg px-4 py-3 text-slate-200 focus:outline-none focus:border-emerald-500 focus:ring-1 focus:ring-emerald-500 transition-colors"
              />
            </div>
          </div>

          <div>
            <label class="block text-sm font-medium text-slate-300 mb-2"
              >Email</label
            >
            <input
              type="email"
              formControlName="email"
              class="w-full bg-slate-950 border border-slate-800 rounded-lg px-4 py-3 text-slate-200 focus:outline-none focus:border-emerald-500 focus:ring-1 focus:ring-emerald-500 transition-colors"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-slate-300 mb-2"
              >Contraseña</label
            >
            <input
              type="password"
              formControlName="password"
              class="w-full bg-slate-950 border border-slate-800 rounded-lg px-4 py-3 text-slate-200 focus:outline-none focus:border-emerald-500 focus:ring-1 focus:ring-emerald-500 transition-colors"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-slate-300 mb-2"
              >Soy un:</label
            >
            <div class="grid grid-cols-2 gap-4">
              <label class="cursor-pointer">
                <input
                  type="radio"
                  formControlName="role"
                  value="CANDIDATE"
                  class="peer sr-only"
                />
                <div
                  class="text-center py-3 rounded-lg border border-slate-800 bg-slate-950 text-slate-400 peer-checked:border-emerald-500 peer-checked:text-emerald-400 peer-checked:bg-emerald-500/10 transition-all"
                >
                  Candidato
                </div>
              </label>
              <label class="cursor-pointer">
                <input
                  type="radio"
                  formControlName="role"
                  value="COMPANY"
                  class="peer sr-only"
                />
                <div
                  class="text-center py-3 rounded-lg border border-slate-800 bg-slate-950 text-slate-400 peer-checked:border-emerald-500 peer-checked:text-emerald-400 peer-checked:bg-emerald-500/10 transition-all"
                >
                  Empresa
                </div>
              </label>
            </div>
          </div>

          @if (errorMessage()) {
          <div
            class="p-3 bg-red-500/10 border border-red-500/20 rounded-lg text-red-400 text-sm text-center"
          >
            {{ errorMessage() }}
          </div>
          }

          <button
            type="submit"
            [disabled]="registerForm.invalid || isLoading()"
            class="w-full bg-emerald-600 hover:bg-emerald-500 text-white font-medium py-3 rounded-lg transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
          >
            @if (isLoading()) {
            <span class="inline-block animate-spin mr-2">⟳</span> Cargando... }
            @else { Registrarse }
          </button>
        </form>

        <div class="mt-6 text-center text-sm text-slate-400">
          ¿Ya tienes cuenta?
          <a
            routerLink="/login"
            class="text-emerald-400 hover:text-emerald-300 font-medium"
            >Inicia Sesión</a
          >
        </div>
      </div>
    </div>
  `,
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);

  registerForm = this.fb.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    role: ['CANDIDATE', [Validators.required]],
  });

  isLoading = signal(false);
  errorMessage = signal('');

  onSubmit() {
    if (this.registerForm.valid) {
      this.isLoading.set(true);
      this.errorMessage.set('');

      const val = this.registerForm.value;

      this.authService
        .register({
          firstName: val.firstName!,
          lastName: val.lastName!,
          email: val.email!,
          password: val.password!,
          role: val.role as 'CANDIDATE' | 'COMPANY',
        })
        .subscribe({
          next: () => {
            // Navigation handled in service
          },
          error: (err) => {
            this.isLoading.set(false);
            this.errorMessage.set('Error al registrarse. Intente nuevamente.');
            console.error(err);
          },
        });
    }
  }
}
