package gioco_scudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import gioco_scudetto.model.api.GoalNet;

/**
 * This class represents the goal net of the game, it has a list of integers that represent the position of the goalkeeper.
 */
public class GoalNetImpl implements GoalNet {

    private final List<Integer> goalKeeperPosition;

    /**
     * This is the constructor for the GoalNetImpl class.
     */
    public GoalNetImpl() {
        this.goalKeeperPosition = new ArrayList<Integer>();
    }

    /**
     * This method sets the position of the goalkeeper.
     * 
     * @param position the position of the goalkeeper.
     */
    @Override
    public void setGoalKeeperPosition(final int position) {
        if (goalKeeperPosition.size() < 2) {
            this.goalKeeperPosition.add(position);
        }

    }

    /**
     * This method checks if the ball is in the goal or not.
     * 
     * @param ballPosition the position of the ball,
     * @return true if the ball is in the goal and false otherwise.
     */
    @Override
    public boolean isGoal(final int ballPosition) {
        if (goalKeeperPosition.contains(ballPosition)) {
            return false;
        }
        return true;
    }

}
