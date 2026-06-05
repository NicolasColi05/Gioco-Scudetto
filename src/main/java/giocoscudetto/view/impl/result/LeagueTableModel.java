package giocoscudetto.view.impl.result;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;

/**
 * A model for displaying the table information in a JTable.
 */
@SuppressFBWarnings
public class LeagueTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final List<Club> table;
    private final String[] columns = {"Club", "Points", "Net Difference"};

    /**
     * Constructor for the table model.
     * 
     * @param table the table to be displayed
     */
    public LeagueTableModel(final Table table) {
        this.table = new ArrayList<>(table.showPosition());
    }

    @Override 
    public final int getRowCount() {
        return table.size();
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
            case 0 -> table.get(row).getName();
            case 1 -> table.get(row).getPoints();
            case 2 -> table.get(row).getNetDiff();
            default -> null;
        };
    }

}
