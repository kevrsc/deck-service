package sample.controller;

/**
 * This simple form currently has no validation rules on it. 
 * Therefore it can be used for POST actions where only the 
 * deckName is provided. As an example, the POST with body 
 * content JSON text: {"deckName":"Bicycle"}
 * will be accepted as a valid form. But allows for the future
 * option of specifying specific shuffle methods on a per-deck
 * basis, which may be handy for card tricks.
 * 
 * @author Kevin
 *
 */
public class ShuffleForm {
    String deckName;
    String shuffleMethod;

    public ShuffleForm() {
    }

    public ShuffleForm(String deckName) {
        this.deckName = deckName;
    }

    public ShuffleForm(String deckName, String shuffleMethod) {
        this.deckName = deckName;
        this.shuffleMethod = shuffleMethod;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getShuffleMethod() {
        return shuffleMethod;
    }

    public void setShuffleMethod(String shuffleMethod) {
        this.shuffleMethod = shuffleMethod;
    }
}
