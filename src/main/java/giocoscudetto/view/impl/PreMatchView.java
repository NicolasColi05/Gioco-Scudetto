package giocoscudetto.view.impl;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
    //dati di prova
    private static String[] columnNames = {"Clubs", "Points", "Net Diff"};
    private Object[][] dati = {
        {"Inter", 6, 3},
        {"Roma", 7, 2}
    };
    private static String[] columnNames2 = {"Clubs", "Results"};
    private Object[][] dati2 = {
        { "prova", "0-0"},
        {new Pair("Inter", "Roma"), "0-0"}
    };

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_FONT_REDUCTION = 70;

    public PreMatchView(Starter controller){
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //pannello centrale
        JPanel centralPanel = new JPanel(new GridBagLayout());

        //pannello inferiore
        JPanel lowerPanel = new JPanel(new BorderLayout());

        //titolo
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);
        this.add(gameTitle, BorderLayout.NORTH);

        //prima tabella
        final JTable fixtureTable = (JTable) createComponent(new JTable(dati2, columnNames2), getTitleFont(), Color.BLACK);
        fixtureTable.setBackground(Color.WHITE);
        fixtureTable.setEnabled(false);
        fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));
        JTableHeader titolo = new JTableHeader();
        

        //seconda tabella
        final JTable table2 = (JTable) createComponent(new JTable(dati, columnNames), getTitleFont(), Color.BLACK);
        table2.setBackground(Color.WHITE);
        table2.setEnabled(false);
        table2.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));

        //pulsanti in basso
        JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK);
        JButton continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK);
        //backButton.addActionListener();

        //aggiunte al panel inferiore
        lowerPanel.add(backButton, BorderLayout.WEST);
        lowerPanel.add(continueButton, BorderLayout.EAST);

        backButton.addActionListener(e -> { 
            this.controller.changeView("club");
        });

        //aggiunte al panel centrale
        centralPanel.add(new JScrollPane(fixtureTable));
        centralPanel.add(new JScrollPane(table2));


        //aggiunte al panel principale
        this.add(gameTitle, BorderLayout.NORTH);
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();
            
            }
        });
    }

}
