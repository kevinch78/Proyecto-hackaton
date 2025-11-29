import { Component, inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
    <div class="space-y-6">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
        <!-- Stats Card 1 -->
        <div class="bg-slate-900 border border-slate-800 rounded-xl p-6">
          <div class="flex items-center justify-between mb-4">
            <h3 class="text-slate-400 font-medium">Total Postulaciones</h3>
            <div class="p-2 bg-emerald-500/10 rounded-lg text-emerald-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                />
              </svg>
            </div>
          </div>
          <p class="text-3xl font-bold text-white">12</p>
          <p class="text-sm text-emerald-400 mt-2">+2 esta semana</p>
        </div>

        <!-- Stats Card 2 -->
        <div class="bg-slate-900 border border-slate-800 rounded-xl p-6">
          <div class="flex items-center justify-between mb-4">
            <h3 class="text-slate-400 font-medium">Entrevistas</h3>
            <div class="p-2 bg-blue-500/10 rounded-lg text-blue-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
                />
              </svg>
            </div>
          </div>
          <p class="text-3xl font-bold text-white">3</p>
          <p class="text-sm text-blue-400 mt-2">Próxima: Mañana</p>
        </div>

        <!-- Stats Card 3 -->
        <div class="bg-slate-900 border border-slate-800 rounded-xl p-6">
          <div class="flex items-center justify-between mb-4">
            <h3 class="text-slate-400 font-medium">Perfil</h3>
            <div class="p-2 bg-purple-500/10 rounded-lg text-purple-400">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                />
              </svg>
            </div>
          </div>
          <p class="text-3xl font-bold text-white">85%</p>
          <p class="text-sm text-purple-400 mt-2">Completado</p>
        </div>
      </div>

      <!-- Recent Activity -->
      <div class="bg-slate-900 border border-slate-800 rounded-xl p-6">
        <h3 class="text-lg font-bold text-white mb-4">Actividad Reciente</h3>
        <div class="space-y-4">
          <div
            class="flex items-center justify-between p-4 bg-slate-950/50 rounded-lg border border-slate-800"
          >
            <div class="flex items-center space-x-4">
              <div
                class="h-10 w-10 rounded-full bg-emerald-500/20 flex items-center justify-center text-emerald-400"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-5 w-5"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </div>
              <div>
                <p class="text-white font-medium">Postulación Enviada</p>
                <p class="text-sm text-slate-400">
                  Senior Frontend Developer en TechCorp
                </p>
              </div>
            </div>
            <span class="text-xs text-slate-500">Hace 2 horas</span>
          </div>

          <div
            class="flex items-center justify-between p-4 bg-slate-950/50 rounded-lg border border-slate-800"
          >
            <div class="flex items-center space-x-4">
              <div
                class="h-10 w-10 rounded-full bg-blue-500/20 flex items-center justify-center text-blue-400"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-5 w-5"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                  />
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                  />
                </svg>
              </div>
              <div>
                <p class="text-white font-medium">Perfil Visto</p>
                <p class="text-sm text-slate-400">
                  Recruiter de InnovaSoft vio tu perfil
                </p>
              </div>
            </div>
            <span class="text-xs text-slate-500">Ayer</span>
          </div>
        </div>
      </div>
    </div>
  `,
})
export class DashboardComponent {
  authService = inject(AuthService);
}
