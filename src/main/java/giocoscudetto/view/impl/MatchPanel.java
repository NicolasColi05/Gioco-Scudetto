package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MatchPanel extends DefaultPanelImpl {

    private final Starter controller;

    public MatchPanel(final Starter controller) {

        this.controller = controller;

        this.setLayout(new BorderLayout());
        final JPanel boardJPanel = new JPanel(new GridLayout());
        this.add(boardJPanel,BorderLayout.CENTER);
    }
}
