package giocoscudetto.view.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import giocoscudetto.controller.api.Starter;


public class BoardPanel extends DefaultPanelImpl  {

    private static final double OFFSET_HOME_PAWN = 1.0/3.0;
    private static final double OFFSET_GUEST_PAWN = 2.0/3.0;
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

        
        this.drawPawn(g2d, this.getPawnColor("Yellow"), 0, OFFSET_HOME_PAWN);//this.getPawnColor(this.controller.getHomeTeamColor())
        this.drawPawn(g2d, this.getPawnColor("Red"), 0, OFFSET_GUEST_PAWN);//this.controller.getGuestTeamColor())

    }

    private Color getPawnColor(final String color) {
        return switch (color.toLowerCase()) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            default -> throw new IllegalArgumentException("Invalid color: " + color);
        };
        
    }

    private void drawAllBoxes(Graphics2D g2d) {

        for (int i = 0; i < 32; i++) {
            this.drawBox(g2d, this.controller.getBoxImage(i), i);
        }
        
    }

    private void drawBox(Graphics2D g2d, String image, int position) {

        final int x = board_size_w;
        final int y = board_size_h;
        final BufferedImage img;

        try {
            img = ImageIO.read(new File(image));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image", e);
        }

        if (position >= 0 && position <= 8) {
            g2d.drawImage(img, x - (position + 1)*box_w, y - box_h, box_w, box_h, null);
        }
        if (position >= 9 && position <= 16) {
            g2d.drawImage(img, 0, y - (position - 7)*box_h, box_w, box_h, null);
        }
        if (position >= 17 && position <= 24) {
            g2d.drawImage(img, (position - 16)*box_w, 0, box_w, box_h, null);
        }
        if (position >= 25 && position <= 31) {
            g2d.drawImage(img, x - box_w, (position - 24)*box_h, box_w, box_h, null);
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

    private void drawPawn(final Graphics2D g2d, final Color PawnColor, final int position,final double offset) {
        final int x = board_size_w;
        final int y = board_size_h;
        final int r = this.box_w / 6;

        int pawnX = 0;
        int pawnY = 0;

        if (position >= 0 && position <= 8) {
            pawnX = x - (position * box_w + (int)(box_w * offset));
            pawnY = y - box_h / 2;
        }
        else if (position >= 9 && position <= 16) {
            pawnX = box_w / 2;
            pawnY = y - ((position - 8) * box_h + (int)(box_h * offset));           
        }
        else if (position >= 17 && position <= 24) {
            pawnX = (position - 18) * box_w - (int)(box_w * offset);
            pawnY = box_h / 2;
        }
        else if (position >= 25 && position <= 32) {
            pawnX = x - box_w / 2;
            pawnY = (position - 24) * box_h + (int)(box_h * offset);
        }

        drawCircle(g2d, pawnX, pawnY, r, PawnColor);
    }

    private void drawCircle(final Graphics2D g2d,final int x,final int y,final int r,final Color color) {

        g2d.setColor(new Color(0,0,0,80));
        g2d.fillOval(x - r + 3, y - r + 3, r * 2, r * 2);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(x - r, y - r, r * 2, r * 2);

        g2d.setColor(color);
        g2d.fillOval(x - r + 2, y - r + 2, (r - 2) * 2, (r - 2) * 2);
    }


    private void setSizes() {
        this.board_size_h = this.getHeight();
        this.board_size_w = this.getWidth();
        this.box_h = this.board_size_h/BOX_SIDE ;
        this.box_w = this.board_size_w/BOX_SIDE ;
    }
}
