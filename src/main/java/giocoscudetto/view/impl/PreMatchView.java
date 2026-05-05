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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
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
    private static final int BUTTON_BORDER = 5;

    public PreMatchView(Starter controller){
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //pannello centrale
        JPanel centralPanel = new JPanel(new GridBagLayout());

        //pannello inferiore
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        //titolo
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED,null);
        this.add(gameTitle, BorderLayout.NORTH);

        //prima tabella
        final JTable fixtureTable = (JTable) createComponent(new JTable(dati2, columnNames2), getTitleFont(), Color.BLACK,null);
        fixtureTable.setBackground(Color.orange);
        fixtureTable.setEnabled(false);
        fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));
        JTableHeader titolo = new JTableHeader();
        

        //seconda tabella
        final JTable standingsTable = (JTable) createComponent(new JTable(dati, columnNames), getTitleFont(), Color.BLACK, null);
        standingsTable.setBackground(Color.YELLOW);
        standingsTable.setEnabled(false);
        standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));
        standingsTable.setBorder(BorderFactory.createTitledBorder("STANDINGS"));

        //pulsanti in basso
        JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK, null);
        JButton continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);
        //backButton.addActionListener();

        //aggiunte al panel inferiore
        lowerPanel.add(backButton, BorderLayout.WEST);
        lowerPanel.add(continueButton, BorderLayout.EAST);

        backButton.addActionListener(e -> { 
            this.controller.changeView("club");
        });

        continueButton.addActionListener(e -> { 
            this.controller.changeView("match");
        });

        //aggiunte al panel centrale
        centralPanel.add(new JScrollPane(fixtureTable));
        centralPanel.add(new JScrollPane(standingsTable));


        //aggiunte al panel principale
        this.add(gameTitle, BorderLayout.NORTH);
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / 100));
                fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / 100));
                standingsTable.setRowMargin(3);
                fixtureTable.setRowMargin(3);
                standingsTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                fixtureTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                standingsTable.setPreferredScrollableViewportSize(new Dimension(currentWidth/3, currentHeight/3));
                fixtureTable.setPreferredScrollableViewportSize(new Dimension(currentWidth/3, currentHeight/3));
                revalidate();
            
            }
        });
    }

}
