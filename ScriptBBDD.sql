DROP DATABASE IF EXISTS gestion_centro_deportivo;
CREATE DATABASE gestion_centro_deportivo;
USE gestion_centro_deportivo;

CREATE TABLE salas (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL,
    tipo VARCHAR(50),
    planta INT
);

CREATE TABLE material (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(50),
    cantidad INT NOT NULL,
    estado VARCHAR(30),
    id_sala INT,
    FOREIGN KEY (id_sala) REFERENCES salas(id_sala)
);

CREATE TABLE entrenadores (
    id_entrenador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    dni VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(100),
    especialidad VARCHAR(50),
    salario DECIMAL(8,2)
);

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    dni VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(100),
    fecha_nacimiento DATE,
    cuota_mensual DECIMAL(6,2)
);

CREATE TABLE actividades (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200),
    nivel VARCHAR(30),
    duracion_minutos INT,
    id_sala INT,
    id_entrenador INT,
    FOREIGN KEY (id_sala) REFERENCES salas(id_sala),
    FOREIGN KEY (id_entrenador) REFERENCES entrenadores(id_entrenador)
);

CREATE TABLE reservas (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_actividad INT,
    fecha DATE,
    hora TIME,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_actividad) REFERENCES actividades(id_actividad)
);

INSERT INTO salas (nombre, capacidad, tipo, planta) VALUES
('Sala Fitness', 25, 'Musculación', 0),
('Sala Pilates', 12, 'Pilates', 1),
('Sala Cardio', 20, 'Cardio', 0),
('Sala Funcional', 15, 'Entrenamiento funcional', 1);

INSERT INTO material (nombre, tipo, cantidad, estado, id_sala) VALUES
('Mancuernas 5kg', 'Pesas', 12, 'Bueno', 1),
('Mancuernas 10kg', 'Pesas', 10, 'Bueno', 1),
('Barras olímpicas', 'Pesas', 5, 'Bueno', 1),
('Discos 10kg', 'Pesas', 20, 'Bueno', 1),
('Esterillas', 'Suelo', 15, 'Bueno', 2),
('Aros de pilates', 'Pilates', 10, 'Bueno', 2),
('Fitballs', 'Pilates', 8, 'Regular', 2),
('Cintas de correr', 'Cardio', 6, 'Bueno', 3),
('Bicicletas estáticas', 'Cardio', 8, 'Bueno', 3),
('Kettlebells', 'Funcional', 10, 'Bueno', 4),
('TRX', 'Funcional', 6, 'Bueno', 4),
('Steps', 'Funcional', 12, 'Bueno', 4);

INSERT INTO entrenadores 
(nombre, apellidos, dni, telefono, email, especialidad, salario) VALUES
('Carlos', 'Martín López', '12345678A', '600111222', 'carlos.martin@email.com', 'Fitness', 1450.00),
('Laura', 'García Pérez', '23456789B', '600222333', 'laura.garcia@email.com', 'Pilates', 1500.00),
('Miguel', 'Sánchez Ruiz', '34567890C', '600333444', 'miguel.sanchez@email.com', 'Cardio', 1400.00),
('Ana', 'Fernández Díaz', '45678901D', '600444555', 'ana.fernandez@email.com', 'Funcional', 1550.00),
('David', 'Moreno Gil', '56789012E', '600555666', 'david.moreno@email.com', 'Musculación', 1600.00);

INSERT INTO clientes 
(nombre, apellidos, dni, telefono, email, fecha_nacimiento, cuota_mensual) VALUES
('María', 'López Romero', '11111111A', '611111111', 'maria.lopez@email.com', '1995-04-12', 45.00),
('Javier', 'Gómez Torres', '22222222B', '622222222', 'javier.gomez@email.com', '1988-09-20', 50.00),
('Lucía', 'Ramírez Castro', '33333333C', '633333333', 'lucia.ramirez@email.com', '2001-01-15', 40.00),
('Pablo', 'Hernández Vega', '44444444D', '644444444', 'pablo.hernandez@email.com', '1992-06-30', 55.00),
('Sara', 'Navarro Molina', '55555555E', '655555555', 'sara.navarro@email.com', '1985-11-03', 50.00),
('Daniel', 'Ortega León', '66666666F', '666666666', 'daniel.ortega@email.com', '1998-02-18', 45.00),
('Elena', 'Ruiz Medina', '77777777G', '677777777', 'elena.ruiz@email.com', '1990-07-25', 60.00),
('Adrián', 'Santos Peña', '88888888H', '688888888', 'adrian.santos@email.com', '1996-12-09', 45.00);

INSERT INTO actividades 
(nombre, descripcion, nivel, duracion_minutos, id_sala, id_entrenador) VALUES
('Pilates Básico', 'Clase inicial de pilates en suelo', 'Básico', 50, 2, 2),
('Entrenamiento Funcional', 'Circuito de fuerza y resistencia', 'Intermedio', 45, 4, 4),
('Cardio Intenso', 'Clase cardiovascular de alta intensidad', 'Avanzado', 40, 3, 3),
('Musculación Guiada', 'Entrenamiento con pesas supervisado', 'Intermedio', 60, 1, 5),
('Fitness General', 'Clase variada de acondicionamiento físico', 'Básico', 50, 1, 1);

INSERT INTO reservas 
(id_cliente, id_actividad, fecha, hora) VALUES
(1, 1, '2026-06-03', '10:00:00'),
(2, 2, '2026-06-03', '11:00:00'),
(3, 3, '2026-06-04', '18:00:00'),
(4, 4, '2026-06-04', '19:00:00'),
(5, 1, '2026-06-05', '10:00:00'),
(6, 5, '2026-06-05', '17:00:00'),
(7, 2, '2026-06-06', '12:00:00'),
(8, 3, '2026-06-06', '18:30:00');