/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package game;

import java.net.*;
import java.util.*;


/************this class is used to handle the problem of getting **************/
        /*********loopback address instead of ip address***********/
public class IpNetwork2 
{
    static int i=2;
    static int i2=1;
    static InetAddress addresses;
    static int count=0;
    byte[] ip=null;
    
	IpNetwork2() 
    {
	    try 
        {
    		ip = GetExternalIp();
    		System.out.println("myIp: "+InetAddress.getByAddress(ip).toString());
	    } 
        catch (Exception e) 
        {
		    e.printStackTrace();
	    }

	}

/***********************getting ip address of the machine**********************/
    public byte[] GetExternalIp()throws SocketException
    {
    	byte[] ip=null;
        Enumeration e;

        e = NetworkInterface.getNetworkInterfaces();                            //interface generates a series of elements
                                                                                //NetworkInterface represents a Network Interface made up of a name, and a 									      list of IP addresses assigned to this interface.
                                                                                    /*fe80:0:0:0:98d8:ca1e:d8dd:bc1c%wlp2s0
                                                                                    192.168.1.6
                                                                                    0:0:0:0:0:0:0:1%lo
                                                                                    127.0.0.1*/

    	NetworkInterface n = (NetworkInterface) e.nextElement();
    	Enumeration ee = n.getInetAddresses();
    	if (ee.hasMoreElements())
    	{
            InetAddress i = (InetAddress) ee.nextElement();
            i = (InetAddress) ee.nextElement();
    	    try
            {
    		    ip = i.getAddress();                                        //return address in array of bytes
    	    	System.out.println(InetAddress.getByAddress(ip));
    		
    	    } 
            catch (Exception ex) 
            {
    		    ex.printStackTrace();     
    	    }
        }
	    return ip;

    }
    /*******************check if this Ip is reachable or not*******************/
    boolean checkIp()
    {
        final int j = i; 
        final int j2 = i2;
        i++; 
        if(i==255)
        {
            i=1;
            i2++;
            if(i2==255)
            {
                i=1;
                i2=1;
            }
        }
        
        try 
        {
            ip[3] = (byte)j;
            ip[2] = (byte)j2;
            InetAddress address = InetAddress.getByAddress(ip);
            String output = address.toString().substring(1);
                addresses=address;
                System.out.println(address.isReachable(10) + " is time reachable "+ output);
            if (address.isReachable(20)) 
            {
                addresses=address;
                System.out.println(output + " is on the network");
                return true;
            } 
        } 
        catch (Exception e) 
        {
            System.out.println("problem");
            e.printStackTrace();
        }
        return false;
    }
    
    static void initIp()
    {
        i=68;
    }

    public InetAddress getNextAddress()
    {
        return addresses;
    }
}
    
