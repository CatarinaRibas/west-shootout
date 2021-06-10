package westshootout.gameobjects.squares;

import westshootout.gameobjects.Player;

public class BonusSquare extends Square{

    public BonusSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.BONUS);
    }

    public boolean effect(Player player) {
        return false;
    }
}
