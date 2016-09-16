package sample.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sample.Application;
import sample.service.DeckService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DeckRestEndpointTest {

    @Mock
    private DeckService deckService;
    
    @InjectMocks
    private DeckRestEndpoint deckRestEndpoint;
    
    private MockMvc mockMvc;
    
    @SuppressWarnings("rawtypes")
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deckRestEndpoint).build();
    }

    
    @Test
    public void putRequestCreatesNewNamedDeckTest() throws Exception {
        String name = "fancydeck";
        mockMvc.perform(put("/deck/" + name))
        .andExpect(status().isOk());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void postRequestShufflesAnExistingDeck() throws Exception {
        ShuffleForm shuffleForm = new ShuffleForm("fancydeck");
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(shuffleForm,
                MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        String shuffleFormJson = mockHttpOutputMessage.getBodyAsString();

        this.mockMvc.perform(post("/deck/")
                .content(shuffleFormJson)
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getRequestReturnsSetOfDeckNames() throws Exception {
        Set<String> deckNames = new HashSet<>(3);
        deckNames.add("Bicycle");
        deckNames.add("Legends");
        deckNames.add("Expert");
        when(deckService.getSetOfDeckNames()).thenReturn(deckNames);
        
        mockMvc.perform(get("/deck/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0]", is("Bicycle")))
        .andExpect(jsonPath("$[1]", is("Legends")))
        .andExpect(jsonPath("$[2]", is("Expert")));
    }
    
    @Test
    public void getNamedDeckInCurrentShuffledOrder() throws Exception {
        List<String> deck = new ArrayList<>(
                Arrays.asList("ACE-HEART", "TWO-SPADES", "THREE-CLUBS", "FOUR-DIAMONDS"));
        when(deckService.getNamedDeck("Bicycle")).thenReturn(deck);
        
        mockMvc.perform(get("/deck/Bicycle"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$", hasSize(4)))
        .andExpect(jsonPath("$[0]", is("ACE-HEART")))
        .andExpect(jsonPath("$[1]", is("TWO-SPADES")))
        .andExpect(jsonPath("$[2]", is("THREE-CLUBS")))      
        .andExpect(jsonPath("$[3]", is("FOUR-DIAMONDS")));    
        
        verify(deckService, times(1)).getNamedDeck("Bicycle");
    }
    
    @Test
    public void deleteNamedDeckRemovesFromRepository() throws Exception {
        mockMvc.perform(delete("/deck/Bicycle"))
        .andExpect(status().isOk());
        
        verify(deckService, times(1)).deleteNamedDeck("Bicycle");
    }
}
