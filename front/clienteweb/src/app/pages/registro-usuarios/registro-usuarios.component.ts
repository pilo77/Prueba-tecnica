import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { CargoService } from '../../services/cargo.service';
import { ReactiveFormsModule } from '@angular/forms'; 
import { Cargo } from '../../interface/Cargo';
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro-usuarios',
  standalone: true,
  templateUrl: './registro-usuarios.component.html',
  styleUrls: ['./registro-usuarios.component.css'],
  imports: [ReactiveFormsModule, CommonModule], 
})
export class RegistroUsuariosComponent implements OnInit {
  usuarioForm: FormGroup;
  cargos: Cargo[] = [];

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private cargoService: CargoService,
    private router: Router
  ) {
    this.usuarioForm = this.fb.group({
      nombre: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      edad: [0, Validators.required],
      cargoId: [null, Validators.required], // Asegúrate de que el cargoId se pasa correctamente como número
      fechaIngreso: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.cargarCargos(); // Cargar cargos al inicio
  }

  cargarCargos() {
    this.cargoService.obtenerCargos().subscribe(
      (response) => {
        this.cargos = response; // Guardar cargos obtenidos en la lista
      },
      (error) => {
        console.error('Error al cargar los cargos', error);
      }
    );
  }

  registrarUsuario() {
    if (this.usuarioForm.valid) {
      this.usuarioService.registrarUsuario(this.usuarioForm.value).subscribe(
        (response) => {
          console.log('Usuario registrado:', response);
          this.usuarioForm.reset(); // Limpiar formulario
          this.router.navigate(['/lista-productos']); // Redirigir a la lista de productos
        },
        (error) => {
          console.error('Error al registrar el usuario:', error); // Manejo de error
        }
      );
    }
  }
}
