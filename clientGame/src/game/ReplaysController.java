/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class ReplaysController implements Initializable {
    @FXML
    private AnchorPane replayPane;
     Queue<Integer> order = new LinkedList<>(); 
     private String value = "";
    boolean flag = true;


    @FXML
    private ListView<String> replays;
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button next;
    
    public String drawX() {
        return "X";
        
    }
    
    public String draw0() {
        return "O";
    }
    private void getGame(int gameNumber )
    {
        try
        { String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            System.out.println(gameNumber);
            
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt =con.prepareStatement("select num from games where gameorder= ?");
            stmt.setInt(1, gameNumber);
            ResultSet rs = stmt.executeQuery();
     
            while(rs.next())
                {
                    order.add(rs.getInt("num"));
           
                }

            con.close();
            
        }
        
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getGame(ListController.index);
          
          }
    
    @FXML
    void cell00Click(ActionEvent event) {
        if (flag)
            //btn1.setText(drawX());
            System.out.println("X");
        else
//            btn1.setText(draw0());
            System.out.println("O");
        flag = !flag;
            
        //System.out.println("ONE");
       
    }

    @FXML
    void cell01Click(ActionEvent event) {
        if (flag)
            btn2.setText(drawX());
        else
            btn2.setText(draw0());
        flag = !flag;
        
    }

    @FXML
    void cell03Click(ActionEvent event) {
        if (flag)
            btn3.setText(drawX());
        else
            btn3.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell10Click(ActionEvent event) {
        if (flag)
            btn4.setText(drawX());
        else
            btn4.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell11Click(ActionEvent event) {
        if (flag)
            btn5.setText(drawX());
        else
            btn5.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell12Click(ActionEvent event) {
        if (flag)
            btn6.setText(drawX());
        else
            btn6.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell20Click(ActionEvent event) {
        if (flag)
            btn7.setText(drawX());
        else
            btn7.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell21Click(ActionEvent event) {
        if (flag)
            btn8.setText(drawX());
        else
            btn8.setText(draw0());
        flag = !flag;
    }

    @FXML
    void cell22Click(ActionEvent event) {
        if (flag)
            btn9.setText(drawX());
        else
            btn9.setText(draw0());
        flag = !flag;
    }

    @FXML
    void onBack(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("List.fxml"));
            replayPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onNext(ActionEvent event) {
        if(!order.isEmpty())
        {
            int num = order.remove();

            switch(num)
            {
                case 1: cell00Click(event);
                        break;
               case 2: cell01Click(event);
                        break;
               case 3: cell03Click(event);
                        break;
               case 4: cell10Click(event);
                        break;
               case 5: cell11Click(event);
                        break;
               case 6: cell12Click(event);
                        break;
               case 7: cell20Click(event);
                        break;
               case 8: cell21Click(event);
                        break;
               case 9: cell22Click(event);
                        break;
            }
        }
         

    }

    @FXML
    void openVideo(MouseEvent event) {

    }


    
}
