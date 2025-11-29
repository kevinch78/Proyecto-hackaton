import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from '../shared/components/sidebar/sidebar.component';
import { HeaderComponent } from '../shared/components/header/header.component';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [RouterOutlet, SidebarComponent, HeaderComponent],
  template: `
    <div
      class="min-h-screen bg-slate-950 text-slate-200 font-sans selection:bg-emerald-500/30"
    >
      <app-sidebar />

      <div class="pl-64 flex flex-col min-h-screen">
        <app-header />

        <main class="flex-1 p-8 overflow-y-auto">
          <div class="max-w-7xl mx-auto">
            <router-outlet />
          </div>
        </main>
      </div>
    </div>
  `,
})
export class MainLayoutComponent {}
