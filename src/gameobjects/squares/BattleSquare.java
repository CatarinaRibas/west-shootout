package gameobjects.squares;

import gameobjects.Player;

public class BattleSquare extends Square{

    public BattleSquare(int xPos, int yPos, SquareType squareType) {
        super(xPos, yPos, squareType);
    }

    public boolean effect(Player player) {

        player.setCanShoot(true);
        player.setCanReload(true);
        return true;

    }
}
