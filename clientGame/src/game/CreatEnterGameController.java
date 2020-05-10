/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author FreeComp
 */
public class CreatEnterGameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane creatEnterPane;
    @FXML
    private Button createGameBtn;

    @FXML
    private Button enterBtn;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
        @FXML
    void goChoice(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("ofOnChoice.fxml"));
            creatEnterPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void goCreateGame(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("serverTwoPlayer.fxml"));
            creatEnterPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void goEnterGame(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("TwoPlayerFXML.fxml"));
            creatEnterPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
