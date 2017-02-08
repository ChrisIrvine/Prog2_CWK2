/*
 * Main Class to test the Card.java file. Each method is tested in any/all
 * cases. 
 */
package Q1;
import Q1.Card.Rank;
import Q1.Card.Suit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author ruw12gbu, 100036248
 */
public class CardTest 
{
    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException
    {
        //Create a scanner object
        Scanner scan = new Scanner(System.in);
        
        //Create objects for the nested classes within Card.java
        Card.CompareDescending compDesc = new Card.CompareDescending();
        Card.CompareSuit compSuit = new Card.CompareSuit();
        
        //Create testCards objects to see if the constructor works correctly.
        Card testCard1 = new Card(Rank.THREE, Suit.DIAMONDS);
        Card testCard2 = new Card(Rank.SEVEN, Suit.SPADES);
        Card testCard3 = new Card(Rank.TWO, Suit.DIAMONDS);
        Card testCard4 = new Card(Rank.QUEEN, Suit.HEARTS);
        Card testCard5 = new Card(Rank.ACE, Suit.CLUBS);
        
        //Store test cards in an array of Card Objects.
        Card[] testDeck = new Card[5];
        
        //Add in the testCard objects int testDeck array
        testDeck[0] = testCard1;
        testDeck[1] = testCard2;
        testDeck[2] = testCard3;
        testDeck[3] = testCard4;
        testDeck[4] = testCard5;
        
        //Output formatting
        System.out.println("-------------------------------");
        
        /*
         * Iterate over the testDeck to print out the value to the testCard, the
         * rank value of the testCard and the Suit Value of the card. 
         */
        for (int i = 0; i < testDeck.length; i++) 
        {
            System.out.println("Test Card " + (i+1));
            System.out.println("Card Value: " + testDeck[i]);
            System.out.println("Rank Value: " + testDeck[i].rank.getValue());
            System.out.println("Suit Value: " + testDeck[i].suit.getSuitNum());
            System.out.println("-------------------------------");
        }
        
        //Test the getNext() method
        System.out.println("Testing the getNext() method. Expected result is to"
                + " return the next value \nin the enum Rank from the selected "
                + "card, in this case FOUR (as selected card is THREE).\n"
                + "Result: " + testDeck[0].rank.getNext());
        
        //Test the toString() Method
        System.out.println("Testing the toString() method that will return the "
                + "selected card in a string. \nIn this case it should be THREE"
                + " DIAMONDS. \n"
                + "Result: " + testDeck[0].toString());
        
        //Test the difference() Method
        System.out.println("Testing the difference() method that will returns "
                + "the difference in ranks \nbetween two cards. Expected "
                + "results is 2-12 = -11 (because it looks at the position in\n"
                + "the enum. This indicates that the second card is a higher "
                + "position on the enum than the first.\n"
                + "Results: " + testDeck[0].difference(testCard5));
        
        //Test the differenceValue() Method
        System.out.println("Testing the differenceValue() method that will "
                + "return the difference in value\nbetween two cards. Expected"
                + " results is 11-3 = -8, indicating that the second \ncard is "
                + "higher than the first.\n" 
                + "Results: " + testDeck[0].differenceValue(testCard5));
        
        //Test the CompareSuit nested class
        System.out.println("Testing the CompareSuit class. Expected result is a"
                + " 2 (as Diamonds have a value of 3 and Clubs\nhave a value of"
                + "1) this shows that Diamonds are higher than Clubs (due to "
                + " positive output).\n" 
                + "Result: " + compSuit.compare(testCard1, testCard5 ));
        
        //Test the second case of the Compare Suit nested class
        System.out.println("Testing the CompareSuit class with two cards of the"
                + " same suit. Expected outcome is that their\nranks will be "
                + "compared instead.(2-3 = 1)\n"  
                + "Result: " + compSuit.compare(testCard1, testCard3));
        
        //Test the CompareDescending nested class
        System.out.println("Testing the CompareDescending class. Expected "
                + "result is a -8 (as 3-11 = -8) this shows that\nthe second card"
                + " is lower than the first.\n"
                + "Result: " + compDesc.compare(testCard1, testCard5));
    }
}
