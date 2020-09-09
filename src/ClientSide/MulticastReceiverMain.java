/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ServerSide.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author salahazekour
 */
public class MulticastReceiverMain {
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        
        String multicasterURL = "rmi://localhost/Multicast";
        MulticasterInterface stub = (MulticasterInterface) Naming.lookup(multicasterURL);
        /**try {
            Registry registry = LocateRegistry.getRegistry(host);
            stub = (MulticasterInterface) registry.lookup("Multicast");
            
            
            
        } catch(Exception e) {
           System.err.println("Client exception: " + e.toString());
           e.printStackTrace(); 
        }*/
        
        new Thread(new MulticastReceiver(stub)).start();
    }
    
}
