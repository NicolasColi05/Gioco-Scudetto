package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.CornerBox;

public class TestCornerBox {
    private Club clubHome;
    private Club clubAway;
    private Match match;
    private Boxes cornerBox;

    @BeforeEach
    public void setUp() {
        clubHome = new ClubImpl("home", null);
        clubAway = new ClubImpl("away", null);
        match = new MatchImpl(clubHome, clubAway);
        cornerBox = new CornerBox(5);
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testEventSetsGameModeToCorner() {
        assertNotEquals("CORNER", match.getGameMode());
        cornerBox.event(match);
        assertEquals("CORNER", match.getGameMode());
    }

    @Test
    public void testCornerBoxPosition() {
        assertEquals(5, cornerBox.getPosition());
    }

    @Test
    public void testCornerBoxName() {
        assertEquals("Corner Box", cornerBox.getName());
    }

    @Test
    public void testCornerBoxImage() {
        assertEquals("casella_19.png", cornerBox.getImage());
    }

    @Test
    public void testCornerEvent() {
        final int dice1;
        final int dice2;
        final int oldHomeScore = this.match.getScore().getHomeScore();
        final int oldGuestScore = this.match.getScore().getGuestScore();

        cornerBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();

        if (dice1 == 1 || dice2 == 1) {
            assertEquals(oldHomeScore + 1, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        } else {
            assertEquals(oldHomeScore, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        }
    }
}
