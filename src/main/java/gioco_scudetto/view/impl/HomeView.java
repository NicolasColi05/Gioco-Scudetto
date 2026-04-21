package gioco_scudetto.view.impl;

import javax.swing.*;
import java.awt.*;
import gioco_scudetto.controller.api.Starter;

/**
 * This class contains the Game's Home implementation to decide to play 
 * against bots or friends
 */
public class HomeView {

    private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;
    private static final int BUTTONS_HORIZONTAL_GAP = 80;
    private static final int TITLE_FONT_RESIZING = 15;
    private static final int BUTTON_FONT_RESIZING = 25;
    private static final int EXIT_FONT_RESIZING = 40;


    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    private final Starter starter;

    /**
     * This class contains the Game's Home implementation to decide to play 
     * against bots or friends
     * @param starter refers to the controller linked to this view
     */
    public HomeView(final Starter starter) {
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
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        final JComponent selectBot = createComponent(new JButton("<html>PLAY WITH<br>BOTS</html>"), buttonFont, Color.BLUE);
        final JComponent selectFriend = createComponent(new JButton( "<html>PLAY WITH<br>FRIENDS</html>"), buttonFont, Color.BLUE);
        selectButtonPanel.add(selectBot); 
        selectButtonPanel.add(selectFriend);   

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        final JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JComponent exitGame = createComponent(new JButton("EXIT"), exitFont, Color.BLACK);
        exitButtonPanel.add(exitGame);

        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(exitButtonPanel, BorderLayout.SOUTH);

        //Listener to change responsivly font size dipending by resolution changes
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = frame.getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                selectBot.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                selectFriend.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                exitGame.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / EXIT_FONT_RESIZING));

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
