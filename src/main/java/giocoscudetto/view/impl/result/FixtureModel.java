package giocoscudetto.view.impl.result;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.match.Scoreboard;

/**
 * A model for displaying the fixture in a JTable.
 */
@SuppressFBWarnings
public class FixtureModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final List<Match> matches;
    private final List<Scoreboard> scores;
    private final String[] columns = {"Match", "Score"};

    /**
     * Constructor for the fixture model.
     * 
     * @param fixture the fixture to be displayed
     */
    public FixtureModel(final Fixtures fixture) {
        this.matches = new ArrayList<>(fixture.getListOfMatches());
        this.scores = matches.stream()
                              .map(fixture::getScoreboard)
                              .toList();
    }

    @Override 
    public final int getRowCount() {
        return matches.size();
    }

    @Override 
    public final int getColumnCount() {
        return columns.length;
    }

    @Override 
    public final String getColumnName(final int col) {
        return columns[col];
    }

    @Override
    public final Object getValueAt(final int row, final int col) {
        return switch (col) {
            case 0 -> matches.get(row).toString();
            case 1 -> scores.get(row) != null ? scores.get(row).toString() : " vs";
            default -> null;
        };
    }

}
