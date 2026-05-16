package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.GameObserver;

public class DicePanel extends DefaultPanelImpl implements GameObserver{
    
    private static final Color BACKGROUND_COLOR = new Color(223,189,138);
    private final Starter controller;
    private final JLabel messageLabel;
    private final BoardPanel board;
    private final JButton rollDiceButton;

    public DicePanel(final Starter controller,final BoardPanel board) {
        this.rollDiceButton = new JButton("Roll Dice");
        this.controller = controller;
        this.controller.addObserver(this);
        this.board = board;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);
        messageLabel = new JLabel();
        messageLabel.setBackground(BACKGROUND_COLOR);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        this.add(rollDiceButton, BorderLayout.SOUTH);        
        this.add(messageLabel,BorderLayout.CENTER);

        rollDiceButton.addActionListener(e -> {
            messageLabel.setText(""+this.controller.move());
            this.board.repaint();
        });
    }

    public void setDice(boolean active) {
       this.rollDiceButton.setEnabled(active);
    }

    @Override
    public void updateState() {
            boolean isAnimating = controller.getHomePosition() != board.getAnimatedHomePosition()
                                || controller.getGuestPosition() != board.getAnimatedGuestPosition();
            if (isAnimating || this.controller.isPenalty()) {
                this.setDice(false);
            } else {
                this.setDice(true);
            }
    }
}
