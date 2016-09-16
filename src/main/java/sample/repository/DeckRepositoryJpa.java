package sample.repository;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpastore")
public class DeckRepositoryJpa implements DeckRepository {

    // TODO - Implement the database repository
    
    @Override
    public List<String> save(String name, List<String> cards) {
        // TODO Auto-generated method stub
        
        return cards;
    }

    @Override
    public List<String> update(String name, List<String> cards) {
        // TODO Auto-generated method stub
        
        return cards;
    }

    @Override
    public Set<String> getSetOfDeckNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getNamedDeck(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> delete(String name) {
        // TODO Auto-generated method stub
        
        return null;
    }

}
