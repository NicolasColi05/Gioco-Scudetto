package giocoscudetto.view.impl;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import giocoscudetto.controller.api.Starter;

public class ClubPanel extends DefaultPanelImpl{
    
    private static final int TEAM_INFO_REDUCTION = 80;
    private static final int TEAM_INFO_VERTICAL_SPACE = 25;
    private static final int BUTTON_BORDER = 5;

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

        final JPanel teamInfoPanel = new JPanel();
        teamInfoPanel.setLayout(new BoxLayout(teamInfoPanel, BoxLayout.Y_AXIS));

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
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                    teamInfoPanel.add(nameTeam2);
                    break;
                case 3:
                    teamInfoPanel.add(nameTeam1);
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                    teamInfoPanel.add(nameTeam2);
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                    teamInfoPanel.add(nameTeam3);
                    break;
                case 4:
                    teamInfoPanel.add(nameTeam1);
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                    teamInfoPanel.add(nameTeam2);
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                    teamInfoPanel.add(nameTeam3);
                    teamInfoPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
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

        //Creating button to go back in the home or continue to visualize the pre match view
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        
        //Setting the border of the button
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        final JButton btnBack = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK);
        final JButton btnCont = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK);
        
        buttonPanel.add(btnBack, BorderLayout.WEST);
        buttonPanel.add(btnCont, BorderLayout.EAST);

        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        //Back button to go back to the home
        btnBack.addActionListener(e -> { //added by fede
            this.controller.changeView("home");
        }); 
        
        //Back button to go back to the home
        btnCont.addActionListener(e -> { //added by fede
            this.controller.changeView("pre");
        }); 

        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                selectNumberOfTeams.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / BUTTON_FONT_RESIZING));
                nameTeam1.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION));
                nameTeam2.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION));
                nameTeam3.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION));
                nameTeam4.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION));
                btnCont.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / EXIT_FONT_RESIZING));
                btnBack.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / EXIT_FONT_RESIZING));

                revalidate();
            
            }
        });
    }

    private JComponent createComponent(final JComponent component, final Font font, final Color color) {
        component.setFont(font);
        component.setForeground(color);
        return component;
    }
}
