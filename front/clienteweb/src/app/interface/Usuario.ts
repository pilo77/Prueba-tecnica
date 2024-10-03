export interface Usuario {
  id?: number;
  nombre: string;
  email: string;
  edad: number;
  cargo: string; // Aquí se usa el nombre del cargo
  fechaIngreso: string;
}
