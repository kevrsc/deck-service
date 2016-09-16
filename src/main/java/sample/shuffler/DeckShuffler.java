package sample.shuffler;

import java.util.List;

/**
 * Provides the contract for deck shuffling algorithms.
 * 
 * @author Kevin
 *
 */
public interface DeckShuffler {

    List<String> shuffle(List<String> deck);
    
}
