package giocoscudetto.view.impl;

import javax.swing.*;
import java.awt.*;

import giocoscudetto.controller.api.Starter;

public class EndGameView extends DefaultPanelImpl {
    
    private Starter controller;
    
    //intestazione tabella
    private static String[] columnNames = {"Club", "Points", "Net Diff"};
    //dati di prova
    private Object[][] data = {
        {"Inter", 10, 5},
        {"Roma", 8, 2}
    };

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_BORDER = 5;

    public EndGameView(Starter controller, String winner) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("FINAL RANKING", SwingConstants.CENTER);
        title.setFont(getTitleFont());
        title.setForeground(Color.RED);

        //vincitore
        JLabel winnerLabel = new JLabel("WINNER:" + winner, SwingConstants.CENTER);
        winnerLabel.setFont(new Font(FONT_SELECTED, Font.BOLD, 30));
        winnerLabel.setForeground(Color.BLUE);

        //tabella
        JTable standingsTable = new JTable(data, columnNames);
        standingsTable.setEnabled(false);
        standingsTable.setBackground(Color.YELLOW);
        standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / 70));

        //se necessario
        JScrollPane tableScroll = new JScrollPane(standingsTable);

        //pannello inferiore
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        //pulsanti
        JButton menuButton = new JButton("MENU");
        JButton restartButton = new JButton("RESTART");

        menuButton.setFont(getExitFont());
        restartButton.setFont(getExitFont());

        //aggiunte al panel inferiore
        lowerPanel.add(menuButton, BorderLayout.WEST);
        lowerPanel.add(restartButton, BorderLayout.EAST);

        //torna al menu
        menuButton.addActionListener(e -> {
            controller.resetFixture();
            controller.resetTable();
            controller.changeView("club");
        });

        //ricomincia
        restartButton.addActionListener(e -> {
            controller.resetFixture();
            controller.changeView("prematch");
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //aggiunte al panel centrale
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(winnerLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(tableScroll);

        //aggiunte al panel principale
        this.add(title, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {   
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                title.setFont(new Font(FONT_SELECTED, Font.BOLD, width / TITLE_FONT_RESIZING));
                winnerLabel.setFont(new Font(FONT_SELECTED, Font.BOLD, width / 30));
                standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, width / 100));
                standingsTable.setRowHeight(height / 20);

                revalidate();
            }
        });

    }


}