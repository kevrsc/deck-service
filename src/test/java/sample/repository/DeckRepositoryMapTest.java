package sample.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sample.service.DeckService;
import sample.service.DeckServiceImp;

public class DeckRepositoryMapTest {

    private DeckRepository deckRepository = new DeckRepositoryMap();
    
    private DeckService deckServiceImp = new DeckServiceImp();
    
    String name = "fancydeck";
    List<String> newDeck = new ArrayList<>(52);
    
    @Before
    public void setUp() throws Exception {
        newDeck = ((DeckServiceImp) deckServiceImp).createCards();
    }
    
    @Test
    public void saveDeckAndVerifyItWasSaved() {
        this.deckRepository.save(name, newDeck);
        
        Set<String> deckNames = this.deckRepository.getSetOfDeckNames();
        
        assertThat(deckNames.size(), is(equalTo(1)));
        assertTrue(deckNames.contains(name));
        List<String> savedDeck = this.deckRepository.getNamedDeck(name);
        assertThat(newDeck, is(equalTo(savedDeck)));
    }

    @After
    public void removeDeck() {
        this.deckRepository.delete(name);
        Set<String> deckNames = this.deckRepository.getSetOfDeckNames();
        assertThat(deckNames.size(), is(equalTo(0)));
    }
    
    
}
