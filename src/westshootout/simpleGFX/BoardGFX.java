package westshootout.simpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import westshootout.Game;
import westshootout.gameobjects.Player;
import westshootout.gameobjects.cards.TypeOfCards;
import westshootout.gameobjects.squares.Square;

public class BoardGFX implements KeyboardHandler {

    private Picture background;

    // Players' portraits, to appear on each corner.
    // How many are shown corresponds with number of players in game.
    private Rectangle portrait1;
    private Rectangle portrait2;
    private Rectangle portrait3;
    private Rectangle portrait4;
    private Picture portrait1Picture;
    private Picture portrait2Picture;
    private Picture portrait3Picture;
    private Picture portrait4Picture;
    private Picture dice;

    //available players
    private Rectangle player1Available;
    private Rectangle player2Available;
    private Rectangle player3Available;
    private Rectangle player4Available;

    //change AskRoll to TEXT
    private Text askRollText;
    private Text movesLeftText;

    private Rectangle moveRectangle;
    private Rectangle shootRectangle;
    private Rectangle reloadRectangle;
    private Picture player1Lives;
    private Picture player2Lives;
    private Picture player3Lives;
    private Picture player4Lives;
    private Picture player1Bullets;
    private Picture player2Bullets;
    private Picture player3Bullets;
    private Picture player4Bullets;
    private Picture player1Token;
    private Picture player2Token;
    private Picture player3Token;
    private Picture player4Token;
    private Picture bang;

    private Square square;

    public final static int PADDING = 0;
    public final static int SQUARE_SIZE = 75;
    public final static int BOARD_X_MARGIN = 550;
    public final static int BOARD_Y_MARGIN = 75;
    public final static int PORTRAIT_SIZE = 100;

    private Rectangle[][] gridGFX;

    private Game game;

    private Player activePlayer;

    // Keyboard properties.
    private Keyboard keyboard;

    private KeyboardEvent targetA = new KeyboardEvent();
    private KeyboardEvent targetB = new KeyboardEvent();
    private KeyboardEvent targetC = new KeyboardEvent();
    private KeyboardEvent shoot = new KeyboardEvent();
    private KeyboardEvent reload = new KeyboardEvent();
    private KeyboardEvent move = new KeyboardEvent();
    private KeyboardEvent roll = new KeyboardEvent();
    private KeyboardEvent moveA = new KeyboardEvent();
    private KeyboardEvent moveB = new KeyboardEvent();

    private boolean rolled;
    private boolean moveShooted;
    private boolean moveReloaded;
    private boolean moved;

    //Constuction
    public BoardGFX(Game game) {

        this.game = game;

        this.keyboard = new Keyboard(this);

        this.gridGFX = new Rectangle[game.getBoard().BOARD_SIZE][game.getBoard().BOARD_SIZE];
        createBoardGFX();

        background = new Picture(PADDING, PADDING, "numMenu.png");

        dice = new Picture(PADDING + 150, 400, "Dice1.png");

        //askRoll = new Rectangle(BOARD_X_MARGIN + 300 ,BOARD_Y_MARGIN - 60, 200, 50);

        moveRectangle = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE - 100, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, 100, 50);
        moveRectangle.setColor(Color.WHITE);

