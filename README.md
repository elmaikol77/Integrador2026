**GestionCentroDeportivo  |  README**


**Universidad Europea**

**GestionCentroDeportivo**

**README — Documentación del Proyecto**

CURSO ACADÉMICO 2025-2026


Miguel  ·  Yeray  ·  Jaime

universidadeuropea.com

# **Descripción**
GestionCentroDeportivo es una aplicación de escritorio desarrollada en Java con Swing que simula el sistema de gestión de un centro deportivo. Implementa el patrón arquitectónico MVC (Modelo–Vista–Controlador) y persiste los datos en una base de datos MySQL local.

El sistema contempla dos tipos de acceso con distintos niveles de funcionalidad: administrador y cliente, cada uno con su propio entorno de trabajo dentro de la misma aplicación. Al arrancar, se muestra una pantalla de login donde el usuario elige su tipo de acceso.

# **Funcionalidades**

|**Nº**|**Requisito Funcional**|**Rol**|
| :-: | :-: | :-: |
|**RF1**|Consulta de clientes (sin IDs internos)|**Todos**|
|**RF2**|Consulta de entrenadores|**Todos**|
|**RF3**|Consulta de salas|**Todos**|
|**RF4**|Consulta de material (con nombre de sala)|**Todos**|
|**RF5**|Alta de clientes|**Administrador**|
|**RF6**|Modificación de clientes|**Administrador**|
|**RF7**|Eliminación de clientes|**Administrador**|
|**RF8**|Alta de entrenadores|**Administrador**|
|**RF9**|Modificación de entrenadores|**Administrador**|
|**RF10**|Eliminación de entrenadores|**Administrador**|
|**RF11**|Alta de salas|**Administrador**|
|**RF12**|Modificación de salas|**Administrador**|
|**RF13**|Eliminación de salas|**Administrador**|
|**RF14**|Alta de material|**Administrador**|
|**RF15**|Modificación de material|**Administrador**|
|**RF16**|Eliminación de material|**Administrador**|
|**RF17**|Login diferenciado admin / cliente|**Todos**|

# **Roles de usuario**

|**Rol**|**Acceso**|
| :-: | :-: |
|**🔑 Administrador**|Consulta + Gestión completa (CRUD sobre todas las entidades). Accede con usuario y contraseña.|
|**👤 Cliente**|Solo consulta (Clientes, Entrenadores, Salas, Material). El menú Gestión no aparece en la interfaz.|

## **Credenciales de administrador**

|**Usuario**|**Contraseña**|
| :-: | :-: |
|admin|admin|

# **Arquitectura**
El proyecto sigue estrictamente el patrón MVC:

**Vista (View)**

PLogin · VPCentroDeportivo · PInicio · PConsultarClientes · PConsultarEntrenadores · PConsultarSalas · PConsultarMaterial · PGestionClientes · PGestionEntrenadores · PGestionSalas · PGestionMaterial

**Controlador (Ctrl)**

CentroDeportivoListener — Gestiona la navegación entre paneles y centraliza la lógica de eventos. Se comunica con la Vista mediante ActionListener.

**Modelo (Model)**

POJOs: Cliente · Entrenador · Sala · Material

DAOs:  ClienteDAO · EntrenadorDAO · SalaDAO · MaterialDAO · ConexionDB

# **Estructura del repositorio**

Integrador2026-master/

├── src/

│   └── com/centrodeportivo/

│       ├── control/

│       │   └── CentroDeportivoListener.java   # Controlador principal

│       ├── main/

│       │   └── MainCentroDeportivo.java        # Punto de entrada + login

│       ├── model/

│       │   ├── data/                           # POJOs / entidades

│       │   │   ├── Cliente.java

│       │   │   ├── Entrenador.java

│       │   │   ├── Sala.java

│       │   │   └── Material.java

│       │   └── db/                             # DAOs + conexión

│       │       ├── ConexionDB.java

│       │       ├── ClienteDAO.java

│       │       ├── EntrenadorDAO.java

│       │       ├── SalaDAO.java

│       │       └── MaterialDAO.java

│       └── view/                               # Vistas Swing

│           ├── PLogin.java

│           ├── VPCentroDeportivo.java

│           ├── PInicio.java

│           ├── PConsultarClientes.java  ...

│           └── PGestionMaterial.java

├── bin/                                        # Clases compiladas

├── ScriptBBDD.sql                              # Script de creación e inserción

├── .classpath

├── .project

└── README.md

# **Base de datos**
La base de datos MySQL (gestion\_centro\_deportivo) se crea mediante el script ScriptBBDD.sql. El esquema relacional es el siguiente:

salas ──< material

salas ──< actividades >── entrenadores

clientes ──< reservas >── actividades

|**Tabla**|**Descripción**|
| :-: | :-: |
|**salas**|Salas del centro con capacidad, tipo y planta|
|**entrenadores**|Personal técnico con especialidad y salario|
|**clientes**|Socios del centro con cuota mensual|
|**material**|Equipamiento asignado a cada sala|
|**actividades**|Clases vinculadas a sala y entrenador|
|**reservas**|Relación cliente ↔ actividad con fecha y hora|

# **Pruebas**
El proyecto incluye casos de prueba manuales documentados en el Plan de Pruebas que cubren los dos roles de usuario y los flujos principales:

|**Área**|**Casos cubiertos**|
| :-: | :-: |
|**Login**|Acceso admin correcto/incorrecto, acceso cliente directo|
|**Consultas**|Clientes, entrenadores, salas, material (todos los roles)|
|**Gestión**|CRUD clientes, entrenadores, salas, material (solo admin)|
|**Control de acceso**|Verificar que clientes no ven ni pueden acceder al menú Gestión|
|**Validación de datos**|Campos vacíos, tipos incorrectos, integridad referencial|

# **Cómo ejecutar**
## **Requisitos previos**
Java 17 o superior

MySQL 8.x en ejecución en localhost:3307

Conector JDBC MySQL en el build path del proyecto

## **Pasos**
1\.  Ejecutar ScriptBBDD.sql en MySQL para crear la base de datos y los datos de prueba:

SOURCE ScriptBBDD.sql;

2\.  Importar el proyecto en Eclipse desde la carpeta Integrador2026-master/

3\.  Verificar que el conector JDBC MySQL está en el build path

4\.  Ejecutar la clase principal:

com.centrodeportivo.main.MainCentroDeportivo

5\.  En la pantalla de login, elegir Acceso Administrador (usuario: admin, contraseña: admin) o Acceso Cliente (cualquier nombre).

# **Tecnologías**

|**Tecnología**|**Uso**|
| :-: | :-: |
|**Java 17**|Lenguaje principal|
|**Java Swing**|Interfaz gráfica de escritorio|
|**MySQL 8**|Base de datos relacional|
|**JDBC**|Acceso a la base de datos desde Java|
|**Eclipse IDE**|Entorno de desarrollo|
|**Git**|Control de versiones|


*Proyecto Integrador 2026 — Miguel, Yeray, Jaime*
Página  de 
