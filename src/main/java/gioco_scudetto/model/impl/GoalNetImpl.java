package gioco_scudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import gioco_scudetto.model.api.GoalNet;

public class GoalNetImpl implements GoalNet {

    private List<Integer> goalKeeperPosition;

    public GoalNetImpl() {
        this.goalKeeperPosition = new ArrayList<Integer>();
    }

    @Override
    public void setGoalKeeperPosition(int position) {
        
        if ( goalKeeperPosition.size() < 2) {
            this.goalKeeperPosition.add(position);
        } 

    }

    @Override
    public boolean isGoal() {
        return false;
    }

}
