/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.game;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class GameUIFXMLController implements Initializable {
    Random random = new Random();
    int yAxis = random.nextInt(400 - (-400) + 1) + (-400);
    int xAxis = -900;
    @FXML
    private AnchorPane gameWindow;
    @FXML
    private Rectangle player1;
    @FXML
    private Rectangle player2;
    @FXML
    private Circle ball;
    TranslateTransition transition;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MovementController mvmt = new MovementController(player1, gameWindow);
        collisionTimer.start();
        makeTransition();
        
    }    
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
        
    }));
    
    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            checkCollision();
        }
    };
    
    public void checkCollision(){
        if(player1.getBoundsInParent().intersects(ball.getBoundsInParent()) || player2.getBoundsInParent().intersects(ball.getBoundsInParent())){
            System.out.println("Ball Hit!");
            transition.stop();
            transition.setToX(-xAxis);
            transition.play();
        }
    }
    
    public void makeTransition(){
        
        transition = new TranslateTransition();

        transition.setNode(ball);
        transition.setDuration(Duration.millis(5000));
        transition.setToX(xAxis);
        transition.setToY(yAxis);
        System.out.println(yAxis);
        transition.play();
    }
}
