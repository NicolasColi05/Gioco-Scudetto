package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;

public class TableModel extends AbstractTableModel {

    private final List<Club> table;
    private final String[] columns = {"Club", "Points", "Net Difference"};

    public TableModel(Table table) {
        this.table = new ArrayList<>(table.showPosition());
    }

    @Override public int getRowCount() { return table.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int col) { return columns[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        return switch (col) {
            case 0 -> table.get(row).getName();
            case 1 -> table.get(row).getPoints();
            case 2 -> table.get(row).getNetDiff();
            default -> null;
        };
    }

}
