/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Alejandro Sanz Mediavilla
 * 
 * Muestra ventanas de error, confirmación o alerta en las distintas pantallas
 */
public class MensajeAlerta {

    private Alert alert;

    /*
    MENSAJE DE EXITO
    */
    public void successMessage(String message) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje Informativo - EduOrganizer Hub");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /*
    MENSAJE DE ERROR
    */
    public void errorMessage(String message) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mensaje de error - EduOrganizer Hub");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /*
    MENSAJE DE CONFIRMACIÓN
    */
    public boolean confirmMessage(String message) {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensaje de confirmación - EduOrganizer Hub");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> option = alert.showAndWait();

        return option.get().equals(ButtonType.OK);

    }

}