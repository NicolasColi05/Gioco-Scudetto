package giocoscudetto.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DicePanel extends DefaultPanelImpl {
    
    public DicePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        JLabel messageLabel = new JLabel("ciao");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(8, 4, 8, 4));

        JButton rollDiceButton = new JButton("Roll Dice");

        this.add(rollDiceButton, BorderLayout.SOUTH);        
        this.add(messageLabel,BorderLayout.CENTER);

    }
}
