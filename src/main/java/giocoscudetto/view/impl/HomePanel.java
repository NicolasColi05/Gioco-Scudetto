package giocoscudetto.view.impl;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import giocoscudetto.controller.api.Starter;

public class HomePanel extends DefaultPanelImpl {

    private static final int BUTTONS_HORIZONTAL_GAP = 80;
    private static final int BUTTON_FONT_RESIZING = 25;

    private final Starter controller;

    public HomePanel(Starter controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Adding Game Title and setting it in the top center position of the frame
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);

        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(getTitleFont());
        this.add(gameTitle, BorderLayout.NORTH);

        //Creating buttons to select to play with bots or friend
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        final JButton btnBot = (JButton) createComponent(new JButton("<html>PLAY WITH<br>BOTS</html>"), getButtonFont(), Color.BLUE);
        final JButton btnFriend = (JButton) createComponent(new JButton( "<html>PLAY WITH<br>FRIENDS</html>"), getButtonFont(), Color.BLUE);
        selectButtonPanel.add(btnBot); 
        selectButtonPanel.add(btnFriend);   

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        final JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton exitGame = (JButton) createComponent(new JButton("EXIT"), getExitFont(), Color.BLACK);
        exitButtonPanel.add(exitGame);


        //Adding the action listener to the buttons
        btnBot.addActionListener(e -> {
            this.controller.changeView("club");
        });

        btnFriend.addActionListener(e -> {
            this.controller.changeView("club");
        });
        
        exitGame.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                        "Do you really want to quit?",
                        "QUITTING...",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.controller.closeGame();
                }

        });
        
        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                btnBot.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                btnFriend.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                exitGame.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();
            
            }
        });

        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(exitButtonPanel, BorderLayout.SOUTH);
       
    }

}
