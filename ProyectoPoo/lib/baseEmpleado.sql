-- Crear la base de datos
CREATE DATABASE empresa;

-- Seleccionar la base de datos
USE empresa;

-- Crear la tabla
CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    cargo VARCHAR(30) NOT NULL,
    usuario VARCHAR(30) NOT NULL UNIQUE,
    clave VARCHAR(30) NOT NULL
);
select *from empleado;
-- Insertar registros
INSERT INTO empleado (codigo, nombre, apellido, cargo, usuario, clave) VALUES
('EMP001', 'Fernanda', 'Rodríguez', 'administrador', 'admin', '123'),
('EMP002', 'Carlos', 'Pérez', 'empleado', 'cperez', 'Venta123'),
('EMP003', 'María', 'López', 'cliente', 'mlopez', 'Super123'),
('EMP004', 'Juan', 'Gómez', 'cliente', 'jgomez', 'Bodega123'),
('EMP005', 'Ana', 'Torres', 'empleado', 'atorres', 'Caja123'),
('EMP006', 'Luis', 'Mendoza', 'empleado', 'lmendoza', 'Venta456'),
('EMP007', 'Gabriela', 'Castro', 'empleado', 'gcastro', 'Admin456');


-- Crear la tabla productos

CREATE TABLE productos(
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          codigo VARCHAR(10) NOT NULL,
                          nombre VARCHAR(100) NOT NULL,
                          catalogo VARCHAR(20) NOT NULL,
                          marca VARCHAR(50) NOT NULL,
                          stock INT NOT NULL,
                          precio DECIMAL(10,2) NOT NULL
);


INSERT INTO productos(codigo, nombre, catalogo, marca, stock, precio) VALUES
 CREATE TABLE productos(
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          codigo VARCHAR(10) NOT NULL,
                          nombre VARCHAR(100) NOT NULL,
                          catalogo VARCHAR(20) NOT NULL,
                          marca VARCHAR(50) NOT NULL,
                          stock INT NOT NULL,
                          precio DECIMAL(10,2) NOT NULL
);


INSERT INTO productos(codigo, nombre, catalogo, marca, stock, precio) VALUES
                                                                          ('CEL001','Galaxy S25','Celular','Samsung',15,999.99),
                                                                          ('CEL002','iPhone 16','Celular','Apple',10,1299.99),
                                                                          ('CEL003','Redmi Note 14','Celular','Xiaomi',20,349.99),
                                                                          ('CAR001','Cargador 20W USB-C','Cargadores','Apple',30,29.99),
                                                                          ('CAR002','Cargador Super Fast 45W','Cargadores','Samsung',25,39.99),
                                                                          ('CAR003','Cargador Turbo 67W','Cargadores','Xiaomi',18,34.99),
                                                                          ('AUD001','AirPods Pro 2','Audífonos','Apple',12,249.99),
                                                                          ('AUD002','Galaxy Buds 3','Audífonos','Samsung',14,179.99),
                                                                          ('AUD003','Redmi Buds 6','Audífonos','Xiaomi',22,59.99),
                                                                          ('AUD004','WH-CH520','Audífonos','Sony',8,89.99),                                                                         ('CEL001','Galaxy S25','Celular','Samsung',15,999.99),
                                                                         