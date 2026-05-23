package giocoscudetto.view.impl;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.TitledBorder;

import giocoscudetto.controller.api.Starter;

public class EndGameView extends DefaultPanelImpl {
    
    private Starter controller;
    private final Image image;
    
    //intestazione tabella
    

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_BORDER = 5;

    public EndGameView(Starter controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(
                new File("src/main/resources/images/backgrounds/end-game-background.jpeg")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image", e);
        }

        //vincitore
        JLabel winnerLabel = new JLabel("WINNER:" + controller.getWinner(), SwingConstants.RIGHT);
        winnerLabel.setFont(new Font(FONT_SELECTED, Font.BOLD, 30));
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //tabella
        JTable standingsTable = new JTable(controller.getLeagueTableModel());
        standingsTable.setEnabled(false);
        standingsTable.setOpaque(false);
        standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, minimumWidht / 70));

        //se necessario
        JScrollPane tableScroll = new JScrollPane(standingsTable);
        tableScroll.setPreferredSize(new Dimension(1000, 600));
        tableScroll.setMaximumSize(new Dimension(1000, 600));
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);

        TitledBorder titleS = new TitledBorder("FINAL RANKING");
        titleS.setTitleJustification(TitledBorder.CENTER);
        titleS.setTitleColor(new Color(195,45,35));

        tableScroll.setBorder(titleS);

        //pannello inferiore
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setOpaque(false);
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
            controller.restartLeague();
            controller.changeView("pre");
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //aggiunte al panel centrale
        centerPanel.add(Box.createVerticalStrut(80));
        centerPanel.add(winnerLabel);
        centerPanel.add(Box.createVerticalStrut(120));

        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);

        tablePanel.add(tableScroll);

        centerPanel.add(tablePanel);


        //aggiunte al panel principale
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {   
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                winnerLabel.setFont(new Font(FONT_SELECTED, Font.BOLD, width / 30));
                standingsTable.setFont(new Font(FONT_SELECTED, Font.BOLD, width / 100));
                standingsTable.setRowHeight(height / 20);

                revalidate();
            }
        });

    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
    }


}