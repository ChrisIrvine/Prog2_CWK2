/*
 * Author:      ruw12gbu, 100036248
 * 
 * Description: This class implements the Strategy Interface and will work with 
 *              other classes in the project to run a game of Cheat between 
 *              computer controlled players. This class follows the standard
 *              rules of Cheat as well as following this stratergy:
 *              1. Never cheat unless you have to. If a cheat is required, play
 *                 a single card randomly.
 *              2. If not cheating, always play the maximum nubmer of cards of 
 *                 possible of the lowest rank possible.
 *              3. Call another player a cheat only when certain they are 
 *                 cheating (based off their own hand.
 *              I have added in a random element to determine what rank the 
 *              cheat bid is called at. But all rules are adhered to.
 * 
 * Version:     1.0 - Created
 *              1.1 - cheat() reworked
 *              1.2 - chooseBid() reworked
 *              1.3 - chooseBid() reworked again && refined
 *              1.4 - comments added 
 *              1.5 - chooseBid() reworked
 *              1.6 - polishing and refinments
 *
 * Known Issue: Game can take a long time to finish, from testing it has been 
 *              known to go up to 400+ rounds. Cause and validity of issue is 
 *              unknown. Perhaps cheat detection is too good/conservative?
 */
package question2;

import question1.Hand;
import question1.Card;
import question1.Card.Suit;
import java.util.Random;

/**
 * @author ruw12gbu, 100036248
 */
public class BasicStrategy implements Strategy {

    /**
     * Method to detect whether a player has to cheat or not. This is done by
     * checking to see if there are any cards in the hand that are of the same
     * rank as the previous bid, or of the next rank in the list. If any are
     * found; it returns FALSE, else it returns TRUE.
     * @param prevBid - Last bid played
     * @param playerHand - Hand of the current player
     * @return - TRUE = need to cheat   FLASE = can play honestly
     */
    @Override
    public boolean cheat(Bid prevBid, Hand playerHand) 
    {
        //variable to hold the result of testing
        boolean cheat;

        //Check for any cards of the bid rank
        if (playerHand.countRank(prevBid.getRank()) != 0) 
        {
            //Player can play honestly
            cheat = false;
        }
        //Check for any cards of the next rank
        else if ((playerHand.countRank(prevBid.getRank().getNext()) != 0))
        {
            //Player can play honestly
            cheat = false;
        }
        //If no cards of bid Rank or next rank are found
        else
        {
            //Player can cheat
            cheat = true;
        }
        return cheat;
    }

    /**
     * Method to determine what card the player should play. This is done by 
     * using the result from the cheat() method. If the cheat() method 
     * determines the player must cheat then the player will cheat, selecting 
     * a random card to cheat with. If honest play is determines then the
     * maximum number of cards of the lowest rank will be played.
     * @param prevBid - Last bid played
     * @param playerHand - Hand of the current player
     * @param isCheat - result from cheat() method
     * @return 
     */
    @Override
    public Bid chooseBid(Bid prevBid, Hand playerHand, boolean isCheat) 
    {
        Bid bid = new Bid();
        /* Check if the hand is empty, if it then return a bit of TWO from a
         * new Hand object. This ensures that a bid is always played.
         */
        if(playerHand.handSize() == 0)
        {
            return new Bid(new Hand(), prevBid.getRank());
        }
        
        //Decide if we need to cheat or not.
        if(isCheat == true) //when we need to cheat...
        {
            //Create a random object to be used later
            Random random = new Random();
            //Create a Card.Rank variable to be used later
            Card.Rank cheatRank;
            
            //Select a random card from the players hand
            Card cheatCard = 
                    playerHand.getCard(random.nextInt(playerHand.handSize()));
            
            //Hand Object to hold the cheat card
            Hand cheatHand = new Hand();
            
            //Add cheat card to the cheat hand
            cheatHand.add(cheatCard);
            
            //Decide to cheat using the rank previously called, or the next rank
            int lowUp = random.nextInt(2);
            if(lowUp == 0)
            {
                cheatRank = prevBid.getRank();
            }
            else
            {
                cheatRank = prevBid.getRank().getNext();
            }
            
            playerHand.remove(cheatHand);
            //Bid Object to hold the cheatBid
            bid = new Bid(cheatHand, cheatRank);
        }
        else //If we can play honestly...
        {
            //Create a hand object that will hold the cards to play
            Hand honestHand = new Hand();
            Card.Rank honestRank;
            
            //Create cards to look for of the same rank as the previous Bid
            Card clubCard = new Card(prevBid.getRank(), Suit.CLUBS);
            Card heartCard = new Card(prevBid.getRank(), Suit.HEARTS);
            Card diaCard = new Card(prevBid.getRank(), Suit.DIAMONDS);
            Card spadesCard = new Card(prevBid.getRank(), Suit.SPADES);
            
            //Create cards to look for of the next rank from the previous Bid
            Card clubCardNext = 
                    new Card(prevBid.getRank().getNext(), Suit.CLUBS);
            Card heartCardNext = 
                    new Card(prevBid.getRank().getNext(), Suit.HEARTS);
            Card diaCardNext = 
                    new Card(prevBid.getRank().getNext(), Suit.DIAMONDS);
            Card spadesCardNext = 
                    new Card(prevBid.getRank().getNext(), Suit.SPADES);
            
            //Check for each of the cards of the bid Rank
            //Add any cards found to honestHand
            if ((playerHand.remove(clubCard)) == true)
                honestHand.add(clubCard);
            if ((playerHand.remove(heartCard)) == true)
                honestHand.add(heartCard);
            if ((playerHand.remove(diaCard)) == true)
                honestHand.add(diaCard);
            if ((playerHand.remove(spadesCard)) == true)
                honestHand.add(spadesCard);
            
            //IF no cards found, check for cards of the next rank
            if (honestHand.handSize() == 0)
            {
                if ((playerHand.remove(clubCardNext)) == true)
                    honestHand.add(clubCardNext);
                if ((playerHand.remove(heartCardNext)) == true)
                    honestHand.add(heartCardNext);
                if ((playerHand.remove(diaCardNext)) == true)
                    honestHand.add(diaCardNext);
                if ((playerHand.remove(spadesCardNext)) == true)
                    honestHand.add(spadesCardNext);
                //set the bid rank to the be the next rank
                honestRank = prevBid.getRank().getNext();
            }
            else
                //use the same bid rank as before
                honestRank = prevBid.getRank();
            
            //Ensure cards are removed players hand (protects against 
            //duplicate cards)
            playerHand.remove(honestHand);
            
            //Create a bid object that is honest
            bid = new Bid(honestHand, honestRank);
        }
        //return the bid object
        return bid;        
    }
    
    /**
     * Method to determine if a cheat should be called by a player object or
     * not. A cheat is only called when it is certain another player is 
     * cheating. This is done by checking the number of cards in the playersHand
     * of a given rank and seeing if the number of cards of that rank being 
     * played is impossible.
     * @param playerHand - Hand of the Current Player
     * @param bidCard - current bid being played
     * @return 
     */
    @Override
    public boolean callCheat(Hand playerHand, Bid bidCard) 
    {
        int player = playerHand.countRank(bidCard.getRank());
        int bid = bidCard.getCount();

        bid -= player;
        return bid < 0;
    }
}
