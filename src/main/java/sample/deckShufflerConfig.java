package sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import sample.shuffler.DeckShuffler;
import sample.shuffler.HandShufflingDeckShuffler;
import sample.shuffler.ReverseShuffler;
import sample.shuffler.SimpleDeckShuffler;

@Component
public class deckShufflerConfig {

    @Value("${shuffleMethod}")
    private String shuffleMethod;
    
    @Bean
    public DeckShuffler setDeckShuffler() {
        DeckShuffler deckShuffler = null;
        
        // TODO - refactor the switch requirement for setting the 
        //        deckShuffler to something more scalable and less 
        //        problematic.
        
        switch (shuffleMethod) {
            case "HandShufflingDeckShuffler":
                deckShuffler = new HandShufflingDeckShuffler();
                break;

            case "ReverseShuffler":
                deckShuffler = new ReverseShuffler();
                break;

            default:
                deckShuffler = new SimpleDeckShuffler();
                break;
        }
        return deckShuffler;
    }
    
}
