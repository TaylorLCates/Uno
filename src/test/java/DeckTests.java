import com.improving.Card;
import com.improving.Color;
import com.improving.Deck;
import com.improving.Face;
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
