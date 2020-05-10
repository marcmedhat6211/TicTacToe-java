/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.teamdev.jxcapture.Codec;
import com.teamdev.jxcapture.EncodingParameters;
import com.teamdev.jxcapture.VideoCapture;
import com.teamdev.jxcapture.video.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class ServerTwoPlayerController implements Initializable {

    private Boolean stopPress=true;
    
    private int player1Score=0, player2Score=0, tieScore=0;
    private VideoCapture videoCapture;


    @FXML
    private AnchorPane towPlayerPane;
    @FXML
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
    private Button backBtn;
    @FXML
    private Button resetBtn;

    @FXML
    private MediaView mediaView;

    @FXML
    private Label firstPlayerName;
    @FXML
    private Label secondPlayerName;

    MediaPlayer player;
    @FXML
    private Button recordBtn;
    @FXML
    private Button stopBtn1;
    
    Button [] btns;
//    TwoOffline twooffline;
    
    Server2 server;

    
    
    class Server2 extends Server
    {
        void useLogicX(int i)
        {
            btns[i].setText("X");
        }
        void useLogicO(int i)
        {
            btns[i].setText("O");
            
        }
        
        void displayWinner(char w)
        {
            if(w=='x')
            {
                player1Score++;
                JOptionPane.showMessageDialog(null, "Player1 " + " wins");
            }
            if(w=='o')
            {
                player2Score++;
                JOptionPane.showMessageDialog(null, "Player2 " + " wins");
            }
            if(w=='t')
            {
                tieScore++;
                JOptionPane.showMessageDialog(null, "No One" + " wins");
            }
            for(int i=0 ; i<9 ; i++) 
            {
                btns[i].setDisable(true);
                System.out.println("==>"+getOrder(i));
            }
            System.out.println("Player1  Player2  tie");
            System.out.println(player1Score+"       "+player2Score+"        "+tieScore);
            
            resetBtn.setDisable(false);
        }
        
        void startAgain()
        {
            restart(22);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstPlayerName.setText("You");
        secondPlayerName.setText("Friend");
        btns = new Button[9];
        btns[0]=btn1;
        btns[1]=btn2;
        btns[2]=btn3;
        btns[3]=btn4;
        btns[4]=btn5;
        btns[5]=btn6;
        btns[6]=btn7;
        btns[7]=btn8;
        btns[8]=btn9;

//        firstPlayerName.setText(JOptionPane.showInputDialog("First Player", "Enter Your Name"));
//        secondPlayerName.setText(JOptionPane.showInputDialog("Second Player", "Enter Your Name"));

        server = new Server2();
        System.out.println("connection started server");
        resetBtn.setDisable(true);
        JFrame frame = new JFrame("Save your game");
int answer=JOptionPane.showConfirmDialog(
                            frame, "Would you like to save this game?",
                            "Save your game?",
                            JOptionPane.YES_NO_OPTION);
if (answer == JOptionPane.YES_OPTION) {
System.out.println("Deleted");}
        firstPlayerName.setText(JOptionPane.showInputDialog("First Player", "Enter Your Name(offline)"));
        secondPlayerName.setText(JOptionPane.showInputDialog("Second Player", "Enter Your Name"));
    }

    //When click on first cell
    @FXML
    private void cell00Click(ActionEvent event) {
        System.out.println("block 0");
        server.myTurn(0);
        
    }

    //When click on second cell
    @FXML
    private void cell01Click(ActionEvent event) {
        System.out.println("block 1");
        server.myTurn(1);
        
    }

    //When click on third cell
    @FXML
    private void cell03Click(ActionEvent event) {
        System.out.println("block 2");
        server.myTurn(2);
        
    }

    //When click on forth cell
    @FXML
    private void cell10Click(ActionEvent event) {
        System.out.println("block 3");
        server.myTurn(3);
        
    }

    //When click on fifth cell
    @FXML
    private void cell11Click(ActionEvent event) {
        System.out.println("block 4");
        server.myTurn(4);

    }

    //When click on sixth cell
    @FXML
    private void cell12Click(ActionEvent event) {
        System.out.println("block 5");
        server.myTurn(5);

    }

    //When click on seventh cell
    @FXML
    private void cell20Click(ActionEvent event) {
        System.out.println("block 6");
        server.myTurn(6);

    }

    /**
     * When click on eighth cell
     *
     * @param event
     */
    @FXML
    private void cell21Click(ActionEvent event) {
        System.out.println("block 7");
        server.myTurn(7);
        
    }

    //When click on ninth cell
    @FXML
    private void cell22Click(ActionEvent event) {
        System.out.println("block 8");
        server.myTurn(8);

    }

    /**
     * to back to main page
     *
     * @param event
     */
    @FXML
    private void onBack(ActionEvent event) {
//        gameMediaPlayer.stop();
//        failMediaPlayer.stop();
        try {
            server.close();
            Pane main = FXMLLoader.load(getClass().getResource("creatEnterGame.fxml"));
            towPlayerPane.getChildren().setAll(main);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * to start new game
     *
     * @param event
     */
    @FXML
    private void onReset(ActionEvent event) {
        restart(1);
    }
        @FXML
//    private void recordClick(ActionEvent event) throws IOException 
//    {
//        stopPress = false;
//        videoCapture = VideoCapture.create();
//        videoCapture.setVideoSource(new Desktop());
//        java.util.List<Codec> videoCodecs = videoCapture.getVideoCodecs();
//        System.out.println(videoCodecs);
//        Codec videoCodec = videoCodecs.get(0);
//        EncodingParameters encodingParameters = new EncodingParameters(new File("Replays//Replay"+firstPlayerName.getText().trim()+"VS"+secondPlayerName.getText().trim()+System.currentTimeMillis()+".mp4"));
//        encodingParameters.setSize(new Dimension(1000, 600));
//        encodingParameters.setBitrate(800000);
//        encodingParameters.setFramerate(10);
//        encodingParameters.setCodec(videoCodec);
//        videoCapture.setEncodingParameters(encodingParameters);
//        videoCapture.start();
//    }
    /**
     * to stop recording 
     * @param event
     *
     */
//    @FXML
//    private void stopClick(ActionEvent event) {
//        if(!stopPress)
//        {
//            videoCapture.stop();
//            stopPress = true;
//        }
//        else{
//            JOptionPane.showMessageDialog(null, "Please start recording");
//        }
//    }



    /*to restart the game*/
    public void restart(int num) 
    {
        for(int i=0 ; i<9 ; i++)
        {
            btns[i].setText("");
        }

        for(int i=0 ; i<9 ; i++) btns[i].setDisable(false);
        resetBtn.setDisable(true);
        if (num==1)
        {
            server.clear();
            server.turn=0;
            server.myTurn(22);
        }
    }
    
    /**
     * to play the video when win
     *
     */
//    public void playVedio() {
//        String workingDir = System.getProperty("user.dir");
//        File f = new File(workingDir, "src//game//music//videoplayback.wav");
//        Media m = new Media(f.toURI().toString());
//        MediaPlayer mp = new MediaPlayer(m);
//        MediaView mv = new MediaView(mp);
//        BorderPane borderPane = new BorderPane();
//        borderPane.getChildren().add(mv);
//        Scene scene = new Scene(borderPane, 600, 350);
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(scene);
//        stage.setTitle("You won!");
//        stage.show();
//        mp.play();
//    }

}
