


import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.Vector;
 
public interface GameInterface extends Remote {
    public void gameOverNotify() throws RemoteException;
    public String newMove(int guess) throws RemoteException;
    public void startGame() throws RemoteException;    
    public void setClient(ChatInterface c)throws RemoteException; 
    public void removeClient(ChatInterface c) throws RemoteException;
}