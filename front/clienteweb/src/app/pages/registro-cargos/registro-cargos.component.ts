import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CargoService } from '../../services/cargo.service';
import { ReactiveFormsModule } from '@angular/forms'; // Importar ReactiveFormsModule

@Component({
  selector: 'app-registro-cargos',
  standalone: true,
  templateUrl: './registro-cargos.component.html',
  styleUrls: ['./registro-cargos.component.css'],
  imports: [ReactiveFormsModule],
})
export class RegistroCargosComponent {
  cargoForm: FormGroup;

  constructor(private fb: FormBuilder, private cargoService: CargoService) {
    this.cargoForm = this.fb.group({
      nombre: ['', Validators.required],
    });
  }

  registrarCargo() {
    if (this.cargoForm.valid) {
      this.cargoService
        .registrarCargo(this.cargoForm.value)
        .subscribe((response) => {
          console.log('Cargo registrado:', response);
          this.cargoForm.reset();
        });
    }
  }
}
