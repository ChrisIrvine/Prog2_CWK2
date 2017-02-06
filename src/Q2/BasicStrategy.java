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
    public boolean cheat(Bid bidCard, Hand playerHand) {
        int count = playerHand.countRank(bidCard.getRank());

        if (count == 0) {
            return true;
        }

        count = playerHand.countRank(bidCard.getRank().getNext());
        if (count == 0) {
            return true;
        }

        return false;
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) 
    {
        Random random = new Random();
        Bid bid;

        if (cheat) 
        {
            Hand hand = new Hand();
            /*
            Find a way to allow a player to play more than one card at a time. 
            maybe iterate over the determined card the number of times that it 
            has that card?
            */

            Card card = h.remove(random.nextInt(h.handSize()));
            hand.add(card);
            bid = new Bid(hand, b.getRank().getNext());
        } 
        else 
        {
            Hand hand = new Hand();
            Card.Rank save = Card.Rank.TWO;        //next one
            
            for (Card.Rank rank : Card.Rank.values()) 
            {
                boolean take = false;

                if (b.getRank().getValue() == 13
                        || (b.getRank().ordinal() >= rank.ordinal()
                        || h.countRank(rank) <= 0)) 
                {
                    if (h.countRank(rank) > 0
                            && b.getRank().getValue() == 13) 
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
                }
                if (!take) 
                {
                    
                } 
                else 
                {
                    save = rank;
                    for (int i = 0; i < h.handSize(); i++) 
                    {
                        Card temporary = h.getCard(i);
                        
                        if (temporary.getRank() != rank) 
                        {
                            
                        } 
                        else 
                        {
                            hand.add(temporary);
                        }
                    }
                    break;
                }
            }
            bid = new Bid(hand, save);
            h.remove(hand);
        }
        return bid;
    }

    @Override
    public boolean callCheat(Hand playerHand, Bid bidCard) {
        int player = playerHand.countRank(bidCard.getRank());
        int bid = bidCard.getCount();

        bid -= player;
        return bid < 0;
    }
}
