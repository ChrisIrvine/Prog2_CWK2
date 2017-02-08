/*
 * Main Class to test the Deck.java file. Each method is tested in any/all
 * cases. 
 */
package Q1;

import Q1.Deck.OddEvenIterator;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ruw12gbu, 100036248
 */
public class CardTest 
{
    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException
    {
        //Create a new deck of cards
        Deck deck = new Deck();
        
        //Create an int object to be used during testing 
        int i;
        
        //Testing the size() method in the Deck class
        System.out.println("Testing the size() method... \n");
        System.out.println("The size of the deck is: " + deck.size());     
        
        //Formatting for result readability
        System.out.println("-------------------------------------------------"
                + "----------");
        
        //Formatting for results
        System.out.println("Within the deck of cards there are the following "
                + "cards: ");
    
        System.out.println("Testing the getCurrent() method and the "
                + "constructor method... \n");
        //Iterate over the deck of cards and print out each card
        for (i = 0; i < deck.size(); i++) 
        {
            System.out.println(deck.getCurrent(i).toString());
        }
        
        //Formatting for result readability
        System.out.println("-------------------------------------------------"
                + "----------");
        
        //Reset i
        i = 0;
        
        //Testing the deal() method
        System.out.println("Testing the deal() method... \n"
                + "The top 5 Cards are: ");
        while(i <= 4)
        {
            System.out.println(deck.deal().toString());
            i++;
        }
        System.out.println("\nThere are " + deck.size() + " cards remaining: ");
        
        //print out the remaining cards
        for (i = 0; i < deck.size(); i++) 
        {
            System.out.println(deck.getCurrent(i).toString());
        }
        
        //Formatting for result readability
        System.out.println("-------------------------------------------------"
                + "----------");
        
        //Testing the newDeck() method
        System.out.println("Testing the newDeck() method...\n");
        
        //Call newDeck() method to referesh the deck of cards
        deck.newDeck();
        
        System.out.println("Refreshed deck containing " + deck.size() 
                + " cards: ");
        
        //print out the cards
        for (i = 0; i < deck.size(); i++) 
        {
            System.out.println(deck.getCurrent(i).toString());
        }
        
        //Formatting for result readability
        System.out.println("-------------------------------------------------"
                + "----------");
        
        //Testing the shuffle() method
        
        System.out.println("Testing the shuffle() method... \n");
        deck.shuffle();
        
        System.out.println("Shuffled deck, containing " + deck.size() 
                + " cards: ");
        
        //print out the cards
        for (i = 0; i < deck.size(); i++) 
        {
            System.out.println(deck.getCurrent(i).toString());
        }
        
        //Formatting for result readability
        System.out.println("-------------------------------------------------"
                + "----------");
        
        System.out.println("Testing the OddEvenIterator nested class... \n");
        
        //Create OddEvenIterator object, called oE
        OddEvenIterator oE = new OddEvenIterator();
        
        //Call the oddEvenResult() method
        oE.oddEvenResult();
    }
}
