import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../interface/Producto';
import { appsettings } from '../settings/appsettings';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private baseUrl = appsettings.apiUrl;

  constructor(private http: HttpClient) {}

  registrarProducto(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(`${this.baseUrl}/productos`, producto);
  }

  obtenerProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.baseUrl}/productos`);
  }
}
