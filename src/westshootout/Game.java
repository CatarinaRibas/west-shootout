package westshootout;

import westshootout.gameobjects.Board;
import westshootout.gameobjects.Dice;
import westshootout.gameobjects.ObjectFactory;
import westshootout.gameobjects.Player;
import westshootout.simpleGFX.MainMenuGFX;
import westshootout.simpleGFX.GameOverGFX;
import westshootout.simpleGFX.BoardGFX;


public class Game {

    private Board board;

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            play();
        }
    });

    private Player[] players;

    private Dice dice;

    private boolean isWon;

    // GFX
    private BoardGFX boardGFX;

    private GameOverGFX gameOverGFX;

    public Game() {

        this.board = ObjectFactory.createBoard();
        this.dice = ObjectFactory.createDice();
        this.boardGFX = new BoardGFX(this);
        this.gameOverGFX = new GameOverGFX();
        this.isWon = false;

    }

    public void setPlayers(int numPlayers) {

        this.players = new Player[numPlayers];

        for (int i = 0; i < this.players.length; i++) {

            players[i] = ObjectFactory.createPlayer(i + 1, this);

            switch (players[i].getPlayerNumber()) {
                case 1:
                    players[i].move(board.getGrid()[0][10]);
                    break;
                case 2:
                    players[i].move(board.getGrid()[10][0]);
                    break;
                case 3:
                    players[i].move(board.getGrid()[10][10]);
                    break;
                case 4:
                    players[i].move(board.getGrid()[0][0]);
                    break;
            }
        }

        boardGFX.showInit();
        System.out.println("Going to play()");
        thread.start();

    }

    public boolean mainMenu() {

        MainMenuGFX mainMenu = new MainMenuGFX(this);
        System.out.println("Showing main menu");
        mainMenu.show();

        return true;

    }

    public boolean play() {

        while (!isWon()) {

            System.out.println("Entered while !isWon");

            for (Player player : players) {

                System.out.println(player.getPlayerNumber());
                boardGFX.setActivePlayer(player);

                player.chooseAction();

                System.out.println("action chosen");

                if (checkVictory()) {
                    break;
                }
            }
        }
        announceWinner();
        return false;
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

        setWinner();
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
        return -1;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Dice getDice() {
        return dice;
    }

    public Board getBoard() {
        return board;
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
