package gioco_scudetto.view.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.util.Arrays;

import gioco_scudetto.controller.api.Starter;

public class ClubPanel extends JPanel{
  



    private static final String FONT_SELECTED = Font.MONOSPACED;
    private static final int TITLE_FONT_REDUCTION = 30;
    private static final int BUTTON_FONT_REDUCTION = 50;
    private static final int EXIT_FONT_REDUCTION = 80;
    private static final int BUTTONS_HORIZONTAL_GAP = 80;

    private final Starter controller;


    public ClubPanel(Starter controller) {
        this.controller = controller;
        
        this.setBackground(Color.RED);

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

        //Creating Text Areas to write the name of the clubs and creating the box to select the number of clubs
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        final JComboBox<Integer> selectNumberOfTeams = (JComboBox<Integer>) createComponent(new JComboBox<>(new Integer[]{1,2,3,4}), buttonFont, Color.BLUE);
        final TextArea nameTeam1 = new TextArea(2,20); 
        final TextArea nameTeam2 = new TextArea(2,20); 
        final TextArea nameTeam3 = new TextArea(2,20); 
        final TextArea nameTeam4 = new TextArea(2,20); 
        selectButtonPanel.add(selectNumberOfTeams);
        selectNumberOfTeams.addActionListener(e ->{
            Integer numberSelected = (Integer) selectNumberOfTeams.getSelectedItem();
            switch ( (int) numberSelected) {
                case 1:
                    selectButtonPanel.add(nameTeam1);
                    break;
                case 2:
                    selectButtonPanel.add(nameTeam1);
                    selectButtonPanel.add(nameTeam2);
                    break;
                case 3:
                    selectButtonPanel.add(nameTeam1);
                    selectButtonPanel.add(nameTeam2);
                    selectButtonPanel.add(nameTeam3);
                    break;
                case 4:
                    selectButtonPanel.add(nameTeam1);
                    selectButtonPanel.add(nameTeam2);
                    selectButtonPanel.add(nameTeam3);
                    selectButtonPanel.add(nameTeam4);
                    break;
                default:
                    break;
            }
        });
        /*selectButtonPanel.add(nameTeam1);
        selectButtonPanel.add(nameTeam2);
        selectButtonPanel.add(nameTeam3);
        selectButtonPanel.add(nameTeam4);*/
        
        

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(selectButtonPanel);

        //Creating button to exit from the game
        final JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton exitGame = (JButton) createComponent(new JButton("EXIT"), exitFont, Color.BLACK);
        exitButtonPanel.add(exitGame);

        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(exitButtonPanel, BorderLayout.SOUTH);

        //Exit button Listener to exit the game
        exitGame.addActionListener(e -> { //added by fede
            this.controller.changeView("home");
        });
        
        
       
    }

    private JComponent createComponent(final JComponent component, final Font font, final Color color) {
        component.setFont(font);
        component.setForeground(color);
        return component;
    }




}
