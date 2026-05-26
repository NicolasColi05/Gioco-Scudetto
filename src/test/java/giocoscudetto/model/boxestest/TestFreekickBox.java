package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.FreeKickBox;

public class TestFreekickBox {
    private Club clubHome;
    private Club clubAway;
    private Match match;
    private Boxes cornerBox;

    @BeforeEach
    public void setUp() {
        clubHome = new ClubImpl("home", null);
        clubAway = new ClubImpl("away", null);
        match = new MatchImpl(clubHome, clubAway);
        cornerBox = new FreeKickBox(5);
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testEventSetsGameModeToFreekick() {
        assertNotEquals("FREE_KICK", match.getGameMode());
        cornerBox.event(match);
        assertEquals("FREE_KICK", match.getGameMode());
    }

    @Test
    public void testFreekickBoxPosition() {
        assertEquals(5, cornerBox.getPosition());
    }

    @Test
    public void testFreekickBoxName() {
        assertEquals("Freekick Box", cornerBox.getName());
    }

    @Test
    public void testFreekickBoxImage() {
        assertEquals("casella_26.png", cornerBox.getImage());
    }

    @Test
    public void testFreekickEvent() {
        final int dice1;
        final int dice2;
        final int oldHomeScore = this.match.getScore().getHomeScore();
        final int oldGuestScore = this.match.getScore().getGuestScore();

        cornerBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();

        if (dice1 + dice2 == 7) {
            assertEquals(oldHomeScore + 1, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        } else {
            assertEquals(oldHomeScore, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        }
    }
}
