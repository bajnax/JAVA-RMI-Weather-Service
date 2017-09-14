
import java.rmi.Remote;
import java.rmi.RemoteException;

// it is used for callbacks
public interface ChatInterface extends Remote {
    public void send(String msg) throws RemoteException;
}
