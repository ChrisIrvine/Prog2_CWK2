/*
 * Hand class for package Q1. This class is to hold methods to do the following:
 * 1. Contain three constructors, one empty, one taking an array and one taking
 *      another hand object.
 * 2. Serializable with serializationID 102
 * 3. Count the number of each rank and suit in the hand, to be displayed in as
 *      a histogram (work in progress).
 * 4. Store the total value of the cards in the hand. (work in progress?)
 * 5. Have three add methods, one for a single card, one for a Collection typed 
 *      to Card and one for another Hand Object.
 * 6. Have three remove methods, one for a single card, one for all the cards in
 *      the hand and one for a specific position in the Hand Object. First two
 *      should return a boolean and the last should return the removed card.
 * 7. Should be Iterable, implementing Iterator
 * 8. sortAscending method (using Card compareTo)
 * 9. sortDescending method (using CompareDescending)
 * 10. countSuit that takes a suit argument and checks the card for Cards of 
 *      that Suit.
 * 11. countRank that takes a rank argument and checks the card for Cards of 
 *      that Rank.
 * 12. handValue method that returns the total value of the ranks
 * 13. toString to display the card
 * 14. isFlush that returns true if cards are all of the same Suit
 * 15. isStraight that returns ture if cards are all in consecutive order
 */
package Q1;
import Q1.Card.CompareDescending;
import Q1.Card.Rank;
import Q1.Card.Suit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ruw12gbu, 100036248
 */
public class Hand implements Serializable, Iterable
{
    static final long serialVersionUID = 102;
    
    private List<Card> hand;
    
    /**
     * Default constructor that accepts no arguments and returns an empty hand.
     */
    public Hand()
    {
        //Create a new LinkedList
        this.hand = new <Card>LinkedList();
        
    }
    
    /**
     * Constructor that takes an array of Cards, called c, and adds them to a 
     * hand. 
     * @param cards - Collection of Cards to be added to a hand
     */
    public Hand(Card [] cards)
    {
        //Create a new LinkedList.
        this.hand = new <Card>LinkedList();
        
        //Iterate over the Collection, adding the cards to the LinkedList, hand.
        this.hand.addAll(Arrays.asList(cards));
    }
    
    /**
     * Constructor that takes a Hand object and copies it into a new Hand Object
     * @param otherHand - Hand to be copied into another hand.
     */
    public Hand(Hand otherHand)
    {
        //Create a new LinkedList
        this.hand = new <Card>LinkedList();
        
        //Copy all objects in otherHand into the new LinkedList hand 
        hand.addAll(otherHand.getCards());
    }
    
    /**
     * Mutator method that clears the current Hand Object.
     */
    public void clearHand()
    {
        this.hand.clear();
    }
    
    /**
     * Accessor method to return the number of Card Objects in the hand.
     * @return 
     */
    public int handSize()
    {
        return this.hand.size();
    }
    
    /**
     * Accessor method to return all Card Objects in the hand.
     * @return 
     */
    public List<Card> getCards()
    {
        return this.hand;
    }

    /**
     * Mutator method to add a single card to a Hand Object
     * @param card - Card to add to the hand
     */
    public void add(Card card)
    {
        this.hand.add(card);
    }
    
    /**
     * Mutator method to add a Collection of Cards to a Hand Object
     * @param cards - Cards to add to hand
     */
    public void add(Collection<Card> cards)
    {
        this.hand.addAll(cards);
    }
    
    /**
     * Mutator method to add all the cards in from another Hand to the current
     * hand object.
     * @param otherHand - Hand to copy cards from
     */
    public void add(Hand otherHand)
    {
        //Iterate over the otherHand Hand object
        for(Card c : otherHand.getCards())
        {
            hand.add(c);
        }
    }
    
