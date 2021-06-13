package westshootout.gameobjects.cards;

import westshootout.gameobjects.Player;
import westshootout.gameobjects.squares.Square;
import westshootout.Game;

public class Card {

    public static void cardEffect(TypeOfCards typeOfCard, Player player) {

        switch (typeOfCard) {

            case ADDLIVES:

                if (player.getCurrentLives() < player.getMaxLives()) {
                    System.out.println("Congratulation, You gain one live");
                    player.setCurrentLives(player.getCurrentLives() + 1);
                    player.getGame().getBoardGFX().updateLives(player);
                }
                break;

            case MOVEPLACE:

                player.prepareMove();
                break;

            case TAKELIVESFROMANOTHERPLAYER:
                //Use method choose Player
                //Take Lives From Another Player is the same as player get Shot
                //With this we will check if the player is dead;
                int randomNumberToChoosePlayer = (int) (Math.floor(Math.random() * player.getGame().getPlayers().length));

                while (player.getGame().getPlayers()[randomNumberToChoosePlayer] == player || player.getGame().getPlayers()[randomNumberToChoosePlayer].isDead()) {
                    randomNumberToChoosePlayer = (int) (Math.floor(Math.random() * player.getGame().getPlayers().length));
                }
                Player playerChosen = player.getGame().getPlayers()[randomNumberToChoosePlayer];
                playerChosen.getShot();
                player.getGame().getBoardGFX().updateLives(playerChosen);

                if (player.getMaxLives() > player.getCurrentLives()) {
                    player.setCurrentLives(player.getCurrentLives() + 1);
                    System.out.println(player.getCurrentLives());
                    player.getGame().getBoardGFX().updateLives(player);
                }
                break;

            case REMOVELIVES:
                //this is the same as the player has been shot
                //this will also check if the player is dead;
                player.getShot();
                break;

            case OUTOFMUNITIONS:
                //The player will be out of munitions
                player.getGun().setRemainingBullets(0);
                player.getGame().getBoardGFX().updateBullets();
                break;

            case SWITCHPOSITIONWITHRANDOMPLAYER:
                //Choose a player random
                //Method to choose a player random
                //The method used shoud be the same as game.choosePlayer.
                //Different here is that we will generate a random number before invoking the method.
                randomNumberToChoosePlayer = (int) (Math.floor(Math.random() * player.getGame().getPlayers().length));

                while (randomNumberToChoosePlayer == player.getPlayerNumber() - 1) {
                    randomNumberToChoosePlayer = (int) (Math.floor(Math.random() * player.getGame().getPlayers().length));
                }

                Square currentPlayerSquare = player.getCurrentSquare();
                playerChosen = player.getGame().getPlayers()[randomNumberToChoosePlayer];
                player.move(playerChosen.getCurrentSquare());
                playerChosen.move(currentPlayerSquare);
                break;

            case SKIPTURN:
                //Skip the turn;
                player.setSkipped(true);
                break;
        }
    }

}
