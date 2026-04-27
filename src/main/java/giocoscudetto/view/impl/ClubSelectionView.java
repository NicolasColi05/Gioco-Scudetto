package giocoscudetto.view.impl;

import javax.swing.*;

import giocoscudetto.controller.api.Starter;

import java.awt.*;

/**
 * The consrtuctor contains the Game's Club Selection implementation to choose the 
 * number and information of the club that will play the match.
 * 
 */
public class ClubSelectionView {
    private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;
    private static final int TITLE_FONT_RESIZING = 15;
    private static final int EXIT_FONT_RESIZING = 40;


    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    private final Starter starter;

    /**
     * The consrtuctor contains the Game's Club Selection implementation to choose the 
     * number and information of the club that will play the match.
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

        
        //Adding Game Title and setting it in the top center position of the frame
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), titleFont, Color.RED);
        //JLabel gameTitle = new JLabel("GIOCO DELLO SCUDETTO",SwingConstants.CENTER);
        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(titleFont);
        mainPanel.add(gameTitle, BorderLayout.NORTH);

        //Creating buttons to select to play with bots or friend
        

        //Creating button to exit from the game
        final JPanel buttonsPanel = new JPanel(new BorderLayout());
        final JComponent backButton = createComponent(new JButton("BACK"), exitFont, Color.BLACK);
        final JComponent playButton = createComponent(new JButton("PLAY"), exitFont, Color.BLACK);

        buttonsPanel.add(backButton, BorderLayout.WEST);
        buttonsPanel.add(playButton, BorderLayout.EAST);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        //Buttons Listener to go back to the home or start the match
        if (backButton instanceof JButton) {
            ((JButton)backButton).addActionListener(e -> {
                //Bisogna tornare alla HomeView
                //this.starter.changeView("home"); ci ho provato ma non so se ho capito Fede
            });
        }

        if (backButton instanceof JButton) {
            ((JButton)backButton).addActionListener(e -> {
                    
                //Bisogna iniziare la partita
                
            });
        }

        //Listener to change responsivly font size dipending by resolution changes
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = frame.getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                backButton.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / EXIT_FONT_RESIZING));

                frame.revalidate();
            }
        });

        //Setting frame main panel
        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JComponent createComponent(final JComponent component, final Font font, final Color color) {
        component.setFont(font);
        component.setForeground(color);
        return component;
    }
}
