package gioco_scudetto.model.api;


public interface GoalNet {

    void setGoalKeeperPosition(int position); //in base alla prima posiizione alcune non saranno selezionabili ma lo devo controllare nel controller

    boolean isGoal();
}
