package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.GoalNet;

/**
 * This class represents the goal net of the game, it has a list of integers that represent the position of the goalkeeper.
 */
public class GoalNetImpl implements GoalNet {

    private final List<Integer> goalKeeperPosition;

    public GoalNetImpl() {
        this.goalKeeperPosition = new ArrayList<Integer>();
    }

    @Override
    public void setGoalKeeperPosition(final int position) {
        if (goalKeeperPosition.size() < 2) {
            this.goalKeeperPosition.add(position);
        }

    }

    @Override
    public boolean isGoal(final int ballPosition) {
        if (goalKeeperPosition.contains(ballPosition)) {
            return false;
        }
        return true;
    }

}
