import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter,RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(),
    importProvidersFrom(ReactiveFormsModule,CommonModule,RouterModule),
    importProvidersFrom(FormsModule), // Asegúrate de importar FormsModule aquí
  ],
};
