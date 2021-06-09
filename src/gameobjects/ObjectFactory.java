package gameobjects;

import gameobjects.squares.BonusSquare;
import gameobjects.squares.Square;
import gameobjects.squares.SquareType;

public class ObjectFactory {

    public static Board createBoard() {
        return new Board();
    }

    public static Square createSquare(SquareType type, int xPos, int yPos){
        return new BonusSquare();
    }

    public static Gun createGun() {
        return new Gun();
    }

}
