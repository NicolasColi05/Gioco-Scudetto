package giocoscudetto.controller.impl;

import giocoscudetto.controller.api.MatchController;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.view.api.GameObserver;
import java.util.ArrayList;
import java.util.List;

public class MatchControllerImpl implements MatchController {
    private final CreateUpdateController controller;
    private final Board board = new BoardImpl();
    private Fixtures fixture;
    private Match match;
    private Table table;
    private List<GameObserver> observers = new ArrayList<>();
    private boolean helpFlag = false;

    /**
     * Constructor for MatchControllerImpl.
     * 
     * @param manager the view manager to use for the controller.
     */
    public MatchControllerImpl(CreateUpdateController controller) {
        this.controller = controller;
    }

    @Override
    public void checkBox() {
        this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).event(this.match);
        notifyViews();
    }

    @Override
    public String getBoxImage(int i) {
        return this.board.getBoxImage(i);
    }

    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    @Override
    public int getHomePosition() {
        return this.match.getClubHome().getPawn().getPosition();
    }

    @Override
    public void setKeeperPosition(int i) {
        this.match.setKeeperPosition(i);
    }

    @Override
    public int getGuestPosition() {
        return this.match.getClubAway().getPawn().getPosition();
    }

    @Override
    public boolean kickPenalty() {
        final int oldGuestScore = this.match.getScore().getGuestScore();
        final int oldHomeScore = this.match.getScore().getHomeScore();
        this.match.eventMode();
        return (this.match.getScore().getGuestScore() != oldGuestScore || this.match.getScore().getHomeScore() != oldHomeScore);
    }

    @Override
    public String getCurrentPlayer() {
        return this.match.getCurrentPlayer().getName();
    }

    @Override
    public int move() {
        int resultDice = this.match.rollDice();
        if (resultDice == 0) {
            this.match.turn();
        } else {
        this.match.getCurrentPlayer().getPawn().changePosition(resultDice);
        }
        notifyViews();
        return resultDice;
    }

    @Override
    public Table getTable(){
        return this.table;
    }

    @Override
    public String getDescription() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    @Override
    public void setMatch(){
        this.fixture = controller.getFixture();
        this.match = this.fixture.setNextMatch();
        this.table = controller.getTable();
        notifyViews();
    }


    @Override
    public String getGameMode() {
        return this.match.getGameMode();
    }

    @Override
    public void gameModeFinished() {
        this.match.setGameMode(Match.GameMode.NONE);
        this.match.turn();
        notifyViews();
    }

    @Override
    public void addObserver(final GameObserver ob) {
        this.observers.add(ob);
    }

    @Override
    public void removeObserver(final GameObserver ob) {
        this.observers.remove(ob);
    }
    
    @Override
    public void notifyViews() {
        for (GameObserver ob : observers) {
            ob.updateState();
        }
    }

    @Override
    public int getHomePawnRGB() {
        return this.match.getClubHome().getPawn().getPawnRGB();
    }

    @Override
    public int getGuestPawnRGB() {
        return this.match.getClubAway().getPawn().getPawnRGB();
    }
    
    @Override
    public boolean isLastBox(){
        return this.match.getCurrentPlayer().getPawn().getPosition() == 32;
    }

    @Override
    public void lastBox(){
        this.fixture.setScore(match, this.match.getScore());
        System.out.println (this.fixture.toString());
    }

    @Override
    public boolean isLastMatch(){
        return this.fixture.seeNextMatch(this.match)==null;
    }

    @Override 
    public void setPositionsZero(){
        for (Club club : this.controller.getClubs()) {
            club.getPawn().setPosition(0);
        }
        notifyViews();
    }

    @Override
    public void addPoints(){
        if(this.match.getScore().getHomeScore()==this.match.getScore().getGuestScore()){
            this.match.getClubHome().incrementPoints(1);
            this.match.getClubAway().incrementPoints(1);
        }else{
            this.match.getWinnerClub().incrementPoints(3);
        }
        this.match.getClubHome().changeNetDiffs(this.match.getScore().getHomeScore(), this.match.getScore().getGuestScore());
        this.match.getClubAway().changeNetDiffs(this.match.getScore().getGuestScore(), this.match.getScore().getHomeScore());

        this.table.updateClubRank();
        
    }

    
    public int diceEvent() {
       return this.match.diceEvent();
    }

    @Override
    public String getHomeName() {
        return this.match.getClubHome().getName();
    }

    @Override
    public String getGuestName() {
        return this.match.getClubAway().getName();
    }

    @Override
    public void setHelpFlag(final boolean selected) {
        this.helpFlag = selected;
    }

    @Override
    public boolean isHelpFlag() {
        return this.helpFlag;
    }

    @Override
    public String getBoxName() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getName();
    }

    @Override
    public String getBoxDescript() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    public String getWinner() {
        return this.table.getClubs().get(0).getName();
    }

}
