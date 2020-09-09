/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ServerSide.MulticasterInterface;

/**
 *
 * @author salahazekour
 */
public class MulticastReceiver implements Runnable {
    private static final String IP_ADDRESS = "239.192.0.1";
    private static final int PORT = 4321;
    private String name;
    public MulticasterInterface stub;
    private RmiChatUI gui;
    
    protected MulticastReceiver(MulticasterInterface stub) {
        this.stub = stub;
    }
    
    public void receiveMessage(String ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(PORT);
        InetAddress group = InetAddress.getByName(IP_ADDRESS);
        socket.joinGroup(group);
        
        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println(message);
            displayMessage(message + "\n");
            if("EXIT".equals(message)) {
                System.out.println("Exiting!!");
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }
    
   
    
    public void sendMessage(String message) throws RemoteException {
        
        stub.multicast(message, IP_ADDRESS, PORT);
   
    }
    
    public void displayMessage(String message) {
        gui.jTextArea1.append(message);
    }
    
    public void setUsername(String username) {
        name = username;
       
    }

    public void initgui() throws IOException {
        
        gui = new RmiChatUI();
        gui.setVisible(true);
        displayMessage("Waiting for message!!!\n");
        
        gui.jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    String message = gui.jTextField1.getText();
                     gui.jTextField1.setText("");
                    try { 
                        sendMessage(name + ": "+ message);
                    } catch (Exception ex) {
                        Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
        });
        
        gui.jButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    name = gui.jTextField2.getText();
                    setUsername(name);
                    gui.setTitle(name + " Chat");
                    gui.jTextField1.setText("");
                    try {
                        
                        sendMessage(name + " joined the Chat!!!");
                        
                    } catch(Exception ex) {
                        Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }
                }
        });
        
        receiveMessage(IP_ADDRESS, PORT);
    }
    
    
    
    
    @Override
    public void run() {
        
        try {
            initgui();
        } catch (IOException ex) {
            Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
