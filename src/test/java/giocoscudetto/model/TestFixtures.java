package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class TestFixtures {
    Club roma = new ClubImpl(new PawnImpl());
    Club inter = new ClubImpl(new PawnImpl());
    Club napoli = new ClubImpl(new PawnImpl());
    Club juventus = new ClubImpl(new PawnImpl());
    ArrayList<Club> listOfClubs = new ArrayList<>();
    
    @Test
    void TestFixturesGeneration(){
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        //listOfClubs.add(napoli);
        //listOfClubs.add(juventus);
        FixturesImpl fixture = new FixturesImpl(listOfClubs);
        System.out.println(fixture.toString());
    }
    
    //Fixtures fixtures = new FixturesImpl();
}