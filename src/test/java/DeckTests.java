import com.improving.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTests {

    @Test
    public void deck_Should_Be_112_Cards() {
        //arrange
        Deck deck = new Deck();
        //act
        var result = deck.getDrawPile().size();
        //assert
        assertEquals(112, result);
    }



}
