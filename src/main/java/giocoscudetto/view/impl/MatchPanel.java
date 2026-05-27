package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.api.ViewManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class represents the panel where the match is played, 
 * it contains the board, 
 * the dice panel, the net panel and the event panel.
 */
public class MatchPanel extends DefaultPanelImpl implements GameObserver {

    // CHECKSTYLE: MagicNumber OFF
    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(223, 189, 138);
    private static final int DIM_X = 300;
    private static final int DIM_Y = 200;
    private final Starter controller;
    private final JLabel turnLabel;
    private final NetPanel netPanel;
    private final DicePanel bottomDice;
    private final JButton continueButton;
    private final EventPanel eventPanel;
    private final JCheckBox helpBox;
    private final ViewManager viewManager;

    /**
     * Constructor of the MatchPanel class.
     * 
     * @param controller the game controller.
     * @param viewManager the view manager.
     * @throws IOException if an error occurs while loading the image int the net panel.
     */
    @SuppressFBWarnings
    public MatchPanel(final Starter controller, final ViewManager viewManager) throws IOException {

        final BoardPanel boardJPanel = new BoardPanel(controller);
        boardJPanel.start();
        this.bottomDice = new DicePanel(controller, boardJPanel);
        this.netPanel = new NetPanel(controller);
        this.controller = controller;
        this.viewManager = viewManager;
        this.setLayout(new BorderLayout()); //NOPMD
        this.setBackground(BACKGROUND_COLOR); //NOPMD
        this.controller.addObserver(this);
        this.add(boardJPanel, BorderLayout.CENTER); //NOPMD
        this.helpBox = new JCheckBox("Help for box");
        this.helpBox.setSelected(false);

        this.helpBox.addActionListener(e -> { 
            this.controller.setHelpFlag(this.helpBox.isSelected());
        });

        this.eventPanel = new EventPanel(controller);
        eventPanel.setMaximumSize(new Dimension(DIM_X, DIM_Y));

        final JPanel helpPanel = new JPanel();
        helpPanel.setBackground(BACKGROUND_COLOR);
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
        helpPanel.add(helpBox);

        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(280, 0));

        final JPanel turnPanel = new JPanel();
        turnLabel = new JLabel("Turn of :" + controller.getCurrentPlayer());
        turnPanel.setBackground(BACKGROUND_COLOR);
        turnLabel.setFont(new Font("Turn", Font.BOLD, 20));
        turnPanel.add(turnLabel);
        turnPanel.setAlignmentX(CENTER_ALIGNMENT);
        turnPanel.setMaximumSize(new Dimension(280, 120));

        final JPanel netWrapper = new JPanel(new BorderLayout());
        netWrapper.setMaximumSize(new Dimension(DIM_X, DIM_Y));
        this.continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);
        continueButton.setEnabled(false);
        continueButton.setVisible(false);


        netWrapper.setOpaque(false);
        netWrapper.setAlignmentX(CENTER_ALIGNMENT);
        netWrapper.add(netPanel, BorderLayout.CENTER);

        bottomDice.setAlignmentX(CENTER_ALIGNMENT);
        bottomDice.setMaximumSize(new Dimension(280, 120));

        rightPanel.add(helpPanel);
        rightPanel.add(turnPanel);
        rightPanel.add(netWrapper);
        rightPanel.add(bottomDice);
        rightPanel.add(eventPanel);
        rightPanel.add(continueButton);

        continueButton.addActionListener(e -> {
            if (this.controller.isLastMatch()) {
                final EndGameView endGameView = new EndGameView(this.controller);
                this.viewManager.addView(endGameView, "end");
                this.controller.addPoints();
                this.controller.changeView("end");
            } else {
                this.controller.addPoints();
                this.controller.changeView("pre");
            }
        });
        this.add(rightPanel, BorderLayout.EAST); //NOPMD

        this.addComponentListener(new java.awt.event.ComponentAdapter() { //NOPMD
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                final int currentWidth = getWidth();

                revalidate();
                repaint();
                continueButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / (SWITCHER_BUTTON_FONT_RESIZING * 2)));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
        SwingUtilities.invokeLater(() -> {
            turnLabel.setText("Turn of :" + controller.getCurrentPlayer());

            switch (this.controller.getGameMode()) {
                case "PENALTY": 
                    netPanel.setButtonsEnabled(true);
                    break;
                case "FREE_KICK":
                    this.eventPanel.configure(EventPanel.EventType.FREE_KICK);
                    this.eventPanel.setVisible(true);
                    break;
                case "CORNER":
                    this.eventPanel.configure(EventPanel.EventType.CORNER);
                    this.eventPanel.setVisible(true);
                    break;
                case "RESULT":
                    this.eventPanel.configure(EventPanel.EventType.RESULT);
                    this.eventPanel.setVisible(true);
                    break;
                default:
                    this.eventPanel.setVisible(false);
                    break;
            }

            continueButton.setVisible(controller.isLastBox());
            continueButton.setEnabled(controller.isLastBox());
            if (controller.isLastBox()) {
                this.lastBox();
            }
        });
    }

    private void lastBox() {
        controller.LastBox();
    }

}
