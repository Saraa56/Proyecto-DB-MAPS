CREATE DATABASE EstudiantesDB;
USE EstudiantesDB;
CREATE TABLE Estudiantes (
    Cedula INT PRIMARY KEY,
    Nombres VARCHAR(100),
    Apellidos VARCHAR(100),
    DireccionResidencia VARCHAR(255),
    UbicacionResidencia POINT,
    DireccionTrabajo VARCHAR(255),
    UbicacionTrabajo POINT
);
select * from Estudiantes;