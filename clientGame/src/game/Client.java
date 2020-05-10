/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
/**
 *
 * @author safaa
 */
public class Client {
    
    
	Socket mySocket;
	BufferedReader dis;
	//DataInputStream dis ;
	PrintStream ps;
	String checkConnection="";
                
	char[] XO={0};
	int turn=1;
        char winner=0;
        boolean running =true;
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
	Client()
	{
            if(game==0)
            {
                IpNetwork2 ip = new IpNetwork2();
                while(!checkConnection.equals("player1"))
                {
                    checkConnection="";
                    try
                    {
                        if(ip.checkIp())
                        {
                        mySocket = new Socket(InetAddress.getLocalHost(), 5005);
//                        mySocket = new Socket(ip.getNextAddress(), 6665);

                        mySocket.setSoTimeout(300);
                        dis = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                        ps = new PrintStream(mySocket.getOutputStream()); 

                        ps.println("player2");
                        checkConnection = dis.readLine();
                        if(checkConnection==null)
                        {
                            close();
                            checkConnection="";
                        }
                        }
                    }
                    catch(IOException ex)
                    {
                        System.out.println("connection Exception");
                    }
                }
                System.out.println("client is connected to : "+checkConnection);

                try 
                {
                    mySocket.setSoTimeout(100000);
                } catch (SocketException ex) {
                    System.err.println("TimeOut");;
                }

                try
                {
                    twooffline = new TwoOffline();
                    ReadMsg th = new ReadMsg();
                    th.start();
                }
                catch (IllegalThreadStateException e)
                {
                    System.out.println("thread client exception");
                }
            }
	}

	class ReadMsg extends Thread
	{
            public void run()
            {
                int i;
                while(running)
                {
                    i=yourTurn();
                    if(i==99)
                    {
                        System.out.println("server is down");
//                        close(); 
                        Platform.runLater(()->mainPage());
                        break;
                    }
                    else if(i==55)
                    {
                        System.out.println("client is closed");
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
                        }
                        else turn=0;
                    }
                }
            }
	}

	public void myTurn(int num)
	{
            if(turn==0)
            {
                System.out.println("send");
                if(num!=22)
                {
                    if(twooffline.start(num))
                    {
                        Platform.runLater(() ->useLogicO(num));
                        ps.println(num);
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
                    ps.println(num);
                    turn =1;
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
                    String readMsg = dis.readLine();
                    if(readMsg!=null)
                    {
                        final int indexFinal = Integer.parseInt(readMsg);
                        index = indexFinal;
                        System.out.println("client received=> "+ index);
                        if(index!=22)
                        {
                            if(twooffline.start(index))
                            {
                                Platform.runLater(() ->useLogicX(indexFinal));
                                System.out.println("");
                            }
                        }
                    }
                    else if(readMsg==null) index=99;
                }
                catch(IOException e)
                {
                    System.out.println("client is closed!!");
                    index=55;
                    close();
//                    e.printStackTrace();
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
            turn=1;
            
        }
        
        void startAgain()
        {
            
        }
        
        void mainPage()
        {
            
        }
        
	void close()
	{
		try
		{
                    running = false;
                    ps.close();
                    dis.close();
                    mySocket.close();
                    IpNetwork2.initIp();
		}
		catch(Exception ex)
		{
//			ex.printStackTrace();
		}
	}

    
}
