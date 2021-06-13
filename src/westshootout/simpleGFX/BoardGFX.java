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
import westshootout.Sound;
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
    private Picture player1Target;
    private Picture player2Target;
    private Picture player3Target;
    private Picture player4Target;
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
    private Text startTurnText;
    private Text bonusText;
    private Text badLuckText;
    private Picture backgroundText;

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

    private KeyboardEvent target1 = new KeyboardEvent();
    private KeyboardEvent target2 = new KeyboardEvent();
    private KeyboardEvent target3 = new KeyboardEvent();
    private KeyboardEvent target4 = new KeyboardEvent();
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
    private boolean target1Valid;
    private boolean target2Valid;
    private boolean target3Valid;
    private boolean target4Valid;
    private boolean aiming;


    //SOUND

    private Sound diceSound;
    private Sound walkSound;
    private Sound shotSound;
    private Sound reloadSound;
    private Sound killSound;
    private Sound cardSound;
    private Sound winnerSound;


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
        player1Target = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "mira.png");
        portrait1Picture = new Picture(BOARD_X_MARGIN - 300, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 15, "playerOne.png");
        portrait1Picture.grow(10, 10);
        tokenText1 = new Text(BOARD_X_MARGIN - 270, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 33, "Player 1");
        player1Token = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE), BOARD_Y_MARGIN + (SQUARE_SIZE), "playerOne.png");
        player1Available = new Rectangle(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player1Available.setColor(Color.BLACK);
        player1Lives = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "3Lives.png");
        player1Bullets = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait2 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait2.setColor(Color.RED);
        character2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 106, "character2.png");
        player2Target = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 106, "mira.png");
        tokenText2 = new Text(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + SQUARE_SIZE + 80, BOARD_Y_MARGIN + 68, "Player 2");
        player2Token = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE), BOARD_Y_MARGIN + (SQUARE_SIZE), "playerTwo.png");
        player2Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + 100, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player2Available.setColor(Color.BLACK);
        portrait2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + SQUARE_SIZE + 50, BOARD_Y_MARGIN + 120, "playerTwo.png");
        portrait2Picture.grow(10, 10);
        player2Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "3Lives.png");
        player2Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");


        portrait3 = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait3.setColor(Color.YELLOW);
        character3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "character3.png");
        player3Target = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "mira.png");
        tokenText3 = new Text(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + PORTRAIT_SIZE + 50, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 33, "Player 3");
        player3Token = new Picture(0, 0, "playerThree.png");
        player3Available = new Rectangle(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        player3Available.setColor(Color.BLACK);
        portrait3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE + PORTRAIT_SIZE + 30, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 20, "playerThree.png");
        portrait3Picture.grow(10, 10);
        player3Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, "3Lives.png");
        player3Bullets = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE - 20, "maxBullets.png");

        portrait4 = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, PORTRAIT_SIZE, PORTRAIT_SIZE);
        portrait4.setColor(Color.GREEN);
        character4Picture = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 106, "character4.png");
        player4Target = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 106, "mira.png");
        tokenText4 = new Text(BOARD_X_MARGIN - 270, game.getBoard().BOARD_SIZE * SQUARE_SIZE - 682, "Player 4");
        player4Token = new Picture(0, 0, "playerFourth.png");
        player4Available = new Rectangle(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 100, SQUARE_SIZE, SQUARE_SIZE);
        player4Available.setColor(Color.BLACK);
        portrait4Picture = new Picture(BOARD_X_MARGIN - 295, BOARD_Y_MARGIN + 115, "playerFourth.png");
        portrait4Picture.grow(10, 10);
        player4Lives = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, "3Lives.png");
        player4Bullets = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN - SQUARE_SIZE + 100 - 20, "maxBullets.png");

        bang = new Picture(BOARD_X_MARGIN + 150, BOARD_Y_MARGIN + 200, "Bang.png");

        backgroundText = new Picture(BOARD_X_MARGIN + 325 - 100, BOARD_Y_MARGIN - 40 - 20, "backgroundMessage.png");
        bonusText = new Text(BOARD_X_MARGIN + 300, BOARD_Y_MARGIN - 40, "Lucky card drawn! What does it do?");
        bonusText.setColor(Color.GREEN);
        bonusText.grow(20, 20);

        badLuckText = new Text(BOARD_X_MARGIN + 300, BOARD_Y_MARGIN - 40, "Bad luck card drawn! Uh oh!");
        badLuckText.setColor(Color.RED);
        badLuckText.grow(20,20);

        diceSound = new Sound("/DiceRoll.wav");
        walkSound = new Sound("/Walk.wav");
        shotSound = new Sound("/Shot.wav");
        reloadSound = new Sound("/Reload.wav");
        killSound = new Sound("/Mataram-me.wav");
        cardSound = new Sound("/card.wav");
        //winnerSound = new Sound("/finished.wav");
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
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "deathSquare.png");
                            break;
                        case SLIDE_EAST:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "slideEAST.png");
                            break;
                        case SLIDE_SOUTH:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "slideSOUTH.png");
                            break;
                        case SLIDE_WEST:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "slideWEST.png");
                            break;
                        case SLIDE_NORTH:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "slideNORTH.png");
                            break;
                        case BATTLE_CYAN:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square3.png");
                            break;
                        case BATTLE_GREEN:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square2.png");
                            break;
                        case BATTLE_ORANGE:
                            gridGFX[i][j] = new Picture(BOARD_X_MARGIN + (SQUARE_SIZE * i), BOARD_Y_MARGIN + (SQUARE_SIZE * j), "square1.png");
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

        dice.delete();

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

        askRollText = new Text(BOARD_X_MARGIN + 315, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + " , roll your bones, cowboy!");
        askRollText.grow(20, 20);

        switch (activePlayer.getPlayerNumber()) {
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

    // Show Players Portait; This method depends on the numbers of players selected
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
                    //deathSquare.draw();
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
                    player1Available.delete();
                    tokenText1.delete();
                    player1Token.delete();
                    character1Picture.delete();
                    portrait1Picture.delete();
                    player1Lives.delete();
                    player1Bullets.delete();
                    break;
                case 1:
                    portrait2.delete();
                    player2Available.delete();
                    tokenText2.delete();
                    player2Token.delete();
                    character2Picture.delete();
                    portrait2Picture.delete();
                    player2Lives.delete();
                    player2Bullets.delete();
                    break;
                case 2:
                    portrait3.delete();
                    player3Available.delete();
                    tokenText3.delete();
                    player3Token.delete();
                    character3Picture.delete();
                    portrait3Picture.delete();
                    player3Lives.delete();
                    player3Bullets.delete();
                    break;
                case 3:
                    portrait4.delete();
                    player4Available.delete();
                    tokenText4.delete();
                    player4Token.delete();
                    character4Picture.delete();
                    portrait4Picture.delete();
                    player4Lives.delete();
                    player4Bullets.delete();
                    break;
                default:
                    portrait1.delete();
                    tokenText1.delete();
                    player1Token.delete();
                    character1Picture.delete();
                    portrait1Picture.delete();
                    player1Lives.delete();
                    player1Bullets.delete();
                    break;
            }
        }
    }

    // Changes character image to a tombstone
    public void buryPlayer(Player player) {

        switch (player.getPlayerNumber()) {

            case 1:
                character1Picture.delete();
                character1Picture = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "tombstone.png");
                character1Picture.draw();
                player1Token.delete();
                break;
            case 2:
                character2Picture.delete();
                character2Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN + 106, "tombstone.png");
                character2Picture.draw();
                player2Token.delete();
                break;
            case 3:
                character3Picture.delete();
                character3Picture = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE + 6, "tombstone.png");
                character3Picture.draw();
                player3Token.delete();
                break;
            case 4:
                character4Picture.delete();
                character4Picture = new Picture(BOARD_X_MARGIN - 200, BOARD_Y_MARGIN + 106, "tombstone.png");
                character4Picture.draw();
                player4Token.delete();
                break;
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

        startTurnText = new Text(BOARD_X_MARGIN + 280, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + "'s turn! Draw n' shoot or get movin' pardner!");
        startTurnText.grow(20, 20);
        startTurnText.draw();

        switch (activePlayer.getPlayerNumber()) {
            case 1:
                startTurnText.setColor(Color.BLUE);
                break;
            case 2:
                startTurnText.setColor(Color.RED);
                break;
            case 3:
                startTurnText.setColor(Color.YELLOW);
                break;
            case 4:
                startTurnText.setColor(Color.GREEN);
                break;
            default:
                startTurnText.setColor(Color.BLUE);
                break;
        }
    }

    public void showMoveReload() {
        moveReloaded = true;
        moveButton.draw();
        reloadButton.draw();

        startTurnText = new Text(BOARD_X_MARGIN + 300, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + "'s turn! Load up or high-tail it pardner!");
        startTurnText.grow(20, 20);
        startTurnText.draw();

        switch (activePlayer.getPlayerNumber()) {
            case 1:
                startTurnText.setColor(Color.BLUE);
                break;
            case 2:
                startTurnText.setColor(Color.RED);
                break;
            case 3:
                startTurnText.setColor(Color.YELLOW);
                break;
            case 4:
                startTurnText.setColor(Color.GREEN);
                break;
            default:
                startTurnText.setColor(Color.BLUE);
                break;
        }
    }

    public void showInit() {

        keyboardInit();
        background.draw();
        backgroundText.draw();
        showDice();
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

            if (availableTarget[i] != null) {

                System.out.println(availableTarget[i].getPlayerNumber());

                switch (availableTarget[i].getPlayerNumber()) {

                    case 1:
                        player1Target.draw();
                        target1Valid = true;
                        break;
                    case 2:
                        player2Target.draw();
                        target2Valid = true;
                        break;
                    case 3:
                        player3Target.draw();
                        target3Valid = true;
                        break;
                    case 4:
                        player4Target.draw();
                        target4Valid = true;
                        break;
                    default:
                        player1Available.draw();
                        break;
                }

                aiming = true;
            }
            else {
                System.out.println("null");
            }
        }
    }

    public void showCard(TypeOfCards typeOfCards) {

        switch (typeOfCards) {

            case ADDLIVES:

                bonusText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "addLives.png");
                card.draw();
                break;

            case SKIPTURN:

                badLuckText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "youDontPlayNextRound.png");
                card.draw();
                break;

            case MOVEPLACE:

                bonusText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "rollAgain.png");
                card.draw();
                break;

            case REMOVELIVES:

                badLuckText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "youLoseALife.png");
                card.draw();
                break;

            case OUTOFMUNITIONS:

                badLuckText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "youOfBullets.png");
                card.draw();
                break;

            case TAKELIVESFROMANOTHERPLAYER:

                bonusText.draw();
                card.delete();
                card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "takeALifeFromAnotherPlayer.png");
                card.draw();
                break;

            case SWITCHPOSITIONWITHRANDOMPLAYER:

                badLuckText.draw();
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
        bonusText.delete();
        badLuckText.delete();
        card.delete();
        card = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + 70, 350, "pickACard.png");
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
                        System.out.println(i+"Lives.png");
                        player1Lives.delete();
                        player1Lives = new Picture(BOARD_X_MARGIN - 200, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, i + "Lives.png");
                        player1Lives.draw();
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        System.out.println(i+"Lives.png");
                        player2Lives.delete();
                        player2Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, BOARD_Y_MARGIN - SQUARE_SIZE + 100 + 25, i + "Lives.png");
                        player2Lives.draw();
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        System.out.println(i+"Lives.png");
                        player3Lives.delete();
                        player3Lives = new Picture(game.getBoard().BOARD_SIZE * SQUARE_SIZE + BOARD_X_MARGIN + PORTRAIT_SIZE, game.getBoard().BOARD_SIZE * SQUARE_SIZE - SQUARE_SIZE + 25, i + "Lives.png");
                        player3Lives.draw();
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (currentLives == i) {
                        System.out.println(i+"Lives.png");
                        player4Lives.delete();
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

        movesLeftText = new Text(BOARD_X_MARGIN + 325, BOARD_Y_MARGIN - 40, "Player " + activePlayer.getPlayerNumber() + " , you have " + movesLeft + " moves left!");
        movesLeftText.grow(20, 20);

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
                    player1Token.delete();
                    player1Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerOne.png");
                    player1Token.draw();
                    break;
                case 1:
                    player2Token.delete();
                    player2Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerTwo.png");
                    player2Token.draw();
                    break;
                case 2:
                    player3Token.delete();
                    player3Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerThree.png");
                    player3Token.draw();
                    break;
                case 3:
                    player4Token.delete();
                    player4Token = new Picture(BOARD_X_MARGIN + (xTokenPosition * SQUARE_SIZE), BOARD_Y_MARGIN + (yTokenPosition * SQUARE_SIZE), "playerFourth.png");
                    player4Token.draw();
                    break;
                default:
                    player1Token.delete();
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

    public void hideButtonsAndMessage() {
        shootButton.delete();
        reloadButton.delete();
        moveButton.delete();
        startTurnText.delete();
    }

    public void hideTargets() {
        player1Target.delete();
        player2Target.delete();
        player3Target.delete();
        player4Target.delete();
    }

    public void hideAll() {

        hideAskRoll();
        hideBoard();
        hideMovesLeft();
        hidePortrait();
        hideDice();
        movesLeftText.delete();
        startTurnText.delete();
        background.delete();
        card.delete();
        backgroundText.delete();

    }

    public Sound getShotSound() {
        return shotSound;
    }

    public Sound getKillSound() {
        return killSound;
    }

    public Sound getCardSound(){
        return cardSound;
    }

    public Sound getWinnerSound(){
        return winnerSound;
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

        target1.setKey(KeyboardEvent.KEY_1);
        target1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(target1);

        target2.setKey(KeyboardEvent.KEY_2);
        target2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(target2);

        target3.setKey(KeyboardEvent.KEY_3);
        target3.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(target3);

        target4.setKey(KeyboardEvent.KEY_4);
        target4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(target4);

        moveA.setKey(KeyboardEvent.KEY_1);
        moveA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveA);

        moveB.setKey(KeyboardEvent.KEY_2);
        moveB.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveB);
    }

    public void keyboardRemove() {

        keyboard.removeEventListener(roll);
        keyboard.removeEventListener(shoot);
        keyboard.removeEventListener(reload);
        keyboard.removeEventListener(move);
        keyboard.removeEventListener(target1);
        keyboard.removeEventListener(target2);
        keyboard.removeEventListener(target3);
        keyboard.removeEventListener(target4);
        keyboard.removeEventListener(moveA);
        keyboard.removeEventListener(moveB);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:

                if (rolled) {
                    diceSound.play(true);
                    activePlayer.setHasRolled(true);
                    rolled = false;
                    hideAskRoll();
                }
                break;

            case KeyboardEvent.KEY_S:

                if (moveShooted) {

                    shotSound.play(true);
                    System.out.println("Shoot that guy");
                    System.out.println(activePlayer);
                    activePlayer.setWillShoot(true);
                    moveShooted = false;
                    hideButtonsAndMessage();
                }
                break;

            case KeyboardEvent.KEY_R:

                if (moveReloaded) {

                    reloadSound.play(true);
                    System.out.println("reloading");
                    activePlayer.setWillReload(true);
                    moveReloaded = false;
                    hideButtonsAndMessage();
                }
                break;

            case KeyboardEvent.KEY_M:

                if (moveReloaded || moveShooted) {

                    activePlayer.setWillMove(true);
                    moveShooted = false;
                    moveReloaded = false;
                    hideButtonsAndMessage();
                }
                break;

            case KeyboardEvent.KEY_1:

                if (moved) {

                    walkSound.play(true);
                    activePlayer.setHasMovedToA(true);
                    moved = false;
                    break;
                }

                if (aiming && target1Valid) {

                    activePlayer.setTarget(1);
                    aiming = false;
                    target1Valid = false;
                    target2Valid = false;
                    target3Valid = false;
                    target4Valid = false;
                    hideTargets();
                    break;
                }

            case KeyboardEvent.KEY_2:

                if (moved) {

                    walkSound.play(true);
                    activePlayer.setHasMovedToB(true);
                    moved = false;
                    break;
                }

                if (aiming && target2Valid) {

                    activePlayer.setTarget(2);
                    aiming = false;
                    target1Valid = false;
                    target2Valid = false;
                    target3Valid = false;
                    target4Valid = false;
                    hideTargets();
                    break;
                }

            case KeyboardEvent.KEY_3:

                if (aiming && target3Valid) {

                    activePlayer.setTarget(3);
                    aiming = false;
                    target1Valid = false;
                    target2Valid = false;
                    target3Valid = false;
                    target4Valid = false;
                    hideTargets();
                }
                break;

            case KeyboardEvent.KEY_4:

                if (aiming && target4Valid) {

                    activePlayer.setTarget(4);
                    aiming = false;
                    target1Valid = false;
                    target2Valid = false;
                    target3Valid = false;
                    target4Valid = false;
                    hideTargets();
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
