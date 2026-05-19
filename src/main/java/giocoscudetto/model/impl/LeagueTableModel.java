package giocoscudetto.model.impl;
import javax.swing.table.AbstractTableModel;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;

import java.util.List;

public class LeagueTableModel extends AbstractTableModel {

    private final List<Club> clubs;

    private final String[] columns = {
        "Club",
        "Points",
        "Net Diff",
    };

    public LeagueTableModel(Table table) {

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
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Club club = clubs.get(row);

        return switch (col) {

            case 0 -> club.getName();

            case 1 -> club.getPoints();

            case 2 -> club.getNetDiff();

            default -> null;

        };
    }


}
