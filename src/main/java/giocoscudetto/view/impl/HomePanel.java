package giocoscudetto.view.impl;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
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
    private final BufferedImage image;

    public HomePanel(Starter controller) {
        this.controller = controller;

        this.setBackground(new Color(224, 201, 166));
        this.setLayout(new BorderLayout());
            
        //Adding Game Title
        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);

        //Creating buttons to select to play with bots or friend
        final JPanel selectButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS_HORIZONTAL_GAP, 0));
        selectButtonPanel.setOpaque(false);


        final JButton btnBot = (JButton) createComponent(new JButton("<html>PLAY WITH<br>BOTS</html>"), getButtonFont(), Color.BLUE);
        final JButton btnFriend = (JButton) createComponent(new JButton( "<html>PLAY WITH<br>FRIENDS</html>"), getButtonFont(), Color.BLUE);
        btnBot.setBackground(new Color(139, 90, 43));
        selectButtonPanel.add(btnBot); 
        selectButtonPanel.add(btnFriend);   

        //Centralizing button vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());

        //Creating button to exit from the game
        final JPanel switchingButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton btnExit = (JButton) createComponent(new JButton("EXIT"), getExitFont(), Color.BLACK);
        switchingButtonPanel.add(btnExit);


        //Adding the action listener to the buttons
        btnBot.addActionListener(e -> {
            this.controller.changeView("club");
        });

        btnFriend.addActionListener(e -> {
            this.controller.changeView("club");
        });
        
        btnExit.addActionListener(e -> {
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
                btnExit.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();
            
            }
        });

        //Setting the main panels opacity on false to show the backgorund color
        centerWrapper.add(selectButtonPanel);
        gameTitle.setOpaque(false);
        centerWrapper.setOpaque(false);
        switchingButtonPanel.setOpaque(false);

        //Placing correctly the specific panels in the main one 
        this.add(gameTitle, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(switchingButtonPanel, BorderLayout.SOUTH);
       

        try {
            this.image = ImageIO.read(new File("caselle_precise/home-image.png"));
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

}
