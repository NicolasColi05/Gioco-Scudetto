package giocoscudetto.view.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.api.ImageBoardLoader;
import giocoscudetto.controller.api.Starter;

/**
 * This class represents the panel where the board is drawn,
 * it has methods to draw the boxes and the pawns of the players, 
 * it also has an animation loop to animate the movement of the pawns when the dice are rolled.
 */
public class BoardPanel extends DefaultPanelImpl implements GameObserver {

    //CHECKSTYLE: MagicNumber OFF
    private static final int BORDER_SIZE = 5;
    private static final Color CENTER_COLOR = new Color(223, 189, 138);
    private static final double OFFSET_HOME_PAWN = 1.0 / 3.0;
    private static final double OFFSET_GUEST_PAWN = 2.0 / 3.0;
    private static final int BOX_SIDE = 9;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final int SLEEP_TIME = 300;
    private static final int SLEEP_TIME2 = 50;
    private final ImageBoardLoader imageLoaded;
    private final Starter controller;
    private volatile boolean checkBoxDone;
    private int animatedHomePos;
    private int animatedGuestPos;
    private int boardSizeh;
    private int boardSizew;
    private int boxW;
    private int boxH;

    /**
     * Constructor of the BoardPanel class.
     * 
     * @param controller the controller of the game.
     */
    public BoardPanel(final Starter controller) {
        this.controller = controller;
        this.controller.addObserver(this);
        this.imageLoaded = new ImageBoardLoaderImpl(controller);
        setBackground(BACKGROUND_COLOR);
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                repaint();
            }
        });

        new Thread(this::animationLoop).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.setSizes();
        final Graphics2D g2d = (Graphics2D) g;

        drawCenter(g2d);
        drawAllBoxes(g2d);
        drawAllPawns(g2d);
    }

    /**
     * This method is the animation loop that animates the movement of the pawns.
     */
    private void animationLoop() {
        boolean wasAnimating = false;
        while (true) {
            try {
                final int targetHome = controller.getHomePosition();
                final int targetGuest = controller.getGuestPosition();

                final boolean homeMoving = animatedHomePos != targetHome;
                final boolean guestMoving = animatedGuestPos != targetGuest;

                if (homeMoving || guestMoving) {
                    wasAnimating = true;
                    if (homeMoving) {
                        animatedHomePos += (animatedHomePos < targetHome) ? 1 : -1;
                    }
                    if (guestMoving) {
                        animatedGuestPos += (animatedGuestPos < targetGuest) ? 1 : -1;
                    }

                    SwingUtilities.invokeLater(this::repaint);
                    Thread.sleep(SLEEP_TIME);
                } else {
                    if (wasAnimating) {
                        wasAnimating = false;

                        if (!this.checkBoxDone) {
                            if (this.controller.isHelpFlag()) {
                            JOptionPane.showMessageDialog(this, this.controller.getBoxDescript(), "Event of " 
                            + this.controller.getBoxName(), JOptionPane.INFORMATION_MESSAGE); 
                            }
                            this.checkBoxDone = true;
                            this.controller.checkBox();
                        }
                        this.controller.notifyViews();
                    }
                    Thread.sleep(SLEEP_TIME2);
                }

            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * This method resets the checkBoxDone flag to false.
     */
    public void resetCheckBoxDone() {
        this.checkBoxDone = false;
    }

    /**
     * This method draws all the pawns of the players.
     * 
     * @param g2d the graphics context.
     */
    private void drawAllPawns(final Graphics2D g2d) {
        this.drawPawn(g2d, new Color(this.controller.getHomePawnRGB()), this.animatedHomePos, OFFSET_HOME_PAWN);
        this.drawPawn(g2d, new Color(this.controller.getGuestPawnRGB()), this.animatedGuestPos, OFFSET_GUEST_PAWN);

    }

    /**
     * This method draws all the boxes of the board.
     * 
     * @param g2d the graphics context.
     */
    private void drawAllBoxes(final Graphics2D g2d) {

        for (int i = 0; i < 32; i++) {
            this.drawBox(g2d, this.controller.getBoxImage(i), i);
        }
    }

    /**
     * This method draws a single box on the board based on its position and the associated image.
     * 
     * @param g2d the graphics context.
     * @param image the image to draw.
     * @param position the position of the box.
     */
    private void drawBox(final Graphics2D g2d, final String image, final int position) {

        final int x = this.boardSizew;
        final int y = this.boardSizeh;
        final Image img;
        img = this.imageLoaded.getImage(position);

        if (position >= 0 && position <= 8) {
            g2d.drawImage(img, x - (position + 1) * boxW, y - boxH, boxW, boxH, null);
        }
        if (position >= 9 && position <= 16) {
            g2d.drawImage(img, 0, y - (position - 7) * boxH, boxW, boxH, null);
        }
        if (position >= 17 && position <= 24) {
            g2d.drawImage(img, (position - 16) * boxW, 0, boxW, boxH, null);
        }
        if (position >= 25 && position <= 32) {
            g2d.drawImage(img, x - boxW, (position - 24) * boxH, boxW, boxH, null);
        }
    }

    /**
     * This method draws the center of the board, it also draws the score and the names of the players.
     * 
     * @param g2d the graphics context.
     */
    private void drawCenter(final Graphics2D g2d) {
        final int x = boxW;
        final int y = boxH;
        final int w = boardSizew - 2 * x;
        final int h = boardSizeh - 2 * y;
        final int scoreY = boxH * 5;
        final int center = boardSizew / 2;
        final int scoreTextY = y * 6;
        final int scoreTextX = center - g2d.getFontMetrics().stringWidth(this.controller.getScore()) / 2;
        final String homeName = this.controller.getHomeName();
        final int homeNameW = g2d.getFontMetrics().stringWidth(homeName);
        final String guestName = this.controller.getGuestName();

        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(x, y, w, h);
        g2d.drawRect(x, y, w, h);

        g2d.setColor(CENTER_COLOR);
        g2d.fillRect(x + BORDER_SIZE, y + BORDER_SIZE, w - 2 * BORDER_SIZE, h - 2 * BORDER_SIZE);
        g2d.drawRect(x + BORDER_SIZE, y + BORDER_SIZE, w - 2 * BORDER_SIZE, h - 2 * BORDER_SIZE);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Boh", Font.BOLD, x / 2));
        g2d.drawString("GIOCO DELLO SCUDETTO", x + x / 3, y * 2);

        g2d.setColor(Color.black);
        g2d.setFont(new Font("Boh", Font.BOLD, x / 3));
        g2d.drawString("SCORE", center - g2d.getFontMetrics().stringWidth("SCORE") / 2, scoreY);
        g2d.drawString(this.controller.getScore(), scoreTextX, scoreTextY);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, x / 3));
        g2d.drawString(homeName, scoreTextX - homeNameW - x, scoreTextY);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, x / 3));
        g2d.drawString(guestName, center + g2d.getFontMetrics().stringWidth(this.controller.getScore()) + x / 2, scoreTextY);
    }

    /**
     * This method draws a single pawn.
     * 
     * @param g2d the graphics context.
     * @param pawnColor the color of the pawn.
     * @param position the position of the pawn.
     * @param offset the offset.
     */
    private void drawPawn(final Graphics2D g2d, final Color pawnColor, final int position, final double offset) {
        final int x = boardSizew;
        final int y = boardSizeh;
        final int r = this.boxW / 6;

        int pawnX = 0;
        int pawnY = 0;

        if (position >= 0 && position <= 8) {
            pawnX = x - (position * boxW + (int) (boxW * offset));
            pawnY = y - boxH / 2;
        } else if (position >= 9 && position <= 16) {
            pawnX = boxW / 2;
            pawnY = y - ((position - 8) * boxH + (int) (boxH * offset));   
        } else if (position >= 17 && position <= 24) {
            pawnX = (position - 15) * boxW - (int) (boxW * offset);
            pawnY = boxH / 2;
        } else if (position >= 25 && position <= 32) {
            pawnX = x - boxW / 2;
            pawnY = (position - 24) * boxH + (int) (boxH * offset);
        }
        drawCircle(g2d, pawnX, pawnY, r, pawnColor);
    }

    /**
     * This method draws a circle with a shadow effect to represent the pawn.
     * 
     * @param g2d the graphics context.
     * @param x the x-coordinate of the center of the circle.
     * @param y the y-coordinate of the center of the circle.
     * @param r the radius of the circle.
     * @param color the color of the circle.
     */
    private void drawCircle(final Graphics2D g2d, final int x, final int y, final int r, final Color color) {

        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.fillOval(x - r + 3, y - r + 3, r * 2, r * 2);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(x - r, y - r, r * 2, r * 2);

        g2d.setColor(color);
        g2d.fillOval(x - r + 2, y - r + 2, (r - 2) * 2, (r - 2) * 2);
    }

    /**
     * This method sets the sizes of the board and the boxes based on the current size of the panel.
     */
    private void setSizes() {
        this.boardSizeh = this.getHeight();
        this.boardSizew = this.getWidth();
        this.boxH = this.boardSizeh / BOX_SIDE;
        this.boxW = this.boardSizew / BOX_SIDE;
    }

    /**
     * This method returns the current animated position of the home pawn.
     * 
     * @return the animated position of the home pawn
     */
    public int getAnimatedHomePosition() {
        return this.animatedHomePos;
    }

    /**
     * This method returns the current animated position of the guest pawn.
     * 
     * @return the animated position of the guest pawn
     */
    public int getAnimatedGuestPosition() {
        return this.animatedGuestPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
        this.repaint();
    }
}
