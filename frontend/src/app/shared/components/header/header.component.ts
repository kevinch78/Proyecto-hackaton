import { Component, inject } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  template: `
    <header
      class="h-16 bg-slate-900/50 backdrop-blur-md border-b border-slate-800 flex items-center justify-between px-8 sticky top-0 z-10"
    >
      <h2 class="text-lg font-medium text-slate-200">
        Bienvenido,
        <span class="text-emerald-400">{{
          authService.currentUser()?.firstName
        }}</span>
      </h2>

      <div class="flex items-center space-x-4">
        <div
          class="h-8 w-8 rounded-full bg-emerald-500/20 flex items-center justify-center text-emerald-400 font-bold"
        >
          {{ authService.currentUser()?.firstName?.charAt(0) }}
        </div>
      </div>
    </header>
  `,
})
export class HeaderComponent {
  authService = inject(AuthService);
}
