import { EditarProductoComponent } from './pages/editar-producto/editar-producto.component';
import { Routes } from '@angular/router';
import { RegistroCargosComponent } from './pages/registro-cargos/registro-cargos.component';
import { RegistroUsuariosComponent } from './pages/registro-usuarios/registro-usuarios.component';
import { RegistroProductosComponent } from './pages/registro-productos/registro-productos.component';
import { ListaProductosComponent } from './pages/lista-productos/lista-productos.component';
import { BuscarProductosComponent } from './pages/buscar-productos/buscar-productos.component';


export const routes: Routes = [
  { path: '', component: ListaProductosComponent }, 
  { path: 'RegistroCargos', component: ListaProductosComponent },
  { path: 'RegistroCargos', component: RegistroCargosComponent },
  { path: 'RegistroUsuarios', component: RegistroUsuariosComponent },
  { path: 'Registro-Productos', component: RegistroProductosComponent },
  { path: 'RegistroProductos', component: RegistroProductosComponent },
  { path: 'lista-Productos', component: ListaProductosComponent },
  { path: 'BuscarProductos', component: BuscarProductosComponent },
  { path: 'editar-producto/:id', component: EditarProductoComponent }

];
