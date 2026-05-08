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

    public DicePanel(final Starter controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);
        messageLabel = new JLabel();
        messageLabel.setBackground(BACKGROUND_COLOR);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        JButton rollDiceButton = new JButton("Roll Dice");

        this.add(rollDiceButton, BorderLayout.SOUTH);        
        this.add(messageLabel,BorderLayout.CENTER);

        rollDiceButton.addActionListener(e -> {
                this.controller.move();
            });
    }

    //da cambiare nel caso facciamo che il controller possa chiamare il repaint() sulle view
    private final class Agent implements Runnable {
        /**
         * 
         */
        private volatile boolean stop;

        @Override
        public void run() {
            while (!this.stop) {
                try {
                    SwingUtilities.invokeAndWait(() -> DicePanel.this.messageLabel.setText(DicePanel.this.controller.getDescription()));
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException ex) {
                        ex.printStackTrace(); //NOPMD
                }
               
            }
        }
    }
}
