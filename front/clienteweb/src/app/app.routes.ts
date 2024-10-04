import { Routes } from '@angular/router';

import { RegistroCargosComponent } from './pages/registro-cargos/registro-cargos.component';
import { RegistroProductosComponent } from './pages/registro-productos/registro-productos.component';
import { RegistroUsuariosComponent } from './pages/registro-usuarios/registro-usuarios.component';
import { ListaProductosComponent } from './pages/lista-productos/lista-productos.component';
import { BuscarProductosComponent } from './pages/buscar-productos/buscar-productos.component';

export const routes: Routes = [
  { path: '', component: RegistroCargosComponent }, // Ruta principal (Inicio)
  { path: 'RegistroCargos', component: RegistroCargosComponent },
  { path: 'RegistroUsuarios', component: RegistroUsuariosComponent },
  { path: 'RegistroProductos', component: RegistroProductosComponent },
  { path: 'ListaProductos', component: ListaProductosComponent },
  { path: 'BuscarProductos', component: BuscarProductosComponent },
];
