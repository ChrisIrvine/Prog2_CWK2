/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import Q1.Card;
import Q1.Hand;

/**
 * @author ruw12gbu, 100036248
 */
public class BasicPlayer implements Player
{
    private final Hand playerHand;
    private CardGame game;
    private Strategy strategy;
    private final BasicCheat cheat;
    
    public BasicPlayer(BasicStrategy basicS, BasicCheat basicC)
    {
        playerHand = new Hand();
        strategy = basicS;
        cheat = basicC;
    }
    
    @Override
    public void addHand(Hand addHand)
    {
        playerHand.add(addHand);
    }
    
    @Override
    public void addCard(Card addCard)
    {
        playerHand.add(addCard);
    }
    
    @Override
    public void setGame(CardGame newGame)
    {
        game = newGame;
    }
    
    @Override
    public void setStrategy(Strategy strat)
    {
        strategy = strat;
    }
    
    @Override
    public Bid playHand(Bid b)
    {
        boolean cheat = strategy.cheat(b, playerHand);
        return strategy.chooseBid(b, playerHand, cheat);
    }
    
    @Override
    public int cardsLeft()
    {
        return playerHand.handSize();
    }
    
    @Override
    public boolean callCheat(Bid b)
    {
        return strategy.callCheat(playerHand, b);
    }
}
