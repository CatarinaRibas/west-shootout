package westshootout.gameobjects;

public class Gun {

    // Reason why there is a maxBullets property distinct from START_MAX_BULLETS explained in setMaxBullets comment.
    private static final int START_MAX_BULLETS = 3;
    private int maxBullets;
    // Is iterated down from maxBullets on shoot(), restablished to maxBullets on reload().
    private int remainingBullets;

    public Gun() {
        this.maxBullets = START_MAX_BULLETS;
        this.remainingBullets = maxBullets;
    }

    // Useable in player's turn, through player class. Method uses up your game turn to spend one bullet to remove one of the opponent's lives.
    // Only works if there are bullets left. Returns true if shoot went well, returns false if there was no ammo left.
    public boolean shoot(Player player) {
        if (!bulletsLeft()) {
            return false;
        }
        player.getShot();
        remainingBullets--;
        return true;
    }

    // Useable in player's turn, through player class. Method uses up your game turn to set your ammo back at full capacity.
    // Only works if gun is fully empty. Returns true if reload worked, false otherwise.
    public boolean reload() {
        if (!bulletsLeft()) {
            remainingBullets = maxBullets;
            return true;
        }
        return false;
    }

    // Checks if there are any remaining bullets. If gun is empty return false, otherwise return true.
    public boolean bulletsLeft() {
        if (remainingBullets == 0) {
            return false;
        }
        return true;
    }

    // Getter for bullets left in gun (useful for displaying ammo left on GUI).
    public int getRemainingBullets() {
        return remainingBullets;
    }

    // Setter for bullets left in gun (useful for bad luck or bonus cards, which might deplete your ammo or replenish it)
    public void setRemainingBullets(int remainingBullets) {
        this.remainingBullets = remainingBullets;
    }

    // Getter for max bullets. Useful for cards that might for example top up your gun on bullets without reloading.
    public int getMaxBullets() {
        return maxBullets;
    }

    // Setter for max bullets.
    // Exists because one bonus card could raise your max amount of bullets. This is also the reason why maxBullets is not a constant.
    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }
}
