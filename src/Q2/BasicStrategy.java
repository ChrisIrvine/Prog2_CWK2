/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import Q1.Hand;
import Q1.Card;
import Q1.Card.Suit;
import java.util.Random;
//import java.util.Iterator;

/**
 *
 * @author ruw12gbu, 100036248
 */
public class BasicStrategy implements Strategy {

    /**
     * 
     * @param bidCard
     * @param playerHand
     * @return - TRUE = need to cheat   FLASE = can play honestly
     */
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
            
            if ((playerHand.remove(clubCard)) == true)
                honestHand.add(clubCard);
            if ((playerHand.remove(heartCard)) == true)
                honestHand.add(heartCard);
            if ((playerHand.remove(diaCard)) == true)
                honestHand.add(diaCard);
            if ((playerHand.remove(spadesCard)) == true)
                honestHand.add(spadesCard);
            
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
                honestRank = prevBid.getRank().getNext();
            }
            else
                honestRank = prevBid.getRank();
            
            playerHand.remove(honestHand);
            bid = new Bid(honestHand, honestRank);
        }
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
