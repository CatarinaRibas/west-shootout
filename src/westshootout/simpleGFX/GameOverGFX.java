package westshootout.simpleGFX;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import westshootout.Game;
import westshootout.Sound;

public class GameOverGFX implements KeyboardHandler {

    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent numberOnePressed = new KeyboardEvent();
    private KeyboardEvent downPressed = new KeyboardEvent();
    private KeyboardEvent upPressed = new KeyboardEvent();


    private final static int ARROWPOSITION1 = 690;
    private final static int ARROWPOSITION2 = 820;
    private final static int ARROWPADDING = 1050;
    private int arrowPosition;
    private int counter = 1;
    private Game game;

    private final static int PADDING = 10;

    private Picture player1Won = new Picture(PADDING, PADDING, "player1won.png");
    private Picture player2Won = new Picture(PADDING, PADDING, "player3won.png");
    private Picture player3Won = new Picture(PADDING, PADDING, "player2won.png");
    private Picture player4Won = new Picture(PADDING, PADDING, "player4won.png");
    private Picture arrow = new Picture(ARROWPADDING,ARROWPOSITION1, "arrow.png");

    //private Sound winnerSound;

    public void setWinner(int playerNumber) {


        switch (playerNumber) {
            case 1:
                player1Won.draw();
                this.arrow.draw();
                //winnerSound = new Sound("/finished.wav");
                //winnerSound.play(true);
                keyboardInit();
                break;
            case 2:
                player2Won.draw();
                this.arrow.draw();
                //winnerSound = new Sound("/finished.wav");
                //winnerSound.play(true);
                keyboardInit();
                break;
            case 3:
                player3Won.draw();
                this.arrow.draw();
                //winnerSound = new Sound("/finished.wav");
                //winnerSound.play(true);
                keyboardInit();
                break;
            case 4:
                player4Won.draw();
                this.arrow.draw();
                //winnerSound = new Sound("/finished.wav");
                //winnerSound.play(true);
                keyboardInit();
                break;
            default:
                player1Won.draw();
                this.arrow.draw();
                //winnerSound = new Sound("/finished.wav");
                //winnerSound.play(true);
                keyboardInit();
                break;
        }



    }

    public void hideAllPlayer(){
        player1Won.delete();
        player2Won.delete();
        player3Won.delete();
        player4Won.delete();
    }

    public void keyboardInit() {
        numberOnePressed.setKey(KeyboardEvent.KEY_1);
        numberOnePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(numberOnePressed);
        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(downPressed);
    }

    public void keyboardRemoveGameOverMenu() {

        keyboard.removeEventListener(numberOnePressed);
        keyboard.removeEventListener(upPressed);
        keyboard.removeEventListener(downPressed);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_1:
                switch (arrowPosition) {
                    case ARROWPOSITION1:
                        this.arrow.delete();
                        hideAllPlayer();
                        this.game = new Game();
                        this.game.mainMenu();
                        keyboardRemoveGameOverMenu();
                        break;
                    case ARROWPOSITION2:
                        System.out.println("Key pressed");
                        System.exit(0);
                        break;
                }
                break;

            case KeyboardEvent.KEY_UP:

                if (counter == 1) {
                    counter = 0;
                } else {
                    counter++;
                }

                switch (counter) {
                    case 0:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION2, "arrow.png");
                        this.arrow.draw();
                        break;
                    case 1:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION1, "arrow.png");
                        this.arrow.draw();
                        break;
                }
                break;
            case KeyboardEvent.KEY_DOWN:

                if (counter == 0) {
                    counter = 1;
                } else {
                    counter--;
                }

                switch (counter) {

                    case 1:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION1, "arrow.png");
                        this.arrow.draw();
                        System.out.println("going down");
                        break;
                    case 0:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION2, "arrow.png");
                        this.arrow.draw();
                        break;
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}