package gameobjects.squares;

import gameobjects.Player;

public class BadLuckSquare extends Square{

    private SquareType squareType;

    public BadLuckSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.BAD_LUCK);
    }

    public boolean effect(Player player) {
        return false;
    }
}