    /**
     * Mutator method, that returns a boolean True/False result, that removes a 
     * single card from a Hand Object. It checks each card in the hand; if it 
     * finds the card that was passed into the method as an argument, it removes
     * the card. Otherwise no card is removed.
     * @param removeCard - Card Object to be removed.
     * @return - True if card is found and removed. False if no card found.
     */
    public boolean remove(Card removeCard)
    {
        //Iterate over the hand, Card by Card
        for(Card card : this.hand)
        {
            //Check to see if the card is the card to be removed
            if(card.compareTo(removeCard)==0)
            {
                //remove the card
                this.hand.remove(card);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mutator method, that returns a boolean True/False result, to remove all 
     * the cards from a Hand Object. 
     * @param otherHand
     * @return 
     */
    public boolean remove(Hand otherHand)
    {
        boolean removedAll = true;
        Card[] removedCards = new Card [otherHand.hand.size()];
        for (int i = 0; i < this.hand.size(); i++) 
        {
            for (int j = 0; j < otherHand.hand.size(); j++) 
            {
                if(hand.get(i).compareTo(otherHand.hand.get(j)) == 0)
                {
                    removedCards[j] = hand.get(i);
                    hand.remove(i);
                }
            }
        }
        
        for (Card removedCard : removedCards) {
            if (removedCard == null) {
                removedAll = false;
            }
        }
        
        return removedAll;           
    }
    
    /**
     * Mutator method to remove a Card Object at a given position from a Hand
     * Object.
     * @param position - index of the Card Object to be removed.
     * @return 
     */
    public Card remove(int position)
    {
        //Save the removed Card Object
        Card removedCard = hand.remove(position);
        
        //Return the removed Card Object
        return removedCard;
    }
    
    /**
     * Override method for the Iterator class
     * @return 
     */
    @Override
    public Iterator<Card> iterator() 
    {
        return hand.iterator();
    }

    /**
     * Mutator method to sort a Hand Object into ascending order (lowest to 
     * highest). It does this by comparing each card in turn, one by one. If the
     * first card is found to be higher than the second card then they it is 
     * swapped with the second card.
     */
    public void sortAscending()
    {
        Collections.sort(this.hand);
    }
    
    /**
     * Mutator method to sort a Hand Object into Descending Order (highest to 
     * lowest). It does this by comparing each card in turn, one by one. If the
     * first card is found to be lower than the second card then they it is 
     * swapped with the second card.
     */
    public void sortDescending()
    {
        Collections.sort(hand, new CompareDescending());
    }

    /**
     * Accessor method to work out how many of each Suit is in the Hand Object.
     * @param suitCheck - Suit to check for.
     * @return 
     */
    public int countSuit(Suit suitCheck)
    {
        //Counter for the found Cards
        int suitCount = 0;
        
        //Iterate over the hand
        for(int i = 0; i < this.hand.size(); i++)
        {
            /* Check if the Suit of the Card Object matches the Suit to check 
             * against
             */
            if(hand.get(i).getSuit() == suitCheck)
            {
                //Add 1 to the counter
                suitCount++;
            }
        }
        //Return the number of found Card Objects
        return suitCount;
    }

    /**
     * Accessor method to work out how many of each Rank is in the Hand Object. 
     * @param rankCheck - Rank to check for
     * @return 
     */
    public int countRank(Rank rankCheck)
    {
        //Create a counter
        int rankCount = 0;
        
        //Iterate over the Hand Object
        for(int i = 0; i < this.hand.size(); i++)
        {
            /* Check that the selected card object has the same Rank as the Rank
             * to check against.
             */
            if(this.hand.get(i).getRank() == rankCheck)
            {
                rankCount++;
            }
        }
        return rankCount;
    }
    
    /**
     * Accessor method to work out the total value of the ranks in the current 
     * Hand Object.
     * @return 
     */
    public int handValue()
    {
        //Counter variable
        int handValueCount = 0;
        
        //Iterate over the Hand Object
        for (int i = 0; i < hand.size(); i++) 
        {
            //Add the Value of the Rank to the Counter Variable
            handValueCount += hand.get(i).rank.getValue();
        }
        
        //Return the Counter Variable containing the total Value of the Hand. 
        return handValueCount;
    }
    
    /**
     * Method to iterate over a Hand Object and produce a string that can be 
     * printed out.
     * @return 
     */
    @Override 
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < this.hand.size(); i++)
        {
            str.append(this.hand.get(i).toString()).append("\n");

        }
        return str.toString();
    }
    
    /**
     * Accessor method to check if the current Hand Object contains a Flush,
     * where all cards are of the same suit. Returns true if a Flush is found.
     * @return 
     */
    public boolean isFlush()
    {
        //Check to see if the card is empty
        if(this.hand.size() > 1)
        {
            //Iterate starting at the second card
            for(int i = 1; i < hand.size(); i++)
            {
                //Compare ith card to the first card, if not same suit return
                //false
                if(hand.get(i).getSuit() != hand.get(0).getSuit())
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Accessor method that check the current hand object to see if it contains 
     * a Straight, meaning that all cards are in consecutive order (lowest to 
     * highest). This is done by having a int variable hold the rank of the last
     * card checked and comparing it against the current one. If the difference 
     * is more than 1 then the hand is not a Straight. Iteration is started at 
     * the second card.
     * @return 
     */
    public boolean isStraight()
    {
        //sort the cards into Ascending order
        sortAscending();
        //Check if the hand is empty
        if(this.hand.size() > 1)
        {
            //Iterate over hand, starting at second card.
            for(int i = 1; i < hand.size(); i++)
            {
                //Variable to hold rank value of previous card
                int lastCardRank = hand.get(i-1).getRank().getValue();
                //check the difference betweent the cards
                if(hand.get(i).getRank().getValue() - lastCardRank != 1)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
