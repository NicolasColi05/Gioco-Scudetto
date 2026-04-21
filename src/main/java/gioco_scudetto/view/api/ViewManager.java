package gioco_scudetto.view.api;

import javax.swing.JPanel;

public interface ViewManager {

    public void addView(JPanel panel, String name);

    public void showView(String name);
}
