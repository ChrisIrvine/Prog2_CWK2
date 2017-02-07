/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import Q1.Hand;
import Q1.Card;
import java.util.Random;
//import java.util.Iterator;

/**
 *
 * @author ruw12gbu, 100036248
 */
public class BasicStrategy implements Strategy {

    @Override
    public boolean cheat(Bid bidCard, Hand playerHand) 
    {
        int count = playerHand.countRank(bidCard.getRank());
        boolean cheat;
        //testing and debugging
        int totalCount = playerHand.countRank(bidCard.getRank()) + playerHand.countRank(bidCard.getRank().getNext());

        if (count != 0) 
        {
            cheat = false;
        }
        else if ((playerHand.countRank(bidCard.getRank().getNext()) != 0))
        {
            cheat = false;
        }
        else
        {
            cheat = true;
        }

        //testing and debugging
        System.out.println("Number of honest cards: " + totalCount);
        return cheat;
    }

    @Override
    public Bid chooseBid(Bid bidCard, Hand playerHand, boolean isCheat) 
    {
        //Create a new Random object
        Random random = new Random();
        //Create a new Bid Object
        Bid bid = new Bid();

        //If I have to cheat...
        if (isCheat == true) 
        {
            //Create a hand object to hold the randomly selected Cheat Card
            Hand bidHand = new Hand();
            
            //Card Object to hold the randomly selected Cheat Card that is 
            //removed from the players hand.
            Card cheatCard = playerHand.remove(random.nextInt(playerHand.handSize()));
            
            //add the removed card to the bidHand
            bidHand.add(cheatCard);
            
            //Create a new bid using the randomly selected card and a false 
            //Rank (the next rank from bidCard).
            bid = new Bid(bidHand, bidCard.getRank().getNext());
        }
        //If I don't have to cheat and can play honestly...
        else 
        {
            //Create a Hand Object to hold the cards I am playing
            Hand bidHand = new Hand();
            
            //Holder Object whilst the program iterates over the ranks
            Card.Rank save = Card.Rank.TWO;        //next one
            
            //For every rank in all the ranks do...
            for (Card.Rank currentRank : Card.Rank.values())
            {
                //What does this do??? Does it tell the program which card is 
                //suitable to play
                boolean take = false;

                /* IF bidCard is an ACE                                       OR
                 * IF bidCard position on enum greater than (or equal to)
                 * the current rank being examined                            OR
                 * IF playerHand has no cards of the current rank being examined
                 */
                if (bidCard.getRank().ordinal() == 13
                        || (bidCard.getRank().ordinal() >= currentRank.ordinal()
                        || playerHand.countRank(currentRank) <= 0)) 
                {
                    //then do...
                    
                    /* IF player has more than 1 of the currentRanks in hand  
                     *                                                       AND
                     * IF the bidCard is an ACE
                     */
                    if (playerHand.countRank(currentRank) > 0
                            && bidCard.getRank().ordinal() == 13) 
                    {
                        //then do...
                        
                        /* IF currentRank is a TWO                            OR
                         * IF currentRank is an ACE
                         */
                        if (currentRank.ordinal() == 1 
                                || currentRank.ordinal() == 13) 
                        {
                            //Tell the program to take the card
                            take = true;
                        }
                    }
                    /* If there is not more than one of the currentRank in hand
                     * or if the bidCard is not an ace.
                     */
                    else 
                    {
                        //Tell the program to take the card
                        take = true;
                    }
                }
                //Check to see IF take == false
                if (take == false) 
                {
                    //If its false then do nothing
                    //Can we just check for take == true instead?
                } 
                //IF take == true
                else
                {
                    //overwrite the holder variable with the current rank
                    save = currentRank;
                    
                    //Iterate over the players hand
                    for (int i = 0; i < playerHand.handSize(); i++) 
                    {
                        //IF the selected card equals the currentRank
                        if (playerHand.getCard(i).getRank() == currentRank) 
                        {
                            //Then add the selected card to bidHand
                            bidHand.add(playerHand.getCard(i));
                        } 
                    }
                    //do we need this break here?
                    break;
                }
            }
            //New bid using the currentRank and bidHand
            bid = new Bid(bidHand, save);
            //remove all cards that were selected to bid from the players hand
            playerHand.remove(bidHand);
        }
        System.out.println("Output of chooseBid() is: " + bid.toString());
        //return the bid object
        return bid;
    }

    @Override
    public boolean callCheat(Hand playerHand, Bid bidCard) 
    {
        int player = playerHand.countRank(bidCard.getRank());
        int bid = bidCard.getCount();

        bid -= player;
        return bid < 0;
    }
}
