/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

import java.util.List;
import question1.Card;
import question1.Hand;

/**
 * @author ruw12gbu, 100036248
 */
public class HumanPlayer implements Player
{
    //variable across all player objects
    private final Hand playerHand;
    private CardGame game;
    private Strategy strategy;
    private final BasicCheat cheat;
    
    /**
     * Constructor method that takes a Strategy object and a Cheat object.
     * @param strat - which strategy to use
     * @param basicC - initial bid
     */
    public HumanPlayer(Strategy strat, BasicCheat basicC)
    {
        playerHand = new Hand();
        strategy = strat;
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
        //Display their turn options to the player
        System.out.println("It's your turn! You cards are: \n" 
                + this.playerHand.toString() + "\nThe previous bid is "
                + prevBid.toString() + ". Please play a card...");
        return strategy.chooseBid(prevBid, playerHand, true);
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
        Card firstCard = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        //Test to see if this player has the TWO of CLUBS in their hand
        return this.playerHand.findCard(firstCard);
    }
    
    /**
     * Accessor method that returns a string containing the hand of the player
     * @return 
     */
    @Override
    public String printHand()
    {
        return this.playerHand.toString();
    }

    /**
     * Mutator method that sorts the hand into Ascending Order.
     */
    @Override
    public void sortedHand() 
    {
        this.playerHand.sortAscending();
    }
}
