package gameobjects.squares;

import gameobjects.Board;
import gameobjects.Player;

public class SlideSquare extends Square{

    private SquareType squareType;

    public SlideSquare(int xPos, int yPos, SquareType squareType) {
        super(xPos, yPos, squareType);
    }

    public boolean effect(Player player, Board board) {
        switch (squareType) {
            case SLIDE_EAST:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() - 2]);
                return true;
            case SLIDE_SOUTH:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() - 2]);
                return true;
            case SLIDE_WEST:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() - 2]);
                return true;
            case SLIDE_NORTH:
                player.move(board.getGrid()[super.getxPos()][super.getyPos() - 2]);
                return true;
        }
        System.out.println("Error in SlideSquare.effect(). Returned false.");
        return false;
    }
}
