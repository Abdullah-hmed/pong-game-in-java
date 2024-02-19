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
    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    
    private BooleanBinding keyPressed = wPressed.or(sPressed).or(upPressed).or(downPressed);
    
    Random random = new Random();
    
    int movementSpeed = 4;
    
    public MovementController(Rectangle player1, Rectangle player2, AnchorPane gameWindow) {
        this.player1 = player1;
        this.player2 = player2;
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
            
            if(upPressed.get() && player2.getLayoutY() > 0) {
                player2.setLayoutY(player2.getLayoutY() - movementSpeed);
                System.out.println(player2.getLayoutY());
            }

            if(downPressed.get() && player2.getLayoutY() < GAME_HEIGHT){
                player2.setLayoutY(player2.getLayoutY() + movementSpeed);
                System.out.println(player2.getLayoutY());
            }
        }
    };
    
    public void movementSetup(){
        gameWindow.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }
            
            if(e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }
            
            if(e.getCode() == KeyCode.UP) {
                upPressed.set(true);
            }
            
            if(e.getCode() == KeyCode.DOWN) {
                downPressed.set(true);
            }
        });

        gameWindow.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }
            
            if(e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }
            
            if(e.getCode() == KeyCode.UP) {
                upPressed.set(false);
            }
            
            if(e.getCode() == KeyCode.DOWN) {
                downPressed.set(false);
            }
        });
    }
    
}
