package giocoscudetto.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import giocoscudetto.controller.api.Starter;


public class BoardPanel extends DefaultPanelImpl  {

    private static final int BOARD_SIZE  = 900;
    private static final int BOX_SIDE = 9;
    private static final int BOX_WIDTH = (BOARD_SIZE) / BOX_SIDE;
    private static final int BOX_HEIGHT = (BOARD_SIZE) / BOX_SIDE;;
    private static final Color BACKGROUND_COLOR = new Color(0xC8E6C9);

    private final Starter controller;

    public BoardPanel(Starter controller) {

        this.controller = controller;
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawCenter(g2d);
        drawAllBoxes(g2d);
        
    }

    private void drawAllBoxes(Graphics2D g2d) {

        for (int i = 0; i < 32; i++) {
            this.drawBox(g2d, this.controller.getBoxImage(i),getRotation(i), i);
        }
        
    }

    public void drawBox(Graphics2D g2d, Image image, double rotation, int position) {
        int x = BOARD_SIZE;
        int y = BOARD_SIZE;
        if (position >= 0 && position <= 8) {
            g2d.drawImage(image, x - (position + 1)*BOX_WIDTH, y - BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT, null);
        }
        if (position >= 9 && position <= 16) {
            g2d.drawImage(image, 0, y - (position - 7)*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT, null);
        }
        if (position >= 17 && position <= 24) {
            g2d.drawImage(image, (position - 16)*BOX_WIDTH, 0, BOX_WIDTH, BOX_HEIGHT, null);
        }
        if (position >= 25 && position <= 31) {
            g2d.drawImage(image, x - BOX_WIDTH, (position - 24)*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT, null);
        }
    }

    private double getRotation(int i) {
        return 0.0;
    }

    private void drawCenter(Graphics2D g2d) {
        
        int x = BOX_HEIGHT;
        int y = BOX_HEIGHT;
        int w = BOARD_SIZE - 2*x;
        int h = BOARD_SIZE - 2*y;

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(x, y, w, h);
        g2d.drawRect(x, y, w, h);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Boh", Font.BOLD, 20));
        g2d.drawString("GIOCO DELLO SCUDETTO", 180, 320);

    }
}
