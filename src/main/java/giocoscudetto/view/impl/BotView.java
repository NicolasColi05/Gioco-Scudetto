package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;

import giocoscudetto.controller.api.Starter;

public class BotView extends DefaultPanelImpl{
    
    private Starter controller;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;
    private static final int BUTTON_FONT_REDUCTION = 70;
    private static final int BUTTON_BORDER = 5;
    private final Image image;

    public BotView(Starter controller){
        
        this.controller = controller;

        this.setLayout(new BorderLayout());

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/home-background.jpeg"));
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

        JTextArea title = (JTextArea) createComponent(new JTextArea("IN THE NEXT VERSION"), getFont(), new Color(195, 45, 35), null);
        title.setEditable(false);

        centralPanel.add(title);

        JButton backButton = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK, null);

        backButton.addActionListener(e -> { 
            this.controller.changeView("home");
        });

        lowerPanel.add(backButton, BorderLayout.CENTER);

        this.add(centralPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                backButton.setSize(new Dimension(currentWidth/ SWITCHER_BUTTON_FONT_RESIZING, currentHeight/ SWITCHER_BUTTON_FONT_RESIZING));
                
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
