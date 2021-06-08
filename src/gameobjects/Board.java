package gameobjects;

import gameobjects.squares.Square;
import gameobjects.squares.SquareType;

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

        // Outer board done
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
        grid

        // Canyons

    }
}
