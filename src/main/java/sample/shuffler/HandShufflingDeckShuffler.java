package sample.shuffler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Mimics the perfect hand shuffling where the deck is split
 * exactly in half and each card is perfectly interleaved.
 * This is repeated a small random number of times.
 * 
 * A future upgrade may be to add some human error to the 
 * process in the form of an uneven deck split and an
 * imperfect interleaving.
 * 
 * @author Kevin
 *
 */
public class HandShufflingDeckShuffler implements DeckShuffler {

    @Override
    public List<String> shuffle(List<String> deck) {
        Random r = new Random();
        int numberOfShuffles = r.nextInt(4) + 2;

        for (int j = 0; j < numberOfShuffles; j++) {
            List<String> temp = new ArrayList<>(52);

            for (int i = 0; i < 26; i++) {
                temp.add(deck.get(i));
                temp.add(deck.get(i + 26));
            }

            Collections.copy(deck, temp);
        }

        return deck;
    }
}