        shootRectangle = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + 300, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, 100, 50);
        shootRectangle.setColor(Color.RED);

        reloadRectangle = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + 300, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, 100, 50);
        reloadRectangle.setColor(Color.GREEN);

        portrait1 = new Rectangle(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait1.setColor(Color.BLUE);

        portrait1Picture = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, "playerOne.png");
        portrait1Picture.grow(10, 10);

        player1Token = new Picture(10, 10, "playerOne.png");

        player1Available = new Rectangle(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player1Available.setColor(Color.BLACK);

        player1Lives = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "maxLives.png");
        player1Bullets = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait2 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait2.setColor(Color.RED);

        player2Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player2Available.setColor(Color.BLACK);

        portrait2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 100, "playerTwo.png");
        portrait2Picture.grow(10, 10);

        player1Token = new Picture(10, 10, "playerTwo.png");

        player2Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "maxLives.png");
        player2Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");

        portrait3 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait3.setColor(Color.YELLOW);

        player3Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player3Available.setColor(Color.BLACK);

        portrait3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, "playerThree.png");
        portrait3Picture.grow(10, 10);

        player1Token = new Picture(10, 10, "playerThree.png");

        player3Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "maxLives.png");
        player3Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait4 = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait4.setColor(Color.GREEN);

        player4Available = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player4Available.setColor(Color.BLACK);

        portrait4Picture = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, "playerFourth.png");
        portrait4Picture.grow(10, 10);

        player1Token = new Picture(10, 10, "playerFourth.png");

        player4Lives = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "maxLives.png");
        player4Bullets = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");

        bang = new Picture(BOARD_X_MARGIN + 150, BOARD_Y_MARGIN + 200, "Bang.png");

    }

    //Related with Board
    public void createBoardGFX() {

        for (int i = 0; i < gridGFX.length; i++) {

            for (int j = 0; j < gridGFX[i].length; j++) {

                if (game.getBoard().getGrid()[i][j] != null) {
                    gridGFX[i][j] = new Rectangle(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), SQUARE_SIZE, SQUARE_SIZE);
                    gridGFX[i][j].setColor(game.getBoard().getGrid()[i][j].getSquareType().getColor());
                }
            }
        }
    }

    public void showBoard() {

        for (int i = 0; i < gridGFX.length; i++) {

            for (int j = 0; j < gridGFX[i].length; j++) {

                if (gridGFX[i][j] != null) {
                    gridGFX[i][j].fill();
                }
            }
        }
    }

    public void hideBoard() {

        for (int i = 0; i < gridGFX.length; i++) {

            for (int j = 0; j < gridGFX[i].length; j++) {
                if (gridGFX[i][j] != null) {
                    gridGFX[i][j].delete();
                }
            }
        }
    }

    //Related with Dice
    public void setDice(int result) {

        switch (result) {
            case 1:
                dice = new Picture(PADDING + 150, 400, "Dice1.png");
                break;
            case 2:
                dice = new Picture(PADDING + 150, 400, "Dice2.png");
                break;
            case 3:
                dice = new Picture(PADDING + 150, 400, "Dice3.png");
                break;
            case 4:
                dice = new Picture(PADDING + 150, 400, "Dice4.png");
                break;
            case 5:
                dice = new Picture(PADDING + 150, 400, "Dice5.png");
                break;
            case 6:
                dice = new Picture(PADDING + 150, 400, "Dice6.png");
                break;
        }
        showDice();
    }

    public void showDice() {
        dice.draw();
    }

    public void hideDice() {
        dice.delete();
    }

    public void askRoll() {
        askRollText = new Text(BOARD_X_MARGIN + 325, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + " , please Roll the dice");
        askRollText.grow(15, 20);
        askRollText.setColor(Color.BLUE);
        rolled = true;
        showAskRoll();
    }

    public void showAskRoll() {
        askRollText.draw();
    }

    public void hideAskRoll() {
        askRollText.delete();
    }

    //Related with Player

    //Show Players Portait; This method depends on the numbers of players selected
    public void showPortrait() {
        for (int i = 0; i < game.getPlayers().length; i++) {
            switch (i) {
                case 0:
                    portrait1.fill();
                    portrait1Picture.draw();
                    break;
                case 1:
                    portrait2.fill();
                    portrait2Picture.draw();
                    break;
                case 2:
                    portrait3.fill();
                    portrait3Picture.draw();
                    break;
                case 3:
                    portrait4.fill();
                    portrait4Picture.draw();
                    break;
                default:
                    portrait1.fill();
                    portrait1Picture.draw();
                    break;
            }
        }
    }

    public void deletePortrait() {
        for (int i = 0; i < game.getPlayers().length; i++) {
            switch (i) {
                case 0:
                    portrait1.delete();
                    break;
                case 1:
                    portrait2.delete();
                    break;
                case 2:
                    portrait3.delete();
                    break;
                case 3:
                    portrait4.delete();
                    break;
                default:
                    portrait1.delete();
                    break;
            }
        }
    }

    public void showPlayerLives() {
        for (int i = 0; i < game.getPlayers().length; i++) {
            switch (i) {
                case 0:
                    player1Lives.draw();
                    break;
                case 1:
                    player2Lives.draw();
                    break;
                case 2:
                    player3Lives.draw();
                    break;
                case 3:
                    player4Lives.draw();
                    break;
                default:
                    player1Lives.draw();
                    break;
            }
        }
    }

    public void showPlayerBullets() {
        for (int i = 0; i < game.getPlayers().length; i++) {
            switch (i) {
                case 0:
                    player1Bullets.draw();
                    break;
                case 1:
                    player2Bullets.draw();
                    break;
                case 2:
                    player3Bullets.draw();
                    break;
                case 3:
                    player4Bullets.draw();
                    break;
                default:
                    player1Bullets.draw();
                    break;
            }
        }
    }

    public void showMoveShoot() {
        moveShooted = true;
        shootRectangle.fill();
        moveRectangle.fill();
    }

    public void showMoveReload() {
        moveReloaded = true;
        moveRectangle.fill();
        reloadRectangle.fill();
    }

    public void showInit() {

        keyboardInit();
        background.draw();
        dice.draw();
        showBoard();
        showPortrait();
        showPlayerLives();
        showPlayerBullets();
        startPlayersPosition();

    }

    public void showBang() {

        //We can improve this method, showing Bang
        //o the player shooted
        bang.draw();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Error");
        }

        bang.delete();
    }

    public void showTargets(Player[] availableTarget) {
        for (int i = 0; i < availableTarget.length; i++) {
            switch (availableTarget[i].getPlayerNumber()) {
                case 1:
                    player1Available.draw();
                    break;
                case 2:
                    player2Available.draw();
                    break;
                case 3:
                    player3Available.draw();
                    break;
                case 4:
                    player4Available.draw();
                    break;
                default:
                    player1Available.draw();
                    break;
            }
        }

    }

    public void showCard(TypeOfCards typeOfCards) {
        return;
    }

    public void updateLives() {

        int numberPlayer = activePlayer.getPlayerNumber();
        int currentLives = activePlayer.getCurrentLives();

        switch (numberPlayer) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        player1Lives = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, i + "Lives.png");
                        player1Lives.draw();
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        player2Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, i + "Lives.png");
                        player2Lives.draw();
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        System.out.println(i);
                        player3Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, i + "Lives.png");
                        player3Lives.draw();
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        System.out.println(i);
                        player4Lives = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, i + "Lives.png");
                        player4Lives.draw();
                        break;
                    }
                }
                break;
        }
    }

    public void updateBullets() {
        int numberPlayer = activePlayer.getPlayerNumber();
        int currentBullets = activePlayer.getGun().getRemainingBullets();


        switch (numberPlayer) {
            case 1:

                for (int i = 0; i < 4; i++) {
                    if (currentBullets == i) {

                        player1Bullets.delete();

                        player1Bullets = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, i + "Bullets.png");

                        player1Bullets.draw();
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    if (currentBullets == i) {

                        player2Bullets.delete();
                        player2Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, i + "Bullets.png");
                        player2Bullets.draw();
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    if (currentBullets == i) {
                        player3Bullets.delete();
                        player3Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, i + "Bullets.png");
                        player3Bullets.draw();
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (currentBullets == i) {
                        player4Bullets.delete();
                        player4Bullets = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, i + "Bullets.png");
                        player4Bullets.draw();
                        break;
                    }
                }
                break;
        }
    }

    public void setMovesLeft(int movesLeft) {

        movesLeftText = new Text(BOARD_X_MARGIN + 325, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + " , you have " + movesLeft + "moves available");
        movesLeftText.grow(15, 20);
        movesLeftText.setColor(Color.BLUE);
        movesLeftText.draw();
        moved = true;

    }

    public void hideMovesLeft() {
        movesLeftText.delete();
    }

    public void setActivePlayer(Player player) {

        this.activePlayer = player;

    }

    public void startPlayersPosition() {

        for (int i = 0; i < game.getPlayers().length; i++) {
            Square playerStartSquare = game.getPlayers()[i].getCurrentSquare();
            int xTokenPosition = playerStartSquare.getxPos();
            int yTokenPosition = playerStartSquare.getyPos();

            System.out.println(xTokenPosition);
            System.out.println(yTokenPosition);

            switch (i) {
                case 0:
                    player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                    player1Token.draw();
                    break;
                case 1:
                    player2Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerTwo.png");
                    player2Token.draw();
                    break;
                case 2:
                    player3Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerThree.png");
                    player3Token.draw();
                    break;
                case 3:
                    player4Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerFourth.png");
                    player4Token.draw();
                    break;
                default:
                    player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                    player1Token.draw();
                    break;
            }
        }
        System.out.println("Left StartPositions");
    }

    public void updatePositions(Player player) {
        Square playerCurrentSquare = player.getCurrentSquare();
        int xTokenPosition = playerCurrentSquare.getxPos();
        int yTokenPosition = playerCurrentSquare.getyPos();

        switch (player.getPlayerNumber()) {
            case 1:
                player1Token.delete();
                player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                player1Token.draw();
                break;
            case 2:
                player2Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerTwo.png");
                player2Token.draw();
                break;
            case 3:
                player3Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerThree.png");
                player3Token.draw();
                break;
            case 4:
                player4Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerFourth.png");
                player4Token.draw();
                break;
            default:
                player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                player1Token.draw();
                break;
        }
    }

    public void hideAll() {

    }

    public void keyboardInit() {

        roll.setKey(KeyboardEvent.KEY_SPACE);
        roll.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(roll);

        shoot.setKey(KeyboardEvent.KEY_S);
        shoot.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(shoot);

        move.setKey(KeyboardEvent.KEY_M);
        move.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(move);

        reload.setKey(KeyboardEvent.KEY_R);
        reload.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(reload);

        targetA.setKey(KeyboardEvent.KEY_A);
        targetA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(targetA);

        targetB.setKey(KeyboardEvent.KEY_B);
        targetB.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(targetB);

        targetC.setKey(KeyboardEvent.KEY_C);
        targetC.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(targetC);

        moveA.setKey(KeyboardEvent.KEY_1);
        moveA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveA);

        moveB.setKey(KeyboardEvent.KEY_2);
        moveB.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveB);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                if (rolled) {
                    activePlayer.setHasRolled(true);
                    rolled = false;
                    hideAskRoll();
                }
                break;
            case KeyboardEvent.KEY_S:
                if (moveShooted) {
                    activePlayer.setWillShoot(true);
                    moveShooted = false;
                }
                break;
            case KeyboardEvent.KEY_R:
                if (moveReloaded) {
                    activePlayer.setWillReload(true);
                    moveReloaded = false;
                }
            case KeyboardEvent.KEY_1:
                if (moved) {
                    activePlayer.setHasMovedToA(true);
                    System.out.println("Yolo");
                    moved = false;
                    System.out.println("moved is now false");
                }
                break;
            case KeyboardEvent.KEY_2:
                if (moved) {
                    activePlayer.setHasMovedToB(true);
                    moved = false;
                }

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
