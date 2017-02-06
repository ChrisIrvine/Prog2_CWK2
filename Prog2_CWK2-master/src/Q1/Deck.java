/*
 * Author:      ruw12gbu, 100036248
 *
 * Description: This class takes a LinkedList of Card Objects so that it acts 
 *              like a Deck of Cards would. You can deal from the Deck Object 
 *              into a Hand Object (or another deck object). Shuffle the Deck 
 *              into a random order. There is also a nested class that will 
 *              split the Deck into the cards at Odd Positions and Even 
 *              Positions. This class is Serializable (serializationID 101) and
 *              Iterable. 
 *
 * Version:     1.0 - Created
 *              1.1 = Shuffle method reworked to not use Collections.shuffle
 *              1.2 - OddEvenIterator nested class implemented
 *              1.3 - OddEvenIterator reworked
 *              1.4 - Adjustments from testing results
 *              1.5 - Comments refined.
 */
package Q1;

import Q1.Card.Rank;
import Q1.Card.Suit;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * @author ruw12gbu, 100036248
 */
public class Deck implements Iterable, Serializable
{
    static final long serialVersionUID = 101;
    
    private static List<Card> deck;
    
    /**
     * Constructor method for the Deck class. This creates a new LinkedList of 
     * Card Objects, called deck, and class upon the newDeck() method to fill 
     * the deck with the 52 Card Objects.
     */
    public Deck()
    {
        this.deck = new <Card>LinkedList();
        this.newDeck();
    }
    
    /**
     * Method to clear and fill a new deck of 52 cards. Iterates over the Suit 
     * enum from Card.java, creating one card for each Rank enum in the Suit, 
     * before moving onto the next Suit. 
     */
    public final void newDeck()
    {
        deck.clear();
        for(Suit s: Suit.values())
        {
            for(Rank r: Rank.values())
            {
                deck.add(new Card(r, s));
            }
        }
    }
    
    /**
     * Mutator method to the return and remove the top card of the deck
     * LinkedList. This mimics the action of dealing a card from a deck of cards
     * @return - the first object in the LinkedList deck (the top card)
     */
    public Card deal()
    {
        return deck.remove(0);
    }
    
    /**
     * Accessor method to return the size of the deck of cards. 
     * @return - a single integer that is the number of cards remaining in the
     *              deck.
     */
    public int size()
    {
        return deck.size();
    }
    
    /**
     * Mutator method to randomly shuffle a deck of cards. 
     */
    public void shuffle()
    {
        //Create a Random object called random
        Random random = new Random();
        //Iterate over the deck of cards
        for (int i = 0; i < deck.size(); i++) 
        {
            //Create a random number between 0 and 52 (53 exclusive)
            int j = random.nextInt(52);
            //Hold the current card
            Card holdCard = deck.get(j);
            //Replace the randomly selected card with the current card
            deck.set(j, deck.get(i));
            //Fill in the empty slot with the current card.
            deck.set(i, holdCard);
        }
    }
    
    /**
     * Accessor method to get the current card when iterating over the deck from
     * another class. Primarily used for testing purposes at this stage.
     * @param i - the index of the card you want to return.
     * @return 
     */
    public Card getCurrent(int i)
    {
        return deck.get(i);
    }

    @Override
    public Iterator iterator() 
    {
        return this.deck.iterator();
    }
    
    public static class OddEvenIterator implements Iterator<Card>
    {
        private int currentCard;
        private List<Card> evenDeck;
        private List<Card> oddDeck;

        public OddEvenIterator()
        {
            currentCard = 0;
            this.evenDeck = new <Card>LinkedList();
            this.oddDeck = new <Card>LinkedList();
        }
        
        public void oddEvenResult()
        {
            for (currentCard = 0; currentCard < deck.size(); currentCard++) 
            {
                if((currentCard % 2) == 1)
                {
                    oddDeck.add(deck.get(currentCard));
                }
                else
                {
                    evenDeck.add(deck.get(currentCard));
                }
            }
            //print out the odd cards
            System.out.println("The odd position cards are: \n");
            for (int i = 0; i < oddDeck.size(); i++) 
            {
                System.out.println(oddDeck.get(i).toString());
            }          
            //print out the odd cards
            System.out.println("\nThe even position cards are: \n");
            for (int i = 0; i < evenDeck.size(); i++) 
            {
                System.out.println(evenDeck.get(i).toString());
            }   
        }
        
        /*
         * Following methods included because of errors being thrown, never 
         * actually used. The next() method jumps a card, allowing for a 
         * different way of getting all the odd and even cards seperated. The
         * hasNext() method checks to see if there is a card left in the deck.
         */
        @Override
        public Card next()
        {
            currentCard += 2;
            return deck.get(currentCard);
        }
        
        @Override
        public boolean hasNext()
        {
            return (currentCard +2 < deck.size());
        }
    }
}
