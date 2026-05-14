package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class EmptyBox implements Boxes {

    private final int position;
    private final String image;
    private final String description = "Box Event: Empty. If you land on this box, you do nothing.";

    /**
     * @param position the position of the box in the board.
     */
    public EmptyBox(final int position) {
        this.position = position;
       
        switch (position) {
            case(1): 
                this.image = "caselle_precise/casella_12.png";
                break;
            case(3):
                this.image = "caselle_precise/casella_13.png";
                break;
            case(5):
                this.image = "caselle_precise/casella_23.png";
                break;
            case(7):
                this.image = "caselle_precise/casella_24.png";
                break;
            case(9):
                this.image = "caselle_precise/casella_25.png";
                break;
            case(11):
                this.image = "caselle_precise/casella_27.png";
                break;
            case(13):
                this.image = "caselle_precise/casella_29.png";
                break;
            case(18):
                this.image = "caselle_precise/casella_4.png";
                break;
            case(20):
                this.image = "caselle_precise/casella_6.png";
                break;
            case(22):
                this.image = "caselle_precise/casella_8.png";
                break;
            case(25):
                this.image = "caselle_precise/casella_17.png";
                break;
            case(27):
                this.image = "caselle_precise/casella_20.png";
                break;
            default: 
                this.image = "caselle_precise/casella_12.png";
                break;
        } 
    }
    
    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {  
        match.turn();
      }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
