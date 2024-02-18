/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.game;


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author SC
 */
public class PongGame extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInterfaceFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GameUIFXML.fxml"));
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}