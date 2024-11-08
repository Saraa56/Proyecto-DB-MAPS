package co.edu.uniempresarial.proyectobd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RegistroEstudiantesForm extends JFrame {
    private JTextField txtCedula, txtNombres, txtApellidos, txtDireccionResidencia;
    private JTextField txtLatitudResidencia, txtLongitudResidencia;
    private JTextField txtDireccionTrabajo, txtLatitudTrabajo, txtLongitudTrabajo;

    public RegistroEstudiantesForm() {
        setTitle("Formulario de Registro de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);

        mainPanel.add(createLabeledField("Número de Documento:", txtCedula = new JTextField()));
        mainPanel.add(createLabeledField("Nombres:", txtNombres = new JTextField()));
        mainPanel.add(createLabeledField("Apellidos:", txtApellidos = new JTextField()));
        mainPanel.add(createLabeledField("Dirección de Residencia:", txtDireccionResidencia = new JTextField()));
        mainPanel.add(createLabeledField("Latitud de Residencia:", txtLatitudResidencia = new JTextField()));
        mainPanel.add(createLabeledField("Longitud de Residencia:", txtLongitudResidencia = new JTextField()));
        mainPanel.add(createLabeledField("Dirección de Trabajo:", txtDireccionTrabajo = new JTextField()));
        mainPanel.add(createLabeledField("Latitud de Trabajo:", txtLatitudTrabajo = new JTextField()));
        mainPanel.add(createLabeledField("Longitud de Trabajo:", txtLongitudTrabajo = new JTextField()));

        JButton btnAbrirMapa = new JButton("Abrir Mapa");
        btnAbrirMapa.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAbrirMapa.addActionListener(e -> abrirMapa());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnAbrirMapa);

        JButton btnObtenerCoordenadas = new JButton("Obtener Coordenadas");
        btnObtenerCoordenadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnObtenerCoordenadas.addActionListener(e -> cargarCoordenadas());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnObtenerCoordenadas);

        JButton btnGuardar = new JButton("Guardar Usuario");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(this::guardarUsuario);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnGuardar);
    }

    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    private void abrirMapa() {
        try {
            URI uri = new URI("file:///C:/Users/camb0/OneDrive/Escritorio/google_maps.html");
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void cargarCoordenadas() {
        Map<String, Double> coordenadas = WebServer.getCoordenadas();
        txtLatitudResidencia.setText(String.valueOf(coordenadas.get("latitud")));
        txtLongitudResidencia.setText(String.valueOf(coordenadas.get("longitud")));
    }

    private void guardarUsuario(ActionEvent e) {
        try {
            int cedula = Integer.parseInt(txtCedula.getText());
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String direccionResidencia = txtDireccionResidencia.getText();
            double latResidencia = Double.parseDouble(txtLatitudResidencia.getText());
            double lngResidencia = Double.parseDouble(txtLongitudResidencia.getText());
            String direccionTrabajo = txtDireccionTrabajo.getText();
            double latTrabajo = Double.parseDouble(txtLatitudTrabajo.getText());
            double lngTrabajo = Double.parseDouble(txtLongitudTrabajo.getText());

            EstudianteDto estudiante = new EstudianteDto(
                    cedula, nombres, apellidos, direccionResidencia, 
                    latResidencia, lngResidencia, direccionTrabajo, latTrabajo, lngTrabajo
            );

            EstudianteDAO estudianteDAO = new EstudianteDAO();
            estudianteDAO.insertEstudiante(estudiante);

            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}