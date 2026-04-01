package it.unibo.sampleapp.model.api;

import java.util.List;

public interface GoalNet {

    void setGoalKeeperPosition(int position); //in base alla prima posiizione alcune non saranno selezionabili ma lo devo controllare nel controller

    boolean isGoal();
}
