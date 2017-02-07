/*
 * Author:      ruw12gbu, 100036248
 * 
 * Description: This class is the backbone of the entire Cheat! project. It
 *              contains two enums, Rank and Suit, that for each Card Object. 
 *              There are 13 Ranks and 4 Suits, creating a possible 52 
 *              combinations of Card Objects. Each Rank and Suit has a value
 *              assigned to it that will be used whilst playing Cheat! This 
 *              class also contains a few Override methods that override the 
 *              default methods in the Comparable class. This allows proper 
 *              comparisons between two Card Objects (in order to see if which
 *              one is higher, lower or if they are equal). This class is also
 *              Serializable (serializationID 100).
 *
 * Version:     1.0 - Created
 *              1.1 - Override methods reworked
 *              1.2 - Override methods refined
 *              1.3 - Adjustments from test results.
 *              1.4 - Comments refined.
 */
package Q1;
import java.io.Serializable;
import java.util.Comparator;

/**
 * @author ruw12gbu, 100036248
 */
public class Card implements Comparable<Card>, Serializable
{
    //Declare serialisationID as 100
    static final long serialVersionUID = 100;

    //Create enum called Rank
    public enum Rank
    {
        //Declare enum values for the ranks
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5),
        SIX (6),
        SEVEN (7),
        EIGHT (8),
        NINE (9),
        TEN (10),
        JACK (10),
        QUEEN (10),
        KING (10),
        ACE (11);
        
        //Declare private value field
        private int value;
        
        /**
         * Constructor method for the Rank enum. Takes a single integer 
         * parameter.
         * @param r - the value of the rank 
         */
        Rank (int r)
        {
            value = r;
        }
        
        /**
         * Method to return the next value in the enum list from a given card. 
         * Uses the Card object that was used to call this method. If the Card 
         * object has a rank value of a FOUR then FIVE will be returned.
         * @return - the enum value that is next on the enum list.
         */
        public Rank getNext()
        {
            //Create rank object to hold the value of the next rank.
            Rank next;
            //if condition to check position is not at the end of the enum list.
            if(this.ordinal() < Rank.values().length - 1)
            {
                /*
                 * if position is not at the end of the list then select the
                 * next position on the list.
                 */
                next = Rank.values()[this.ordinal() + 1];
            }
            else
            {
                // Otherwise go to the front of the list.
                next = Rank.values()[0];
            }
            //Return next object.
            return next;
        }
        
        /**
         * Accessor Method to return the Rank Value of the current Card Object. 
         * @return
         */
        public int getValue()
        {
            return this.value;
        }
    }
    
    //Create enum called Suit
    public enum Suit
    {
        //Declare enum values for Suit
        CLUBS (1),
        HEARTS (2),
        DIAMONDS (3),
        SPADES (4);

        //Create int value to hold the Suit Value
        private final int suitNum;

        /**
         * Constructor method for the Suit enum. Takes a single integer
         * parameter that sets the Suit of the card.
         * @param s 
         */
        Suit(int s)
        {
            this.suitNum = s;
        }

        /**
         * Accessor method to return the Suit Number of the current card object.
         * @return 
         */
        public int getSuitNum()
        {
            return this.suitNum;
        }
    }
    
    //Create Rank variable
    Rank rank;
    //Create Suit variable
    Suit suit;

    public int getRankValue()
    {
        return this.rank.value;
    }
    /**
     * Constructor for Card Objects. This method takes two parameters (detailed)
     * below. Used to fill a Deck of Cards (52) card objects.
     * @param r - rank of the card, between TWO & ACE (ACE High)
     * @param s - suit of the card, HEARTS, CLUBS, DIAMONDS, SPADES
     */
    public Card(Rank r, Suit s)
    {
        this.rank = r;
        this.suit = s;
    }

    /**
     * Accessor Method to return this Card's Rank enum.
     * @return 
     */
    public Rank getRank()
    {
        return rank;
    }

    /**
     * Accessor Method to return this Card's Suit enum.
     * @return 
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Method to override the Comparator class' compareTo method. This compareTo 
     * method will check to see which card is lower. If the first card is lower
     * a negative number will be the result, otherwise it will be a positive 
     * number. that the rank of the cards are the same. If the ranks are the 
     * same, the suits are compared, results are interpetted in the same manner.
     * @param c - Card to be compared against.
     * @return - Negative = First Card is lower. Positive = Second Card is lower
     */
    @Override
    public int compareTo(Card c)
    {
        //Check for identical ranks
        if(this.rank == c.getRank())
        {
            //Current Card Suit value - Compare Card Suit value
            return this.suit.ordinal() - c.getSuit().ordinal();
        }
        else
        {
            //Current Card Rank value - Compare Card Rank value
            return this.rank.ordinal() - c.getRank().ordinal();
        }
    }
    
    /**
     * Method to override the String class' toString method. Simply outputs the
     * current Card object's information in a readable manner.
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append(rank).append(" ").append(suit);

        return str.toString();
    }
    
    /**
     * Method to return the difference in the ranks between the two cards.
     * @param c - Card to be compared against.
     * @return 
     */
    public int difference(Card c)
    {
        //Current Card Rank position - Compare Card Rank position
        return this.rank.ordinal() - c.getRank().ordinal();
    }
    
    /**
     * Method to return the difference in value in the ranks between two cards.
     * @param c - Card to be compared against.
     * @return 
     */
    public int differenceValue(Card c)
    {
        //Current Card Rank value - Compare Card Rank value
        return this.rank.getValue() - c.getRank().getValue();
    }

    /*
     * Nested class to be used to sort the cards into a descending order. Has
     * only one method that is to call the compareTo(Card c) method (as noted
     * above). Descending order is to be done by order of Rank, Suit is to be
     * only used when two Card Objects have the same Rank.
     */
    public static class CompareDescending implements Comparator<Card>
    {
        /**
         * Method to override the compare() method of the Comparator class. This
         * calls the compareTo(Card c) method as listed above. Returns a single 
         * integer. Negative means Card c is lower. Positive means Card c1 is 
         * lower.
         * @param c - Current Card Object.
         * @param c1 - Card Object to compare against.
         * @return 
         */
        @Override
        public int compare (Card c, Card c1)
        {
            //Call and return the compareTo method
            return c1.compareTo(c);
        }
    }
    
    /*
     * Nested class to be used to sort Card Objects into ascending order of
     * suit. Once sorted into suits cards should be in ascending order by rank.
     * Has one method, to override the compare() method.
     */
    public static class CompareSuit implements Comparator<Card>
    {
        /**
         * Method to override the compare() method of the Comparator Class. This
         * compares the position on the enum. Checks to see if the Suits are the
         * same. If they are the Ranks are checked, otherwise Suits are checked.
         * @param c - First Card Object
         * @param c1 - Second Card Object
         * @return - Negative Integer = first Card Object is higher
         *           Positive Integer = second Card Object is higher
         */
        @Override
        public int compare(Card c, Card c1)
        {
            //Check if Suits are the the same
            if (c.getSuit() == c1.getSuit())
            {
                //if Suits are the same then test the Ranks
                return c.getRank().ordinal() - c1.getRank().ordinal();
            }
            else
            {
                //Otherwise, check test the Suits
                return c.getSuit().ordinal() - c1.getSuit().ordinal();
            }
        }
    }
}
