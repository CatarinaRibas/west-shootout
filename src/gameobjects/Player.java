package gameobjects;

public class Player {

    // Unique object ID. Can be 1, 2, 3 or 4 depending on how many players there are.
    private int playerNumber;

    // Used to display winner at game finish.
    private boolean isWinner;

    // Every cowboy has his own gun, they don't share.
    private Gun gun;

    // START_LIVES is max lives at game start, maxLives is mutable as it can be changed through cards obtained.
    private static final int START_LIVES = 3;
    private int maxLives;
    private int currentLives;

    // Flagged up by checkDead(), prevents player turn from happening.
    // Game ends when only one player is left not dead.
    private boolean isDead;

    public Player(int playerNumber) {

        this.playerNumber = playerNumber;

        this.isWinner = false;

        this.gun = ObjectFactory.createGun();

        this.maxLives = START_LIVES;
        this.currentLives = this.maxLives;

        this.isDead = false;
    }

    //Returns false if currentLives has not reached 0. True otherwise.
    //Cannot go under 0, since checkDead() is invoked every time that currentLives is decremented.
    public boolean checkDead() {

        if (currentLives == 0) {
            isDead = true;
            return true;
        }

        return false;
    }

    // Sets player as winner when game detects all other players' isDead flags have been raised.
    public void win() {
        isWinner = true;
    }

    //Getter isWinner
    public boolean isWinner() {
        return isWinner;
    }

    // Asks gun to shoot a specified opponent. Politely.
    // Gun checks for bullets left as a precaution, but fireAt() should not be invokeable if gun is out of ammo.
    // Game logic should permit calls to this method only if they would return true. (= option to shoot should be "greyed out" if the gun is empty)
    // Square logic should condition valid players to shoot (only others in same color of Battle Square).
    public boolean fireAt(Player player) {
        return gun.shoot(player);
    }

    //Handles getting shot by another player. Removes one life and checks if there are any remaining lives with checkdead().
    public void getShot() {
        currentLives--;
        checkDead();
    }

    // Tells gun to reload, firmly.
    // Game logic should only allow this option to be invoked if there are no bullets left in gun. Boolean return is simply for debugging.
    // reload() should only be allowed to return true. Situations where it would return false should not happen as per game logic conditions.
    public boolean reload() {
        return gun.reload();
    }

}
