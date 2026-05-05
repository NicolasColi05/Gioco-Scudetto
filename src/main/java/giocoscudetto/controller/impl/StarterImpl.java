package giocoscudetto.controller.impl;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Table;

/**
 * Starter implementation.
 */
public class StarterImpl implements Starter {

    private final ViewManager viewManager;
    private final Board board = new BoardImpl();
    private final Match match = new MatchImpl();
    private final ArrayList<Club> listofClubs = new ArrayList<Club>();
    private Fixtures fixture;
    private int numberOfClubs;
    private Table table;

    /**
     * Constructor for StarterImpl.
     * 
     * @param manager the view manager to use for the controller.
     */
    public StarterImpl(final ViewManager manager) {
        this.viewManager = manager;
    }

    @Override
    public void startGame() {
        this.viewManager.showView("home");
    }

    @Override
    public void changeView(final String panelName) {
        SwingUtilities.invokeLater(() -> viewManager.showView(panelName));
        System.out.println("controller");
    }

    @Override
    public void closeGame() {
        this.viewManager.quit();
    }

    @Override
    public void checkBox() {
        
    }

    @Override
    public Image getBoxImage(int i) {
        return this.board.getBoxImage(i);
    }

    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    @Override
    public Color getHomeTeamColor() {
        return this.match.getClubHome().getColor();
    }

    @Override
    public int getHomePosition() {
        return this.match.getClubHome().getPawn().getPosition();
    }

    @Override
    public void setNumberOfClubs(int n) {
        this.numberOfClubs = n;
    }

    @Override
    public void setNewClub(String name) { //come facciamo la parte di pawn? inoltre vogliamo fare che si passa
        //una squadra alla volta o direttamente tutta la lista? perchè dopo cambia anche la chiamata di setFixture
        if(this.listofClubs.size() < numberOfClubs){
            Club club = new ClubImpl(name , null);
            listofClubs.add(club);
        }
        //vogliamo tirare un'ecception anche se in teoria 
        //non si dovrebbe mai verificare una cosa del genere?
    }

    private void setFixture(){
        this.fixture = new FixturesImpl(listofClubs);
    }

     @Override
    public Fixtures getFixture() {
       return this.fixture;
    }

    private void setTable(){
        this.table = new TableImpl(listofClubs);
    }

    @Override
    public Table getTable(){
        return this.table;
    }

    
}
