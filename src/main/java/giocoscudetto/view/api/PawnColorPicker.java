package giocoscudetto.view.api;

import java.awt.Color;
import java.util.Set;
import java.util.function.Consumer;

public interface PawnColorPicker {
    
    /**
     * This method disable the color selected from a team for other clubs.
     * 
     * @param takenByOthers set of color already taken.
     */
    void setTakenColors(Set<Color> takenByOthers);

    /**
     * @return the selected color.
     */
    Color getSelectedColor();

    /**
     * Reset used when clubs number changed in the ClubPanel.
     */
    void reset();

    /**
     * This method is used in ClubPanel to refresh the color taken when a color got picked.
     * 
     * @param callback .
     */
    void setOnColorChanged(Consumer<Color> callback);
}
