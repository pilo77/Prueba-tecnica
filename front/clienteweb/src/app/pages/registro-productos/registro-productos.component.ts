import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoService } from '../../services/producto.service';
import { ReactiveFormsModule } from '@angular/forms'; // Importar ReactiveFormsModule

@Component({
  selector: 'app-registro-productos',
  standalone: true,
  templateUrl: './registro-productos.component.html',
  styleUrls: ['./registro-productos.component.css'],
  imports: [ReactiveFormsModule], // Importa ReactiveFormsModule
})
export class RegistroProductosComponent {
  productoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService
  ) {
    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      cantidad: [0, Validators.required],
      fechaIngreso: ['', Validators.required],
      usuarioId: [0, Validators.required],
    });
  }

  registrarProducto() {
    if (this.productoForm.valid) {
      this.productoService
        .registrarProducto(this.productoForm.value)
        .subscribe((response) => {
          console.log('Producto registrado:', response);
          this.productoForm.reset();
        });
    }
  }
}
