/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniempresarial.proyectobd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public void insertEstudiante(EstudianteDto estudiante) {
        String sql = "INSERT INTO estudiantes (Cedula, Nombres, Apellidos, DireccionResidencia, UbicacionResidencia, DireccionTrabajo, UbicacionTrabajo) " +
                     "VALUES (?, ?, ?, ?, ST_GeomFromText(?), ?, ST_GeomFromText(?))";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, estudiante.getCedula());
            statement.setString(2, estudiante.getNombres());
            statement.setString(3, estudiante.getApellidos());
            statement.setString(4, estudiante.getDireccionResidencia());
            statement.setString(5, "POINT(" + estudiante.getLatitudResidencia() + " " + estudiante.getLongitudResidencia() + ")");
            statement.setString(6, estudiante.getDireccionTrabajo());
            statement.setString(7, "POINT(" + estudiante.getLatitudTrabajo() + " " + estudiante.getLongitudTrabajo() + ")");

            statement.executeUpdate();
            System.out.println("Estudiante insertado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar el estudiante en la base de datos.");
        }
    }

    // Método para obtener todos los estudiantes de la base de datos
    public List<EstudianteDto> getAllEstudiantes() {
        List<EstudianteDto> estudiantes = new ArrayList<>();
        String sql = "SELECT Cedula, Nombres, Apellidos, DireccionResidencia, " +
                     "ST_X(UbicacionResidencia) AS latitudResidencia, ST_Y(UbicacionResidencia) AS longitudResidencia, " +
                     "DireccionTrabajo, ST_X(UbicacionTrabajo) AS latitudTrabajo, ST_Y(UbicacionTrabajo) AS longitudTrabajo " +
                     "FROM estudiantes";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EstudianteDto estudiante = new EstudianteDto(
                        resultSet.getInt("Cedula"),
                        resultSet.getString("Nombres"),
                        resultSet.getString("Apellidos"),
                        resultSet.getString("DireccionResidencia"),
                        resultSet.getDouble("latitudResidencia"),
                        resultSet.getDouble("longitudResidencia"),
                        resultSet.getString("DireccionTrabajo"),
                        resultSet.getDouble("latitudTrabajo"),
                        resultSet.getDouble("longitudTrabajo")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los estudiantes de la base de datos.");
        }
        return estudiantes;
    }

    // Método para actualizar un estudiante en la base de datos
    public void updateEstudiante(EstudianteDto estudiante) {
        String sql = "UPDATE estudiantes SET Nombres = ?, Apellidos = ?, DireccionResidencia = ?, UbicacionResidencia = ST_GeomFromText(?), " +
                     "DireccionTrabajo = ?, UbicacionTrabajo = ST_GeomFromText(?) WHERE Cedula = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estudiante.getNombres());
            statement.setString(2, estudiante.getApellidos());
            statement.setString(3, estudiante.getDireccionResidencia());
            statement.setString(4, "POINT(" + estudiante.getLatitudResidencia() + " " + estudiante.getLongitudResidencia() + ")");
            statement.setString(5, estudiante.getDireccionTrabajo());
            statement.setString(6, "POINT(" + estudiante.getLatitudTrabajo() + " " + estudiante.getLongitudTrabajo() + ")");
            statement.setInt(7, estudiante.getCedula());

            statement.executeUpdate();
            System.out.println("Estudiante actualizado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el estudiante en la base de datos.");
        }
    }

    // Método para eliminar un estudiante de la base de datos
    public void deleteEstudiante(int cedula) {
        String sql = "DELETE FROM estudiantes WHERE Cedula = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cedula);
            statement.executeUpdate();
            System.out.println("Estudiante eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el estudiante de la base de datos.");
        }
    }
}
//////////