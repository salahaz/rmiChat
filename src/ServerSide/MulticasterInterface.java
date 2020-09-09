/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author salahazekour
 */
public interface MulticasterInterface extends Remote {
    void multicast(String message, String ip, int port) throws RemoteException;
}
