package giocoscudetto.model.impl;

import javax.swing.table.AbstractTableModel;

import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;

import java.util.*;

public class FixtureModel extends AbstractTableModel {

    private final List<Match> matches;
    private final List<Scoreboard> scores;
    private final String[] columns = {"Match", "Score"};

    public FixtureModel(Fixtures fixture) {
        System.out.println(fixture.toString());
        // Congela l'ordine della mappa in due liste parallele
        this.matches = new ArrayList<>(fixture.getListOfMatches());
        this.scores  = matches.stream()
                              .map(match -> fixture.getScoreboard(match))
                              .toList();
    }

    @Override public int getRowCount() { return matches.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int col) { return columns[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        return switch (col) {
            case 0 -> matches.get(row).toString();
            case 1 -> (scores.get(row)!= null ? scores.get(row).toString() : " vs");
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return col == 0 ? Match.class : Scoreboard.class;
    }
}
