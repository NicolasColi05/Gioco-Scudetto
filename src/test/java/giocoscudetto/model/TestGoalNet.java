package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.impl.GoalNetImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.GoalNetImpl}.
 */
public class TestGoalNet {

    private GoalNetImpl goalNet;

    @BeforeEach
    public void setUp() {
        goalNet = new GoalNetImpl();
    }

    @Test
    public void testSetGoalKeeperPosition() {
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(5);
    }
    
    @Test
    public void testSetGoalKeeperPositionMaxPositions() {
        goalNet.setGoalKeeperPosition(1);
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(5);
        goalNet.setGoalKeeperPosition(7);
        assertTrue(goalNet.isGoal(5));
    }

    @Test
    public void testIsGoal() {
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(5);
        assertTrue(goalNet.isGoal(1));
    }

    @Test
    public void testIsNotGoal() {
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(8);
        assertFalse(goalNet.isGoal(8));
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(8);
        assertFalse(goalNet.isGoal(2));
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(4);
        assertFalse(goalNet.isGoal(4));
    }

    @Test
    public void testMultipleRounds() {
        goalNet.setGoalKeeperPosition(1);
        goalNet.setGoalKeeperPosition(5);
        assertTrue(goalNet.isGoal(3));
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(6);
        assertFalse(goalNet.isGoal(2));
        goalNet.setGoalKeeperPosition(4);
        goalNet.setGoalKeeperPosition(8);
        assertTrue(goalNet.isGoal(7));
    }

}
