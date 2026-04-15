package gioco_scudetto.model.api;


public interface GoalNet {

    void setGoalKeeperPosition(int position); //in base alla prima posiizione alcune non saranno selezionabili ma lo devo controllare nel controller,
                                             // es: se il portiere è in posizione 1 posso scegliere solo tra 2,4,5

    boolean isGoal();
}
