/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author FreeComp
 */
public class OfOnChoiceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane onOfPane;
    @FXML
    private Button onlineBtn;

    @FXML
    private Button offlineBtn;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
        @FXML
    void goMain(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("MainXML.fxml"));
            onOfPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void goOfflineGame(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("playersOff.fxml"));
            onOfPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void goOnlineChoice(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("creatEnterGame.fxml"));
            onOfPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
