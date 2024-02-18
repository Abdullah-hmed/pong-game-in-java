/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.game;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class MovementController {
    int GAME_HEIGHT = 350;
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
    
    Random random = new Random();
    
    int movementSpeed = 4;
    
    public MovementController(Rectangle player1, AnchorPane gameWindow) {
        this.player1 = player1;
        this.gameWindow = gameWindow;

        movementSetup();

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    
        
    @FXML
    private Rectangle player1;

    @FXML
    private Rectangle player2;
    
    @FXML
    private AnchorPane gameWindow;
    
    public void movementListener(){
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }
    
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            
            if(wPressed.get() && player1.getLayoutY() > 0) {
                player1.setLayoutY(player1.getLayoutY() - movementSpeed);
                System.out.println(player1.getLayoutY());
            }
            

            if(sPressed.get() && player1.getLayoutY() < GAME_HEIGHT){
                player1.setLayoutY(player1.getLayoutY() + movementSpeed);
                System.out.println(player1.getLayoutY());
            }
            /*
            if(aPressed.get()){
                player1.setLayoutX(player1.getLayoutX() - movementSpeed);
                
            }

            if(dPressed.get()){
                player1.setLayoutX(player1.getLayoutX() + movementSpeed);
                
            }
            */
        }
    };
    
    public void movementSetup(){
        gameWindow.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        gameWindow.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }
    
}
