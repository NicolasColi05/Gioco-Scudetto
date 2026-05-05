package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MatchPanel extends DefaultPanelImpl {
    private final Starter controller;

    public MatchPanel(final Starter controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout(10, 0));

        final JPanel boardJPanel = new BoardPanel(controller);
        this.add(boardJPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(0, 8));
        this.setBackground(new Color(0xC8E6C9));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(280, 0));

        JPanel netWrapper = new JPanel(new BorderLayout()) {
            @Override
            public void doLayout() {
                super.doLayout();
                int w = getWidth();
                int h = (int)(w * 3.0 / 4.0);
                Component net = getComponent(0);
                net.setSize(w, h);
                net.setPreferredSize(new Dimension(w, h));
            }
        };
        netWrapper.setOpaque(false);
        netWrapper.add(new NetPanel(controller), BorderLayout.CENTER);
        rightPanel.add(new DicePanel(), BorderLayout.NORTH);
        rightPanel.add(netWrapper, BorderLayout.CENTER);
        rightPanel.add(new DicePanel(), BorderLayout.SOUTH);

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
