package westshootout.simpleGFX;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class GameOverGFX implements KeyboardHandler {

    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent numberOnePressed = new KeyboardEvent();
    private KeyboardEvent downPressed = new KeyboardEvent();
    private KeyboardEvent upPressed = new KeyboardEvent();

    private Picture arrow;
    private final static int ARROWPOSITION1 = 485;
    private final static int ARROWPOSITION2 = 658;
    private int arrowPosition;

    private final static int PADDING = 10;
    private int picturePositionX = 450;
    private int picturePositionY = 600;
    private MainMenuGFX mainMenuGFX;

    private Picture player1Won = new Picture(picturePositionX, picturePositionY, "playersNumber1.png");
    private Picture player2Won = new Picture(picturePositionX, picturePositionY, "playersNumber 2.png");
    private Picture player3Won = new Picture(picturePositionX, picturePositionY, "playersNumber 3.png");
    private Picture player4Won = new Picture(picturePositionX, picturePositionY, "playersNumber 4.png");
    private Picture backGround = new Picture(PADDING, PADDING, "finished.png");


    public void setWinner(int playerNumber) {

        switch (playerNumber) {
            case 1:
                backGround.draw();
                player1Won.draw();
                break;
            case 2:
                backGround.draw();
                player2Won.draw();
                break;
            case 3:
                backGround.draw();
                player3Won.draw();
                break;
            case 4:
                backGround.draw();
                player4Won.draw();
                break;
            default:
                backGround.draw();
                player1Won.draw();
                break;
        }

    }

    public void keyboardInit() {
        numberOnePressed.setKey(KeyboardEvent.KEY_1);
        numberOnePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

    }

    public void keyboardRemove() {

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
                        mainMenuGFX.showMainMenu();
                        break;
                    case ARROWPOSITION2:
                        System.out.println("Key pressed");
                        System.exit(0);
                        break;

                }
                break;

            case KeyboardEvent.KEY_DOWN:
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}