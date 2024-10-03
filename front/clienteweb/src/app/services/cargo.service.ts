import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cargo } from '../interface/Cargo';
import { appsettings } from '../settings/appsettings';

@Injectable({
  providedIn: 'root',
})
export class CargoService {
  private baseUrl = appsettings.apiUrl;

  constructor(private http: HttpClient) {}

  registrarCargo(cargo: Cargo): Observable<Cargo> {
    return this.http.post<Cargo>(`${this.baseUrl}/cargos`, cargo);
  }

  obtenerCargos(): Observable<Cargo[]> {
    return this.http.get<Cargo[]>(`${this.baseUrl}/cargos`);
  }
}
