package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.api.GameObserver;

public class DicePanel extends DefaultPanelImpl implements GameObserver{
    
    private static final Color BACKGROUND_COLOR = new Color(223,189,138);
    private final MatchController matchController;
    private final JLabel messageLabel;
    private final BoardPanel board;
    private final JButton rollDiceButton;

    public DicePanel(final BoardPanel board, final MatchController matchController) {
        this.matchController = matchController;
        this.rollDiceButton = new JButton("Roll Dice");
        this.matchController.addObserver(this);
        this.board = board;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);
        messageLabel = new JLabel();
        messageLabel.setBackground(BACKGROUND_COLOR);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        this.add(rollDiceButton, BorderLayout.SOUTH);        
        this.add(messageLabel,BorderLayout.CENTER);

        rollDiceButton.addActionListener(e -> {
            this.board.resetCheckBoxDone();
            this.rollDiceButton.setEnabled(false);
            this.animateAndResolve();
        });
    }

    private void animateAndResolve() {
        final Random rnd = new Random();
        final long startTime = System.currentTimeMillis();
        final Timer animTimer = new Timer(80, null);

        animTimer.addActionListener(e -> {
            messageLabel.setText(String.valueOf(rnd.nextInt(13)));
            if (System.currentTimeMillis() - startTime > 700) {
                animTimer.stop();
                showResult();
            }
        });
        animTimer.start();
    }

    private void showResult() {
        messageLabel.setText(""+this.matchController.move());
        this.board.repaint();
    }

    public void setDice(boolean active) {
       this.rollDiceButton.setEnabled(active);
    }

    @Override
    public void updateState() {
            boolean isAnimating = matchController.getHomePosition() != board.getAnimatedHomePosition()
                                || matchController.getGuestPosition() != board.getAnimatedGuestPosition();
            if (isAnimating ||  this.matchController.getGameMode() !="NONE")  {
                this.setDice(false);
            } else {
                this.setDice(true);
            }
    }
}
