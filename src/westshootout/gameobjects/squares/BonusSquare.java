package westshootout.gameobjects.squares;

import westshootout.gameobjects.Player;
import westshootout.gameobjects.cards.Card;
import westshootout.gameobjects.cards.TypeOfCards;

public class BonusSquare extends Square {


    public BonusSquare(int xPos, int yPos) {
        super(xPos, yPos, SquareType.BONUS);
    }

    public boolean effect(Player player) {

        TypeOfCards bonusCard = TypeOfCards.values()[(int) (Math.ceil(Math.random() * 3) - 1)];
        player.getGame().getBoardGFX().showCard(bonusCard);
        Card.cardEffect(bonusCard, player);
        return true;

    }
}
