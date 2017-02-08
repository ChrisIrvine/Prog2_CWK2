/*
 * Author:      ruw12gbu, 100036248
 *
 * Description: This class contains the methods required to allow the user to 
 *              paricipate in the game of cheat against computer controlled 
 *              players or other users. The methods here are, for the most part
 *              stripped down versions of the methods found in BasicStrategy 
 *              as the decision making is left to the user rather than the 
 *              program. Error catching could be added but time constraints
 *              permitted against it, simply add more logic for OutOfBounds 
 *              from user input and give the chance for the user to go back and 
 *              remake decisions before playing their card.
 *
 * Version:     1.0 - Created
 *              1.1 - Tested and refined
 *              1.2 - Comments added and refined
 */
package question2;

import question1.Hand;
import java.util.Scanner;
import question1.Card.Rank;
import question1.Card;

/**
 *
 * @author ruw12gbu, 100036248
 */
public class HumanStrategy implements Strategy 
{

    /**
     * This method detects if the selected cards to play, from the chooseBid() 
     * method, is a cheat bid or not. Allowing the user to decide what rank the
     * cheat bid will be. Returns true if the proposed bid is a cheat bid.
     * @param prevBid - the last bid played
     * @param bidHand - the proposed bid
     * @return 
     */
    @Override
    public boolean cheat(Bid prevBid, Hand bidHand) 
    {
        //Boolean variable to return
        boolean cheat;

        //Check for cards of the current bid's rank
        if(bidHand.countRank(prevBid.getRank()) != 0)
        {
            //player is not cheating
            cheat = false;
        }
        //Check for cards of the next rank in the list
        else if(bidHand.countRank(prevBid.getRank().getNext()) != 0)
        {
            //player is not cheating
            cheat = false;
        }
        else
        {
            //player is cheating
            cheat = true;
        }
        return cheat;
    }
    
    /**
     * Method that allows the player to play cards in the game of Cheat! It 
     * gives the option for the player to Cheat and play up to Four cards per 
     * turn. Follows the rules of the game, and will automatically give 
     * additional options when the player does decide to cheat.
     * @param prevBid - the last bid played
     * @param playerHand - the hand of the human player
     * @param isCheat - unused
     * @return 
     */
    @Override
    public Bid chooseBid(Bid prevBid, Hand playerHand, boolean isCheat) 
    {
        //Variables and objects to be used during this method
        Scanner scan = new Scanner(System.in);
        int numberToPlay;
        Hand bidHand = new Hand();
        boolean iCheat;
        Card.Rank cheatRank;
        Card.Rank honestRank = Rank.TWO;
        Bid bid = new Bid();
        
        //User prompt
        System.out.println("Please enter the number of cards you would like "
                + "to bid (between 1 and 4)...");
        numberToPlay = scan.nextInt();
        
        //Iterate the number of times the user wants to play a card
        for (int i = 0; i < numberToPlay; i++) 
        {
            //User prompt
            System.out.println("Please which card(s) you would like to play "
                    + "(Select the card number): ");
            
            //Remove the selected card into a temporary card object
            Card tempCard = playerHand.remove(scan.nextInt()-1);
            
            //Add temporary card object into the bid hand
            bidHand.add(tempCard);
            
            //Check the rank of the cards being selected
            honestRank = tempCard.getRank();
            
            //Reprint the hand without the removed card, for user clarity
            System.out.println(playerHand.toString());
            
            //error handling possibility for future versions
//            if(bidHand.handSize() == 0)
//            {
//                System.out.println("Bid hand detected as empty. Please ensure"
//                        + " that you are entering the Card correctly.");
//                i = 0;
//            }
        }
        
        //Check if the user is cheating
        iCheat = cheat(prevBid, bidHand);
        
        //If the player is cheating
        if(iCheat == true)
        {
            //User prompt
            System.out.println("Bid detected as a cheat bid. Please decide "
                    + "what rank you wish the bid to be. Enter a 1 (for the "
                    + "current bid rank) or 2 (for the next rank)");
            if(scan.nextInt() == 1)
            {
                cheatRank = prevBid.getRank();
            }
            else
            {
                cheatRank = prevBid.getRank().getNext();
            }
            
            //Make bid with cheat rank
            bid = new Bid(bidHand, cheatRank);
        }
        //If the player is not cheating    
        else
        {
            //Make the bid with honest Rank
            bid = new Bid(bidHand, honestRank);
        }
        return bid;
    }

    /**
     * Method that asks the user if they would like to call a cheat on the 
     * current bid being played.
     * @param playerHand - unused
     * @param currBid - unused but required for interface implementaion
     * @return 
     */
    @Override
    public boolean callCheat(Hand playerHand, Bid currBid) 
    {
        //Variables and objects to be used in this method
        Scanner scan = new Scanner(System.in);
        boolean cheat;
        
        //User prompt
        System.out.println("Do you think the current bid is a Cheat Bid (c) "
                + "or an Honest Bid (h)?");
        
        //User calls the bid honest
        if("h".equals(scan.next()) == true)
        {
            System.out.println("You called the bid Honest!");
            cheat = false;
        }
        //User calls the bid cheat
        else
        {
            System.out.println("You called the bid Cheat!");
            cheat = true;
        }
        return cheat;
    }
    
}
