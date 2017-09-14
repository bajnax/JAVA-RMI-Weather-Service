
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class GameServer
{
    public static void main (String[] argv)
    {
        try
        {
            // Instantiating the implementation class
            TheGame game = new TheGame();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            GameInterface stub = (GameInterface) UnicastRemoteObject.exportObject(game, 0);

            // Binding the remote object (stub) in the registry
            LocateRegistry.createRegistry(2020);
            Naming.rebind("//localhost:2020/RemoteGame", stub);

            System.out.println("The game server is ready.");

        } catch (Exception e)
        {
            System.out.println("The game server failed to start: " + e);
            e.printStackTrace();
        }
    }
}
