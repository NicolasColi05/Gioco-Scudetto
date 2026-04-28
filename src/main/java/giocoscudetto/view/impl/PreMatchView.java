package giocoscudetto.view.impl;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import giocoscudetto.controller.api.Starter;

public class PreMatchView extends DefaultPanelImpl{
    
    private Starter controller;

    public PreMatchView(Starter controller){
        this.controller = controller;

        this.setLayout(new BorderLayout());

        final JComponent gameTitle = createComponent(new JLabel("GIOCO DELLO SCUDETTO", SwingConstants.CENTER), getTitleFont(), Color.RED);
        this.add(gameTitle, BorderLayout.NORTH);
    }

    
}
