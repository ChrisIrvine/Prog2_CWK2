/*
 * Author:      ruw12gbu, 100036248
 *
 * Description: This class implements the Player interface and will create 
 *              Player objects for a computer to control. This allows the game
 *              of Cheat to be played. Additional methods includes a method to 
 *              see which player should go first, as per the rules of the game.
 *            
 * Version:     1.0 - Created
 *              1.1 - Tested
 *              1.2 - whosFirst() method added
 *              1.3 - refined and comments added
 */
package Q2;

import Q1.Card;
import Q1.Card.Rank;
import Q1.Card.Suit;
import Q1.Hand;
import java.util.List;

/**
 * @author ruw12gbu, 100036248
 */
public class BasicPlayer implements Player
{
    //variable across all player objects
    private final Hand playerHand;
    private CardGame game;
    private Strategy strategy;
    private final BasicCheat cheat;
    
    /**
     * Constructor method that takes a Strategy object and a Cheat object.
     * @param basicS - which strategy to use
     * @param basicC - initial bid
     */
    public BasicPlayer(BasicStrategy basicS, BasicCheat basicC)
    {
        playerHand = new Hand();
        strategy = basicS;
        cheat = basicC;
    }
    
    /**
     * Override method that adds a hand of cards to the hand of the player
     * @param addHand - hand object containing cards to add to players hand
     */
    @Override
    public void addHand(Hand addHand)
    {
        playerHand.add(addHand);
    }
    
    /**
     * Override method to add a single card to a players hand
     * @param addCard - card object to add to the players hand
     */
    @Override
    public void addCard(Card addCard)
    {
        playerHand.add(addCard);
    }
    
    /**
     * Method to choose what game to play
     * @param newGame 
     */
    @Override
    public void setGame(CardGame newGame)
    {
        game = newGame;
    }
    
    /**
     * Tells what Strategy the player object should be using
     * @param strat 
     */
    @Override
    public void setStrategy(Strategy strat)
    {
        strategy = strat;
    }
    
    /**
     * Method to play a turn in the game
     * @param prevBid - bid to compare against
     * @return 
     */
    @Override
    public Bid playHand(Bid prevBid)
    {
        boolean isCheat = strategy.cheat(prevBid, playerHand);
        return strategy.chooseBid(prevBid, playerHand, isCheat);
    }
    
    /**
     * Method to determine if there are any cards left in the hand
     * @return - 0 means the hand is empty
     */
    @Override
    public int cardsLeft()
    {
        return playerHand.handSize();
    }
    
    /**
     * Method to determine if a cheat should be called (and is called).
     * @param currBid - the current bid being played
     * @return 
     */
    @Override
    public boolean callCheat(Bid currBid)
    {
        return strategy.callCheat(playerHand, currBid);
    }
    
    /**
     * Accessor method to return the cards in the players hand
     * @return 
     */
    public List<Card> getHand()
    {
        return this.playerHand.getCards();
    }
    
    /**
     * Accessor method to determine which player should go first. This should
     * always be the player who is holding the TWO of CLUBS.
     * @return 
     */
    @Override
    public boolean whosFirst()
    {
        //Card Object to look for, in this case TWO of CLUBS
        Card firstCard = new Card(Rank.TWO, Suit.CLUBS);
        //Test to see if this player has the TWO of CLUBS in their hand
        return this.playerHand.findCard(firstCard);
    }
}
