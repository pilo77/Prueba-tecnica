export interface Producto {
  id?: number;
  nombre: string;
  cantidad: number;
  fechaIngreso: string;
  usuarioId: number; // Relacionado con el usuario que lo creó
  fechaModificacion?: string; // Fecha de última modificación
}
