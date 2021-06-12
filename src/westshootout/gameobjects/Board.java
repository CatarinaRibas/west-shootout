package westshootout.gameobjects;

import westshootout.gameobjects.squares.Square;
import westshootout.gameobjects.squares.SquareType;

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


    // Fills the grid with the appropriate specific square objects in the appropriate coordinates. Other positions not used in game are left null.
    // Organized by board sections: Board ring is outer square of board, divided into 4 corridors (N, W, E, S), Paths are alternatives connectors
    // placed at border corridors' corners. They connect one corridor with its adjacent one of the appropriate corner (e.g NW path connects West
    // corridor to North Corridor through alternative path.
    // ONLY PLACES BOARDS, DOES NOT DEFINE GO-TO CONNECTIONS BETWEEN SQUARES. See boardConnect() for that.
    public void boardPopulate() {

        // Outer Board Ring

        //// West Corridor
        grid[0][0] = ObjectFactory.createSquare(0, 0, SquareType.BONUS,this);
        grid[0][1] = ObjectFactory.createSquare(0, 1, SquareType.BATTLE_CYAN,this);
        grid[0][2] = ObjectFactory.createSquare(0, 2, SquareType.BATTLE_GREEN,this);
        grid[0][3] = ObjectFactory.createSquare(0, 3, SquareType.BATTLE_ORANGE,this);
        grid[0][4] = ObjectFactory.createSquare(0, 4, SquareType.BATTLE_CYAN,this);
        grid[0][5] = ObjectFactory.createSquare(0, 5, SquareType.COVER,this);
        grid[0][6] = ObjectFactory.createSquare(0, 6, SquareType.BATTLE_GREEN,this);
        grid[0][7] = ObjectFactory.createSquare(0, 7, SquareType.BATTLE_ORANGE,this);
        grid[0][8] = ObjectFactory.createSquare(0, 8, SquareType.BATTLE_CYAN,this);
        grid[0][9] = ObjectFactory.createSquare(0, 9, SquareType.BATTLE_GREEN,this);
        grid[0][10] = ObjectFactory.createSquare(0, 10, SquareType.BONUS,this);

        //// South Corridor
        grid[1][10] = ObjectFactory.createSquare(1, 10, SquareType.BATTLE_ORANGE,this);
        grid[2][10] = ObjectFactory.createSquare(2, 10, SquareType.BATTLE_CYAN,this);
        grid[3][10] = ObjectFactory.createSquare(3, 10, SquareType.BATTLE_GREEN,this);
        grid[4][10] = ObjectFactory.createSquare(4, 10, SquareType.BATTLE_ORANGE,this);
        grid[5][10] = ObjectFactory.createSquare(5, 10, SquareType.COVER,this);
        grid[6][10] = ObjectFactory.createSquare(6, 10, SquareType.BATTLE_CYAN,this);
        grid[7][10] = ObjectFactory.createSquare(7, 10, SquareType.BATTLE_GREEN,this);
        grid[8][10] = ObjectFactory.createSquare(8, 10, SquareType.BATTLE_ORANGE,this);
        grid[9][10] = ObjectFactory.createSquare(9, 10, SquareType.BATTLE_CYAN,this);
        grid[10][10] = ObjectFactory.createSquare(10, 10, SquareType.BONUS,this);

        //// East Corridor
        grid[10][9] = ObjectFactory.createSquare(10, 9, SquareType.BATTLE_GREEN,this);
        grid[10][8] = ObjectFactory.createSquare(10, 8, SquareType.BATTLE_ORANGE,this);
        grid[10][7] = ObjectFactory.createSquare(10, 7, SquareType.BATTLE_CYAN,this);
        grid[10][6] = ObjectFactory.createSquare(10, 6, SquareType.BATTLE_GREEN,this);
        grid[10][5] = ObjectFactory.createSquare(10, 5, SquareType.COVER,this);
        grid[10][4] = ObjectFactory.createSquare(10, 4, SquareType.BATTLE_ORANGE,this);
        grid[10][3] = ObjectFactory.createSquare(10, 3, SquareType.BATTLE_CYAN,this);
        grid[10][2] = ObjectFactory.createSquare(10, 2, SquareType.BATTLE_GREEN,this);
        grid[10][1] = ObjectFactory.createSquare(10, 1, SquareType.BATTLE_ORANGE,this);
        grid[10][0] = ObjectFactory.createSquare(10, 0, SquareType.BONUS,this);

        //// North Corridor
        grid[9][0] = ObjectFactory.createSquare(9, 0, SquareType.BATTLE_CYAN,this);
        grid[8][0] = ObjectFactory.createSquare(8, 0, SquareType.BATTLE_GREEN,this);
        grid[7][0] = ObjectFactory.createSquare(7, 0, SquareType.BATTLE_ORANGE,this);
        grid[6][0] = ObjectFactory.createSquare(6, 0, SquareType.BATTLE_CYAN,this);
        grid[5][0] = ObjectFactory.createSquare(5, 0, SquareType.COVER,this);
        grid[4][0] = ObjectFactory.createSquare(4, 0, SquareType.BATTLE_GREEN,this);
        grid[3][0] = ObjectFactory.createSquare(3, 0, SquareType.BATTLE_ORANGE,this);
        grid[2][0] = ObjectFactory.createSquare(2, 0, SquareType.BATTLE_CYAN,this);
        grid[1][0] = ObjectFactory.createSquare(1, 0, SquareType.BATTLE_GREEN,this);


        // Side paths
        //// NW path
        grid[1][3] = ObjectFactory.createSquare(1, 3, SquareType.BAD_LUCK,this);
        grid[2][3] = ObjectFactory.createSquare(2, 3, SquareType.BONUS,this);
        grid[2][2] = ObjectFactory.createSquare(2, 2, SquareType.BATTLE_ORANGE,this);
        grid[3][2] = ObjectFactory.createSquare(3, 2, SquareType.BONUS,this);
        grid[3][1] = ObjectFactory.createSquare(3, 1, SquareType.BAD_LUCK,this);

        //// SW path
        grid[1][7] = ObjectFactory.createSquare(1, 7, SquareType.BAD_LUCK,this);
        grid[2][7] = ObjectFactory.createSquare(2, 7, SquareType.BONUS,this);
        grid[2][8] = ObjectFactory.createSquare(2, 8, SquareType.BATTLE_GREEN,this);
        grid[3][8] = ObjectFactory.createSquare(3, 8, SquareType.BONUS,this);
        grid[3][9] = ObjectFactory.createSquare(3, 9, SquareType.BAD_LUCK,this);

        //// SE path
        grid[7][9] = ObjectFactory.createSquare(7, 9, SquareType.BAD_LUCK,this);
        grid[7][8] = ObjectFactory.createSquare(7, 8, SquareType.BONUS,this);
        grid[8][8] = ObjectFactory.createSquare(8, 8, SquareType.BATTLE_ORANGE,this);
        grid[8][7] = ObjectFactory.createSquare(8, 7, SquareType.BONUS,this);
        grid[9][7] = ObjectFactory.createSquare(9, 7, SquareType.BAD_LUCK,this);

        //// NE path
        grid[7][1] = ObjectFactory.createSquare(7, 1, SquareType.BAD_LUCK,this);
        grid[7][2] = ObjectFactory.createSquare(7, 2, SquareType.BONUS,this);
        grid[8][2] = ObjectFactory.createSquare(8, 2, SquareType.BATTLE_CYAN,this);
        grid[8][3] = ObjectFactory.createSquare(8, 3, SquareType.BONUS,this);
        grid[9][3] = ObjectFactory.createSquare(9, 3, SquareType.BAD_LUCK,this);


        // Canyons
        //// West-to-North
        grid[1][5] = ObjectFactory.createSquare(1, 5, SquareType.SLIDE_EAST,this);
        grid[2][5] = ObjectFactory.createSquare(2, 5, SquareType.SLIDE_EAST,this);
        grid[3][5] = ObjectFactory.createSquare(3, 5, SquareType.BONUS,this);
        grid[4][5] = ObjectFactory.createSquare(4, 5, SquareType.DEATH,this);
        grid[4][4] = ObjectFactory.createSquare(4, 4, SquareType.BONUS,this);
        grid[5][4] = ObjectFactory.createSquare(5, 4, SquareType.DEATH,this);
        grid[5][3] = ObjectFactory.createSquare(5, 3, SquareType.BONUS,this);
        grid[5][2] = ObjectFactory.createSquare(5, 2, SquareType.SLIDE_SOUTH,this);
        grid[5][1] = ObjectFactory.createSquare(5, 1, SquareType.SLIDE_SOUTH,this);

        //// East-to-South
        grid[9][5] = ObjectFactory.createSquare(9, 5, SquareType.SLIDE_WEST,this);
        grid[8][5] = ObjectFactory.createSquare(8, 5, SquareType.SLIDE_WEST,this);
        grid[7][5] = ObjectFactory.createSquare(7, 5, SquareType.BONUS,this);
        grid[6][5] = ObjectFactory.createSquare(6, 5, SquareType.DEATH,this);
        grid[6][6] = ObjectFactory.createSquare(6, 6, SquareType.BONUS,this);
        grid[5][6] = ObjectFactory.createSquare(5, 6, SquareType.DEATH,this);
        grid[5][7] = ObjectFactory.createSquare(5, 7, SquareType.BONUS,this);
        grid[5][8] = ObjectFactory.createSquare(5, 8, SquareType.SLIDE_NORTH,this);
        grid[5][9] = ObjectFactory.createSquare(5, 9, SquareType.SLIDE_NORTH,this);
    }

    // Sets each previously populated Square's next connection to the appropriate following square by design.
    // Most squares connect only to one of their neighbours (to force one single movement direction), but some branch
    // into the path and canyon sections of the board, before reconnecting to the outer square somewhere.
    // These branching squares make use of their nextSquareA and nextSquareB, and assign each to the next square in
    // the corresponding branches. Single-heading squares only set nextSquareA, and nextSquareB is left null.
    // Player class and Square class logic should check allowed movement by checking nextSquareA and nextSquareB when
    // implementing movement methods.
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
