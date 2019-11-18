import com.improving.Card;
import com.improving.Colors;
import com.improving.Faces;
import com.improving.Game;
import com.improving.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTests {

    @Test
    public void take_Turn_Should_Play_First_Available_Valid_Card() {
        //arrange
        Game game = new Game(1);
        List<Card> hand = new ArrayList<>();
        Player player = new Player((ArrayList<Card>) hand, 1);
        //act
        hand.add(new Card(Faces.FIVE, Colors.BLUE));
        hand.add(new Card(Faces.THREE, Colors.RED));
        hand.add(new Card(Faces.THREE, Colors.BLUE));
        game.getDeck().getDiscardPile().add(new Card(Faces.THREE, Colors.GREEN));

        player.takeTurn(game);

        Card result = new Card(Faces.THREE, Colors.RED);

        //assert
        assertEquals(result.getFaces(), game.topCardOfDiscard().getFaces());
    }

        @Test
    public void take_Turn_Should_Play_Wild_Cards() {
        //arrange
            Game game = new Game(1);
            List<Card> hand = new ArrayList<>();
            Player player = new Player((ArrayList<Card>) hand, 1);
        //act
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.THREE, Colors.GREEN));
            game.getDeck().getDiscardPile().add(new Card(Faces.THREE, Colors.RED));

        player.takeTurn(game);

        //assert
        assertEquals(1, hand.size());
    }

    @Test
    public void only_One_Card_Should_Be_Drawn_If_No_Playable_Card_In_Hand() {
                //arrange
        Game game = new Game(2);
        List<Card> hand = new ArrayList<>();
        Player player = new Player((ArrayList<Card>) hand, 1);

       //act
        hand.add(new Card(Faces.FIVE, Colors.BLUE));
        game.getDeck().getDiscardPile().add(new Card(Faces.SEVEN, Colors.GREEN));
        player.takeTurn(game);
        //assert
        assertEquals(2, hand.size());


    }



}
