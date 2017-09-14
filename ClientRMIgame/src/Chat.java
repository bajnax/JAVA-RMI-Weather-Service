
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Chat extends UnicastRemoteObject implements ChatInterface {

    public Chat() throws RemoteException
    {
        super();
    }
    
    @Override
    public void send(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
}
