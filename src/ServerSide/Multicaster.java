/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author salahazekour
 */
public class Multicaster extends UnicastRemoteObject implements MulticasterInterface {
    
    public Multicaster() throws RemoteException {
    
    }
    
    public void multicast(String message, String ip, int port) {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName(ip);
            byte[] msg = message.getBytes();
            DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
            socket.send(packet);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Multicaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
