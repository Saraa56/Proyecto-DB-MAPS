SELECT 
    Cedula,
    Nombres,
    Apellidos,
    DireccionResidencia,
    ST_AsText(UbicacionResidencia) AS UbicacionResidencia,
    DireccionTrabajo,
    ST_AsText(UbicacionTrabajo) AS UbicacionTrabajo,
    ST_Distance_Sphere(UbicacionResidencia, UbicacionTrabajo) AS DistanciaMetros
FROM 
    Estudiantes
ORDER BY 
    DistanciaMetros ASC;
