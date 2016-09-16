package sample.repository;

import java.util.List;
import java.util.Set;

/**
 * This contract ensures that all services that use a 
 * concrete implementation will be able to rely on the
 * functionality described. However, there is no 
 * requirement for the persistence medium and because
 * of this, an in-memory storage may be used, thus 
 * causing data loss upon server reboot.
 * 
 * @author Kevin
 *
 */
public interface DeckRepository {

    /**
     * Save a new named deck, does not clobber an
     * existing if in a different sort order.
     * 
     * @param name
     * @param cards
     */
    List<String> save(String name, List<String> cards);
    
    /**
     * Updates an existing named deck, or saves
     * it if it does not exist. Used to store
     * decks with modified sort order.
     * 
     * @param name
     * @param cards
     */
    List<String> update(String name, List<String> cards);
    
    /**
     * Returns a list of the deck names stored 
     * in the repository.
     * 
     * @return
     */
    Set<String> getSetOfDeckNames();

    /**
     * Returns a named deck as it is currently sorted.
     * Or an empty list if the deck does not exist.
     * 
     * @param name
     * @return
     */
    List<String> getNamedDeck(String name);
    
    /**
     * Deletes the named deck.
     * 
     * @param name
     */
    List<String> delete(String name);
    
}
