package giocoscudetto.view.impl;

import giocoscudetto.controller.api.Starter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MatchPanel extends DefaultPanelImpl {
    private final Starter controller;
    private final JLabel turnLabel;

    public MatchPanel(final Starter controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0xC8E6C9));

        final JPanel boardJPanel = new BoardPanel(this.controller);
        this.add(boardJPanel, BorderLayout.CENTER);


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(280, 0));

        JPanel turnPanel = new JPanel();
        turnLabel = new JLabel();//mettere this.controller.getCurrentPlayer() +
        turnLabel.setFont(new Font("Turn",Font.BOLD,20));
        turnPanel.add(turnLabel);
        turnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnPanel.setMaximumSize(new Dimension(280, 120));

        JPanel netWrapper = new JPanel(new BorderLayout());
        netWrapper.setMaximumSize(new Dimension(300,200 ));
            
        netWrapper.setOpaque(false);
        netWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        netWrapper.add(new NetPanel(this.controller), BorderLayout.CENTER);

        JPanel bottomDice = new DicePanel(this.controller);
        bottomDice.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomDice.setMaximumSize(new Dimension(280, 120));

        rightPanel.add(turnPanel);
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

        final Agent agent = new Agent();
        new Thread(agent).start();
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
                    SwingUtilities.invokeAndWait(() -> MatchPanel.this.turnLabel.setText("Turn of :"));
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException ex) {
                        ex.printStackTrace(); //NOPMD
                }
               
            }
        }
    }
}
