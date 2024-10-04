import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../interface/Producto';

@Component({
  selector: 'app-buscar-productos',
  standalone: true, // Indica que es un componente standalone
  imports: [FormsModule, CommonModule], // Asegúrate de importar FormsModule
  templateUrl: './buscar-productos.component.html',
  styleUrls: ['./buscar-productos.component.css'],
})
export class BuscarProductosComponent {
  productos: Producto[] = [];
  nombre: string = '';
  usuarioId: number | null = null;
  fechaIngreso: string | null = null;
  fechaModificacion: string | null = null;

  constructor(private productoService: ProductoService) {}

  buscar() {
    this.productoService
      .buscarProductos(
        this.nombre,
        this.usuarioId,
        this.fechaIngreso,
        this.fechaModificacion
      )
      .subscribe((data: Producto[]) => {
        this.productos = data;
      });
  }
}
