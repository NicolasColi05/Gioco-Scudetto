package gioco_scudetto.model.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Table;



public class TableImpl implements Table{

    //Class Fields
    private final List<Club> clubRank = new LinkedList<>();

    //Class Constructor
    public TableImpl(final List<Club> clubs) {
        this.clubRank.addAll(clubs);
    }   
    
    //Class Method
    @Override
    public void updateClubRank() {
        clubRank.sort(
            Comparator.comparingInt(Club::getPoints)
                  .thenComparingInt(Club::getNetDiff)
                  .reversed());
    }

    @Override
    public List<Club> showPosition() {
        return this.clubRank;
    }
    
}
