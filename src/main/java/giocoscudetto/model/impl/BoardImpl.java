package giocoscudetto.model.impl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Boxes;

public class BoardImpl implements Board {

    private final List<Boxes> board;

    public BoardImpl() {
        this.board = new ArrayList<>(33);
        this.board.add(0,new StartBox());
        this.board.add(1,new EmptyBox(1));
        this.board.add(2,new ResultBox());
        this.board.add(3,new EmptyBox(3));
        this.board.add(4,new FreeKickBox());
        this.board.add(5,new EmptyBox(5));
        this.board.add(6,new BackToStartBox(6));
        this.board.add(7,new EmptyBox(7));
        this.board.add(8,new ResultBox());
        this.board.add(9,new EmptyBox(9));
        this.board.add(10,new CornerBox());
        this.board.add(11,new EmptyBox(1));
        this.board.add(12,new FreeKickBox());
        this.board.add(13,new EmptyBox(1));
        this.board.add(14,new ResultBox());
        this.board.add(15,new SuspendMatchBox());
        this.board.add(16,new FirstHalfBox());
        this.board.add(17,new JoinBox(17));
        this.board.add(18,new EmptyBox(8));
        this.board.add(19,new PenaltyBox());
        this.board.add(20,new EmptyBox(2));
        this.board.add(21,new SkipTurnBox(21));
        this.board.add(22,new EmptyBox(2));
        this.board.add(23,new ResultBox());
        this.board.add(24,new GoalConceidedBox());
        this.board.add(25,new EmptyBox(2));
        this.board.add(26,new PenaltyBox());
        this.board.add(27,new EmptyBox(2));
        this.board.add(28,new CornerBox());
        this.board.add(29,new ResultBox());
        this.board.add(30,new GoalRemovedBox());
        this.board.add(31,new CesariniBox());
        this.board.add(32,new FinishBox());    
    }

    @Override
    public Boxes getBox(int index) {
       return board.get(index);
    }

    @Override
    public Image getBoxImage(int i) {
        return this.board.get(i).getImage();
    }
    
}
