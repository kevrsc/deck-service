package sample.shuffler;

import java.util.Collections;
import java.util.List;

/**
 * Simple deck reversing. 
 * 
 * @author Kevin
 *
 */
public class ReverseShuffler implements DeckShuffler {

    @Override
    public List<String> shuffle(List<String> deck) {
        Collections.reverse(deck);
        return deck;
    }

}
