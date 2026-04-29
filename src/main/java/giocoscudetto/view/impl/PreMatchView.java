package giocoscudetto.view.impl;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextArea;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

import giocoscudetto.view.impl.ClubPanel;
import giocoscudetto.model.api.Pair;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.controller.api.Starter;

public class PreMatchView extends DefaultPanelImpl{
    
    private Starter controller;
    private static String[] columnNames = {"Clubs", "Points", "Net Diff"};
    private Object[][] dati = {
        {"Inter", 6, 3},
        {"Roma", 7, 2}
    };
    private static String[] columnNames2 = {"Clubs", "Results"};
    private Object[][] dati2 = {
        {new Pair<String, String>(new ClubImpl(new PawnImpl()).getName(), new ClubImpl(new PawnImpl()).getName()), "0-0"},
        {new Pair("Inter", "Roma"), "0-0"}
    };

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_FONT_REDUCTION = 70;

    public PreMatchView(Starter controller){
        this.controller = controller;

        this.setLayout(new BorderLayout());


        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);
        this.add(gameTitle, BorderLayout.NORTH);

        final JTable fixtureTable = (JTable) createComponent(new JTable(dati2, columnNames2), getTitleFont(), Color.RED);
        fixtureTable.setBackground(Color.WHITE);
        fixtureTable.setEnabled(false);
        fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));

        final JTable table2 = (JTable) createComponent(new JTable(dati, columnNames), getTitleFont(), Color.RED);
        table2.setBackground(Color.WHITE);
        table2.setEnabled(false);
        
        table2.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));

        this.add(gameTitle, BorderLayout.NORTH);
        this.add(new JScrollPane(fixtureTable), BorderLayout.WEST);
        this.add(new JScrollPane(table2), BorderLayout.EAST);
    }

}
