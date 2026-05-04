package giocoscudetto.view.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import giocoscudetto.controller.api.Starter;


public class BoardPanel extends DefaultPanelImpl  {

    private static final int BOX_SIDE = 9;
    private static final Color BACKGROUND_COLOR = new Color(0xC8E6C9);
    
    private final Starter controller;
    private int board_size;
    private int box_w;
    private int box_h;

    public BoardPanel(Starter controller) {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                repaint();
            }
        });
        this.controller = controller;
        setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.setSizes();
        Graphics2D g2d = (Graphics2D) g;

        drawCenter(g2d);
        drawAllBoxes(g2d);
        
    }

    private void drawAllBoxes(Graphics2D g2d) {

        for (int i = 0; i < 32; i++) {
            this.drawBox(g2d, this.controller.getBoxImage(i), i);
        }
        
    }

    private void drawBox(Graphics2D g2d, Image image, int position) {
        int x = board_size;
        int y = board_size;
        if (position >= 0 && position <= 8) {
            g2d.drawImage(image, x - (position + 1)*box_w, y - box_h, box_w, box_h, null);
        }
        if (position >= 9 && position <= 16) {
            g2d.drawImage(image, 0, y - (position - 7)*box_h, box_w, box_h, null);
        }
        if (position >= 17 && position <= 24) {
            g2d.drawImage(image, (position - 16)*box_w, 0, box_w, box_h, null);
        }
        if (position >= 25 && position <= 31) {
            g2d.drawImage(image, x - box_w, (position - 24)*box_h, box_w, box_h, null);
        }
    }

    private void drawCenter(Graphics2D g2d) {
        
        int x = box_h;
        int y = box_h;
        int w = board_size - 2*x;
        int h = board_size - 2*y;

        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(x, y, w, h);
        g2d.drawRect(x, y, w, h);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Boh", Font.BOLD, 40));
        g2d.drawString("GIOCO DELLO SCUDETTO", 180, 320);

    }
    
    private void setSizes() {
        this.board_size = this.getHeight();
        this.box_h = this.board_size/BOX_SIDE;
        this.box_w = this.board_size/BOX_SIDE;
    }
}
