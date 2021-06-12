package westshootout.gameobjects.squares;

import westshootout.Game;
import westshootout.gameobjects.Player;

public abstract class Square {

    private int xPos;
    private int yPos;
    private Square nextSquareA;
    private Square nextSquareB;
    private SquareType squareType;

    public Square(int xPos, int yPos, SquareType squareType) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.squareType = squareType;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Square getNextSquareA() {
        return nextSquareA;
    }

    public Square getNextSquareB() {
        return nextSquareB;
    }

    public void setNextSquareA(Square nextSquareA) {
        this.nextSquareA = nextSquareA;
    }

    public void setNextSquareB(Square nextSquareB) {
        this.nextSquareB = nextSquareB;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public abstract boolean effect(Player player);
}
