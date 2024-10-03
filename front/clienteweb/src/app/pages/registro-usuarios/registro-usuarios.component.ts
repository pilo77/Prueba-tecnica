import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { ReactiveFormsModule } from '@angular/forms'; // Importar ReactiveFormsModule

@Component({
  selector: 'app-registro-usuarios',
  standalone: true,
  templateUrl: './registro-usuarios.component.html',
  styleUrls: ['./registro-usuarios.component.css'],
  imports: [ReactiveFormsModule], // Importa ReactiveFormsModule
})
export class RegistroUsuariosComponent {
  usuarioForm: FormGroup;

  constructor(private fb: FormBuilder, private usuarioService: UsuarioService) {
    this.usuarioForm = this.fb.group({
      nombre: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      edad: [0, Validators.required],
      cargo: ['', Validators.required],
      fechaIngreso: ['', Validators.required],
    });
  }

  registrarUsuario() {
    if (this.usuarioForm.valid) {
      this.usuarioService
        .registrarUsuario(this.usuarioForm.value)
        .subscribe((response) => {
          console.log('Usuario registrado:', response);
          this.usuarioForm.reset();
        });
    }
  }
}
