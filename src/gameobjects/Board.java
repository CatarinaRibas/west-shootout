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
        boardConnect();
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void boardPopulate() {

        // Outer Board Ring

        //// West Corridor
        grid[0][0] = ObjectFactory.createSquare(SquareType.BONUS, 0, 0);
        grid[0][1] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 0, 1);
        grid[0][2] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 0, 2);
        grid[0][3] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 0, 3);
        grid[0][4] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 0, 4);
        grid[0][5] = ObjectFactory.createSquare(SquareType.COVER, 0, 5);
        grid[0][6] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 0, 6);
        grid[0][7] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 0, 7);
        grid[0][8] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 0, 8);
        grid[0][9] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 0, 9);
        grid[0][10] = ObjectFactory.createSquare(SquareType.BONUS, 0, 10);

        //// South Corridor
        grid[1][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 1, 10);
        grid[2][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 2, 10);
        grid[3][10] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 3, 10);
        grid[4][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 4, 10);
        grid[5][10] = ObjectFactory.createSquare(SquareType.COVER, 5, 10);
        grid[6][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 6, 10);
        grid[7][10] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 7, 10);
        grid[8][10] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 8, 10);
        grid[9][10] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 9, 10);
        grid[10][10] = ObjectFactory.createSquare(SquareType.BONUS, 10, 10);

        //// East Corridor
        grid[10][9] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 10, 9);
        grid[10][8] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 10, 8);
        grid[10][7] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 10, 7);
        grid[10][6] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 10, 6);
        grid[10][5] = ObjectFactory.createSquare(SquareType.COVER, 10, 5);
        grid[10][4] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 10, 4);
        grid[10][3] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 10, 3);
        grid[10][2] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 10, 2);
        grid[10][1] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 10, 1);
        grid[10][0] = ObjectFactory.createSquare(SquareType.BONUS, 10, 0);

        //// North Corridor
        grid[9][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 9, 0);
        grid[8][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 8, 0);
        grid[7][0] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 7, 0);
        grid[6][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 6, 0);
        grid[5][0] = ObjectFactory.createSquare(SquareType.COVER, 5, 0);
        grid[4][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 4, 0);
        grid[3][0] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 3, 0);
        grid[2][0] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 2, 0);
        grid[1][0] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 1, 0);


        // Side paths
        //// NW path
        grid[1][3] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 1, 3);
        grid[2][3] = ObjectFactory.createSquare(SquareType.BONUS, 2, 3);
        grid[2][2] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 2, 2);
        grid[3][2] = ObjectFactory.createSquare(SquareType.BONUS, 3, 2);
        grid[3][1] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 3, 1);

        //// SW path
        grid[1][7] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 1, 7);
        grid[2][7] = ObjectFactory.createSquare(SquareType.BONUS, 2, 7);
        grid[2][8] = ObjectFactory.createSquare(SquareType.BATTLE_GREEN, 2, 8);
        grid[3][8] = ObjectFactory.createSquare(SquareType.BONUS, 3, 8);
        grid[3][9] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 3, 9);

        //// SE path
        grid[7][9] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 7, 9);
        grid[7][8] = ObjectFactory.createSquare(SquareType.BONUS, 7, 8);
        grid[8][8] = ObjectFactory.createSquare(SquareType.BATTLE_ORANGE, 8, 8);
        grid[8][7] = ObjectFactory.createSquare(SquareType.BONUS, 8, 7);
        grid[9][7] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 9, 7);

        //// NE path
        grid[7][1] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 7, 1);
        grid[7][2] = ObjectFactory.createSquare(SquareType.BONUS, 7, 2);
        grid[8][2] = ObjectFactory.createSquare(SquareType.BATTLE_CYAN, 8, 2);
        grid[8][3] = ObjectFactory.createSquare(SquareType.BONUS, 8, 3);
        grid[9][3] = ObjectFactory.createSquare(SquareType.BAD_LUCK, 9, 3);


        // Canyons
        //// West-to-North
        grid[1][5] = ObjectFactory.createSquare(SquareType.SLIDE_EAST, 1, 5);
        grid[2][5] = ObjectFactory.createSquare(SquareType.SLIDE_EAST, 2, 5);
        grid[3][5] = ObjectFactory.createSquare(SquareType.BONUS, 3, 5);
        grid[4][5] = ObjectFactory.createSquare(SquareType.DEATH, 4, 5);
        grid[4][4] = ObjectFactory.createSquare(SquareType.BONUS, 4, 4);
        grid[5][4] = ObjectFactory.createSquare(SquareType.DEATH, 5, 4);
        grid[5][3] = ObjectFactory.createSquare(SquareType.BONUS, 5, 3);
        grid[5][2] = ObjectFactory.createSquare(SquareType.SLIDE_SOUTH, 5, 2);
        grid[5][1] = ObjectFactory.createSquare(SquareType.SLIDE_SOUTH, 5, 1);

        //// East-to-South
        grid[9][5] = ObjectFactory.createSquare(SquareType.SLIDE_WEST, 9, 5);
        grid[8][5] = ObjectFactory.createSquare(SquareType.SLIDE_WEST, 8, 5);
        grid[7][5] = ObjectFactory.createSquare(SquareType.BONUS, 7, 5);
        grid[6][5] = ObjectFactory.createSquare(SquareType.DEATH, 6, 5);
        grid[6][6] = ObjectFactory.createSquare(SquareType.BONUS, 6, 6);
        grid[5][6] = ObjectFactory.createSquare(SquareType.DEATH, 5, 6);
        grid[5][7] = ObjectFactory.createSquare(SquareType.BONUS, 5, 7);
        grid[5][8] = ObjectFactory.createSquare(SquareType.SLIDE_NORTH, 5, 8);
        grid[5][9] = ObjectFactory.createSquare(SquareType.SLIDE_NORTH, 5, 9);

    }

    public void boardConnect() {

        //Board ring
        ////North corridor
        grid[0][0].setNextSquareA(grid[1][0]);
        grid[1][0].setNextSquareA(grid[2][0]);
        grid[2][0].setNextSquareA(grid[3][0]);
        grid[3][0].setNextSquareA(grid[4][0]);
        grid[4][0].setNextSquareA(grid[5][0]);
        grid[5][0].setNextSquareA(grid[6][0]);
        grid[6][0].setNextSquareA(grid[7][0]);
        // Double-direction: goto NE path.
        grid[7][0].setNextSquareA(grid[8][0]);
        grid[7][0].setNextSquareB(grid[7][1]);
        // -----
        grid[8][0].setNextSquareA(grid[9][0]);
        grid[9][0].setNextSquareA(grid[10][0]);
        grid[10][0].setNextSquareA(grid[10][1]);

        //// East corridor.
        grid[10][1].setNextSquareA(grid[10][2]);
        grid[10][2].setNextSquareA(grid[10][3]);
        grid[10][3].setNextSquareA(grid[10][4]);
        grid[10][4].setNextSquareA(grid[10][5]);
        // Double-direction: goto E-S canyon.
        grid[10][5].setNextSquareA(grid[10][6]);
        grid[10][5].setNextSquareB(grid[9][5]);
        // -----
        grid[10][6].setNextSquareA(grid[10][7]);
        // Double-direction: goto SE path.
        grid[10][7].setNextSquareA(grid[10][8]);
        grid[10][7].setNextSquareB(grid[9][7]);
        // -----
        grid[10][8].setNextSquareA(grid[10][9]);
        grid[10][9].setNextSquareA(grid[10][10]);
        grid[10][10].setNextSquareA(grid[9][10]);

        ////South corridor
        grid[9][10].setNextSquareA(grid[8][10]);
        grid[8][10].setNextSquareA(grid[7][10]);
        grid[7][10].setNextSquareA(grid[6][10]);
        grid[6][10].setNextSquareA(grid[5][10]);
        grid[5][10].setNextSquareA(grid[4][10]);
        grid[4][10].setNextSquareA(grid[3][10]);
        // Double-direction: goto SW path.
        grid[3][10].setNextSquareA(grid[2][10]);
        grid[3][10].setNextSquareB(grid[3][9]);
        // -----
        grid[2][10].setNextSquareA(grid[1][10]);
        grid[1][10].setNextSquareA(grid[0][10]);
        grid[0][10].setNextSquareA(grid[0][9]);

        //// West corridor
        grid[0][9].setNextSquareA(grid[0][8]);
        grid[0][8].setNextSquareA(grid[0][7]);
        grid[0][7].setNextSquareA(grid[0][6]);
        grid[0][6].setNextSquareA(grid[0][5]);
        // Double-direction: goto E-N canyon.
        grid[0][5].setNextSquareA(grid[0][4]);
        grid[0][5].setNextSquareB(grid[1][5]);
        // -----
        grid[0][4].setNextSquareB(grid[0][3]);
        // Double-direction: goto NW path.
        grid[0][3].setNextSquareA(grid[0][2]);
        grid[0][3].setNextSquareB(grid[1][3]);
        // -----
        grid[0][2].setNextSquareA(grid[0][1]);
        grid[0][1].setNextSquareA(grid[0][0]);

        // Side paths
        //// NW path
        grid[1][3].setNextSquareA(grid[2][3]);
        grid[2][3].setNextSquareA(grid[2][2]);
        grid[2][2].setNextSquareA(grid[3][2]);
        grid[3][2].setNextSquareA(grid[3][1]);
        grid[3][1].setNextSquareA(grid[3][0]);

        //// NE path
        grid[7][1].setNextSquareA(grid[7][2]);
        grid[7][2].setNextSquareA(grid[8][2]);
        grid[8][2].setNextSquareA(grid[8][3]);
        grid[8][3].setNextSquareA(grid[9][3]);
        grid[9][3].setNextSquareA(grid[10][3]);

        //// SE path
        grid[9][7].setNextSquareA(grid[8][7]);
        grid[8][7].setNextSquareA(grid[8][8]);
        grid[8][8].setNextSquareA(grid[7][8]);
        grid[7][8].setNextSquareA(grid[7][9]);
        grid[7][9].setNextSquareA(grid[7][10]);

        //// SW path
        grid[3][9].setNextSquareA(grid[3][8]);
        grid[3][8].setNextSquareA(grid[2][8]);
        grid[2][8].setNextSquareA(grid[2][7]);
        grid[2][7].setNextSquareA(grid[1][7]);
        grid[1][7].setNextSquareA(grid[0][7]);

        // Canyons
        //// W-N Canyon
        grid[1][5].setNextSquareA(grid[2][5]);
        grid[2][5].setNextSquareA(grid[3][5]);
        grid[3][5].setNextSquareA(grid[4][5]);
        grid[4][5].setNextSquareA(grid[4][4]);
        grid[4][4].setNextSquareA(grid[5][4]);
        grid[5][4].setNextSquareA(grid[5][3]);
        grid[5][3].setNextSquareA(grid[5][2]);
        grid[5][2].setNextSquareA(grid[5][1]);
        grid[5][1].setNextSquareA(grid[5][0]);

        //// E-S Canyon
        grid[9][5].setNextSquareA(grid[8][5]);
        grid[8][5].setNextSquareA(grid[7][5]);
        grid[7][5].setNextSquareA(grid[6][5]);
        grid[6][5].setNextSquareA(grid[6][6]);
        grid[6][6].setNextSquareA(grid[5][6]);
        grid[5][6].setNextSquareA(grid[5][7]);
        grid[5][7].setNextSquareA(grid[5][8]);
        grid[5][8].setNextSquareA(grid[5][9]);
        grid[5][9].setNextSquareA(grid[5][10]);
    }
}
