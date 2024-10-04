import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoService } from '../../services/producto.service';
import { ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; // Importar Router para redirigir

@Component({
  selector: 'app-registro-productos',
  standalone: true,
  
  templateUrl: './registro-productos.component.html',
  styleUrls: ['./registro-productos.component.css'],
  imports: [ReactiveFormsModule, CommonModule], 
})
export class RegistroProductosComponent {
  productoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private router: Router // Inyectar Router para la redirección
  ) {
    this.productoForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      cantidad: [0, [Validators.required, Validators.min(1)]], 
      fechaIngreso: ['', Validators.required],
      usuarioId: [null, Validators.required], 
    });
  }

  registrarProducto() {
    if (this.productoForm.valid) {
      this.productoService
        .registrarProducto(this.productoForm.value)
        .subscribe((response) => {
          console.log('Producto registrado:', response);
          this.productoForm.reset();
          this.router.navigate(['/lista-productos']); // Redirigir a la lista de productos
        }, error => {
          console.error('Error al registrar producto:', error);
        });
    }
  }

  get fechaActual() {
    return new Date().toISOString().split('T')[0]; 
  }
}
