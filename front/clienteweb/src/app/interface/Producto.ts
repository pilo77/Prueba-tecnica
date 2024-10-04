export interface Producto {
  id: number;
  nombre: string;
  cantidad: number;
  fechaIngreso: string;
  usuarioId?: number | null; // Modificar aquí para permitir null
  fechaModificacion?: string;
}
