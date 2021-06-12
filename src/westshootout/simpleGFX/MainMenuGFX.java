package westshootout.simpleGFX;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import westshootout.Game;

public class MainMenuGFX implements KeyboardHandler {

    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent numberOnePressed = new KeyboardEvent();
    private KeyboardEvent spacePressed = new KeyboardEvent();
    private KeyboardEvent downPressed = new KeyboardEvent();
    private KeyboardEvent upPressed = new KeyboardEvent();

    Rectangle rectangle1 = new Rectangle(400, 400, 200, 200);
    Rectangle rectangle2 = new Rectangle(150, 400, 200, 200);
    Rectangle rectangle3 = new Rectangle(400, 150, 200, 200);
    Rectangle rectangle4 = new Rectangle(150, 150, 200, 200);


    private int counter = 2;

    private int arrowPosition;


    private Game game;
    private Picture menu;
    private Picture playersNumber;
    private Picture instructions;
    private Picture boardGame;
    private Picture finished;

    private Picture arrow;
    private final static int ARROWPOSITION1 = 485;
    private final static int ARROWPOSITION2 = 658;
    private final static int ARROWPOSITION3 = 835;
    private final static int ARROWPADDING = 25;

    private boolean isOntheMenu = true;


    private final static int PADDING = 10;

    public MainMenuGFX(Game game) {
        this.game = game;
        this.arrowPosition = ARROWPOSITION1;
        this.arrow = new Picture(ARROWPADDING, ARROWPOSITION1, "arrow.png");
        this.instructions = new Picture(PADDING, PADDING, "instructions.png");
        this.menu = new Picture(PADDING, PADDING, "menu.png");
        this.playersNumber = new Picture(PADDING, PADDING, "playersNumber.png");
        //this.boardGame = new Picture(PADDING, PADDING, "boardGame.png");
        //this.finished = new Picture(PADDING, PADDING, "finished.png");
        keyboardInit();

    }

    public void show() {
        showMainMenu();
    }


    public void showMainMenu() {
        this.isOntheMenu = true;
        this.rectangle1.delete();
        this.rectangle2.delete();
        this.rectangle3.delete();
        this.rectangle4.delete();
        this.arrow.delete();
        //this.boardGame.delete();
        this.playersNumber.delete();
        this.instructions.delete();
        this.menu.draw();
        this.arrow.draw();
/*        keyboardRemovePlayersNumber();
        keyboardInit();*/
    }

    public void showInstructions() {
        this.arrow.delete();
        this.menu.delete();
        this.instructions.draw();
/*        keyboardRemoveMainMenu();
        keyboardInit();*/
    }

    public void showPlayersNumber() {
        this.isOntheMenu = false;
        this.arrow.delete();
        this.menu.delete();
        this.playersNumber.draw();
        this.arrow.draw();
    }

    public void hideMenus() {
        this.arrow.delete();
        this.menu.delete();
        this.instructions.delete();
        this.playersNumber.delete();
    }


    public void showFinished() {
        this.finished.draw();
    }

    public void keyboardInit() {

        numberOnePressed.setKey(KeyboardEvent.KEY_1);
        numberOnePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        keyboard.addEventListener(numberOnePressed);
        keyboard.addEventListener(spacePressed);
        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(downPressed);
    }

    public void keyboardRemovePlayersNumber() {

        keyboard.removeEventListener(numberOnePressed);
        keyboard.removeEventListener(upPressed);
        keyboard.removeEventListener(downPressed);

    }

    public void keyboardRemoveMainMenu() {

        keyboard.removeEventListener(numberOnePressed);
        keyboard.removeEventListener(upPressed);
        keyboard.removeEventListener(downPressed);
        keyboard.removeEventListener(spacePressed);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_1:
                if (!this.isOntheMenu) {
                    switch (arrowPosition) {
                        case ARROWPOSITION1:
                            hideMenus();
                            keyboardRemoveMainMenu();
                            game.setPlayers(2);
/*                            rectangle1.setColor(Color.RED);
                            rectangle1.fill();
                            rectangle2.setColor(Color.BLUE);
                            rectangle2.fill();*/
                            break;
                        case ARROWPOSITION2:
                            hideMenus();
                            keyboardRemoveMainMenu();
                            game.setPlayers(3);
/*                            rectangle1.setColor(Color.RED);
                            rectangle1.fill();
                            rectangle2.setColor(Color.BLUE);
                            rectangle2.fill();
                            rectangle3.setColor(Color.GREEN);
                            rectangle3.fill();*/
                            break;
                        case ARROWPOSITION3:
                            hideMenus();
                            keyboardRemoveMainMenu();
                            game.setPlayers(4);
/*                            rectangle1.setColor(Color.RED);
                            rectangle1.fill();
                            rectangle2.setColor(Color.BLUE);
                            rectangle2.fill();
                            rectangle3.setColor(Color.GREEN);
                            rectangle3.fill();
                            rectangle4.setColor(Color.YELLOW);
                            rectangle4.fill();*/
                            break;

                    }
                    break;
                } else {
                    switch (arrowPosition) {
                        case ARROWPOSITION1:
                            showPlayersNumber();
                            break;
                        case ARROWPOSITION2:
                            showInstructions();
                            break;
                        case ARROWPOSITION3:
                            System.out.println("Key pressed");
                            System.exit(0);
                            break;

                    }
                    break;
                }
            case KeyboardEvent.KEY_UP:

                if (counter == 2) {
                    counter = 0;
                } else {
                    counter++;
                }

                switch (counter) {
                    case 0:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION3, "arrow.png");
                        this.arrow.draw();
                        break;
                    case 1:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION2, "arrow.png");
                        this.arrow.draw();
                        break;
                    case 2:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION1, "arrow.png");
                        this.arrow.draw();
                        break;
                }
                break;
            case KeyboardEvent.KEY_DOWN:

                if (counter == 0) {
                    counter = 2;
                } else {
                    counter--;
                }

                switch (counter) {

                    case 2:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION1, "arrow.png");
                        this.arrow.draw();
                        break;
                    case 1:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION2, "arrow.png");
                        this.arrow.draw();
                        break;
                    case 0:
                        this.arrow.delete();
                        this.arrow = new Picture(ARROWPADDING, this.arrowPosition = ARROWPOSITION3, "arrow.png");
                        this.arrow.draw();
                        break;
                }
                break;

            case KeyboardEvent.KEY_SPACE:
                showMainMenu();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
