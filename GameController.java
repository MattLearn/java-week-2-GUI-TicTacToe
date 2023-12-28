package com.example.week2gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    // Buttons are named as if in a 2D array
    //[00 01 02]
    //[10 11 12]
    //[20 21 22]

    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private Text headerTxt;

    @FXML
    private GridPane grid;

    private int turnCounter = 0;

    ArrayList<Button> buttonGrid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //headerTxt.setText("Tic Tac Toe");
        buttonGrid = new ArrayList<>(Arrays.asList(btn00,btn01,btn02,btn10,btn11,btn12,btn20,btn21,btn22));
        buttonGrid.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    private void setupButton(Button button){
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameOver();
        });
    }

    @FXML
    private void restartGame(ActionEvent event){
        buttonGrid.forEach(this::resetButton);
        headerTxt.setText("Tic-Tac-Toe");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        grid.setStyle("-fx-background-color: #ffffff;");
        button.setText("");
    }
    private void checkIfGameOver() {
        for(int winCondition = 0; winCondition < 8; winCondition++){
            String line = switch(winCondition){
                // horizontal win cases
                case 0 -> btn00.getText() + btn01.getText() + btn02.getText();
                case 1 -> btn10.getText() + btn11.getText() + btn12.getText();
                case 2 -> btn20.getText() + btn21.getText() + btn22.getText();
                // Diagonal win cases
                case 3 -> btn00.getText() + btn11.getText() + btn22.getText();
                case 4 -> btn02.getText() + btn11.getText() + btn20.getText();
                // Vertical win cases
                case 5 -> btn00.getText() + btn10.getText() + btn20.getText();
                case 6 -> btn01.getText() + btn11.getText() + btn21.getText();
                case 7 -> btn02.getText() + btn12.getText() + btn22.getText();
                //default
                default -> null;
            };

            if(line.equals("XXX")){
                headerTxt.setText("X wins!");
                grid.setStyle("-fx-background-color: #98fb98;");
            }
            else if(line.equals(("OOO"))){
                headerTxt.setText("O wins!");
                grid.setStyle("-fx-background-color: #ff9a9a;");
            }
        }
    }


    private void setPlayerSymbol(Button button) {
        if(turnCounter % 2 == 0){
            button.setText("X");
        }
        else{
            button.setText("O");
        }
        turnCounter+=1;
    }
}