package westshootout.gameobjects.squares;

import westshootout.gameobjects.Board;
import westshootout.gameobjects.Player;

public class SlideSquare extends Square{

    private Board board;
    private SquareType squareType;

    public SlideSquare(int xPos, int yPos, SquareType squareType, Board board) {
        super(xPos, yPos, squareType);
        this.board = board;
        this.squareType = squareType;
    }

    public boolean effect(Player player) {
        switch (squareType) {
            case SLIDE_EAST:
                player.move(board.getGrid()[super.getxPos() + 2][super.getyPos()]);
                return true;
            case SLIDE_SOUTH:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() + 2]);
                return true;
            case SLIDE_WEST:
                player.move(board.getGrid()[super.getxPos() - 2][super.getyPos()]);
                return true;
            case SLIDE_NORTH:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() - 2]);
                return true;
        }
        System.out.println("Error in SlideSquare.effect(). Returned false.");
        return false;
    }
}
