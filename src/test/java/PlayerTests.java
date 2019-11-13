import com.improving.Card;
import com.improving.Color;
import com.improving.Deck;
import com.improving.Face;
import com.improving.Game;
import com.improving.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

//public class PlayerTests {
//
//    @Test
//    public void take_Turn_Should_Play_First_Available_Valid_Card() {
//        //arrange
//        Game game = new Game;
//        Deck deck = new Deck();
//        List<Card> hand = new ArrayList<>();
//        Player player = new Player(hand);
//        //act
//        player.getHand().getPlayerHand().add(new Card(Face.FIVE, Color.BLUE));
//        player.getHand().getPlayerHand().add(new Card(Face.THREE, Color.RED));
//        player.getHand().getPlayerHand().add(new Card(Face.THREE, Color.BLUE));
//        player.getDeck().getDiscardPile().add(new Card(Face.THREE, Color.GREEN));
//
//        player.takeTurn();
//
//        Card result = new Card(Face.THREE, Color.RED);
//
//        //assert
//        assertEquals(result.getFace(), deck.getDiscardPile().get(deck.getDiscardPile().size()-1).getFace());
//    }
//
//        @Test
//    public void take_Turn_Should_Play_Wild_Cards() {
//        //arrange
//        Deck deck = new Deck();
//        Hand hand = new Hand(deck);
//        Player player = new Player(hand);
//        //act
//        player.getHand().getPlayerHand().add(new Card(Face.DRAW_FOUR, Color.WILD));
//            player.getDeck().getDiscardPile().add(new Card(Face.THREE, Color.GREEN));
//            player.getHand().getPlayerHand().add(new Card(Face.THREE, Color.RED));
//
//        player.takeTurn();
//
//        //assert
//        assertEquals(1, hand.getPlayerHand().size());
//    }
//
//    @Test
//    public void only_One_Card_Should_Be_Drawn_If_No_Playable_Card_In_Hand() {
//                //arrange
//       Deck deck = new Deck();
//       Hand hand = new Hand(deck);
//       Player player = new Player(hand);
//
//       //act
//        player.getHand().getPlayerHand().add(new Card(Face.FIVE, Color.BLUE));
//        deck.getDiscardPile().add(new Card(Face.SEVEN, Color.GREEN));
//        player.takeTurn();
//        //assert
//        assertEquals(2, player.getHand().getPlayerHand().size());
//
//
//    }
//
//
//
//}
