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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import gioco_scudetto.controller.api.Starter;

public class ClubPanel extends DefaultPanelImpl{
    
    private final Starter controller;


    public ClubPanel(Starter controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Adding Game Title and setting it in the top center position of the frame
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);
        this.add(gameTitle, BorderLayout.NORTH);

        //Creating Text Areas to write the name of the clubs and creating the box to select the number of clubs
        final JPanel numberOfTeamPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        
        @SuppressWarnings("unchecked")
        final JComboBox<Integer> selectNumberOfTeams = (JComboBox<Integer>) createComponent(
            new JComboBox<>(new Integer[]{2, 3, 4}),
            getButtonFont(),
            Color.BLUE);

        final JPanel teamInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        final TextArea nameTeam1 = new TextArea(2, 20); 
        final TextArea nameTeam2 = new TextArea(2, 20); 
        final TextArea nameTeam3 = new TextArea(2, 20); 
        final TextArea nameTeam4 = new TextArea(2, 20); 
        numberOfTeamPanel.add(selectNumberOfTeams);
        selectNumberOfTeams.addActionListener(e -> {
            final Integer numberSelected = (Integer) selectNumberOfTeams.getSelectedItem();
            teamInfoPanel.removeAll();
            switch ((int) numberSelected) {
                case 2:
                    teamInfoPanel.add(nameTeam1);
                    teamInfoPanel.add(nameTeam2);
                    break;
                case 3:
                    teamInfoPanel.add(nameTeam1);
                    teamInfoPanel.add(nameTeam2);
                    teamInfoPanel.add(nameTeam3);
                    break;
                case 4:
                    teamInfoPanel.add(nameTeam1);
                    teamInfoPanel.add(nameTeam2);
                    teamInfoPanel.add(nameTeam3);
                    teamInfoPanel.add(nameTeam4);
                    break;
                default:
                    break;
            }
            this.revalidate(); 
            this.repaint();
        });
        
        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(numberOfTeamPanel);
        centerWrapper.add(teamInfoPanel);

        //Creating button to exit from the game
        final JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton exitGame = (JButton) createComponent(new JButton("EXIT"), getExitFont(), Color.BLACK);
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
