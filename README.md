# **Documentación Prueba Técnica: Sistema de Inventario Automotriz**

## 1. **Descripción General del Proyecto**
Este proyecto es un **Sistema de Inventario Automotriz** que permite gestionar productos, cargos y usuarios. Los usuarios pueden registrar productos, ver una lista de los mismos, buscar productos por distintos filtros y actualizar la información de los productos. El sistema incluye un backend desarrollado en Java con Spring Boot y un frontend desarrollado en Angular.

## 2. **Tecnologías Utilizadas**

### Backend:
- **Java** (Spring Boot)
- **Spring Data JPA** (para la gestión de bases de datos)
- **PostgreSQL** (base de datos relacional)
- **Maven** (gestión de dependencias)
- **JUnit** (pruebas unitarias)
- **Swagger** (documentación de API)

### Frontend:
- **Angular** (versión 17)
- **TypeScript**
- **HTML5 y CSS3**
- **Bootstrap** (opcional, para estilos adicionales)
- **ReactiveFormsModule y FormsModule** (para la gestión de formularios)

---

## 3. **Estructura del Backend**

El backend está estructurado siguiendo el patrón **MVC** (Model-View-Controller) con una separación clara de responsabilidades:

### Directorios Principales:
- **Controller**: Contiene los controladores que manejan las solicitudes HTTP y responden a las solicitudes REST.
- **Service**: Contiene la lógica de negocio de la aplicación.
- **Repository**: Interactúa con la base de datos mediante Spring Data JPA.
- **DTO (Data Transfer Objects)**: Maneja los datos transferidos entre el cliente y el servidor.
- **Model**: Contiene las entidades que mapean las tablas de la base de datos.

### Endpoints:
- **/api/productos**: CRUD de productos.
- **/api/cargos**: Gestión de cargos.
- **/api/usuarios**: CRUD de usuarios.

### Configuración de la base de datos:
El proyecto utiliza **PostgreSQL** como base de datos. Asegúrate de configurar las credenciales correctas en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventario
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 4. **Estructura del Frontend**

El frontend está estructurado utilizando **Angular** y sigue el patrón de componentes con servicios.

### Directorios Principales:
- **app/pages**: Contiene los componentes de cada página, como el formulario de registro y las listas.
- **app/services**: Contiene los servicios que hacen las solicitudes HTTP al backend.
- **app/interface**: Define las interfaces que se utilizan para tipar los objetos de datos, como `Producto`, `Usuario`, y `Cargo`.
  
### Componentes Importantes:
- **RegistroProductosComponent**: Formulario para registrar nuevos productos.
- **ListaProductosComponent**: Muestra la lista de productos en el inventario.
- **BuscarProductosComponent**: Permite la búsqueda de productos filtrando por nombre, fecha, usuario, etc.

---

## 5. **Instrucciones de Instalación y Ejecución**

### **Backend**

1. Clona el repositorio del backend:
   ```bash
   git clone https://github.com/usuario/prueba-tecnica-backend.git
   ```

2. Configura la base de datos PostgreSQL según los datos en `application.properties`.

3. Navega a la carpeta del proyecto:
   ```bash
   cd prueba-tecnica-backend
   ```

4. Ejecuta el siguiente comando para instalar las dependencias y construir el proyecto:
   ```bash
   mvn clean install
   ```

5. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```

6. Accede a la documentación de la API (Swagger) en:
   ```
   http://localhost:8080/swagger-ui.html
   ```

### **Frontend**

1. Clona el repositorio del frontend:
   ```bash
   git clone https://github.com/usuario/prueba-tecnica-frontend.git
   ```

2. Navega a la carpeta del frontend:
   ```bash
   cd prueba-tecnica-frontend
   ```

3. Instala las dependencias de Angular:
   ```bash
   npm install
   ```

4. Configura la URL base de la API en el archivo `appsettings.ts`:
   ```typescript
   export const appsettings = {
     apiUrl: 'http://localhost:8080/api/'
   };
   ```

5. Ejecuta la aplicación Angular:
   ```bash
   ng serve
   ```

6. Abre tu navegador en:
   ```
   http://localhost:4200
   ```

---

## 6. **Uso del Sistema**

### Funcionalidades:
- **Registro de productos**: Puedes acceder al formulario de registro de productos mediante el menú de navegación en el frontend.
- **Listado de productos**: Navega a la lista de productos para ver todos los productos registrados.
- **Búsqueda de productos**: Puedes buscar productos por nombre, usuario, o fecha utilizando el formulario de búsqueda.
- **Actualización y eliminación**: Cada producto puede ser actualizado o eliminado, según sea necesario.

---

## 7. **Pruebas Unitarias**

El proyecto cuenta con pruebas unitarias implementadas con **JUnit** para el backend. Las pruebas están localizadas en la carpeta `src/test/java`. Para ejecutarlas, usa el siguiente comando:

```bash
mvn test
```

---
