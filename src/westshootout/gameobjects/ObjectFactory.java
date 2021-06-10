package westshootout.gameobjects;

import westshootout.gameobjects.squares.*;
import westshootout.*;


public class ObjectFactory {

    public static Board createBoard() {
        return new Board();
    }

    public static Square createSquare(int xPos, int yPos, SquareType type) {

        switch (type) {
            case BAD_LUCK:
                return new BadLuckSquare(xPos, yPos);
            case BONUS:
                return new BonusSquare(xPos, yPos);
            case COVER:
                return new CoverSquare(xPos, yPos);
            case DEATH:
                return new DeathSquare(xPos, yPos);
            case SLIDE_EAST:
                return new SlideSquare(xPos, yPos, type);
            case SLIDE_SOUTH:
                return new SlideSquare(xPos, yPos, type);
            case SLIDE_WEST:
                return new SlideSquare(xPos, yPos, type);
            case SLIDE_NORTH:
                return new SlideSquare(xPos, yPos, type);
            case BATTLE_CYAN:
                return new BattleSquare(xPos, yPos, type);
            case BATTLE_GREEN:
                return new BattleSquare(xPos, yPos, type);
            case BATTLE_ORANGE:
                return new BattleSquare(xPos, yPos, type);
        }
        System.out.println("Error: Something went wrong in Square creation...");
        return null;
    }

    public static Gun createGun() {
        return new Gun();
    }

    public static Dice createDice() {
        return new Dice();
    }

    public static Player createPlayer(int playerNum, Game game) {
        return new Player(playerNum, game);
    }

}
