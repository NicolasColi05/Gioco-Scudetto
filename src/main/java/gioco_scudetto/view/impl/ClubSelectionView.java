package gioco_scudetto.view.impl;

import javax.swing.*;
import java.awt.*;
import gioco_scudetto.controller.api.Starter;

public class ClubSelectionView {
        private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;


    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    private final Starter starter;

    /**
     * This class contains the Game's Home implementation to decide to play 
     * against bots or friends.
     * 
     * @param starter refers to the controller linked to this view.
     */
    public ClubSelectionView(final Starter starter) {
        this.starter = starter;

        //Setting screen responsive resolution and placing it in the center
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int minimumWidht = screenSize.width / 2;
        final int minimumHeight = screenSize.height / 2;

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(minimumWidht, minimumHeight));

        //Creating different font for each component
        final Font titleFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION);
        final Font buttonFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION);
        final Font exitFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / EXIT_FONT_REDUCTION);

        //Creating the mainframe panel
        final JPanel mainPanel = new JPanel(new BorderLayout());



        //Setting frame main panel
        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
