package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.api.Starter;

/**
 * View displayed at the end of the League showing
 * the winner and the final ranking.
 */
public final class EndGameView extends DefaultPanelImpl {
    
    private final Starter controller;
    private final MatchController matchController;
    private final CreateUpdateController createUpdateController;
    private final Image image;
    

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_BORDER = 5;

    public EndGameView(final Starter controller,
        final CreateUpdateController createUpdateController,
         final MatchController matchController) {
        this.controller = controller;
        this.createUpdateController = createUpdateController;
        this.matchController = matchController;
        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(
                new File("src/main/resources/images/backgrounds/end-game-background.jpeg")
            );
        } catch (final IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }

        //vincitore
        final JLabel winnerLabel = new JLabel("WINNER:" + this.matchController.getLeagueWinner(), SwingConstants.RIGHT);
        winnerLabel.setFont(
            new Font(FONT_SELECTED, Font.BOLD, 30)
        );
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //tabella
        final JTable standingsTable = new JTable(this.createUpdateController.getLeagueTableModel());
        standingsTable.setEnabled(false);
        standingsTable.setOpaque(false);
        standingsTable.setFont(
            new Font(FONT_SELECTED, Font.BOLD, minimumWidht / 70)
        );

        //se necessario
        final JScrollPane tableScroll = new JScrollPane(standingsTable);
        tableScroll.setPreferredSize(new Dimension
            (1000, 600)
        );
        tableScroll.setMaximumSize(
            new Dimension(1000, 600)
        );
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);

        final TitledBorder titleS = new TitledBorder("FINAL RANKING");
        titleS.setTitleJustification(TitledBorder.CENTER);
        titleS.setTitleColor(new Color(195,45,35));

        tableScroll.setBorder(titleS);

        //pannello inferiore
        final JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        //pulsanti
        final JButton menuButton = new JButton("MENU");
        final JButton restartButton = new JButton("RESTART");

        menuButton.setFont(getExitFont());
        restartButton.setFont(getExitFont());

        //aggiunte al panel inferiore
        lowerPanel.add(menuButton, BorderLayout.WEST);
        lowerPanel.add(restartButton, BorderLayout.EAST);

        //torna al menu
        menuButton.addActionListener(e -> {
            this.createUpdateController.reset();
            this.controller.changeView("club");
        });

        //ricomincia
        restartButton.addActionListener(e -> {
            this.createUpdateController.restartLeague();
            this.controller.changeView("pre");
        });

        final JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //aggiunte al panel centrale
        centerPanel.add(Box.createVerticalStrut(80));
        centerPanel.add(winnerLabel);
        centerPanel.add(Box.createVerticalStrut(120));

        final JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);

        tablePanel.add(tableScroll);

        centerPanel.add(tablePanel);


        //aggiunte al panel principale
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {   
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                final int width = getWidth();
                final int height = getHeight();

                winnerLabel.setFont(
                    new Font(
                        FONT_SELECTED,
                         Font.BOLD,
                          width / 30
                        )
                    );
                standingsTable.setFont(
                    new Font(
                        FONT_SELECTED,
                         Font.BOLD,
                          width / 100
                        )
                    );
                standingsTable.setRowHeight(height / 20);
                standingsTable.setRowMargin(3);

                revalidate();
            }
        });
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
    }
}