package gameobjects;

import gameobjects.squares.Square;
import gameobjects.squares.SquareType;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Board {

    public static final int BOARD_SIZE = 11;
    private Square[][] grid;

    public Board() {
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        boardPopulate();
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void boardPopulate() {

        // Outer Board Ring

        //// West Corridor
        grid[0][0] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[0][1] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[0][2] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[0][3] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[0][4] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[0][5] = ObjectFactory.createSquare(SquareType.COVER);
        grid[0][6] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[0][7] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[0][8] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[0][9] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[0][10] = ObjectFactory.createSquare(SquareType.BONUS);

        //// South Corridor
        grid[1][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[2][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[3][10] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[4][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[5][10] = ObjectFactory.createSquare(SquareType.COVER);
        grid[6][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[7][10] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[8][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[9][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[10][10] = ObjectFactory.createSquare(SquareType.BONUS);

        //// East Corridor
        grid[10][9] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[10][8] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[10][7] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[10][6] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[10][5] = ObjectFactory.createSquare(SquareType.COVER);
        grid[10][4] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[10][3] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[10][2] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[10][1] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[10][0] = ObjectFactory.createSquare(SquareType.BONUS);

        //// North Corridor
        grid[9][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[8][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[7][0] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[6][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[5][0] = ObjectFactory.createSquare(SquareType.COVER);
        grid[4][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[3][0] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[2][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[1][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);


        // Side paths
        //// NW path
        grid[1][3] = ObjectFactory.createSquare(SquareType.BAD_LUCK);
        grid[2][3] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[2][2] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[3][2] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[3][1] = ObjectFactory.createSquare(SquareType.BAD_LUCK);

        //// SW path
        grid[1][7] = ObjectFactory.createSquare(SquareType.BAD_LUCK);
        grid[2][7] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[2][8] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN);
        grid[3][8] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[3][9] = ObjectFactory.createSquare(SquareType.BAD_LUCK);

        //// SE path
        grid[7][9] = ObjectFactory.createSquare(SquareType.BAD_LUCK);
        grid[7][8] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[8][8] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE);
        grid[8][7] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[9][7] = ObjectFactory.createSquare(SquareType.BAD_LUCK);

        //// NE path
        grid[7][1] = ObjectFactory.createSquare(SquareType.BAD_LUCK);
        grid[7][2] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[8][2] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN);
        grid[8][3] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[9][3] = ObjectFactory.createSquare(SquareType.BAD_LUCK);


        // Canyons
        //// West-to-North
        grid[1][5] = ObjectFactory.createSquare(SquareType.SLIDE_EAST);
        grid[2][5] = ObjectFactory.createSquare(SquareType.SLIDE_EAST);
        grid[3][5] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[4][5] = ObjectFactory.createSquare(SquareType.DEATH);
        grid[4][4] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[5][4] = ObjectFactory.createSquare(SquareType.DEATH);
        grid[5][3] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[5][2] = ObjectFactory.createSquare(SquareType.SLIDE_SOUTH);
        grid[5][1] = ObjectFactory.createSquare(SquareType.SLIDE_SOUTH);

        //// East-to-South
        grid[9][5] = ObjectFactory.createSquare(SquareType.SLIDE_WEST);
        grid[8][5] = ObjectFactory.createSquare(SquareType.SLIDE_WEST);
        grid[7][5] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[6][5] = ObjectFactory.createSquare(SquareType.DEATH);
        grid[6][6] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[5][6] = ObjectFactory.createSquare(SquareType.DEATH);
        grid[5][7] = ObjectFactory.createSquare(SquareType.BONUS);
        grid[5][8] = ObjectFactory.createSquare(SquareType.SLIDE_NORTH);
        grid[5][9] = ObjectFactory.createSquare(SquareType.SLIDE_NORTH);

    }
}
