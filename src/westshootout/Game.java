package westshootout;

import westshootout.gameobjects.Board;
import westshootout.gameobjects.Dice;
import westshootout.gameobjects.ObjectFactory;
import westshootout.gameobjects.Player;

public class Game {

    private Board board;

    private Player[] players;

    private Dice dice;

    private boolean isWon;

    // GFX
    private BoardGFX boardGFX;

    public Game() {
        this.board = ObjectFactory.createBoard();
        this.dice = ObjectFactory.createDice();
        this.boardGFX = new BoardGFX(this);
        this.isWon = false;
    }

    public void setPlayers(int numPlayers) {

        this.players = new Player[numPlayers];

        for (int i = 0; i < this.players.length; i++) {

            players[i] = ObjectFactory.createPlayer(i + 1, this);

            switch (players[i].getPlayerNumber()) {
                case 1:
                    players[i].move(board.getGrid()[1][0]);
                    break;
                case 2:
                    players[i].move(board.getGrid()[9][10]);
                    break;
                case 3:
                    players[i].move(board.getGrid()[10][1]);
                    break;
                case 4:
                    players[i].move(board.getGrid()[0][10]);
                    break;
            }
        }
    }

    public boolean mainMenu() {

        MainMenuGFX mainMenu = new MainMenuGFX();
        mainMenu.show();
        return true;

    }

    public boolean play() {

        while (!isWon()) {

            for (Player player : players) {

                player.chooseAction();

                if (checkVictory()) {
                    break;
                }
            }
            announceWinner();
        }
    }

    public boolean checkVictory() {
        int playersAlive = 0;

        for (Player player : players) {

            if (!player.checkDead()) {
                playersAlive++;
            }
        }

        if (playersAlive > 1) {
            return false;
        }

        setWon(true);
        return true;
    }

    public void announceWinner() {
        boardGFX.hideAll();
        gameOverGFX.setWinner(getWinnerNum());
    }

    // Getters
    public boolean isWon() {
        return isWon;
    }

    public BoardGFX getBoardGFX() {
        return boardGFX;
    }

    public int getWinnerNum() {
        for (Player player : players) {
            if (player.isWinner()) {
                return player.getPlayerNumber();
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Dice getDice() {
        return dice;
    }

    // Setters
    public void setWon(boolean won) {
        isWon = won;
    }

    public void setWinner() {

        for (Player player : players) {
            if (!player.checkDead()) {
                player.win();
            }
        }

    }
}
