package gioco_scudetto.view.impl;

import javax.swing.*;
import java.awt.*;
import gioco_scudetto.controller.api.Starter;

public class HomeView {
    
    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    private final Starter starter;

    public HomeView(final Starter starter) {
        this.starter = starter;

        //Setting screen responsive resolution and placing it in the center
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        frame.setSize(width / 2, height / 2);
        frame.setLocationRelativeTo(null);

        frame.add(new JPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //Confrontarsi con gli altri: ha senso creare un interfaccia comune per tutte le view 
    //dove gli uncii due metodi sono next e prev, per navigare tra le varie vie?

}
