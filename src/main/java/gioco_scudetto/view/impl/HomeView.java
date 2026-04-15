package gioco_scudetto.view.impl;

import javax.swing.*;
import gioco_scudetto.controller.api.Starter;

public class HomeView {
    
    private final JFrame frame = new JFrame("GIOCO DELLO SCUDETTO");
    //private final Starter starter;

    public HomeView(final Starter starter) {
        //this.starter = starter;
        frame.add(new JPanel());


        frame.setVisible(true);
    }

    //Confrontarsi con gli altri: ha senso creare un interfaccia comune per tutte le view 
    //dove gli uncii due metodi sono next e prev, per navigare tra le varie vie?

}
