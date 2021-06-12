package westshootout.gameobjects.cards;

import westshootout.gameobjects.Player;
import westshootout.gameobjects.squares.Square;
import westshootout.Game;

public class Card {

    public static void cardEffect(TypeOfCards typeOfCard, Player player){

        switch (typeOfCard) {

            case ADDLIVES:

                if (player.getCurrentLives() == player.getMaxLives()) {

                    int randomNumberToAddExtraMaxLives = (int) (Math.random() * 2);

                    switch (randomNumberToAddExtraMaxLives) {

                        case 0:
                            player.setMaxLives(player.getMaxLives() + 1);
                            //Since we will add Max Lives we also need to update the current Lives;
                            player.setCurrentLives(player.getMaxLives());
                            System.out.println("Lucky Bastord, now it is more difficult to kill you!!");
                            break;

                        case 1:
                            System.out.println("Sorry Cowboy, you have all the lives");
                            break;

                    }
                }

                else {

                    System.out.println("Congratulation, You gain one live");
                    player.setCurrentLives(player.getCurrentLives() + 1);
                }
                break;

            case MOVEPLACE:

                int numberOfMoves = (int) (Math.random() * 6) + 1;
                //When the player reach the last card, he do not have more moves
                //Player.movesLeft = 0; so we will set Moves Left with random number;
                player.setMovesLeft(numberOfMoves);
                player.move();
                break;

/*            case SWITCHPOSITION:

                //Switch position with Player choose
                // Method choosePlayer will return Player;
                //This method should have a parameter int number, so the player can choose
                //his new position according to the player chosen (given by the number);
                Square currentPlayerSquare = player.getCurrentSquare();
                Player playerChosen = game.choosePlayer(number);
                //Keep player Square in order to give to player Chose that same Square
                player.move(playerChosen.getCurrentSquare());
                playerChosen.move(currentPlayerSquare);
                break;*/

/*            case TAKELIVESFROMANOTHERPLAYER:
                //Use method choose Player
                //Take Lives From Another Player is the same as player get Shot
                //With this we will check if the player is dead;
                playerChosen = game.choosePlayer(number);
                playerChosen.getShot();
                break;*/
            case REMOVELIVES:
                //this is the same as the player has been shot
                //this will also check if the player is dead;
                player.getShot();
                break;
            case OUTOFMUNITIONS:
                //The player will be out of munitions
                player.getGun().setRemainingBullets(0);
                break;
/*            case SWITCHPOSITIONWITHRANDOMPLAYER:
                //Choose a player random
                //Method to choose a player random
                //The method used shoud be the same as game.choosePlayer.
                //Different here is that we will generate a random number before invoking the method.
                int randomNumberToChoosePlayer = (int) ((Math.random() * 4) + 1);
                currentPlayerSquare = player.getCurrentSquare();
                playerChosen = game.choosePlayer(randomNumberToChoosePlayer);
                player.move(playerChosen.getCurrentSquare());
                playerChosen.move(currentPlayerSquare);
                break;*/
            case SKIPTURN:
                //Skip the turn;
                player.setSkipped(true);
                break;
        }
    }

}
