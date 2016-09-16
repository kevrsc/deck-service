package sample.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mapstore")
public class DeckRepositoryMap implements DeckRepository {
    protected Map<String, List<String>> deckMap;

    public DeckRepositoryMap() {
        this.deckMap = new HashMap<>();
    }

    
    @Override
    public List<String> save(String name, List<String> cards) {
        /* The named deck already exists, and is possibly sorted */
        if (!deckMap.containsKey(name)) {
            deckMap.put(name, cards);
        }
        return deckMap.get(name);
    }

    @Override
    public List<String> update(String name, List<String> cards) {
        deckMap.put(name, cards);
        return deckMap.get(name);
    }

    @Override
    public Set<String> getSetOfDeckNames() {
        return deckMap.keySet();
    }

    @Override
    public List<String> getNamedDeck(String name) {
        if (deckMap.containsKey(name))
            return deckMap.get(name);
        else
            return new ArrayList<>();
    }

    @Override
    public List<String> delete(String name) {
        List<String> deletedCards = deckMap.get(name);
        deckMap.remove(name);
        return deletedCards;
    }
    
    public void resetMap() {
        this.deckMap = new HashMap<>();
    }
}
