package giocoscudetto.view.impl;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.creation.ClubPanel;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;

public class PreMatchView extends DefaultPanelImpl{
    
    private Starter starter;
    private CreateUpdateController controller;
    private ViewManager viewManager;
    //dati di prova
    private static String[] columnNamesS = {"Clubs", "Points", "Net Diff"};
    private Object[][] dati = {
        {"Inter", 6, 3},
        {"Roma", 7, 2},
        {"Inter", 6, 3},
        {"Roma", 7, 2}
    };
    private static String[] columnNamesF = {"Clubs", "Results"};
    private Object[][] dati2 = {
        { "prova", "0-0"},
        { "inter-roma", "0-0"}
    };

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_FONT_REDUCTION = 70;
    private static final int BUTTON_BORDER = 5;
    private final Image image;

    public PreMatchView(final Starter starter, final CreateUpdateController controller, final ViewManager viewManager){
        this.starter = starter;
        this.controller = controller;
        this.viewManager = viewManager;

        Table tablee = this.controller.getTable();

        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/pre-match-background.jpeg"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image", e);
        }

        //pannello centrale
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setOpaque(false);

        //pannello inferiore
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));
        lowerPanel.setOpaque(false);

        //prima tabella
        final JTable fixtureTable = (JTable) createComponent(new JTable(dati2, columnNamesF), getTitleFont(), Color.BLACK, null);
        fixtureTable.setEnabled(false);
        fixtureTable.setOpaque(false);
        fixtureTable.getTableHeader().setReorderingAllowed(false);
        fixtureTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));
        

        //seconda tabella
        final JTable standingsTable = (JTable) createComponent(new JTable(dati, columnNamesS), getTitleFont(), Color.BLACK, null);
        standingsTable.setEnabled(false);
        standingsTable.setOpaque(false);
        //standingsTable.setBackground(new Color(0, 0, 0, 0));
        standingsTable.getTableHeader().setReorderingAllowed(false);
        standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION));

        //pulsanti in basso
        JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK, null);
        JButton continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);

        backButton.addActionListener(e -> { 
            this.starter.changeView("club");
        });

        continueButton.addActionListener(e -> { 
            this.starter.setMatch();
            MatchPanel MatchPanel = new MatchPanel(this.starter);
            viewManager.addView(MatchPanel, "match");
            this.starter.changeView("match");
        });

        //aggiunte al panel inferiore
        lowerPanel.add(backButton, BorderLayout.WEST);
        lowerPanel.add(continueButton, BorderLayout.EAST);

        //aggiunte al panel centrale
        JScrollPane scrollPaneF = new JScrollPane(fixtureTable);
        TitledBorder titleF = new TitledBorder("FIXTURE");
        titleF.setTitleJustification(TitledBorder.CENTER);
        titleF.setTitleColor(new Color(195, 45, 35));
        scrollPaneF.setOpaque(false);
        scrollPaneF.getViewport().setOpaque(false);
        scrollPaneF.setBorder(titleF);
        centralPanel.add(scrollPaneF);

        JScrollPane scrollPaneS = new JScrollPane(standingsTable);
        TitledBorder titleS = new TitledBorder("STANDINGS");
        titleS.setTitleJustification(TitledBorder.CENTER);
        titleS.setTitleColor(new Color(195, 45, 35));
        scrollPaneS.setOpaque(false);
        scrollPaneS.getViewport().setOpaque(false);
        scrollPaneS.setBorder(titleS);
        centralPanel.add(scrollPaneS);


        //aggiunte al panel principale
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                standingsTable.setFont(new Font(FONT_SELECTED, Font.ROMAN_BASELINE, currentWidth / 100));
                fixtureTable.setFont(new Font(FONT_SELECTED, Font.ROMAN_BASELINE, currentWidth / 100));
                standingsTable.setRowMargin(3);
                fixtureTable.setRowMargin(3);
                standingsTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                fixtureTable.setRowHeight(currentHeight / SWITCHER_BUTTON_FONT_RESIZING);
                standingsTable.setPreferredScrollableViewportSize(new Dimension(currentWidth/3, currentHeight/3));
                fixtureTable.setPreferredScrollableViewportSize(new Dimension(currentWidth/3, currentHeight/3));
                titleS.setTitleFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / 70));
                titleF.setTitleFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / 70));
                revalidate();
            
            }
        });
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0,0, getWidth(), getHeight(),null);
    }

}
