/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniempresarial.proyectobd;

public class EstudianteDto {
    private int cedula;  // Tipo int según la base de datos
    private String nombres;
    private String apellidos;
    private String direccionResidencia;
    private double latitudResidencia;
    private double longitudResidencia;
    private String direccionTrabajo;
    private double latitudTrabajo;
    private double longitudTrabajo;

    public EstudianteDto(int cedula, String nombres, String apellidos, String direccionResidencia,
                         double latitudResidencia, double longitudResidencia,
                         String direccionTrabajo, double latitudTrabajo, double longitudTrabajo) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccionResidencia = direccionResidencia;
        this.latitudResidencia = latitudResidencia;
        this.longitudResidencia = longitudResidencia;
        this.direccionTrabajo = direccionTrabajo;
        this.latitudTrabajo = latitudTrabajo;
        this.longitudTrabajo = longitudTrabajo;
    }

    // Getters y Setters
    public int getCedula() { return cedula; }
    public void setCedula(int cedula) { this.cedula = cedula; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDireccionResidencia() { return direccionResidencia; }
    public void setDireccionResidencia(String direccionResidencia) { this.direccionResidencia = direccionResidencia; }
    public double getLatitudResidencia() { return latitudResidencia; }
    public void setLatitudResidencia(double latitudResidencia) { this.latitudResidencia = latitudResidencia; }
    public double getLongitudResidencia() { return longitudResidencia; }
    public void setLongitudResidencia(double longitudResidencia) { this.longitudResidencia = longitudResidencia; }
    public String getDireccionTrabajo() { return direccionTrabajo; }
    public void setDireccionTrabajo(String direccionTrabajo) { this.direccionTrabajo = direccionTrabajo; }
    public double getLatitudTrabajo() { return latitudTrabajo; }
    public void setLatitudTrabajo(double latitudTrabajo) { this.latitudTrabajo = latitudTrabajo; }
    public double getLongitudTrabajo() { return longitudTrabajo; }
    public void setLongitudTrabajo(double longitudTrabajo) { this.longitudTrabajo = longitudTrabajo; }
}