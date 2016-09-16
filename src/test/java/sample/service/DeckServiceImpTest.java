package sample.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import sample.Application;
import sample.repository.DeckRepository;
import sample.repository.DeckRepositoryMap;
import sample.shuffler.HandShufflingDeckShuffler;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DeckServiceImpTest {
    
    @Autowired
    private DeckRepository deckRepository;
    
    @Autowired
    private DeckServiceImp deckServiceImp;
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createMethodStoresNewDeckInRepositoryAndGetNamedDeckTest() {
        String name = "fancydeck";
        this.deckServiceImp.create(name);
        List<String> cards = deckServiceImp.createCards();
        
        this.deckRepository.save(name, cards);
        
        List<String> savedCards = this.deckRepository.getNamedDeck(name);
        assertThat(savedCards, is(equalTo(cards)));
    }
    
    @Test
    public void shuffleDeckTest() {
        List<String> bicycle = this.deckServiceImp.create("Bicycle");
        
        this.deckServiceImp.setDeckShuffler(new HandShufflingDeckShuffler());
        this.deckServiceImp.shuffleDeck("Bicycle");
        
        // TODO - add assertion checks...
//        bicycle.stream().forEach(c -> System.out.println(c.toString()));
    }
    
    @Test
    public void getDeckNamesTest() {
        ((DeckRepositoryMap) deckRepository).resetMap();
        this.deckServiceImp.create("Bicycle");
        this.deckServiceImp.create("Legends");
        this.deckServiceImp.create("Expert");
        
        Set<String> savedDeckNames = this.deckServiceImp.getSetOfDeckNames();
        assertTrue(savedDeckNames.containsAll(Arrays.asList("Bicycle", "Legends", "Expert")));
    }
    
    @Test
    public void deleteNamedDeckTest() {
        List<String> deck = this.deckServiceImp.create("Legends");
        assertThat(deck.size(), is(equalTo(52)));
        this.deckServiceImp.deleteNamedDeck("Legends");
        List<String> deletedDeck = this.deckServiceImp.getNamedDeck("Legends");
        assertThat(deletedDeck.size(), is(equalTo(0)));
    }
    
}
