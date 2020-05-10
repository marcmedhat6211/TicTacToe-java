/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.net.*;
import java.io.*;
import javafx.application.Platform;
/**
 *
 * @author safaa
 */
public class Server {
    
    
	ServerSocket server;
	Socket c;
	BufferedReader input;
	PrintStream output;
	String checkConnection="";
	char[] XO={0};
	int turn=0;
        char winner=0;
        boolean running = true;
        int win=0;
        int tie=0;
        int game=0;
        
        void useLogicX(int i)
        {
            
        }
        void useLogicO(int i)
        {
            
        }
        
        
        TwoOffline twooffline ;

	Server()
	{
            if(game==0)
            {
                while(!checkConnection.equals("player2"))
                {
                    checkConnection="";
                    try
                    {
                        server = new ServerSocket(6665);
                        c = server.accept();
                        input = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        output = new PrintStream(c.getOutputStream()); 

                        checkConnection = input.readLine();
                        if(checkConnection.equals("player2"))
                        {
                            output.println("player1");
                        }
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println("server is connected to: "+checkConnection);

                try
                {
                    twooffline = new TwoOffline();
                    ReadMsg th = new ReadMsg();
                    th.start();
                }
                catch (IllegalThreadStateException e)
                {
                    System.out.println("thread server exception");
                }
            }

	}

	class ReadMsg extends Thread
	{
            public void run()
            {
                int i=88;
                while(running)
                {
//                    if(winner!=0) break;
                    i = yourTurn();
                    if(i==99)
                    {
                        System.out.println("client closed");
                        close(); 
                        mainPage();
                        break;
                    }
                    else if(i==22)
                    {
                        System.out.println("start again");
                        Platform.runLater(()->startAgain());
                        clear();
                    }
                    else if (i!=88)
                    {
                        winner();
                        if(winner!=0) 
                        {
                            turn = 1;
//                            break;
                        }
                        else turn=0;
                    }
                }
            }
	}

	void myTurn(int num)
	{
            if(turn==0)
            {
                System.out.println("send");
                if(num!=22)
                {
                    if(twooffline.start(num))
                    {
                        Platform.runLater(() -> useLogicX(num));
                        output.println(num);
                        winner();
                        if(winner!=0)
                        {
                            turn =1;
                        }
                        else turn = 1;
                    }
                }
                else
                {
                    output.println(num);
                }
            }
	}


	int yourTurn() 
	{
            int index=88;
            System.out.print("");
            if(turn==1) 
            {
                try
                {
                    System.out.println("receive");
                    String readMsg = input.readLine();
                    if(readMsg!=null)
                    {
                        final int indexFinal = Integer.parseInt(readMsg);
                        index = indexFinal;
                        if(index!=22)
                        {
                            if(twooffline.start(index))
                            {
                                Platform.runLater(() ->useLogicO(indexFinal));
                                System.out.println("");
                            }
                        }
                    }
                    else if(readMsg==null) index=99;
                }
                catch(IOException e)
                {
                    System.out.println("client closed!!");
                    index=99;
                    close();
                }
            }
            return index;
	}

        
        void winner()
        {
            winner = twooffline.whoWon();
            if(winner=='x')
            {
                displayWinner('x');
            }
            else if(winner=='o')
            {
                displayWinner('o');
            }
            else if(winner=='t')
            {
                displayWinner('t');
            }
            
        }
        
        void displayWinner(char w)
        {
            
        }
        
        void startAgain()
        {
            
        }
        
        void mainPage()
        {
            
        }
        
        int getOrder(int i)
        {
            return twooffline.Database_order[i];
        }
        
        void clear()
        {
            game++;
            winner=0;
            win=0;
            tie=0;
            running = true;
            twooffline = new TwoOffline();
            turn=0;
            
        }
        
	void close()
	{

		try
		{
                    running = false;
                    output.close();
                    input.close();
                    c.close();
		}
		catch(Exception ex)
		{
                    ex.printStackTrace();
		}
	}

    
}
