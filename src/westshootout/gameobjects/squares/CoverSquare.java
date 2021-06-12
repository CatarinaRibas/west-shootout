package westshootout.gameobjects.squares;

import westshootout.gameobjects.Player;

public class CoverSquare extends Square{

    public CoverSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.COVER);
    }

    public boolean effect(Player player) {

        return true;

    }
}
