package giocoscudetto.model.impl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Boxes;

public class BoardImpl implements Board {

    private final List<Optional<Boxes>> board;

    public BoardImpl() {
        this.board = new ArrayList<>(33);
        this.board.add(0,Optional.of(new StartBox()));
        this.board.add(1,Optional.empty());
        this.board.add(2,Optional.of(new ResultBox()));
        this.board.add(3,Optional.empty());
        this.board.add(4,Optional.of(new FreeKickBox()));
        this.board.add(5,Optional.empty());
        this.board.add(6,Optional.of(new BackToStartBox(6)));
        this.board.add(7,Optional.empty());
        this.board.add(8,Optional.of(new ResultBox()));
        this.board.add(9,Optional.empty());
        this.board.add(10,Optional.of(new CornerBox()));
        this.board.add(11,Optional.empty());
        this.board.add(12,Optional.of(new FreeKickBox()));
        this.board.add(13,Optional.empty());
        this.board.add(14,Optional.of(new ResultBox()));
        this.board.add(15,Optional.of(new SuspendMatchBox()));
        this.board.add(16,Optional.of(new FirstHalfBox()));
        this.board.add(17,Optional.of(new JoinBox(17)));
        this.board.add(18,Optional.empty());
        this.board.add(19,Optional.of(new PenaltyBox()));
        this.board.add(20,Optional.empty());
        this.board.add(21,Optional.of(new SkipTurnBox(21)));
        this.board.add(22,Optional.empty());
        this.board.add(23,Optional.of(new ResultBox()));
        this.board.add(24,Optional.of(new GoalConceidedBox()));
        this.board.add(25,Optional.empty());
        this.board.add(26,Optional.of(new PenaltyBox()));
        this.board.add(27,Optional.empty());
        this.board.add(28,Optional.of(new CornerBox()));
        this.board.add(29,Optional.of(new ResultBox()));
        this.board.add(30,Optional.of(new GoalRemovedBox()));
        this.board.add(31,Optional.of(new CesariniBox()));
        this.board.add(32,Optional.of(new FinishBox()));    
    }

    @Override
    public Optional<Boxes> getBox(int index) {
       return board.get(index);
    }

    @Override
    public Image getBoxImage(int i) {
        return this.board.get(i).get().getImage();
    }
    
}
