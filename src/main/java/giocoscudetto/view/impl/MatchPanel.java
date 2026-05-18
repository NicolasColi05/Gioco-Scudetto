package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.GameObserver;
import giocoscudetto.view.api.ViewManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MatchPanel extends DefaultPanelImpl implements GameObserver {

    private static final Color BACKGROUND_COLOR = new Color(223,189,138);
    private final Starter controller;
    private final ViewManager viewManager;
    private final JLabel turnLabel;
    private final NetPanel netPanel;
    private final DicePanel bottomDice;
    private final JButton continueButton;
    private final EventPanel eventPanel;

    public MatchPanel(final Starter controller, final ViewManager viewManager) {

        final BoardPanel boardJPanel = new BoardPanel(controller);
        this.bottomDice = new DicePanel(controller,boardJPanel);
        this.netPanel = new NetPanel(controller);
        this.controller = controller;
        this.viewManager = viewManager;
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);
        this.controller.addObserver(this);
        this.add(boardJPanel, BorderLayout.CENTER);

        this.eventPanel = new EventPanel(controller);
        eventPanel.setMaximumSize(new Dimension(300,200 ));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(280, 0));

        JPanel turnPanel = new JPanel();
        turnLabel = new JLabel("Turn of :"+controller.getCurrentPlayer());
        turnPanel.setBackground(BACKGROUND_COLOR);
        turnLabel.setFont(new Font("Turn",Font.BOLD,20));
        turnPanel.add(turnLabel);
        turnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnPanel.setMaximumSize(new Dimension(280, 120));

        JPanel netWrapper = new JPanel(new BorderLayout());
        netWrapper.setMaximumSize(new Dimension(300,200 ));
        
        this.continueButton = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK, null);
        continueButton.setEnabled(false);
        continueButton.setVisible(false);

        netWrapper.setOpaque(false);
        netWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        netWrapper.add(netPanel, BorderLayout.CENTER);

        bottomDice.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomDice.setMaximumSize(new Dimension(280, 120));

        rightPanel.add(turnPanel);
        rightPanel.add(netWrapper);
        rightPanel.add(bottomDice);
        rightPanel.add(eventPanel);
        rightPanel.add(continueButton);
        

        continueButton.addActionListener(e -> { 
            if(this.controller.isLastMatch()){
                EndGameView EndGameView = new EndGameView(this.controller);
                viewManager.addView(EndGameView, "end");
                this.controller.changeView("end");
            }else{
                this.controller.changeView("pre");
            }
        });
        this.add(rightPanel, BorderLayout.EAST);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void updateState() {
        SwingUtilities.invokeLater(() -> {
            turnLabel.setText("Turn of :"+controller.getCurrentPlayer());

            switch (this.controller.getGameMode()) {
                case "PENALTY": 
                    netPanel.setButtonsEnabled(true);
                    break;
                case "FREE_KICK":
                    this.eventPanel.configure(giocoscudetto.view.impl.EventPanel.EventType.FREE_KICK);
                    this.eventPanel.setVisible(true);
                    break;
                case "CORNER":
                    this.eventPanel.configure(giocoscudetto.view.impl.EventPanel.EventType.CORNER);
                    this.eventPanel.setVisible(true);
                    break;
                case "RESULT":
                    this.eventPanel.configure(giocoscudetto.view.impl.EventPanel.EventType.RESULT);
                    this.eventPanel.setVisible(true);
                    break;
                default:
                    this.eventPanel.setVisible(false);
                    break;
            }

            // netPanel.setButtonsEnabled(controller.isPenalty());
            continueButton.setVisible(controller.isLastBox());
            continueButton.setEnabled(controller.isLastBox());
            if(controller.isLastBox()){
                this.LastBox();
            }
            
        });
    }

    private void LastBox(){
        controller.LastBox();
    }
}
