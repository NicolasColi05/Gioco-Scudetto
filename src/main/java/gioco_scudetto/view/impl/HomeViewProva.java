package gioco_scudetto.view.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gioco_scudetto.controller.api.Starter;

public class HomeViewProva extends DefaultPanelImpl {

    private final Starter controller;

    public HomeViewProva(Starter controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Adding Game Title and setting it in the top center position of the frame
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);

        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(getTitleFont());
        this.add(gameTitle, BorderLayout.NORTH);

        //Creating buttons to select to play with bots or friend
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        final JComponent selectBot = createComponent(new JButton("<html>PLAY WITH<br>BOTS</html>"), getButtonFont(), Color.BLUE);
        final JComponent selectFriend = createComponent(new JButton( "<html>PLAY WITH<br>FRIENDS</html>"), getButtonFont(), Color.BLUE);
        selectButtonPanel.add(selectBot); 
        selectButtonPanel.add(selectFriend);   

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        final JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JComponent exitGame = createComponent(new JButton("EXIT"), getExitFont(), Color.BLACK);
        exitButtonPanel.add(exitGame);


        //Adding the action listener to the buttons
        if (selectBot instanceof JButton){
            ((JButton)selectBot).addActionListener(e -> {
                this.controller.changeView("club");
            });
        }

        if (selectBot instanceof JButton){
            ((JButton)exitGame).addActionListener(e -> {
                this.controller.closeGame();
            });
        }

        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(exitButtonPanel, BorderLayout.SOUTH);

        //Exit button Listener to exit the game
        
        
       
    }

    private JComponent createComponent(final JComponent component, final Font font, final Color color) {
        component.setFont(font);
        component.setForeground(color);
        return component;
    }


}
