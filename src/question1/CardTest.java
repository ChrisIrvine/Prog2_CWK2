package question1;

///*
// * Main Class to test the Hand.java file. Each method is tested in any/all
// * cases. 
// */
//package Q1;
//
//import Q1.Card.Rank;
//import Q1.Card.Suit;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author ruw12gbu, 100036248
// */
//public class CardTest 
//{
//    public static void main(String[] args) throws FileNotFoundException,
//            IOException, ClassNotFoundException
//    {
//        //int object to be used whilst testing
//        int i;
//        //Card Objects to be used whilst testing
//        Card card = new Card(Rank.ACE, Suit.SPADES);
//        Card cardTwo = new Card(Rank.EIGHT, Suit.DIAMONDS);
//        Card cardThree = new Card(Rank.THREE, Suit.CLUBS);
//        //Create, shuffle and print out a Deck Object
//        Deck deck = new Deck();
//        deck.shuffle();
//        System.out.println("Shuffled Deck: ");
//        //Iterate over the deck of cards and print out each card
//        for (i = 0; i < deck.size(); i++) 
//        {
//            System.out.println(deck.getCurrent(i).toString());
//        }
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Creating an Array of Card Objects...\n");
//        
//        //Create and fill an array of Card Objects, from the Deck Object
//        Card[] cardArray = new Card[5];
//        cardArray[0] = deck.deal();
//        cardArray[1] = deck.deal();
//        cardArray[2] = deck.deal();
//        cardArray[3] = deck.deal();
//        cardArray[4] = deck.deal();
//        //Print out cardArray
//        System.out.println("Cards in cardArray: \n");
//        for (i = 0; i < cardArray.length; i++) 
//        {
//            System.out.println(cardArray[i].toString());            
//        }
//
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the constructor methods... \n");
//        //Create a new, empty Hand Object.
//        Hand handOne = new Hand();
//        //Prove hand is empty by printing out the number of cards in the hand.
//        System.out.println("Hand One has " + handOne.handSize() + " cards.");
//        //Create a new Hand Object that is filled by an Array of Card Objects.
//        Hand handTwo = new Hand(cardArray);
//        //Test if filledHand has 5 Card Objects from cardArray
//        System.out.println("\nThe Cards in Hand Two are: \n" 
//                + handTwo.toString());
//        //Create a new Hand Object that copies the card from filledHand
//        Hand handThree = new Hand(handTwo);
//        //Test if cards are copies correctly from filledHand
//        System.out.println("\nThe Cards in Hand Three are: \n" 
//                + handThree.toString());
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the add methods...\n");
//        
//        /* Test the first add method, adds a single card at a time by dealing 
//         * 5 Card Objects from the Deck Object. Also test the getCards() method
//         * to return the cards for printing.
//         */
//        //fill handOne
//        for (i = 0; i < 5; i++) 
//        {
//            handOne.add(deck.deal());
//        }
//        //print handOne
//        System.out.println("Cards in Hand One: " + handOne.getCards());
//        
//        /* Test the second add method, adds a Collection of Cards to a Hand 
//         * Object. Begin testing by wiping handTwo, then creating a Collection
//         * finally adding the Collection to handTwo before printing out handTwo.
//         */
//        //Clear handTwo
//        handTwo.clearHand();
//        //Create a List Collection of Card Objects
//        List l = new ArrayList();
//        //Add 5 cards to the List Collection, cardList
//        for (i = 0; i < 5; i++) 
//        {
//            l.add(deck.deal());
//        }
//        //Add the Card Objects in cardList to handTwo
//        handTwo.add(l);
//        //Print handTwo
//        System.out.println("Cards in Hand Two: " + handTwo.getCards());
//        
//        /* Test the third add method, that takes a Hand object and adds the 
//         * cards from that hand. Clearing the previous hand. Test starts by 
//         * clearing handThree. 
//         */
//        //Clear handThree
//        handThree.clearHand();
//        //Add the cards from handOne to handThree
//        handThree.add(handOne);
//        //Print handThree
//        System.out.println("Cards in Hand Three: " + handThree.getCards() 
//                + "\n");
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the remove methods...\n");
//        
//        //Test the first remove method, removing a single card.
//        //Add card to be removed
//        handOne.add(card);
//        //Print out handOne
//        System.out.println("Cards in handOne: " + handOne.getCards());
//        //remove(Card c) method returns a boolean value so need if condition to 
//        //check
//        if(handOne.remove(card) == true)
//        {
//            System.out.println(card + " successfully removed from handOne.");
//        }
//        else
//        {
//            System.out.println(card + " could not be found in handOne");
//        }
//        //Print out hand after removed card
//        System.out.println("Cards in handOne: " + handOne.getCards() + "\n");
//        
//        //Test the second remove method that all cards from another Hand Object.
//        //Clear a hand to be used in testing
//        handTwo.clearHand();
//        //Fill cleared hand with some test cards
//        handTwo.add(card);
//        handTwo.add(cardTwo);
//        handTwo.add(cardThree);
//        //Add the same cards to handOne
//        handOne.add(card);
//        handOne.add(cardTwo);
//        handOne.add(cardThree);
//        //Print out handOne and handTwo
//        System.out.println("Cards in handOne: " + handOne.getCards());
//        System.out.println("Cards in handTwo: " + handTwo.getCards());
//        //Remove the cards in handTwo from handOne
//        if(handOne.remove(handTwo) == true)
//        {
//            System.out.println(handTwo.getCards() + " sucessfully removed from "
//                    + "handOne");
//        }
//        else
//        {
//            System.out.println(handTwo.getCards() + " could not be found in "
//                    + "handOne");
//        }
//        //Print out handOne
//        System.out.println("Cards in handOne: " + handOne.getCards());
//        
//        /* Test the third remove method that removes and returns a card from a 
//         * Hand Object.
//         */
//        //Print out handThree
//        System.out.println("\nCards in handThree: " + handThree.getCards());
//        //Remove the 4th card (index starts at 0)
//        handThree.remove(3);
//        //Print out handThree
//        System.out.println("Cards in handThree: " + handThree.getCards());
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the sort methods...\n");
//        
//        //Test the sortAscending method
//        handOne.sortAscending();
//        System.out.println("handOne sorted into Ascending Order: "
//                + handOne.getCards());
//        
//        //Test the sortDescending method
//        handOne.sortDescending();
//        System.out.println("handOne sorted into Descending Order: "
//                + handOne.getCards());
//        
//        //Formatting 
//        System.out.println("\nCheck that Suit Order is preserved...");
//        
//        //Test to check suit order is preserved
//        //Clear handTwo
//        handTwo.clearHand();
//        //Replace cardArray elements with custom made cards
//        cardArray[0] = new Card(Rank.ACE, Suit.SPADES);
//        cardArray[1] = new Card(Rank.ACE, Suit.DIAMONDS);
//        cardArray[2] = new Card(Rank.ACE, Suit.HEARTS);
//        cardArray[3] = new Card(Rank.ACE, Suit.CLUBS);
//        cardArray[4] = new Card(Rank.FOUR, Suit.HEARTS);
//        //fill handTwo from cardArray
//        for(i = 0; i < cardArray.length; i++)
//        {
//            handTwo.add(cardArray[i]);
//        }
//        //Print handTwo
//        System.out.println("\nCards in handTwo: " + handTwo.getCards());
//        //Sort handTwo into Ascending Order
//        handTwo.sortAscending();
//        //Print sorted handTwo
//        System.out.println("handTwo sorted into Ascending Order: " 
//                + handTwo.getCards());
//        //Sort handTwo into Descending Order
//        handTwo.sortDescending();
//        //Print sorted handTwo
//        System.out.println("handTwo sorted into Descending Order: " 
//                + handTwo.getCards());
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the countSuit & countRank methods...\n");
//        
//        //Create a new Deck, for the purposes of testing
//        deck.newDeck();
//        deck.shuffle();
//        //Clear and fill handOne with 10 new cards from the refreshed deck
//        handOne.clearHand();
//        for(i = 0; i < 10; i++)
//        {
//            handOne.add(deck.deal());
//        }
//        //Print handOne
//        System.out.println("Cards in handOne: " + handOne.getCards());
//        //Count the cards of each of the suits
//        System.out.println("Number of...");
//        System.out.println("CLUBS : " + handOne.countSuit(Suit.CLUBS));
//        System.out.println("DIAMONDS : " + handOne.countSuit(Suit.DIAMONDS));
//        System.out.println("HEARTS : " + handOne.countSuit(Suit.HEARTS));
//        System.out.println("SPADES : " + handOne.countSuit(Suit.SPADES));
//        System.out.println("... in handOne\n");
//        
//        System.out.println("Number of...");
//        System.out.println("TWOs : " + handOne.countRank(Rank.TWO));
//        System.out.println("THREEs : " + handOne.countRank(Rank.THREE));
//        System.out.println("FOURs : " + handOne.countRank(Rank.FOUR));
//        System.out.println("FIVEs : " + handOne.countRank(Rank.FIVE));
//        System.out.println("SIXs : " + handOne.countRank(Rank.SIX));
//        System.out.println("SEVENs : " + handOne.countRank(Rank.SEVEN));
//        System.out.println("EIGHTs : " + handOne.countRank(Rank.EIGHT));
//        System.out.println("NINEs : " + handOne.countRank(Rank.NINE));
//        System.out.println("TENs : " + handOne.countRank(Rank.TEN));
//        System.out.println("JACKs : " + handOne.countRank(Rank.JACK));
//        System.out.println("QUEENs : " + handOne.countRank(Rank.QUEEN));
//        System.out.println("KINGs : " + handOne.countRank(Rank.KING));
//        System.out.println("ACEs : " + handOne.countRank(Rank.ACE));
//        System.out.println(".. in handOne");
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the handValue method...\n");
//        
//        System.out.println("The total value of the ranks in handOne is: "
//                + handOne.handValue());
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the toString methods...\n");
//        
//        System.out.println("Cards in handOne: \n" + handOne.toString());
//        
//        //Formatting
//        System.out.println("-------------------------------------------------");
//        System.out.println("Testing the isFlush & isStraight methods...\n");
//        
//        //Testing isFlush()
//        
//        //Clear handTwo, to install a Flush Hand
//        handTwo.clearHand();
//        //Replace cardArray elements with custom made cards
//        cardArray[0] = new Card(Rank.TWO, Suit.DIAMONDS);
//        cardArray[1] = new Card(Rank.THREE, Suit.DIAMONDS);
//        cardArray[2] = new Card(Rank.FOUR, Suit.DIAMONDS);
//        cardArray[3] = new Card(Rank.FIVE, Suit.DIAMONDS);
//        cardArray[4] = new Card(Rank.SIX, Suit.DIAMONDS);
//        //fill handTwo from cardArray
//        for(i = 0; i < cardArray.length; i++)
//        {
//            handTwo.add(cardArray[i]);
//        }
//        //Print handTwo
//        System.out.println("\nCards in handTwo: " + handTwo.getCards());
//        //Check if handTwo contains a Flush, expected result is it should do.
//        if(handTwo.isFlush() == true)
//        {
//            System.out.println("handTwo contains a Flush.");
//        }
//        else
//        {
//            System.out.println("handTwo does not contain a Flush.");
//        }
//        //Check if handOne contains a Flush, it should not (althought it could 
//        //as cards are random.
//        if(handOne.isFlush() == true)
//        {
//            System.out.println("handOne contains a Flush.");
//        }
//        else
//        {
//            System.out.println("handOne does not contain a Flush.");
//        }
//        
//        //Test the isStraight() method
//        
//        //Check if handTwo contains a straight, it has been planted with one.
//        if(handTwo.isStraight() == true)
//        {
//            System.out.println("handTwo contains a Straight.");
//        }
//        else
//        {
//            System.out.println("handTwo does not contain a Straight.");
//        }
//        //Check if handOne contains a straight, it is highly unlikely (but 
//        //possible).
//        if(handOne.isStraight() == true)
//        {
//            System.out.println("handOne contains a Straight.");
//        }
//        else
//        {
//            System.out.println("handOne does not contain a Straight.");
//        }
//    }
//}
