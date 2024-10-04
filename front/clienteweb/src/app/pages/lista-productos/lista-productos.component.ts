import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../interface/Producto';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms'; // Importar ReactiveFormsModule

@Component({
  selector: 'app-lista-productos',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule], // Agregar ReactiveFormsModule
  templateUrl: './lista-productos.component.html',
  styleUrls: ['./lista-productos.component.css'],
})
export class ListaProductosComponent implements OnInit {
  productos: Producto[] = [];
  filtroForm: FormGroup;

  constructor(
    private productoService: ProductoService,
    private router: Router,
    private fb: FormBuilder
  ) {
    // Inicializar el formulario de búsqueda
    this.filtroForm = this.fb.group({
      nombre: [''],
      usuarioId: [''],
      fechaIngreso: [''],
    });
  }

  ngOnInit(): void {
    this.obtenerProductos();
  }

  obtenerProductos() {
    this.productoService.obtenerProductos().subscribe(
      (response) => {
        this.productos = response;
      },
      (error) => {
        console.error('Error al obtener los productos', error);
      }
    );
  }

  // Función para redirigir al formulario de creación de productos
  irACrearProducto() {
    this.router.navigate(['/RegistroProductos']); // Redirige a la ruta de registro de productos
  }

  // Función para buscar productos según los filtros
  buscarProductos() {
    const { nombre, usuarioId, fechaIngreso } = this.filtroForm.value;
    this.productoService.buscarProductos(nombre, usuarioId, fechaIngreso).subscribe(
      (response) => {
        this.productos = response;
      },
      (error) => {
        console.error('Error al buscar productos', error);
      }
    );
  }

  // Función para redirigir al formulario de edición de productos
  editarProducto(producto: Producto) {
    this.router.navigate(['/editar-producto', producto.id]);
  }

  // Función para eliminar un producto con validación del usuario
  eliminarProducto(producto: Producto) {
    if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
      const usuarioId = prompt(
        'Por favor, ingresa tu ID de usuario para confirmar la eliminación:'
      );

      if (usuarioId && parseInt(usuarioId, 10) === producto.usuarioId) {
        this.productoService.eliminarProducto(producto.id, parseInt(usuarioId, 10)).subscribe(
          () => {
            console.log('Producto eliminado');
            this.obtenerProductos(); // Recargar la lista de productos después de eliminar
          },
          (error) => {
            console.error('Error al eliminar el producto', error);
          }
        );
      } else {
        alert('Solo el usuario que creó el producto puede eliminarlo.');
      }
    }
  }
}
