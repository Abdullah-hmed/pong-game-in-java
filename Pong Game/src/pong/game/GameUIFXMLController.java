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
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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
    int ballX = 0;
    int ballY = 0;
    int BALL_SPEED  = 2;
    int ballXSpeed = BALL_SPEED;
    int ballYSpeed = BALL_SPEED;
    int player1Score = 0;
    int player2Score = 0;
    boolean enableAI;
    @FXML
    private AnchorPane gameWindow;
    @FXML
    private Rectangle player1;
    @FXML
    private Rectangle player2;
    @FXML
    private Circle ball;
    
    @FXML
    private Rectangle boundBoxTop;

    @FXML
    private Rectangle boundBoxBottom;
    
    @FXML
    private Label p1Score;
    
    @FXML
    private Label p2Score;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MovementController mvmt = new MovementController(player1, player2, gameWindow);
        collisionTimer.start();
        
    }
    
    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            checkCollision(player1);
            checkCollision(player2);
        }
    };
    
    public void checkCollision(Rectangle player){
        if(enableAI){
            player2.setTranslateY(ballY);
            System.out.println("AI Enabled");
        }
        if(ball.localToScene(ball.getBoundsInLocal()).getMinX() > gameWindow.getWidth()){
            player1Score++;
            System.out.println("Player 1 scored! "+player1Score);
            p1Score.setText(""+player1Score);
            ballX = 0;
            ballY = random.nextInt(4 - (-4) + 1) + (-4);
        }
        if(ball.localToScene(ball.getBoundsInLocal()).getMinX()<0 ){
            player2Score++;
            System.out.println("Player 2 scored! "+player2Score);
            p2Score.setText(""+player2Score);
            ballX = 0;
            ballY = random.nextInt(4 - (-4) + 1) + (-4);
        }
        if(player.getBoundsInParent().intersects(ball.getBoundsInParent()) || player.getBoundsInParent().intersects(ball.getBoundsInParent())){
            System.out.println("Ball Hit!");
            ballXSpeed = -ballXSpeed;
        }
        ballX += ballXSpeed;
        if(boundBoxTop.getBoundsInParent().intersects(ball.getBoundsInParent()) || boundBoxBottom.getBoundsInParent().intersects(ball.getBoundsInParent())){
            System.out.println("Wall Hit!");
            ballYSpeed = -ballYSpeed;
        }
        ballY += ballYSpeed;
        ball.setTranslateX(ballX);
        ball.setTranslateY(ballY);
    }
    
    public void enableAI(){
        enableAI = true;
    }
}
