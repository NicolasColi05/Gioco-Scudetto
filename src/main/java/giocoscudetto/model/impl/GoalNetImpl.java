package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.GoalNet;

/**
 * This class represents the goal net of the game, it has a list of integers that represent the position of the goalkeeper.
 */
public class GoalNetImpl implements GoalNet {

    private final List<Integer> goalKeeperPositions;

    public GoalNetImpl() {
        this.goalKeeperPositions = new ArrayList<Integer>();
    }

    @Override
    public void setGoalKeeperPosition(final int position) {
        if (goalKeeperPositions.size() < 2) {
            this.goalKeeperPositions.add(position);
        }

    }

    @Override
    public boolean isGoal(final int ballPosition) {
        if (goalKeeperPositions.contains(ballPosition)) {
            this.goalKeeperPositions.clear();
            return false;
        }
        this.goalKeeperPositions.clear();
        return true;
    }

}
