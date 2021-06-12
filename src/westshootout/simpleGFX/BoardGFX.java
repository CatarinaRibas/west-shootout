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
import westshootout.gameobjects.squares.*;

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
    private Picture character1Picture;
    private Picture character2Picture;
    private Picture character3Picture;
    private Picture character4Picture;
    private Picture dice;

    private Picture deathSquare;
    private Picture coverSquare;
    private Picture bonusSquare;
    private Picture badLuckSquare;
    private Picture slideSquare;
    private Picture battleSquareRed;
    private Picture battleSquareGreen;
    private Picture battleSquareYellow;

    //available players
    private Rectangle player1Available;
    private Rectangle player2Available;
    private Rectangle player3Available;
    private Rectangle player4Available;

    //change AskRoll to TEXT
    private Text askRollText;
    private Text movesLeftText;
    private Text switchPlaces;
    private Text tokenText1;
    private Text tokenText2;
    private Text tokenText3;
    private Text tokenText4;

    private Picture moveButton;
    private Picture shootButton;
    private Picture reloadButton;
    private Picture card;
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
    public final static int TOKEN_TEXT_GROW_X = 40;
    public final static int TOKEN_TEXT_GROW_Y = 15;

    private Picture[][] gridGFX;

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
    private boolean playerChoice;
    private boolean targetAselected;
    private boolean targetBselected;
    private boolean targetCselected;

    //Constuction
    public BoardGFX(Game game) {

        this.game = game;

        this.keyboard = new Keyboard(this);

        this.gridGFX = new Picture[game.getBoard().BOARD_SIZE][game.getBoard().BOARD_SIZE];
        createBoardGFX();

        background = new Picture(PADDING, PADDING, "numMenu.png");

        dice = new Picture(PADDING + 150, 400, "Dice1.png");

        card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "pickACard.png");

        //askRoll = new Rectangle(BOARD_X_MARGIN + 300 ,BOARD_Y_MARGIN - 60, 200, 50);

        moveButton = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE - 100, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, "move1.png");

        shootButton = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + 300, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, "shoot1.png");

        reloadButton = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + 300, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 100, "reload1.png");

        portrait1 = new Rectangle(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait1.setColor(Color.BLUE);
        character1Picture = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "character1.png");
        portrait1Picture = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, "playerOne.png");
        portrait1Picture.grow(10, 10);
        tokenText1 = new Text(BOARD_X_MARGIN - 270, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 33, "Player 1");
        player1Token = new Picture(BOARD_X_MARGIN + ( SQUARE_SIZE), BOARD_Y_MARGIN + ( SQUARE_SIZE), "playerOne.png");
        player1Available = new Rectangle(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player1Available.setColor(Color.BLACK);
        player1Lives = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "maxLives.png");
        player1Bullets = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait2 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait2.setColor(Color.RED);
        character2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 106, "character1.png");
        tokenText2 = new Text(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + SQUARE_SIZE + 80, BOARD_Y_MARGIN + 68, "Player 2");
        player2Token = new Picture(BOARD_X_MARGIN + ( SQUARE_SIZE), BOARD_Y_MARGIN + ( SQUARE_SIZE), "playerTwo.png");
        player2Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + 100, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player2Available.setColor(Color.BLACK);
        portrait2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + SQUARE_SIZE + 50, BOARD_Y_MARGIN + 120, "playerTwo.png");
        portrait2Picture.grow(10, 10);
        player2Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "maxLives.png");
        player2Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");
        portrait3 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait3.setColor(Color.YELLOW);

        character3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "character1.png");
        tokenText3 = new Text(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + PORTRAIT_SIZE + 50, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 33, "Player 3");
        player3Token = new Picture(0, 0, "playerThree.png");
        player3Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player3Available.setColor(Color.BLACK);
        portrait3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + PORTRAIT_SIZE + 30, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 20, "playerThree.png");
        portrait3Picture.grow(10, 10);
        player3Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "maxLives.png");
        player3Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait4 = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait4.setColor(Color.GREEN);
        character4Picture = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 106, "character1.png");
        tokenText4 = new Text(BOARD_X_MARGIN - 270, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 682, "Player 4");
        player4Token = new Picture(0, 0, "playerFourth.png");
        player4Available = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player4Available.setColor(Color.BLACK);
        portrait4Picture = new Picture(BOARD_X_MARGIN - 295, BOARD_Y_MARGIN + 115, "playerFourth.png");
        portrait4Picture.grow(10, 10);
        player4Lives = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "maxLives.png");
        player4Bullets = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");

        bang = new Picture(BOARD_X_MARGIN + 150, BOARD_Y_MARGIN + 200, "Bang.png");

    }

    //Related with Board
    public void createBoardGFX() {

        for (int i = 0; i < gridGFX.length; i++) {

            for (int j = 0; j < gridGFX[i].length; j++) {

                if (game.getBoard().getGrid()[i][j] != null) {

                    switch (game.getBoard().getGrid()[i][j].getSquareType()) {
                        case BAD_LUCK:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "Bad Card.png");
                            break;
                        case BONUS:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "bonus square.png");
                            break;
                        case COVER:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "coverSquare.png");
                            break;
                        case DEATH:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "death square.png");
                            break;
                        case SLIDE_EAST:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "whiteSquare.png");
                            break;
                        case SLIDE_SOUTH:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "whiteSquare.png");
                            break;
                        case SLIDE_WEST:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "whiteSquare.png");
                            break;
                        case SLIDE_NORTH:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "whiteSquare.png");
                            break;
                        case BATTLE_CYAN:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square 3 (1).png");
                            break;
                        case BATTLE_GREEN:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square 2.png");
                            break;
                        case BATTLE_ORANGE:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square 1.png");
                            break;
                    }
                }
            }
        }
    }

    public void showBoard() {

        for (int i = 0; i < gridGFX.length; i++) {

            for (int j = 0; j < gridGFX[i].length; j++) {

                if (gridGFX[i][j] != null) {
                    gridGFX[i][j].draw();
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
        switch (activePlayer.getPlayerNumber()){
            case 1:
                askRollText.setColor(Color.BLUE);
                break;
            case 2:
                askRollText.setColor(Color.RED);
                break;
            case 3:
                askRollText.setColor(Color.YELLOW);
                break;
            case 4:
                askRollText.setColor(Color.GREEN);
                break;
            default:
                askRollText.setColor(Color.BLUE);
                break;
        }
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
                    tokenText1.setColor(Color.WHITE);
                    tokenText1.grow(40, 15);
                    tokenText1.draw();
                    character1Picture.draw();
                    portrait1Picture.draw();
                    deathSquare.draw();
                    break;
                case 1:
                    portrait2.fill();
                    character2Picture.draw();
                    tokenText2.setColor(Color.WHITE);
                    tokenText2.grow(TOKEN_TEXT_GROW_X, TOKEN_TEXT_GROW_Y);
                    tokenText2.draw();
                    portrait2Picture.draw();
                    break;
                case 2:
                    portrait3.fill();
                    character3Picture.draw();
                    tokenText3.setColor(Color.WHITE);
                    tokenText3.grow(TOKEN_TEXT_GROW_X, TOKEN_TEXT_GROW_Y);
                    tokenText3.draw();
                    portrait3Picture.draw();
                    break;
                case 3:
                    portrait4.fill();
                    tokenText4.setColor(Color.WHITE);
                    tokenText4.grow(TOKEN_TEXT_GROW_X, TOKEN_TEXT_GROW_Y);
                    tokenText4.draw();
                    character4Picture.draw();
                    portrait4Picture.draw();
                    break;
                default:
                    portrait1.fill();
                    character1Picture.draw();
                    portrait1Picture.draw();
                    break;
            }
        }
    }

    public void hidePortrait() {
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
        shootButton.draw();
        moveButton.draw();
    }

    public void showMoveReload() {
        moveReloaded = true;
        moveButton.draw();
        reloadButton.draw();
    }

    public void showInit() {

        keyboardInit();
        background.draw();
        dice.draw();
        card.draw();
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
        switch (typeOfCards) {
            case ADDLIVES:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "addLives.png");
                card.draw();
                break;
            case SKIPTURN:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "skipTurn.png");
                card.draw();
                break;
            case MOVEPLACE:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "rollAgain.png");
                card.draw();
                break;
            case REMOVELIVES:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "youLoseALife.png");
                card.draw();
                break;
            case OUTOFMUNITIONS:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "youOfBullets.png");
                card.draw();
                break;
            case TAKELIVESFROMANOTHERPLAYER:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "takeALifeFromAnotherPlayer.png");
                card.draw();
                break;
            case SWITCHPOSITIONWITHRANDOMPLAYER:
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "switchWithARandomPlayer.png");
                card.draw();
                break;
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Error");
        }
        card.delete();
        card = new Picture(game.getBoard().BOARD_SIZE + BOARD_X_MARGIN + 100, 400, "takeALifeFromAnotherPlayer.png");
        card.draw();
    }

    public void choosePlayer(Player player) {

        playerChoice = true;
        switchPlaces = new Text(game.getBoard().BOARD_SIZE * SQUARE_SIZE + 150, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 150, "Press the number of the player you want to switch with!");
        switchPlaces.setColor(Color.RED);
        switchPlaces.draw();

    }

    public void updateLives(Player player) {

        int numberPlayer = player.getPlayerNumber();
        int currentLives = player.getCurrentLives();

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

        switch (activePlayer.getPlayerNumber()) {
            case 1:
                movesLeftText.setColor(Color.BLUE);
                break;
            case 2:
                movesLeftText.setColor(Color.RED);
                break;
            case 3:
                movesLeftText.setColor(Color.YELLOW);
                break;
            case 4:
                movesLeftText.setColor(Color.GREEN);
                break;
            default:
                movesLeftText.setColor(Color.BLUE);
                break;
        }

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
                player2Token.delete();
                player2Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerTwo.png");
                player2Token.draw();
                break;
            case 3:
                player3Token.delete();
                player3Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerThree.png");
                player3Token.draw();
                break;
            case 4:
                player4Token.delete();
                player4Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerFourth.png");
                player4Token.draw();
                break;
            default:
                player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                player1Token.draw();
                break;
        }
    }

    public void hideButtons() {
        shootButton.delete();
        reloadButton.delete();
        moveButton.delete();
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
                    System.out.println("Shoot that guy");
                    System.out.println(activePlayer);
                    activePlayer.setWillShoot(true);
                    moveShooted = false;
                    hideButtons();
                }
                break;
            case KeyboardEvent.KEY_R:
                if (moveReloaded) {
                    System.out.println("reloading");
                    activePlayer.setWillReload(true);
                    moveReloaded = false;
                    hideButtons();
                }
                break;
            case KeyboardEvent.KEY_M:
                if (moveReloaded || moveShooted) {
                    activePlayer.setWillMove(true);
                    moveShooted = false;
                    moveReloaded = false;
                    hideButtons();
                }
            case KeyboardEvent.KEY_1:
                if (moved) {
                    activePlayer.setHasMovedToA(true);
                    moved = false;
                }
                break;

            case KeyboardEvent.KEY_2:
                if (moved) {
                    activePlayer.setHasMovedToB(true);
                    moved = false;
                }
                break;

            case KeyboardEvent.KEY_3:
                if (playerChoice && activePlayer.getPlayerNumber() != 3 && game.getPlayers()[2] != null) {
                    activePlayer.getGame().switchPos(activePlayer, 3);
                    playerChoice = false;
                }
                break;

            case KeyboardEvent.KEY_4:
                if (playerChoice && activePlayer.getPlayerNumber() != 4 && game.getPlayers()[3] != null) {
                    activePlayer.getGame().switchPos(activePlayer, 4);
                    playerChoice = false;
                }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}