package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import giocoscudetto.controller.api.MatchController;

public class EventPanel extends DefaultPanelImpl {

    private static final Color BACKGROUND_COLOR = new Color(223,189,138);

    public enum EventType {
        FREE_KICK, CORNER, RESULT
    }

    private final MatchController matchController;
    private final JLabel dice1Label = new JLabel("?", SwingConstants.CENTER);
    private final JLabel dice2Label = new JLabel("?", SwingConstants.CENTER);
    private final JLabel outcomeLabel = new JLabel("", SwingConstants.CENTER);
    private final JButton spinButton = new JButton("kick");
    private final JButton continueButton = new JButton("continue");
    private final JLabel titleLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel plusLabel = new JLabel("+", SwingConstants.CENTER);

    private EventType currentType = EventType.CORNER;

    public EventPanel(final MatchController matchController) {
        this.matchController = matchController;
        buildUI();
        this.setBackground(BACKGROUND_COLOR);
    }

    private void buildUI() {
        setLayout(new BorderLayout());

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setText(getTitleType(currentType));
        add(titleLabel, BorderLayout.NORTH);

        final JPanel dicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        dice1Label.setFont(new Font("Arial", Font.BOLD, 20));
        dice2Label.setFont(new Font("Arial", Font.BOLD, 20));
        plusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dicePanel.add(dice1Label);
        dicePanel.add(plusLabel);
        dicePanel.add(dice2Label);
        add(dicePanel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        outcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        continueButton.setVisible(false);
        bottomPanel.add(outcomeLabel, BorderLayout.NORTH);
        bottomPanel.add(spinButton, BorderLayout.CENTER);
        bottomPanel.add(continueButton, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        spinButton.addActionListener(e -> animateAndResolve());

        continueButton.addActionListener(e -> {
            this.matchController.gameModeFinished();
        });
    }

    private String getTitleType(final EventType type) {
        return switch (type) {
            case FREE_KICK -> "FREE KICK!";
            case CORNER -> "CORNER!";
            case RESULT -> "RISULTATO!";
        };
    }

    private void animateAndResolve() {
        spinButton.setEnabled(false);
        dice1Label.setText("?");
        dice2Label.setText("?");
        outcomeLabel.setText("");
        continueButton.setVisible(false);

        final Random rnd = new Random();
        final long startTime = System.currentTimeMillis();
        final Timer animTimer = new Timer(80, null);

        animTimer.addActionListener(e -> {
            dice1Label.setText(String.valueOf(rnd.nextInt(7)));
            dice2Label.setText(String.valueOf(rnd.nextInt(7)));

            if (System.currentTimeMillis() - startTime > 1000) {
                animTimer.stop();
                showResult();
            }
        });
        animTimer.start();
    }

    private void showResult() {
        dice1Label.setText(String.valueOf(this.matchController.diceEvent()));
        dice2Label.setText(String.valueOf(this.matchController.diceEvent()));

        if (EventType.FREE_KICK == currentType) {
            if (Integer.valueOf(dice1Label.getText()) + Integer.valueOf(dice2Label.getText()) == 7) {
                outcomeLabel.setText("GOAL");
            } else {
                outcomeLabel.setText("NO GOAL");
            }
        } else if (EventType.CORNER == currentType) {
            if (Integer.valueOf(dice1Label.getText()) == 1 || Integer.valueOf(dice2Label.getText()) == 1) {
                outcomeLabel.setText("GOAL");
            } else {
                outcomeLabel.setText("NO GOAL");
            }
        }
        this.continueButton.setVisible(true);
    }

    public void configure(final EventType type) {
        this.currentType = type;
        titleLabel.setText(getTitleType(type));
        dice1Label.setText("?");
        dice2Label.setText("?");
        outcomeLabel.setText("");
        continueButton.setVisible(false);
        spinButton.setEnabled(true);
        if (type == EventType.RESULT) {
            plusLabel.setText("-");
            spinButton.setText("new Result");
        } else if (this.currentType == EventType.CORNER) {
            plusLabel.setText("");
            spinButton.setText("kick the "+ getTitleType(type));
        } else if (this.currentType == EventType.FREE_KICK) {
            spinButton.setText("kick the "+ getTitleType(type));
        }
    }
}