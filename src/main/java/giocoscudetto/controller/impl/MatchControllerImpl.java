package giocoscudetto.controller.impl;

import giocoscudetto.controller.api.MatchController;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.match.BoardImpl;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.view.api.GameObserver;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of the MatchController interface.
 */
public class MatchControllerImpl implements MatchController {
    private final CreateUpdateController controller;
    private final Board board = new BoardImpl();
    private final List<GameObserver> observers = new ArrayList<>();
    private Fixtures fixture;
    private Match match;
    private Table table;
    private boolean helpFlag;

    /**
     * Constructor for MatchControllerImpl.
     * 
     * @param controller the create/update controller to use for the match controller.
     */
    public MatchControllerImpl(final CreateUpdateController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkBox() {
        this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).event(this.match);
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxImage(final int i) {
        return this.board.getBoxImage(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHomePosition() {
        return this.match.getClubHome().getPawn().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeeperPosition(final int i) {
        this.match.setKeeperPosition(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGuestPosition() {
        return this.match.getClubAway().getPawn().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean kickPenalty() {
        final int oldGuestScore = this.match.getScore().getGuestScore();
        final int oldHomeScore = this.match.getScore().getHomeScore();
        this.match.eventMode();
        return this.match.getScore().getGuestScore() != oldGuestScore || this.match.getScore().getHomeScore() != oldHomeScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentPlayer() {
        return this.match.getCurrentPlayer().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int move() {
        final int resultDice = this.match.rollDice();
        if (resultDice == 0) {
            this.match.turn();
        } else {
        this.match.getCurrentPlayer().getPawn().changePosition(resultDice);
        }
        notifyViews();
        return resultDice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings
    public Table getTable() {
        return this.table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMatch() {
        this.fixture = this.controller.getFixture();
        this.match = this.fixture.nextMatch();
        this.table = this.controller.getTable();
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameMode() {
        return this.match.getGameMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameModeFinished() {
        this.match.setGameMode(Match.GameMode.NONE);
        this.match.turn();
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final GameObserver ob) {
        this.observers.add(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(final GameObserver ob) {
        this.observers.remove(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyViews() {
        observers.forEach(GameObserver::updateState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHomePawnRGB() {
        return this.match.getClubHome().getPawn().getPawnRGB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGuestPawnRGB() {
        return this.match.getClubAway().getPawn().getPawnRGB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastBox() {
        return this.match.getCurrentPlayer().getPawn().getPosition() == 32;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lastBox() {
        this.fixture.setScore(match, this.match.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastMatch() {
        return this.fixture.seeNextMatch(this.match) == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public void setPositionsZero() {
        for (final Club club : this.controller.getClubs()) {
            club.getPawn().setPosition(0);
        }
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoints() {
        if (this.match.getScore().getHomeScore() == this.match.getScore().getGuestScore()) {
            this.match.getClubHome().incrementPoints(1);
            this.match.getClubAway().incrementPoints(1);
        } else {
            this.match.getWinnerClub().incrementPoints(3);
        }
        this.match.getClubHome().changeNetDiffs(this.match.getScore().getHomeScore(), this.match.getScore().getGuestScore());
        this.match.getClubAway().changeNetDiffs(this.match.getScore().getGuestScore(), this.match.getScore().getHomeScore());

        this.table.updateClubRank();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int diceEvent() {
       return this.match.diceEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHomeName() {
        return this.match.getClubHome().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGuestName() {
        return this.match.getClubAway().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHelpFlag(final boolean selected) {
        this.helpFlag = selected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHelpFlag() {
        return this.helpFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxName() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxDescript() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLeagueWinner() {
        return this.table.showPosition().get(0).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastShootPosition() {
        return this.match.getLastShootPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotCurrentPlayer() {
        return this.match.getNotCurrentPlayer();
    }

}
