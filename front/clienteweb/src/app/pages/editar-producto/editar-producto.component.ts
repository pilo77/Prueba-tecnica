import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../../services/producto.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-producto',
  standalone: true,
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css'],
  imports: [ReactiveFormsModule,CommonModule], // Importar ReactiveFormsModule aquí
})
export class EditarProductoComponent implements OnInit {
  productoId!: number;  // Producto ID que será asignado
  productoForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      cantidad: ['', Validators.required],
      fechaIngreso: ['', Validators.required],
      usuarioId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // Obtener el ID del producto desde la URL
    this.productoId = +this.route.snapshot.paramMap.get('id')!;
    
    // Llenar el formulario con los datos del producto
    this.cargarProducto();
  }

  cargarProducto() {
    this.productoService.obtenerProductoPorId(this.productoId).subscribe(
      (producto) => {
        // Poner los valores del producto en el formulario
        this.productoForm.patchValue({
          nombre: producto.nombre,
          cantidad: producto.cantidad,
          fechaIngreso: producto.fechaIngreso,
          usuarioId: producto.usuarioId,
        });
      },
      (error) => {
        console.error('Error al cargar el producto', error);
      }
    );
  }

  guardarCambios() {
    if (this.productoForm.valid) {
      this.productoService.actualizarProducto(this.productoId, this.productoForm.value).subscribe(
        (response) => {
          console.log('Producto actualizado:', response);
          // Redirigir a la lista de productos después de actualizar
          this.router.navigate(['/lista-productos']); 
        },
        (error) => {
          console.error('Error al actualizar el producto', error);
        }
      );
    }
  }
}
