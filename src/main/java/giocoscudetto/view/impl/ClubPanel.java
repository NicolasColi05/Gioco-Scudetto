package giocoscudetto.view.impl;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Pawn;

public class ClubPanel extends DefaultPanelImpl{
    
    private static final int NUMBER_COMBOBOX = 65;
    private static final int TEAM_INFO_REDUCTION = 80;
    private static final int TEAM_INFO_VERTICAL_SPACE = 25;
    private static final int BUTTON_BORDER = 5;
    private static final int TEXT_FIELDS_WIDTH = 300;
    private static final int TEXT_FIELDS_HEIGHT = 40;

    private final Starter controller;


    public ClubPanel(Starter controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Adding Game Title
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);

        //Creating the panel to choose the number of teams
        final JPanel numberOfClubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        @SuppressWarnings("unchecked")
        final JComboBox<Integer> selectNumberOfClub = (JComboBox<Integer>) createComponent(
            new JComboBox<>(new Integer[]{2, 3, 4}),
            getButtonFont(),
            Color.BLUE);

        final JLabel clubNumberSelectionLabel = (JLabel) createComponent(
                new JLabel("Choose the number of player "),
                getButtonFont(),
                Color.BLACK);

        numberOfClubPanel.add(clubNumberSelectionLabel);
        numberOfClubPanel.add(selectNumberOfClub);
        numberOfClubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Panel for general info of each club, which will contains clubNamePanel and clubPawnPanel
        final JPanel clubInfoPanel = new JPanel();
        clubInfoPanel.setLayout(new BoxLayout(clubInfoPanel, BoxLayout.X_AXIS));

        final JPanel clubNamePanel = new JPanel();
        clubNamePanel.setLayout(new BoxLayout(clubNamePanel, BoxLayout.Y_AXIS));

        final JPanel clubPawnPanel = new JPanel();
        clubPawnPanel.setLayout(new BoxLayout(clubPawnPanel, BoxLayout.Y_AXIS));

        //List which will contains the JTextFields to select each clubs name
        final List<JTextField> clubsName = new ArrayList<>();

        //List which will contains the Pawn that each clubs can choose
        final List<JTextField> clubsPawn = new ArrayList<>(); //CAMBIARE, HO MESO TEXTFIELDS SOLO PER PROVA

        //Adding by default 2 rows to select a name and pawn
        updateTeamPanels(2, clubNamePanel, clubPawnPanel, clubsName, clubsPawn);

        clubInfoPanel.add(clubNamePanel);
        clubInfoPanel.add(clubPawnPanel);

        selectNumberOfClub.addActionListener(e -> {
            
            final Integer numberSelected = (Integer) selectNumberOfClub.getSelectedItem();
            
            if (numberSelected != null) {
                updateTeamPanels(numberSelected, clubNamePanel, clubPawnPanel, clubsName, clubsPawn);
            }
        });

        //Creating button to go back in the home or continue to visualize the pre match view
        final JPanel switchingButtonPanel = new JPanel(new BorderLayout());
        switchingButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        final JButton btnBack = (JButton) createComponent(new JButton("BACK"), getExitFont(), Color.BLACK);
        final JButton btnCont = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), Color.BLACK);
        
        switchingButtonPanel.add(btnBack, BorderLayout.WEST);
        switchingButtonPanel.add(btnCont, BorderLayout.EAST);

        //Adding the action listener to the buttons
        btnBack.addActionListener(e -> { 
            this.controller.changeView("home");
        }); 
        
        btnCont.addActionListener(e -> { 
            this.controller.changeView("pre");
        }); 

        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                gameTitle.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TITLE_FONT_RESIZING));
                selectNumberOfClub.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / NUMBER_COMBOBOX));
                clubsName.stream().forEach(i -> i.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION)));
                btnCont.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                btnBack.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();
            
            }
        });

        //Centralizing clubInfoPanel vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(numberOfClubPanel);
        centerWrapper.add(clubInfoPanel);

        //Placing correctly the specific panels in the main one
        this.add(gameTitle, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(switchingButtonPanel, BorderLayout.SOUTH);
    }

    private void updateTeamPanels(final int rows, final JPanel namePanel,
                final JPanel pawnPanel,
                final List<JTextField> clubsName, 
                final List<JTextField> clubsPawn){
                    
            namePanel.removeAll();
            pawnPanel.removeAll();

            clubsName.clear();
            clubsPawn.clear();

            int i = 0;
            for (i = 0; i < rows; i++) {
                
                final JTextField nameTextField = (JTextField) createComponent(new JTextField(), getFont(), Color.BLACK);
                nameTextField.setPreferredSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
                nameTextField.setMaximumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
                nameTextField.setMinimumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
                
                //Adding the new textField to the panel and list, then adding little space under it
                namePanel.add(nameTextField);
                namePanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                clubsName.add(nameTextField);

                final JTextField nameTextField1 = (JTextField) createComponent(new JTextField(), getFont(), Color.BLACK);
                nameTextField1.setPreferredSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
                nameTextField1.setMaximumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
                nameTextField1.setMinimumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));

                pawnPanel.add(nameTextField1);
                pawnPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
                clubsPawn.add(nameTextField1);
            }

            //Revalidate and Repaint are necessery to update the interface
            this.revalidate(); 
            this.repaint();
        }
}
