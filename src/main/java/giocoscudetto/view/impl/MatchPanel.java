package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MatchPanel extends DefaultPanelImpl {

    private final Starter controller;

    public MatchPanel(final Starter controller) {

        this.controller = controller;

        this.setLayout(new BorderLayout());
        final JPanel boardJPanel = new BoardPanel(controller);
        this.add(boardJPanel,BorderLayout.CENTER);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(new HomePanel(controller),BorderLayout.CENTER);
        this.add(rightPanel,BorderLayout.EAST);
        
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();
                final int currentHeight = getHeight();

                rightPanel.setSize(currentWidth,currentHeight);
                
                revalidate();
            
            }
        });

    }
}
