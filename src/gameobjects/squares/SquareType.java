package gameobjects.squares;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum SquareType {
    BONUS(Color.YELLOW),
    BATTLE_CYAN(Color.CYAN),
    BATTLE_ORANGE(Color.ORANGE),
    BATTLE_GREEN(Color.GREEN),
    BAD_LUCK(Color.RED),
    COVER(Color.LIGHT_GRAY),
    DEATH(Color.BLACK);

    private Color color;

    SquareType(Color color) {
        this.color = color;
    }
}
