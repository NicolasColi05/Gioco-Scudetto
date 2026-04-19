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
        int minimumWidht = screenSize.width / 2;
        int minimumHeight = screenSize.height / 2;

        frame.setSize(minimumWidht , minimumHeight);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(minimumWidht, minimumHeight));

        //Creating different font for each component
        Font titleFont = new Font("Monospaced", Font.BOLD, minimumWidht / 30);
        Font buttonFont = new Font("Monospaced", Font.BOLD, minimumWidht / 50);
        Font exitFont = new Font("Monospaced", Font.BOLD, minimumWidht / 80);

        //Creating the mainframe panel
        final JPanel mainPanel = new JPanel(new BorderLayout());

        //Adding Game Title and setting it in the top center position of the frame
        JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), titleFont, Color.RED);
        //JLabel gameTitle = new JLabel("GIOCO DELLO SCUDETTO",SwingConstants.CENTER);
        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(titleFont);
        mainPanel.add(gameTitle, BorderLayout.NORTH);

        //Creating buttons to select to play with bots or friend
        JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0));
        JComponent selectBot = createComponent(new JButton("<html><center>PLAY WITH<br>BOTS</center></html>"), buttonFont, Color.BLUE);
        JComponent selectFriend = createComponent(new JButton( "<html><center>PLAY WITH<br>FRIENDS</center></html>"), buttonFont, Color.BLUE);
        selectButtonPanel.add(selectBot); 
        selectButtonPanel.add(selectFriend);       

        //Centralizing button vertically and responsively to the resolution changes
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComponent exitGame = createComponent(new JButton("EXIT"), exitFont, Color.BLACK);
        exitButtonPanel.add(exitGame);

        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(exitButtonPanel, BorderLayout.SOUTH);
        
        //Listener to change responsivly font size dipending by resolution changes
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                
                int currentWidth = frame.getWidth();

                gameTitle.setFont(new Font("Monospaced", Font.BOLD, currentWidth / 15));
                selectBot.setFont(new Font("Monospaced", Font.BOLD, currentWidth / 25));
                selectFriend.setFont(new Font("Monospaced", Font.BOLD, currentWidth / 25));
                exitGame.setFont(new Font("Monospaced", Font.BOLD, currentWidth / 40));
                
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
