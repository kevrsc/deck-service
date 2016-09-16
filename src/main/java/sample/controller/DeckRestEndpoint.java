package sample.controller;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.service.DeckService;

/**
 * This is a basic REST controller providing the functions
 * as given. Where applicable the response body is given as
 * JSON through configuration of jackson core on the classpath.
 * There is currently no validation enabled. And  
 * 
 * @author Kevin
 *
 */
@RestController
public class DeckRestEndpoint {
    private Log log = LogFactory.getLog(DeckRestEndpoint.class);

    private DeckService deckService;
    
    @Autowired
    public void setDeckService(DeckService deckService) {
        log.debug("Installing a DeckService");
        this.deckService = deckService;
    }

    // TODO - implement validation
    // TODO - implement the exception handler
    
    @RequestMapping(value = "/deck/{name}", method = RequestMethod.PUT)
    public void createDeck(@PathVariable("name") String name) {
        this.deckService.create(name);
    }
    
    @RequestMapping(value = "/deck/", method = RequestMethod.POST)
    public void shuffleDeck(@RequestBody ShuffleForm shuffleForm) {
        String deckName = shuffleForm.getDeckName();
        this.deckService.shuffleDeck(deckName);
    }
    
    @RequestMapping(value= "/deck/", method = RequestMethod.GET)
    public Set<String> getSetOfCurrentDecks() {
        return this.deckService.getSetOfDeckNames();
    }
    
    @RequestMapping(value = "/deck/{name}", method = RequestMethod.GET)
    public List<String> getNamedDeck(@PathVariable("name") String name) {
        return this.deckService.getNamedDeck(name);
    }

    @RequestMapping(value = "/deck/{name}", method = RequestMethod.DELETE)
    public void deleteNamedDeck(@PathVariable("name") String name) {
        this.deckService.deleteNamedDeck(name);
    }
}
