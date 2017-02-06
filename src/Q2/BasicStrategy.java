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
public class BasicStrategy implements Strategy
{
    @Override
    public boolean cheat(Bid bidCard, Hand playerHand)
    {
        int count = playerHand.countRank(bidCard.getRank());
        
        if(count == 0)
        {
            count = playerHand.countRank(bidCard.getRank().getNext());
            if(count == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public Bid chooseBid(Bid bidCard, Hand playerHand, boolean cheat)
    {
        Random random = new Random();
        Bid bidC = new Bid();
        
        if (cheat) 
        {             
            Hand hand = new Hand();
            Card card = 
                    playerHand.remove(random.nextInt(playerHand.handSize()));
            hand.add(card);
            bidC = new Bid(hand, bidCard.getRank().getNext());
        } 
        else 
        {
            Hand hand = new Hand();
            Card.Rank save = Card.Rank.TWO;
            
            for (Card.Rank rank : Card.Rank.values()) 
            {
                boolean take = false;

                if (bidCard.getRank().getValue() == 13
                    || (bidCard.getRank().ordinal() >= rank.ordinal() 
                    || playerHand.countRank(rank) <= 0))
                    if (playerHand.countRank(rank) > 0
                        && bidCard.getRank().getValue() == 13) 
                    { 
                        if (rank.getValue() == 2 || rank.getValue() == 13) 
                        {
                            take = true;
                        }
                    } 
                    else 
                    {
                        take = true;
                    }
                if (!take) 
                {
                    //Do nothing
                } 
                else 
                {
                    save = rank;
                    
                    for (int i = 0; i < playerHand.handSize(); i++)
                    {
                        Card temporary = playerHand.getCard(i);
                        if (temporary.getRank() != rank) 
                        {
                            //Do nothing
                        }
                        else 
                        {
                            hand.add(temporary);
                        }
                    }
                    break;
                }
            }
            bidC = new Bid(hand, save);
            playerHand.remove(hand);
        }
        return bidC;
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
