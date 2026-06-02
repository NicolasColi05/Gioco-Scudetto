package giocoscudetto.model.impl;

import javax.swing.table.AbstractTableModel;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;

import java.util.List;

/**
 * Table model used to display the League standings.
 */
public final class LeagueTableModel extends AbstractTableModel {

    private final List<Club> clubs;

    private final String[] columns = {
        "Club",
        "Points",
        "Net Diff",
    };

    /**
     * Creates a League table model.
     * 
     * @param table League table
     */
    public LeagueTableModel(final Table table) {

        this.clubs = table.getClubs(); 
    }

    @Override
    public int getRowCount() {
        return clubs.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(final int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        final Club club = clubs.get(row);

        return switch (col) {

            case 0 -> club.getName();

            case 1 -> club.getPoints();

            case 2 -> club.getNetDiff();

            default -> null;

        };
    }
}
