

import java.rmi.*;
import java.util.Iterator;
import java.util.Vector;

public class TheGame implements GameInterface {
    
    private int number = 0;
    private String gameState = null;
    private boolean gameOver = true;
    public Vector<ChatInterface> clients= new Vector<ChatInterface>();
    
   
    public TheGame ()
    {
        super();
    }

    @Override
    public void gameOverNotify() throws RemoteException {
        
        this.gameOver = true;
        this.gameState = "\n >> !!! This game is over. The right number was " + this.number +" !!! \n";
        
        if(this.clients.size() >= 0)
        {
            for (ChatInterface ch : this.clients)
            {
                ch.send(gameState);
            }            

        }
    }

    @Override
    public String newMove(int guess) throws RemoteException {
        
        if(guess < this.number)
        {
            return " >> The number is bigger, than you've guessed.";
        }
        else if(guess > this.number)
        {
            return " >> The number is smaller, than you've guessed.";
        }
        else
        {
            this.gameOverNotify();
            return " >> \t !!! CONGRATULATIONS, YOU WON !!! \n";
        }
    }
    
    private int randomizeNumber()
    {
        this.number  = 1 + (int)(Math.random() * ((100 - 1) + 1));
        System.out.println(" >> The number is: " + this.number);
        return this.number;
    }
    
    @Override
    public void startGame() throws RemoteException
    {
        if(this.gameOver)
        {
            this.gameOver = false;
            this.gameState = " >> A new game has been started. \n";
            
            if(this.clients.size() >= 0)
            {
                
                for (ChatInterface ch : this.clients)
                {
                    ch.send(gameState);
                }

            }
            
            randomizeNumber();
        }
    }

    // adding a reference to a client's interface to use later for callbacks
    @Override
    public void setClient(ChatInterface c) throws RemoteException {
        clients.add(c);
    }

    // removes a client
    @Override
    public void removeClient(ChatInterface c) throws RemoteException {
        if(this.clients.size() >= 0)
            {
                if(this.clients.contains(c))
                {
                    System.out.println(" >> Client removed.");
                    this.clients.removeElement(c);
                }
            }
    }
    
 }