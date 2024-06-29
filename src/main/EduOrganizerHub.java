/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */

/*
Fichero principal de la Apliación
*/
public class EduOrganizerHub extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/LoginForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("EduOrganizer Hub | Aplicacion de gestión educativa");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
