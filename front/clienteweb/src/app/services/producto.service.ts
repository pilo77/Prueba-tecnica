import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  buscarProductos(
    nombre?: string,
    usuarioId?: number | null,
    fechaIngreso?: string | null,
    fechaModificacion?: string | null
  ): Observable<Producto[]> {
    let params = new HttpParams();

    if (nombre) params = params.append('nombre', nombre);
    if (usuarioId !== null && usuarioId !== undefined)
      params = params.append('usuarioId', usuarioId.toString());
    if (fechaIngreso) params = params.append('fechaIngreso', fechaIngreso);
    if (fechaModificacion)
      params = params.append('fechaModificacion', fechaModificacion);

    return this.http.get<Producto[]>(`${this.baseUrl}/productos/buscar`, {
      params,
    });
  }

  obtenerProductoPorId(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.baseUrl}/productos/${id}`);
  }

  actualizarProducto(id: number, producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(`${this.baseUrl}/productos/${id}`, producto);
  }

  eliminarProducto(id: number, usuarioId: number): Observable<void> {
    const params = new HttpParams().set('usuarioId', usuarioId.toString());
    return this.http.delete<void>(`${this.baseUrl}/productos/${id}`, {
      params,
    });
  }
}
