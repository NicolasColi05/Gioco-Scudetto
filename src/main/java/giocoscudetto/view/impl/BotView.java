package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import giocoscudetto.controller.api.Starter;

/**
 * View for the match with bot. It shows a background image and a back button to return to the home view.
 */
public class BotView extends DefaultPanelImpl {

    private static final int BUTTON_BORDER = 5;
    private static final Color BUTTONS_TEXT_COLOR = new Color(240, 220, 180); 

    private final Starter controller;

    private transient final Image image;

    /**
     * Constructor for the BotView.
     * @param controller the controller responsible for changing views
     */
    public BotView(final Starter controller) {

        this.controller = controller;

        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/bot-background.jpeg"));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load image", e);
        }

        //pannello inferiore
        final JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));
        lowerPanel.setOpaque(false);

        final JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), BUTTONS_TEXT_COLOR, null);

        backButton.addActionListener(e -> { 
            this.controller.changeView("home");
        });

        lowerPanel.add(backButton, BorderLayout.WEST);

        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setSize(new Dimension(currentWidth / SWITCHER_BUTTON_FONT_RESIZING, 
                                                    currentHeight / SWITCHER_BUTTON_FONT_RESIZING));
                revalidate();
            }
        });
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (g instanceof Graphics2D) {
            final Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
