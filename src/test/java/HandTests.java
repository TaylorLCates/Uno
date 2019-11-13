//import com.improving.Card;
//import com.improving.Color;
//import com.improving.Deck;
//import com.improving.Face;
//import com.improving.Hand;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class HandTests {
//
//    @Test
//    public void initialize_Hand_Should_Draw_Seven_Cards() {
//        //arrange
//        Deck deck = new Deck();
//        Hand hand = new Hand(deck);
//        //act
//        hand.initializeHand();
//        //assert
//        assertEquals(7, hand.getPlayerHand().size());
//    }
//    @Test
//    public void play_Should_Place_Card_Into_Discard() {
//        //arrange
//        Deck deck = new Deck();
//        Hand hand = new Hand(deck);
//        Card cardy = new Card(Face.EIGHT, Color.BLUE);
//        //act
//        hand.getPlayerHand().add(cardy);
//        hand.play(cardy);
//        //assert
//        assertEquals(cardy, deck.getDiscardPile().get(0));
//    }
//    @Test
//    public void draw_Should_Pull_Random_Card_From_The_Deck() {
//        //arrange
//        Deck deck = new Deck();
//        Hand hand = new Hand(deck);
//        //act
//
//        hand.draw();
//
//        //assert
//        //assertEquals();
//
//    }
//}
