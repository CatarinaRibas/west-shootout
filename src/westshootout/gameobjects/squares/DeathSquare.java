package westshootout.gameobjects.squares;

import westshootout.gameobjects.Player;

public class DeathSquare extends Square {

    public DeathSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.DEATH);
    }

    public boolean effect(Player player) {
        player.getShot();
        player.setCanShoot(false);
        player.setCanReload(false);
        return true;
    }
}

