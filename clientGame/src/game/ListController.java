/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author FreeComp
 */
public class ListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane listPane;

    @FXML
    private ListView<String> replayList;

    @FXML
    private Button listBackBtn;
    Queue<String> fplayers = new LinkedList<>();
      Queue<String> splayers = new LinkedList<>();
      Queue<Integer> order = new LinkedList<>();
      public static int index;
    protected int checkDB()
    {
        int retval = -1;
        
         try
        {
            String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            String query1 = "select count(distinct(gameorder)) as gameorder from games";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            
           
            
            while(rs1.next())
                {
                   retval = rs1.getInt("gameorder");
           
                }
            
            st1.close();
            con.close();
            
        }
        
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
         finally
         {
             return retval;
         }
        
    }
     protected void addNames()
    {
        // try to access the DB to get all the names of the played games.
        try
        {
            String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            String query1 = "select * from names";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            
           
            
            while(rs1.next())
                {
//                   retval = rs1.getInt("gameCount");
                    fplayers.add(rs1.getString("fplayer"));
                    splayers.add(rs1.getString("splayer"));
   
                }
            
            st1.close();
            con.close();
            
        }
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       addNames();
       System.out.println(checkDB());
     for(int i=0;i<checkDB();i++)
     {
         String str = fplayers.remove()+"VS"+splayers.remove();
         System.out.println(str);
         replayList.getItems().add(str);
     }
     replayList.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            //getGames(replayList.getSelectionModel().getSelectedIndex());
            int num = replayList.getSelectionModel().getSelectedIndex()+1;
            System.out.println("Game number "+replayList.getSelectionModel().getSelectedIndex());
            index  = num;
            goReplaysGrid( event);
//           
        }
    });
     
        
       
    }  
    
     @FXML
    void goMain(ActionEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("MainXML.fxml"));
            listPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void goReplaysGrid(MouseEvent event) {
        try {
            Pane main = FXMLLoader.load(getClass().getResource("Replays.fxml"));
            listPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
