package giocoscudetto.view.impl;

import java.awt.BorderLayout;
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

import giocoscudetto.controller.api.Starter;

public class NetPanel extends DefaultPanelImpl {
    
    private static final int ROWS = 2;
    private static final int COLS = 3;
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
    private int count = 0;

    public NetPanel(final Starter controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.label = new JLabel();
        this.label.setBackground(new java.awt.Color(0xC8E6C9));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.NORTH);
        kickButton.setEnabled(false);
        JPanel net = new JPanel();
        net.setOpaque(false);
        net.setLayout(new GridLayout(ROWS,COLS,6,6));
        net.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.add(net,BorderLayout.CENTER);
        this.add(kickButton, BorderLayout.SOUTH);
        setButtonsEnabled(false);

            kickButton.addActionListener(e -> {
                boolean goal = this.controller.kickPenalty();
                setButtonsEnabled(false);
                this.count = 0;
                this.controller.gameModeFinished();
                if (goal) {
                    label.setText("GOOOOOOOOOOAL!!!");
                } else {
                    label.setText(" WHAT A SAVE BY THE KEEPER!!!");
                }
            });

            button1.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(1);
                    count++;
                    checkButtons(1);
                } else if (count == 1)  {
                    this.controller.setKeeperPosition(1);
                    this.button1.setEnabled(false);
                    count++;
                    kickButton.setEnabled(true);
                }
            });
            button2.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(2);
                    count++;
                    checkButtons(2);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(2);
                    count++;
                    this.button2.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button3.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(3);
                    count++;
                    checkButtons(3);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(3);
                    count++;
                    this.button3.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button4.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(4);
                    count++;
                    checkButtons(4);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(4);
                    count++;
                    this.button4.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button5.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(5);
                    count++;
                    checkButtons(5);
                } else if(count == 1) {
                    this.controller.setKeeperPosition(5);
                    count++;
                    this.button5.setEnabled(false);
                    kickButton.setEnabled(true);
                }
            });
            button6.addActionListener(e -> {
                if (count == 0) {
                    this.controller.setKeeperPosition(6);
                    count++;
                    checkButtons(6);
                } else if (count == 1) {
                    this.controller.setKeeperPosition(6);
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
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load image", e);
        }

    }

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
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0, label.getSize().height, getWidth(), getHeight(), null);
    }
}
