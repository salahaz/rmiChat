/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 *
 * @author salahazekour
 */
public class MulticasterMain {
    
    public static void main(String[] args) throws RemoteException {
        
        System.setProperty("java.security.policy", "policy.all");
	System.setProperty("java.rmi.server.hostname","localhost");
        
        try {
            Multicaster object = new Multicaster();
            Naming.rebind("Multicast", object);
            
            System.out.println("Server ready!!!");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        
        
    }
       
}
