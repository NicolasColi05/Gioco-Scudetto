package gioco_scudetto.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;

import gioco_scudetto.view.api.ViewManager;

public class MainFrame extends JFrame {

    private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;

    private final ViewManager viewManager;

    public MainFrame(ViewManager manager) {
        
        this.viewManager = manager;
        this.setContentPane(viewManager.getContainer());

        //Setting screen responsive resolution and placing it in the center
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int minimumWidht = screenSize.width / 2;
        final int minimumHeight = screenSize.height / 2;

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(minimumWidht, minimumHeight));

        //Setting frame main panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }



}
