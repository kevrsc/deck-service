package sample.service;

import java.util.List;
import java.util.Set;

/**
 * Defines the service contract for creating and managing
 * the stored decks. Suit and Rank enumerations are also 
 * defined to help provide structure for how cards are to
 * be represented, including preventing jokers. Note that
 * enforcement of this is not specified.
 * 
 * @author Kevin
 *
 */
public interface DeckService {

    List<String> create(String name);
    
    List<String> shuffleDeck(String name);

    Set<String> getSetOfDeckNames();

    List<String> getNamedDeck(String name);
    
    List<String> deleteNamedDeck(String name);
    
    
    public enum Suit {
        DIAMONDS (1, "Diamonds"),
        CLUBS    (2, "Clubs"),
        HEARTS   (3, "Hearts"),
        SPADES   (4, "Spades");
        
        private final int value;
        private final String text;
        
        Suit(int value, String text) {
            this.value = value;
            this.text = text;
        }
        
        public int value() {return value;}
        public String text() {return text;}
    }

    public enum Rank {
        ACE    (1 , "Ace"  ),       
        DEUCE  (2 , "Two"  ),
        THREE  (3 , "Three"), 
        FOUR   (4 , "Four" ), 
        FIVE   (5 , "Five" ), 
        SIX    (6 , "Six"  ), 
        SEVEN  (7 , "Seven"),
        EIGHT  (8 , "Eight"), 
        NINE   (9 , "Nine" ), 
        TEN    (10, "Ten"  ), 
        JACK   (11, "Jack" ),
        QUEEN  (12, "Queen"), 
        KING   (13, "King" );
        
        private final int value;
        private final String text;
        
        Rank(int value, String text) {
            this.value = value;
            this.text = text;
        }
        
        public int value() {return value;}
        public String text() {return text;}
    }
}
