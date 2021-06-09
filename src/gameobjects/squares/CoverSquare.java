package gameobjects.squares;

import gameobjects.Player;

public class CoverSquare extends Square{

    public CoverSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.COVER);
    }

    public boolean effect(Player player) {

        player.setCanShoot(false);
        player.setCanReload(true);
        return true;

    }
}
