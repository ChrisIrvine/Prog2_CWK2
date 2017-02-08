/*
 * Deck class for package Q1. This class is to hold methods to do the following:
 * 1. Contain a list of all cards.
 * 2. Constrcutor should create the list and initialise all the cards in the 
 *      deck. A deck should start with all possible 52 cards.
 * 3. Method to shuffle() the deck that randomises cards. For full marks write 
 *      your own shuffle method rather than using the built in one in 
 *      Collections.
 * 4. Implement a method called deal() that removes the top card from the deck
 *      and returns it.
 * 5. Method called size() that returns the size of the deck and a method called
 *      newDeck() that reinitialises the deck.
 * 6. A nested Iterator class called OddEvenIterator that iterates over the 
 *      Cards sorting them into odd positioned cards then even positioned cards.
 * 7. Make the class Iteratable so that by default it traverses it in order they
 *      will be dealt.
 * 8. Make the class Serializable, with serialization ID 101. Make it so that 
 *      the deck is saved with the cards in OddEvenIterator order. 
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
