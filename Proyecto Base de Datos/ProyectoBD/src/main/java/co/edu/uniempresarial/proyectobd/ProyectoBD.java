package co.edu.uniempresarial.proyectobd;

import javax.swing.SwingUtilities;

public class ProyectoBD {

    public static void main(String[] args) {
      

  
        SwingUtilities.invokeLater(() -> {
            RegistroEstudiantesForm frame = new RegistroEstudiantesForm();
            frame.setVisible(true);
        });
    }
}
