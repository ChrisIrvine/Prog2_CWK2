package question2;

import question1.Card;
import question1.Deck;
import question1.Hand;
import java.util.*;

public class BasicCheat implements CardGame
{
    private Player[] players;
    private int nosPlayers;
    public static final int MINPLAYERS=5;
    private int currentPlayer;
    private Hand discards;
    private Bid currentBid;
    

    public BasicCheat()
    {
        this(MINPLAYERS);
    }
    
    public BasicCheat(int n)
    {
        nosPlayers=n;
        players=new Player[nosPlayers];
        
        for(int i=0;i<nosPlayers;i++)
        {
            players[i]=(new BasicPlayer(new BasicStrategy(),this));
        }
        
        currentBid=new Bid();
        currentBid.setRank(Card.Rank.TWO);
        currentPlayer=0;
    }

    @Override
    public boolean playTurn()
    {
        //lastBid=currentBid;
        //Ask player for a play
        
        System.out.println("current bid = "+currentBid);
        currentBid=players[currentPlayer].playHand(currentBid);
        
        System.out.println("Player bid = "+currentBid);
        
        //Add hand played to discard pile
        discards.add(currentBid.getHand());
        
        //Offer all other players the chance to call cheat
        boolean cheat=false;
        
        for(int i=0;i<players.length && !cheat;i++)
        {
            if(i!=currentPlayer)
            {
                cheat=players[i].callCheat(currentBid);
                if(cheat)
                {
                    System.out.println("Player called cheat by Player "+(i+1));
                    if(isCheat(currentBid))
                    {	
                        //CHEAT CALLED CORRECTLY
                        //Give the discard pile of cards to currentPlayer who 
                        //then has to play again                      
                        players[currentPlayer].addHand(discards);
                        System.out.println("Player cheats!");
                        System.out.println("Adding cards to player "+
                                (currentPlayer+1));
                    }
                    else
                    {
                        //CHEAT CALLED INCORRECTLY
                        //Give cards to caller i who is new currentPlayer
                        System.out.println("Player Honest");
                        
                        players[i].addHand(discards);
                        System.out.println("Adding cards to player "+
                                (i+1));
                    }
                    
                    currentPlayer=i;
                    //If cheat is called, current bid reset to an empty bid with
                    //rank two whatever the outcome
                    currentBid=new Bid();
                    //Discards now reset to empty	
                    discards=new Hand();
                }
            }
        }
        if(!cheat)
        {
            //Go to the next player       
            System.out.println("No Cheat Called");        
            currentPlayer=(currentPlayer+1)%nosPlayers;   
        }
        return true;
    }
    
    @Override
    public int winner()
    {
        for(int i=0;i<nosPlayers;i++)
        {
            if(players[i].cardsLeft()==0)
            {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void initialise()
    {
        //Create Deck of cards
        Deck d=new Deck();
        d.shuffle();
        
        //Deal cards to players
        Iterator<Card> it=d.iterator();
        int count=0;
        
        while(it.hasNext())
        {
            players[count%nosPlayers].addCard(it.next());
            it.remove();
            count++;
        }
        //Initialise Discards
        discards=new Hand();
        
        //Chose first player (shouldnt current player be that with the TWO CLUBS)
        currentPlayer=0;
        for (int i = 0; i < nosPlayers; i++) 
        {
            if(players[i].whosFirst() == true)
            {
                currentPlayer = i;
            }
        }
        currentBid=new Bid();
        currentBid.setRank(Card.Rank.TWO);
    }
    
    public void playGame()
    {
        initialise();
        //Turn counter
        int c=0;
        Scanner in = new Scanner(System.in);
        boolean finished=false;
        
        while(!finished)
        {
            //Play a hand
            System.out.println("Cheat turn for player "+(currentPlayer+1));
            playTurn();
            System.out.println("Current discards =\n"+discards.toString());
            c++;
            System.out.println("Turn "+c+ " Complete. Press any key to "
                    + "continue or enter Q to quit>");
            String str=in.nextLine();
            
            if(str.equals("Q")||str.equals("q")||str.equals("quit"))
            {
                finished=true;
            }
            int w=winner();
            if(w != -1)
            {
                System.out.println("The Winner is Player "+(w+1));
                finished=true;
            }

        }
    }
    
    public static boolean isCheat(Bid b)
    {
        for(int i = 0; i < b.getHand().handSize(); i++)
        {
            if(b.getHand().getCard(i).getRank() != b.r)
            {
                return true;
            }   
        }
        return false;
    }
    
    public static void main(String[] args)
    {
        BasicCheat cheat=new BasicCheat();
        cheat.playGame();
    }
}
