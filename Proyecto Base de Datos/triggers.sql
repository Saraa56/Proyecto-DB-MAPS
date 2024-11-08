-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS EstudiantesDB;
USE EstudiantesDB;

-- Crear la tabla principal Estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes (
    Cedula INT PRIMARY KEY,
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    DireccionResidencia VARCHAR(255),
    UbicacionResidencia POINT,
    DireccionTrabajo VARCHAR(255),
    UbicacionTrabajo POINT
);

-- Crear la tabla de auditoría EstudiantesAudit
CREATE TABLE IF NOT EXISTS EstudiantesAudit (
    AuditID INT AUTO_INCREMENT PRIMARY KEY,
    Cedula INT,
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    DireccionResidencia VARCHAR(255),
    UbicacionResidencia POINT,
    DireccionTrabajo VARCHAR(255),
    UbicacionTrabajo POINT,
    OperationType ENUM('INSERT', 'UPDATE', 'DELETE'),
    OperationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    OldNombres VARCHAR(100),
    OldApellidos VARCHAR(100),
    OldDireccionResidencia VARCHAR(255),
    OldUbicacionResidencia POINT,
    OldDireccionTrabajo VARCHAR(255),
    OldUbicacionTrabajo POINT
);

-- Trigger AFTER INSERT para la tabla Estudiantes
DELIMITER //

CREATE TRIGGER after_estudiantes_insert
AFTER INSERT ON Estudiantes
FOR EACH ROW
BEGIN
    INSERT INTO EstudiantesAudit (
        Cedula, Nombres, Apellidos, DireccionResidencia, UbicacionResidencia,
        DireccionTrabajo, UbicacionTrabajo, OperationType, OperationDate
    )
    VALUES (
        NEW.Cedula, NEW.Nombres, NEW.Apellidos, NEW.DireccionResidencia, NEW.UbicacionResidencia,
        NEW.DireccionTrabajo, NEW.UbicacionTrabajo, 'INSERT', NOW()
    );
END //

DELIMITER ;

-- Trigger BEFORE UPDATE para la tabla Estudiantes
DELIMITER //

CREATE TRIGGER before_estudiantes_update
BEFORE UPDATE ON Estudiantes
FOR EACH ROW
BEGIN
    INSERT INTO EstudiantesAudit (
        Cedula, Nombres, Apellidos, DireccionResidencia, UbicacionResidencia,
        DireccionTrabajo, UbicacionTrabajo, OperationType, OperationDate,
        OldNombres, OldApellidos, OldDireccionResidencia, OldUbicacionResidencia,
        OldDireccionTrabajo, OldUbicacionTrabajo
    )
    VALUES (
        NEW.Cedula, NEW.Nombres, NEW.Apellidos, NEW.DireccionResidencia, NEW.UbicacionResidencia,
        NEW.DireccionTrabajo, NEW.UbicacionTrabajo, 'UPDATE', NOW(),
        OLD.Nombres, OLD.Apellidos, OLD.DireccionResidencia, OLD.UbicacionResidencia,
        OLD.DireccionTrabajo, OLD.UbicacionTrabajo
    );
END //

DELIMITER ;

-- Trigger BEFORE DELETE para la tabla Estudiantes
DELIMITER //

CREATE TRIGGER before_estudiantes_delete
BEFORE DELETE ON Estudiantes
FOR EACH ROW
BEGIN
    INSERT INTO EstudiantesAudit (
        Cedula, Nombres, Apellidos, DireccionResidencia, UbicacionResidencia,
        DireccionTrabajo, UbicacionTrabajo, OperationType, OperationDate,
        OldNombres, OldApellidos, OldDireccionResidencia, OldUbicacionResidencia,
        OldDireccionTrabajo, OldUbicacionTrabajo
    )
    VALUES (
        OLD.Cedula, OLD.Nombres, OLD.Apellidos, OLD.DireccionResidencia, OLD.UbicacionResidencia,
        OLD.DireccionTrabajo, OLD.UbicacionTrabajo, 'DELETE', NOW(),
        OLD.Nombres, OLD.Apellidos, OLD.DireccionResidencia, OLD.UbicacionResidencia,
        OLD.DireccionTrabajo, OLD.UbicacionTrabajo
    );
END //

DELIMITER ;

-- Consultas de prueba para verificar los triggers

-- Insertar un nuevo estudiante
INSERT INTO Estudiantes (Cedula, Nombres, Apellidos, DireccionResidencia, UbicacionResidencia, DireccionTrabajo, UbicacionTrabajo)
VALUES (12345, 'Juan', 'Pérez', 'Calle Falsa 123', ST_GeomFromText('POINT(4.60971 -74.08175)'), 'Avenida Siempre Viva 456', ST_GeomFromText('POINT(4.60971 -74.08175)'));

-- Actualizar el estudiante
UPDATE Estudiantes
SET Nombres = 'Juan Carlos', Apellidos = 'Pérez García', DireccionResidencia = 'Calle Nueva 789'
WHERE Cedula = 12345;

-- Eliminar el estudiante
DELETE FROM Estudiantes WHERE Cedula = 12345;

-- Consultar la tabla de auditoría para verificar los registros
SELECT * FROM EstudiantesAudit;
