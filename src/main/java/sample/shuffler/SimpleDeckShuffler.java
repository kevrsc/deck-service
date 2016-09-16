package sample.shuffler;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Random shuffling algorithm.
 * 
 * @author Kevin
 *
 */
public class SimpleDeckShuffler implements DeckShuffler {

    @Override
    public List<String> shuffle(List<String> deck) {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
        return deck;
    }

}
