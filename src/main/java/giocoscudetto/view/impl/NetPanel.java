package giocoscudetto.view.impl;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import giocoscudetto.controller.api.Starter;

public class NetPanel extends DefaultPanelImpl {
    
    private static final int ROWS = 2;
    private static final int COLS = 3;
    private final Starter controller;
    private final BufferedImage image;

    public NetPanel(final Starter controller) {
        this.controller = controller;
        this.setLayout(new GridLayout(ROWS,COLS,6,6));
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.add(new JButton("1"));
        this.add(new JButton("2"));
        this.add(new JButton("3"));
        this.add(new JButton("4"));
        this.add(new JButton("5"));
        this.add(new JButton("6"));

        try {
        this.image = ImageIO.read(new File("caselle_precise/net.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load image", e);
        }

    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
    }
}
