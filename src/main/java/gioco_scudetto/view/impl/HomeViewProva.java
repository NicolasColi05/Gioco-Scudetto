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

public class HomeViewProva extends JPanel {


    private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;
    private static final int BUTTONS_HORIZONTAL_GAP = 80;

    private final Starter controller;


    public HomeViewProva(Starter controller) {
        this.controller = controller;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int minimumWidht = screenSize.width / 2;
        this.setLayout(new BorderLayout());
         //Creating different font for each component
        final Font titleFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION);
        final Font buttonFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION);
        final Font exitFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / EXIT_FONT_REDUCTION);

        //Adding Game Title and setting it in the top center position of the frame
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), titleFont, Color.RED);
        //JLabel gameTitle = new JLabel("GIOCO DELLO SCUDETTO",SwingConstants.CENTER);
        gameTitle.setForeground(Color.RED);
        gameTitle.setFont(titleFont);
        this.add(gameTitle, BorderLayout.NORTH);

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
