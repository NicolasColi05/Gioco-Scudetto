package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import giocoscudetto.controller.api.Starter;

public class DicePanel extends DefaultPanelImpl {
    
    private static final Color BACKGROUND_COLOR = new Color(223,189,138);
    private final Starter controller;
    private final JLabel messageLabel;
    private final BoardPanel board;
    private final JButton rollDiceButton;

    public DicePanel(final Starter controller,final BoardPanel board) {

        this.rollDiceButton = new JButton("Roll Dice");
        this.controller = controller;
        this.board = board;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);
        messageLabel = new JLabel();
        messageLabel.setAutoscrolls(true);
        messageLabel.setBackground(BACKGROUND_COLOR);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        this.add(rollDiceButton, BorderLayout.SOUTH);        
        this.add(messageLabel,BorderLayout.CENTER);

        rollDiceButton.addActionListener(e -> {
                this.controller.move();
                this.board.repaint();
            });

        final Agent2 agent = new Agent2();
        new Thread(agent).start();
    }

    //da cambiare nel caso facciamo che il controller possa chiamare il repaint() sulle view
    private final class Agent2 implements Runnable {
        /**
         * 
         */
        private volatile boolean stop;

        @Override
        public void run() {
            while (!this.stop) {
                try {
                    SwingUtilities.invokeAndWait(() -> {
                        boolean isAnimating = controller.getHomePosition() != board.getAnimatedHomePosition()
                       || controller.getGuestPosition() != board.getAnimatedGuestPosition();
                        rollDiceButton.setEnabled(!isAnimating && !controller.isPenalty());
                        messageLabel.setText(controller.getDescription());
                    });
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException ex) {
                        ex.printStackTrace(); //NOPMD
                }
               
            }
        }
    }

    public void setDice(boolean active) {
       this.rollDiceButton.setEnabled(active);
    }
}
