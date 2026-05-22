package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.impl.boxes.BackToStartBox;
import giocoscudetto.model.impl.boxes.CesariniBox;
import giocoscudetto.model.impl.boxes.CornerBox;
import giocoscudetto.model.impl.boxes.EmptyBox;
import giocoscudetto.model.impl.boxes.FinishBox;
import giocoscudetto.model.impl.boxes.FirstHalfBox;
import giocoscudetto.model.impl.boxes.FreeKickBox;
import giocoscudetto.model.impl.boxes.GoalConceidedBox;
import giocoscudetto.model.impl.boxes.GoalRemovedBox;
import giocoscudetto.model.impl.boxes.JoinBox;
import giocoscudetto.model.impl.boxes.PenaltyBox;
import giocoscudetto.model.impl.boxes.ResultBox;
import giocoscudetto.model.impl.boxes.SkipTurnBox;
import giocoscudetto.model.impl.boxes.StartBox;
import giocoscudetto.model.impl.boxes.SuspendMatchBox;

public class BoardImpl implements Board {

    private final List<Boxes> board;

    public BoardImpl() {
        this.board = new ArrayList<>(32);
        this.board.add(0,new StartBox(0));
        this.board.add(1,new EmptyBox(1));
        this.board.add(2,new ResultBox(2));
        this.board.add(3,new EmptyBox(3));
        this.board.add(4,new FreeKickBox(4));
        this.board.add(5,new EmptyBox(5));
        this.board.add(6,new BackToStartBox(6));
        this.board.add(7,new EmptyBox(7));
        this.board.add(8,new ResultBox(8));
        this.board.add(9,new EmptyBox(9));
        this.board.add(10,new CornerBox(10));
        this.board.add(11,new EmptyBox(11));
        this.board.add(12,new FreeKickBox(12));
        this.board.add(13,new EmptyBox(13));
        this.board.add(14,new ResultBox(14));
        this.board.add(15,new SuspendMatchBox(15));
        this.board.add(16,new FirstHalfBox(16));
        this.board.add(17,new JoinBox(17));
        this.board.add(18,new EmptyBox(18));
        this.board.add(19,new PenaltyBox(19));
        this.board.add(20,new EmptyBox(20));
        this.board.add(21,new SkipTurnBox(21));
        this.board.add(22,new EmptyBox(22));
        this.board.add(23,new ResultBox(23));
        this.board.add(24,new GoalConceidedBox(24));
        this.board.add(25,new EmptyBox(25));
        this.board.add(26,new PenaltyBox(26));
        this.board.add(27,new EmptyBox(27));
        this.board.add(28,new CornerBox(28));
        this.board.add(29,new ResultBox(29));
        this.board.add(30,new GoalRemovedBox(30));
        this.board.add(31,new CesariniBox(31));
        this.board.add(32,new FinishBox(32));
    }

    @Override
    public Boxes getBox(int index) {
       return board.get(index);
    }

    @Override
    public String getBoxImage(int i) {
        return this.board.get(i).getImage();
    }
    
}
