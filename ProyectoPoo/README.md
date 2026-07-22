# TecnoCelular

Sistema de inventario y gestion de empleados para una tienda de telefonos y accesorios tecnologicos. Aplicacion de escritorio desarrollada con JavaFX y MySQL como proyecto final de Programacion Orientada a Objetos.

## Funcionalidades

- **Sistema de login** con autenticacion y roles (administrador, empleado, cliente)
- **Registro de clientes** con validacion de campos
- **Gestion de empleados** (CRUD completo): crear, buscar, actualizar y eliminar
- **Gestion de productos** (CRUD completo): celulares, audifonos y cargadores
- **Catalogo de productos** con busqueda por nombre y marca
- **Interfaces tematizadas**: azul para admin/empleado, naranja para cliente

## Roles

| Rol | Permisos |
|---|---|
| Administrador | Gestionar empleados y productos |
| Empleado | Buscar productos y gestionar clientes |
| Cliente | Consultar catalogo de productos |

## Tecnologias

- **Java 21** (con features preview)
- **JavaFX 21** (GUI con FXML)
- **MySQL** (base de datos en Railway)
- **Maven** (build tool)
- **JUnit 5** (testing)

## Estructura del proyecto

```
ProyectoPoo/
├── src/main/java/org/example/demo/
│   ├── controller/       # Controladores JavaFX
│   ├── model/            # Modelos (Persona, Administrador, Empleado, Cliente, Productos)
│   ├── dao/              # Capa de acceso a datos (UsuariosDAO, ProductosDAO)
│   ├── interfaces/       # Interfaz AccionesCrud
│   ├── conexionDatos/    # Conexion a MySQL
│   └── util/             # Utilidades (Validaciones, Alertas)
├── src/main/resources/
│   ├── view/             # Vistas FXML
│   ├── css/              # Estilos CSS
│   └── images/           # Imagenes
├── lib/                  # Script SQL de la base de datos
└── TecnoCelular/         # Ejecutable Windows (jpackage)
```

## Ejecutar el proyecto

### Con Maven

```bash
./mvnw clean javafx:run
```

### Ejecutable Windows

Ejecutar `TecnoCelular/TecnoCelular.exe` directamente (incluye JRE embebido).

## Base de datos

El esquema SQL se encuentra en `lib/baseEmpleado.sql`. La base de datos esta hospedada en Railway y incluye datos de prueba con 7 usuarios y 10 productos.

## Conceptos OOP aplicados

- **Herencia**: `Persona` (abstracta) -> `Administrador`, `Empleado`, `Cliente`
- **Polimorfismo**: metodo abstracto `obtenerVista()` implementado en cada subclase
- **Interfaces**: `AccionesCrud` con metodos CRUD genericos
- **Abstraccion**: capa DAO que oculta la logica de acceso a datos
- **Patron MVC**: separacion de modelo, vista y controlador
