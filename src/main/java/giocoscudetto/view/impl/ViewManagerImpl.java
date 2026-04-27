package giocoscudetto.view.impl;

import java.awt.CardLayout;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.view.api.ViewManager;

public class ViewManagerImpl implements ViewManager{

    private final CardLayout cardLayout;
    private final JPanel container;

    public ViewManagerImpl() {
        this.cardLayout = new CardLayout();
        this.container = new JPanel(cardLayout);
    }

    @Override
    public void addView(final JPanel panel, final String name) {
        container.add(panel, name);
    }

    @Override
    public void showView(final String name) {
        this.cardLayout.show(container, name);
        System.out.println("funziona");
        container.revalidate();
        container.repaint();
    }

    @Override
    public JPanel getContainer() {
        return this.container;
    }

    @Override
    public void quit() {
        final Window frame = SwingUtilities.getWindowAncestor(this.container);
        frame.dispose();
    }
}
