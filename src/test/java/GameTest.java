//import com.improving.Card;
//import com.improving.Colors;
//import com.improving.Faces;
//import com.improving.Game;
//import com.improving.Player;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class GameTest {
//
//    @Test
//    public void Player_Should_Draw_Appropriate_Amount_Of_Cards_If_Draw_Card_Is_Played() {
//        //arrange
//        Game game = new Game(1);
//        List<Card> hand = new ArrayList<>();
//        Player player = new Player((ArrayList<Card>) hand, 1);
//
//        //act
//        game.getDeck().getDiscardPile().add(new Card(Faces.DRAW_FOUR, Colors.WILD));
//        player.takeTurn(game);
//
//        //assert
//        assertEquals(4, player.handSize());
//
//
//    }
//
//}
