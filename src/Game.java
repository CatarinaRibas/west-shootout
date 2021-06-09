import gameobjects.Board;
import gameobjects.Dice;
import gameobjects.ObjectFactory;
import gameobjects.Player;

public class Game {

    private Board board;
    private Player[] players;
    private Dice dice;

    public Game() {
        this.board = ObjectFactory.createBoard();
        this.dice = ObjectFactory.createDice();
    }

    public void setPlayers(int numPlayers) {
        this.players = new Player[numPlayers];
        for (int i = 0; i < this.players.length; i++) {
            players[i] = ObjectFactory.createPlayer(i + 1);
            switch (players[i].getPlayerNumber()) {
                case 1:
                    players[i].move(board.getGrid()[1][0]);
                case 2:
                    players[i].move(board.getGrid()[9][10]);
                case 3:
                    players[i].move(board.getGrid()[0][9]);
                case 4:
                    players[i]
            }
        }
    }

    public boolean play() {

    }



}
