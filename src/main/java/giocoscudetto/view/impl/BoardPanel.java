package giocoscudetto.view.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import giocoscudetto.controller.api.Starter;


public class BoardPanel extends DefaultPanelImpl  {

    private static final int BOX_SIDE = 9;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    
    private final Starter controller;
    private int board_size_h;
    private int board_size_w;
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
        drawAllPawns(g2d);
    }

    private void drawAllPawns(Graphics2D g2d) {
        drawHomePawn(g2d);
        drawGuestPawn(g2d);
    }

    private void drawAllBoxes(Graphics2D g2d) {

        for (int i = 0; i < 32; i++) {
            this.drawBox(g2d, this.controller.getBoxImage(i), i);
        }
        
    }

    private void drawBox(Graphics2D g2d, Image image, int position) {

        final int x = board_size_w;
        final int y = board_size_h;

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
        
        final int x = box_w;
        final int y = box_h;
        final int w = board_size_w - 2*x;
        final int h = board_size_h - 2*y;

        g2d.setColor(new Color(0xC8E6C9));
        g2d.fillRect(x, y, w, h);
        g2d.drawRect(x, y, w, h);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Boh", Font.BOLD, x/2));
        g2d.drawString("GIOCO DELLO SCUDETTO", x + x/3, y*2);


        final String result = this.controller.getScore();
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Boh", Font.BOLD, x/2));
        g2d.drawString("SCORE", 4*x, y*5);
        g2d.drawString(result, 4*x, y*6);

    }

    private void drawHomePawn(final Graphics2D g2d) {
        final int x = board_size_w;
        final int y = board_size_h;
        // final Color PawnColor = this.controller.getHomeTeamColor();
        // final int position = this.controller.getHomePosition();
        final Color PawnColor  = Color.YELLOW;
        final int position = 10;
        final int r = 15;

        if (position >= 0 && position <= 8) {
            // 1. Ombra (opzionale, aiuta molto la visibilità)
            g2d.setColor(new Color(0, 0, 0, 80));
            g2d.fillOval(x - (position*(this.box_w) + this.box_w/3) -  r + 3, y - (box_h/2) - r + 3, r * 2, r * 2);
            
            // 2. Contorno nero spesso — la chiave della visibilità
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3f));
            g2d.fillOval(x - (position*(this.box_w) + this.box_w/3) - r, y - (box_h/2) - r, r * 2, r * 2);
            
            // 3. Corpo colorato della pedina (leggermente più piccolo)
            g2d.setColor(PawnColor);
            g2d.fillOval(x - (position*(this.box_w) + this.box_w/3) - r + 2, y - (box_h/2) - r + 2, (r - 2) * 2, (r - 2) * 2);
        }
        if (position >= 9 && position <= 16) {
            g2d.setColor(new Color(0, 0, 0, 80));
            g2d.fillOval(0  + this.box_w/2 -  r + 3, y - ((position- 8)*(this.box_h) + this.box_h/3)- r + 3, r * 2, r * 2);
            
            // 2. Contorno nero spesso — la chiave della visibilità
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3f));
            g2d.fillOval(0  + this.box_w/2 - r, y - ((position - 8)*(this.box_h) + this.box_h/3) - r, r * 2, r * 2);
            
            // 3. Corpo colorato della pedina (leggermente più piccolo)
            g2d.setColor(PawnColor);
            g2d.fillOval(0 + this.box_w/2 - r + 2, y - ((position - 8)*(this.box_h) + this.box_h/3) - r + 2, (r - 2) * 2, (r - 2) * 2);
        }
        if (position >= 17 && position <= 24) {
            
        }
        if (position >= 25 && position <= 31) {
            
        }
    }

    private void drawGuestPawn(Graphics2D g2d) {
        final int x = board_size_w;
        final int y = board_size_h;
        // final Color PawnColor = this.controller.getGuestTeamColor();
        // final int position = this.controller.getGuestPosition();
        final Color PawnColor  = Color.BLACK;
        final int position = 1;
        final int r = 15;

        if (position >= 0 && position <= 8) {
            // 1. Ombra (opzionale, aiuta molto la visibilità)
            g2d.setColor(new Color(0, 0, 0, 80));
            g2d.fillOval(x - r + 3, y - (this.box_h) - r + 3, r * 2, r * 2);
            
            // 2. Contorno nero spesso — la chiave della visibilità
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3f));
            g2d.fillOval(x - r, y - (this.box_h) - r, r * 2, r * 2);
            
            // 3. Corpo colorato della pedina (leggermente più piccolo)
            g2d.setColor(PawnColor);
            g2d.fillOval(x - (position*this.box_w) - r + 2, y - (this.box_h) - r + 2, (r - 2) * 2, (r - 2) * 2);
        }
        if (position >= 9 && position <= 16) {
            
        }
        if (position >= 17 && position <= 24) {
            
        }
        if (position >= 25 && position <= 31) {
            
        }
    }

    private void setSizes() {

        this.board_size_h = this.getHeight();
        this.board_size_w = this.getWidth();
        this.box_h = this.board_size_h/BOX_SIDE;
        this.box_w = this.board_size_w/BOX_SIDE;
    }
}
