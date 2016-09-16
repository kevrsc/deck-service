package sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.repository.DeckRepository;
import sample.shuffler.DeckShuffler;
import sample.shuffler.SimpleDeckShuffler;

@Service
public class DeckServiceImp implements DeckService {
    private Log log = LogFactory.getLog(DeckServiceImp.class);

    private DeckShuffler deckShuffler = new SimpleDeckShuffler();
    
    private DeckRepository deckRepository;

    @Autowired
    public void setDeckRepository(DeckRepository deckRepository) {
        log.debug("Installing a DeckRepository");
        this.deckRepository = deckRepository;
    }

    @Autowired
    public void setDeckShuffler(DeckShuffler deckShuffler) {
        this.deckShuffler = deckShuffler;
    }


    @Override
    public List<String> create(String name) {
        List<String> cards = this.createCards();
        this.deckRepository.save(name, cards);
        return cards; 
    }

    @Override
    public List<String> shuffleDeck(String name) {
        List<String> deck = this.deckRepository.getNamedDeck(name);
        deck = this.deckShuffler.shuffle(deck);
        return this.deckRepository.update(name, deck);
    }

    @Override
    public Set<String> getSetOfDeckNames() {
        return this.deckRepository.getSetOfDeckNames();
    }

    @Override
    public List<String> getNamedDeck(String name) {
        return this.deckRepository.getNamedDeck(name);
    }

    @Override
    public List<String> deleteNamedDeck(String name) {
        return this.deckRepository.delete(name);
    }
    


    public List<String> createCards() {
        List<String> cards = new ArrayList<>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                cards.add(r + "-" + s);
            }
        }
        return cards;
    }
}
