/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

public class InitMenuController extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tacebook/view/InitMenu.fxml"));
            Scene scene = new Scene(loader.load());

            // Configurar el título y la escena
            primaryStage.setTitle("Tacebook - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Llamar al método launch para iniciar la aplicación JavaFX
    }
}
