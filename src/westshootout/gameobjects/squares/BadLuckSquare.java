package westshootout.gameobjects.squares;

import westshootout.gameobjects.Player;
import westshootout.gameobjects.cards.Card;
import westshootout.gameobjects.cards.TypeOfCards;

public class BadLuckSquare extends Square {

    private SquareType squareType;

    public BadLuckSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.BAD_LUCK);
    }

    public boolean effect(Player player) {

        TypeOfCards badCard = TypeOfCards.values()[TypeOfCards.values().length - ((int) (Math.random() * 4)) - 1];
        player.getGame().getBoardGFX().getCardSound().play(true);
        player.getGame().getBoardGFX().showCard(badCard);
        Card.cardEffect(badCard, player);
        return true;

    }
}
