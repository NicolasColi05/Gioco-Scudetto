package giocoscudetto.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;

import giocoscudetto.view.api.DefaultPanel;

public class DefaultPanelImpl extends JPanel implements DefaultPanel{
    
    protected static final String FONT_SELECTED = Font.MONOSPACED;
    protected static final int BUTTONS_HORIZONTAL_GAP = 80;

    private static final int TITLE_FONT_REDUCTION = 15;
    private static final int BUTTON_FONT_REDUCTION = 25;
    private static final int EXIT_FONT_REDUCTION = 40;
    protected static final int TITLE_FONT_RESIZING = 15;
    protected static final int BUTTON_FONT_RESIZING = 25;
    protected static final int EXIT_FONT_RESIZING = 40;
    
    /*
     *Getting the deafult screen dimensione to use it to calibrate
     *all different game panel
    */
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;

    //Creating different font for each component
    private final Font titleFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION);
    private final Font buttonFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION);
    private final Font exitFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / EXIT_FONT_REDUCTION);
    
    
    //Getter Method
    @Override
    public Font getTitleFont() {
        return this.titleFont;
    }
    @Override
    public Font getButtonFont() {
        return this.buttonFont;
    }
    @Override
    public Font getExitFont() {
        return this.exitFont;
    }

}
