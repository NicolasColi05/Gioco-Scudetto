package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MatchPanel extends DefaultPanelImpl {
    private final Starter controller;

    public MatchPanel(final Starter controller) {
    this.controller = controller;
    this.setLayout(new BorderLayout(10, 0));
    this.setBackground(new Color(0xC8E6C9));

    final JPanel boardJPanel = new BoardPanel(controller);
    this.add(boardJPanel, BorderLayout.CENTER);

    // Pannello destro verticale
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setOpaque(false);
    rightPanel.setPreferredSize(new Dimension(280, 0));

    // DicePanel in alto
    JPanel topDice = new DicePanel();
    topDice.setAlignmentX(Component.CENTER_ALIGNMENT);
    topDice.setMaximumSize(new Dimension(280, 120));

    // NetWrapper dinamico
    JPanel netWrapper = new JPanel(new BorderLayout()) {
        @Override
        public Dimension getPreferredSize() {
            int w = getParent() != null ? getParent().getWidth() : 280;
            int h = (int)(w * 3.0 / 4.0);
            return new Dimension(w, h);
        }
        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    };
    netWrapper.setOpaque(false);
    netWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
    netWrapper.add(new NetPanel(controller), BorderLayout.CENTER);

    // DicePanel subito sotto la porta
    JPanel bottomDice = new DicePanel();
    bottomDice.setAlignmentX(Component.CENTER_ALIGNMENT);
    bottomDice.setMaximumSize(new Dimension(280, 120));

    rightPanel.add(topDice);
    rightPanel.add(netWrapper);
    rightPanel.add(bottomDice);

    this.add(rightPanel, BorderLayout.EAST);

    this.addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(final java.awt.event.ComponentEvent e) {
            revalidate();
            repaint();
        }
    });
}
}
