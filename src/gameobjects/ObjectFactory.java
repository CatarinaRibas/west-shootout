package gameobjects;

import gameobjects.squares.Square;
import gameobjects.squares.SquareType;

public class ObjectFactory {

    public static Board createBoard() {
        return new Board();
    }

    public static Square createSquare(SquareType type){
        return new Square();
    }

}
