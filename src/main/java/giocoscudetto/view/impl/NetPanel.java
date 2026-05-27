package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.Starter;

/**
 * This class represents the panel where the penalty is resolved.
 */
public class NetPanel extends DefaultPanelImpl {

    // CHECKSTYLE: MagicNumber OFF
    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(0xC8E6C9);
    private static final int ROWS = 2;
    private static final int COLS = 3;
    private static final int H_GAP = 6;
    private static final int BORDER_SIZE = 8;
    private static final int BOTTON1_POS = 1;
    private static final int BOTTON2_POS = 2;
    private static final int BOTTON3_POS = 3;
    private static final int BOTTON4_POS = 4;
    private static final int BOTTON5_POS = 5;
    private static final int BOTTON6_POS = 6;

    private final Starter controller;
    private final BufferedImage image;
    private final JButton button1 = new JButton("1");
    private final JButton button2 = new JButton("2");
    private final JButton button3 = new JButton("3");
    private final JButton button4 = new JButton("4");
    private final JButton button5 = new JButton("5");
    private final JButton button6 = new JButton("6");
    private final JButton kickButton = new JButton("KICK THE PENALTY");
    private final JLabel label;
    private int count;

    /**
     * Constructor of the NetPanel class.
     * 
     * @param controller the game controller.
     * @throws IOException if an error occurs while loading the image.
     */
    @SuppressFBWarnings
    public NetPanel(final Starter controller) throws IOException {
        this.controller = controller;
        this.setLayout(new BorderLayout()); //NOPMD
        this.label = new JLabel();
        this.label.setBackground(new Color(BACKGROUND_COLOR.getRGB()));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.NORTH); //NOPMD
        kickButton.setEnabled(false);
        final JPanel net = new JPanel();
        net.setOpaque(false);
        net.setLayout(new GridLayout(ROWS, COLS, H_GAP, H_GAP));
        net.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        this.add(net, BorderLayout.CENTER); //NOPMD
        this.add(kickButton, BorderLayout.SOUTH); //NOPMD
        setButtonsEnabled(false); //NOPMD

            kickButton.addActionListener(e -> {
                final boolean goal = this.controller.kickPenalty();
                setButtonsEnabled(false);
                this.count = 0;
                this.controller.gameModeFinished();
                if (goal) {
                    label.setText("GOOOOOOOOOOAL!!!");
                } else {
                    label.setText("WHAT A SAVE BY THE KEEPER!!!");
                }
            });

            button1.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON1_POS);
                    count++;
                    checkButtons(BOTTON1_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON1_POS);
                    this.button1.setEnabled(false);
                    count++;
                    kickButton.setEnabled(true);
                }
            });
            button2.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON2_POS);
                    count++;
                    checkButtons(BOTTON2_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON2_POS);
                    count++;
                    this.button2.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button3.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON3_POS);
                    count++;
                    checkButtons(BOTTON3_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON3_POS);
                    count++;
                    this.button3.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button4.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON4_POS);
                    count++;
                    checkButtons(BOTTON4_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON4_POS);
                    count++;
                    this.button4.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button5.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON5_POS);
                    count++;
                    checkButtons(BOTTON5_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON5_POS);
                    count++;
                    this.button5.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button6.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(BOTTON6_POS);
                    count++;
                    checkButtons(BOTTON6_POS);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(BOTTON6_POS);
                    count++;
                    this.button6.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });

        net.add(button1);
        net.add(button2);
        net.add(button3);
        net.add(button4);
        net.add(button5);
        net.add(button6);

        try {
        this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/net.png"));
        } catch (final IOException e) {
            e.printStackTrace(); //NOPMD
            throw new IOException("Failed to load image", e);
        }

    }

    /**
     * This method enables or disables the buttons of the net panel.
     * 
     * @param b true to enable the buttons, false to disable them.
     */
    public void setButtonsEnabled(final boolean b) {
        this.button1.setEnabled(b);
        this.button2.setEnabled(b);
        this.button3.setEnabled(b);
        this.button4.setEnabled(b);
        this.button5.setEnabled(b);
        this.button6.setEnabled(b);
        if (b) {
            label.setText("Choose the position of the keeper");
        }
        this.kickButton.setEnabled(b);
    }

    private void checkButtons(final int position) {
        switch (position) {
            case 1:
                button1.setEnabled(false);
                button6.setEnabled(false);
                button3.setEnabled(false);
                break;
            case 2:
                button2.setEnabled(false);
                break;
            case 3:
                button3.setEnabled(false);
                button1.setEnabled(false);
                button4.setEnabled(false);
                break;
            case 4:
                button4.setEnabled(false);
                button6.setEnabled(false);
                button3.setEnabled(false);
                break;
            case 5:
                button5.setEnabled(false);
                break;
            case 6:
                button6.setEnabled(false);
                button1.setEnabled(false);
                button4.setEnabled(false);
                break;
            default:
                break;
        }
    }

    /**
     *  {@inheritDoc}.
     */
    @Override
    @SuppressFBWarnings 
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, label.getSize().height, getWidth(), getHeight(), null);
    }
}
