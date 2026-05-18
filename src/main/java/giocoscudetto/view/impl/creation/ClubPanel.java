package giocoscudetto.view.impl.creation;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.impl.DefaultPanelImpl;

public class ClubPanel extends DefaultPanelImpl{
    
    private static final int NUMBER_COMBOBOX = 65;
    private static final int TEAM_INFO_REDUCTION = 80;
    private static final int TEAM_INFO_VERTICAL_SPACE = 10;
    private static final int BUTTON_BORDER = 5;
    private static final int TEXT_FIELDS_WIDTH = 300;
    private static final int TEXT_FIELDS_HEIGHT = 40;

    private final Starter viewChanger;
    private final CreateUpdateController controller;
    private final Image image;

    private final List<JTextField> clubsName = new ArrayList<>();
    private final List<PawnColorPickerPanel> clubsPawn = new ArrayList<>();
    private final JButton btnCont = (JButton) createComponent(new JButton("CONTINUE"), getExitFont(), new Color(224, 201, 166),new Color(62, 91, 66));


    public ClubPanel(final Starter viewChanger, final CreateUpdateController controller) {
        this.viewChanger = viewChanger;
        this.controller = controller;

        this.setLayout(new BorderLayout());

        //Creating the panel to choose the number of teams
        final JPanel numberOfClubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        @SuppressWarnings("unchecked")
        final JComboBox<Integer> selectNumberOfClub = (JComboBox<Integer>) createComponent(
            new JComboBox<>(new Integer[]{2, 3, 4}),
            getButtonFont(),
            Color.BLUE, null);

        final JLabel clubNumberSelectionLabel = (JLabel) createComponent(
                new JLabel("Choose the number of player "),
                getButtonFont(),
                Color.BLACK, null);

        numberOfClubPanel.add(clubNumberSelectionLabel);
        numberOfClubPanel.add(selectNumberOfClub);
        numberOfClubPanel.setOpaque(false);
        numberOfClubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Panel for general info of each club, which will contains clubNamePanel and clubPawnPanel
        final JPanel clubInfoPanel = new JPanel();
        clubInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));


        final JPanel clubNamePanel = new JPanel();
        final TitledBorder nameTitle = new TitledBorder("CLUB'S NAME");
        nameTitle.setBorder(BorderFactory.createEmptyBorder());
        nameTitle.setTitleJustification(TitledBorder.CENTER);

        clubNamePanel.setLayout(new BoxLayout(clubNamePanel, BoxLayout.Y_AXIS));
        clubNamePanel.setBorder(nameTitle);
        

        final JPanel clubPawnPanel = new JPanel();
        final TitledBorder pawnTitle = new TitledBorder("PAWN'S COLOR"); 
        pawnTitle.setBorder(BorderFactory.createEmptyBorder());
        pawnTitle.setTitleJustification(TitledBorder.CENTER); 

        clubPawnPanel.setLayout(new BoxLayout(clubPawnPanel, BoxLayout.Y_AXIS));
        clubPawnPanel.setBorder(pawnTitle);

        //Adding by default 2 rows to select a name and pawn
        updateTeamPanels(2, clubNamePanel, clubPawnPanel);

        clubInfoPanel.add(clubNamePanel);
        clubInfoPanel.add(clubPawnPanel);

        //Creating button to go back in the home or continue to visualize the pre match view
        final JPanel switchingButtonPanel = new JPanel(new BorderLayout());
        switchingButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        final JButton btnBack = (JButton) createComponent(new JButton("BACK"), getExitFont(), new Color(224, 201, 166),new Color(62, 91, 66));

        btnCont.setVisible(false);

        switchingButtonPanel.add(btnBack, BorderLayout.WEST);
        switchingButtonPanel.add(btnCont, BorderLayout.EAST);



        //Adding the action listener to the buttons and ComboBox
        
        selectNumberOfClub.addActionListener(e -> {
            
            final Integer numberSelected = (Integer) selectNumberOfClub.getSelectedItem();
            
            if (numberSelected != null) {
                updateTeamPanels(numberSelected, clubNamePanel, clubPawnPanel);
            }
        });

        btnBack.addActionListener(e -> { 
            this.viewChanger.changeView("home");
        }); 
        
        btnCont.addActionListener(e -> { 
            //Creating clubs, table and fixtures to start the match
            this.controller.createClubs(this.clubsName.stream()
                                            .map(JTextField::getText)
                                            .toList(),
                                        this.clubsPawn.stream()
                                            .map(i -> i.getSelectedColor().getRGB())
                                            .toList()); 


            //Then going to the next view
            this.viewChanger.changeView("pre");
        }); 

        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {

                final int currentWidth = getWidth();

                selectNumberOfClub.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / NUMBER_COMBOBOX));
                //clubName.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / TEAM_INFO_REDUCTION));
                btnCont.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                btnBack.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();
            
            }
        });

        //Centralizing clubInfoPanel vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(numberOfClubPanel);
        centerWrapper.add(clubInfoPanel);

        //Setting the main panels opacity on false to show the background color
        clubInfoPanel.setOpaque(false);
        clubNamePanel.setOpaque(false);
        clubPawnPanel.setOpaque(false);
        centerWrapper.setOpaque(false);
        switchingButtonPanel.setOpaque(false);

        //Placing correctly the specific panels in the main one
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(switchingButtonPanel, BorderLayout.SOUTH);

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/club-background.jpeg"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image", e);
        }
    }

    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, 0,0, getWidth(), getHeight(),null);
    }

    /**
     * This method is used to show the correct number of rows depending on clubs number selected
     * and showing the textfields to write the clubs name and color picker to pick the club
     * pawn color.
     * 
     * @param rows selected from the combobox
     * @param namePanel .
     * @param pawnPanel .
     */
    private void updateTeamPanels(final int rows, final JPanel namePanel,final JPanel pawnPanel){
                    
        namePanel.removeAll();
        pawnPanel.removeAll();
        this.clubsName.clear();
        this.clubsPawn.clear();

        int i = 0;
        for (i = 0; i < rows; i++) {

            //Creating textFields to choose the name for each team
            final JTextField nameTextField = (JTextField) createComponent(new JTextField(), getFont(), Color.BLACK, null);
            nameTextField.setPreferredSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
            nameTextField.setMaximumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
            nameTextField.setMinimumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));

            //Adding the new textField to the panel and list, then adding little space under it
            namePanel.add(nameTextField);
            namePanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
            this.clubsName.add(nameTextField);

            nameTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateButtonVisibility();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateButtonVisibility();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateButtonVisibility();
                }
                
            });

            final PawnColorPickerPanel colorPicker = new PawnColorPickerPanel();
            colorPicker.setPreferredSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
            colorPicker.setMaximumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
            colorPicker.setMinimumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));

            colorPicker.setOnColorChanged(c -> refreshColorTaken(this.clubsPawn));

            pawnPanel.add(colorPicker);
            pawnPanel.add(Box.createVerticalStrut(TEAM_INFO_VERTICAL_SPACE));
            this.clubsPawn.add(colorPicker);
        }

        //Revalidate and Repaint are necessery to update the interface
        this.revalidate(); 
        this.repaint();
        
    }

    /**
     * This method disable the color already taken from a club.
     * @param pickers are the picker assigned to each club.
     */
    private void refreshColorTaken(final List<PawnColorPickerPanel> pickers) {
        final Set<Color> allTaken = new HashSet<>();
        for (PawnColorPickerPanel p : pickers) {
            if (p.getSelectedColor() != null) {
                allTaken.add(p.getSelectedColor());
            }
        }

        //and then i disable them
        for (PawnColorPickerPanel p : pickers) {
            final Set<Color> takenByOthers = new HashSet<>(allTaken);
            if (p.getSelectedColor() != null) {
                takenByOthers.remove(p.getSelectedColor());
            }
            
            p.setTakenColors(takenByOthers);
                        this.updateButtonVisibility();
        }
    }

    private void updateButtonVisibility() {
        System.out.println("sentro");
        btnCont.setVisible(this.clubsPawn.stream().allMatch(i -> i.getSelectedColor() != null) &&
                           this.controller.isClubNameComplete(this.clubsName.stream()
                                        .map(JTextField::getText)
                                        .toList()));
    }
}
