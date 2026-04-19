package gioco_scudetto.view.impl;

import javax.swing.*;
import java.awt.*;
import gioco_scudetto.controller.api.Starter;

public class HomeView {
    
    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    private final Starter starter;

    public HomeView(final Starter starter) {
        this.starter = starter;

        //Setting screen responsive resolution and placing it in the center
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        frame.setSize(width / 2, height / 2);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        //Creating different font for each component
        Font titleFont = new Font("Monospaced", Font.BOLD, 48);
        Font buttonFont = new Font("Monospaced", Font.BOLD, 36);
        Font exitFont = new Font("Monospaced", Font.PLAIN, 18);

        //Creating the mainframe panel
        final JPanel mainPanel = new JPanel(new BorderLayout());

        //Adding Game Title and setting it in the top center position of the frame
        JLabel gameTitle = new JLabel("GIOCO DELLO SCUDETTO",SwingConstants.CENTER);
        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(titleFont);
        mainPanel.add(gameTitle, BorderLayout.NORTH);

        //Creating buttons to select to play with bots or friend, and to close the game
        JButton selectBot = createSelectionButton("<html><center>PLAY WITH<br>BOTS</center></html>", buttonFont);
        JButton selectFriend = createSelectionButton("<html><center>PLAY WITH<br>FRIENDS</center></html>", buttonFont);
        JButton exitGame = new JButton("EXIT");

        mainPanel.add(selectBot, BorderLayout.EAST);
        mainPanel.add(selectFriend, BorderLayout.WEST);
        mainPanel.add(exitGame, BorderLayout.SOUTH);
        
        

        //Setting frame main panel
        frame.getContentPane().add(mainPanel);

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JButton createSelectionButton(final String name, Font font) {
        JButton button = new JButton(name);
        button.setFont(font);
        button.setForeground(Color.BLUE);
        return button;
    }

}
