
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

  public static void main (String[] args)  
  {
      ChatInterface client = null;
      GameInterface stub = null;
      
        try 
        {
            int choice = -1;
            Scanner input = null;
            
            // Gets the registry 
            Registry registry = LocateRegistry.getRegistry(2020); 

            // creating an instance of a chat to provide it to the server for callbacks
            client = new Chat();
            
            // Searching for the remote object in the registry 
            stub = (GameInterface) registry.lookup("RemoteGame"); 

            System.out.println(" >> Welcome! Guess a number in a range from 1 to 100. ");
            System.out.println(" >> Enter 0 to quit at any time.");

            // passing ChatInterface to a server
            // to create a callback possibility from there
            stub.setClient(client);
            
            input = new Scanner(System.in);

            
            while(choice != 0)
            {
                // new game is started right away, when the previous one is over
                stub.startGame();                            
                
                System.out.print("Enter your guess: ");
                choice = input.nextInt();
                
                if( choice != 0 )
                {
                if( (choice < 1) || (choice > 100) )
                System.out.println(" >> Wrong selection!");
                else
                    System.out.println(stub.newMove(choice)); // invoking method with a guessed number
                }
            }
            
            System.out.println(" >> Leaving the game! ");
            
            // deleting client from the server
            stub.removeClient(client);            

        }catch (Exception e) 
        {
            System.out.println("GameClient exception: " + e);
            e.printStackTrace();
        }
    }
    
}
