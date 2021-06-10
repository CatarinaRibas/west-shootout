package westshootout.gameobjects;

import westshootout.gameobjects.squares.Square;
import westshootout.Game;

public class Player {

    // Unique object ID. Can be 1, 2, 3 or 4 depending on how many players there are.
    private int playerNumber;
    // START_LIVES is max lives at game start, maxLives is mutable as it can be changed through cards obtained.
    private static final int START_LIVES = 3;
    private int maxLives;
    private int currentLives;
    private int movesLeft;

    // Every cowboy has his own gun, they don't share.
    private Gun gun;

    private Square currentSquare;

    private Game game;

    //// FLAGS
    // Flagged up by checkDead(), prevents player turn from happening.
    // Game ends when only one player is left not dead.
    private boolean isDead;
    // Used to display winner at game finish.
    private boolean isWinner;
    private boolean canShoot;
    private boolean canReload;
    private boolean willShoot;
    private boolean willMove;
    private boolean willReload;
    private boolean hasRolled;
    private boolean hasMovedToA;
    private boolean hasMovedToB;
    private boolean targetA;
    private boolean targetB;
    private boolean targetC;
    // -----

    public Player(int playerNumber, Game game) {

        this.playerNumber = playerNumber;

        this.gun = ObjectFactory.createGun();

        this.game = game;

        this.maxLives = START_LIVES;
        this.currentLives = this.maxLives;

        this.isDead = false;
        this.isWinner = false;
        this.willShoot = false;
        this.willMove = false;
        this.willReload = false;
        this.hasRolled = false;
        this.hasMovedToA = false;
        this.hasMovedToB = false;

        this.movesLeft = 0;
    }

    public void chooseAction() {

        this.willShoot = false;
        this.willMove = false;
        this.willReload = false;
        this.canReload = false;

        if (!gun.bulletsLeft()) {
            this.canShoot = false;
            this.canReload = true;
        }

        if (canShoot) {

            this.game.getBoardGFX().showMoveShoot();

            while (!this.willShoot || !this.willMove) {
            }

            if (this.willMove) {

                prepareMove();
                return;
            }

            if (this.willShoot) {

                chooseTarget();
                return;

            }
        }

        if (canReload) {

            this.game.getBoardGFX().showMoveReload();

            while (!this.willMove || !this.willReload) {
            }

            if (this.willMove) {

                prepareMove();
                return;
            }

            if (this.willReload) {

                gun.reload();
                game.getBoardGFX().updateBullets();
                return;
            }
        }

        prepareMove();
        return;
    }

    public boolean chooseTarget() {

        Player[] availableTargets = availableTargets();

        if (availableTargets[0].equals(null) && availableTargets[1].equals(null) && availableTargets[2].equals(null)) {
            gun.setRemainingBullets(gun.getRemainingBullets() - 1);
            game.getBoardGFX().updateBullets();
            game.getBoardGFX().showBang();
            return true;
        }

        if (!availableTargets[0].equals(null) && availableTargets[1].equals(null) && availableTargets[2].equals(null)) {
            fireAt(availableTargets[0]);
            return true;
        }

        if (availableTargets[0].equals(null) && !availableTargets[1].equals(null) && availableTargets[2].equals(null)) {
            fireAt(availableTargets[1]);
            return true;
        }

        if (availableTargets[0].equals(null) && availableTargets[1].equals(null) && !availableTargets[2].equals(null)) {
            fireAt(availableTargets[2]);
            return true;
        }

        if (!availableTargets[0].equals(null) && !availableTargets[1].equals(null) && availableTargets[2].equals(null)) {
            chooseFromTwo(availableTargets[0], availableTargets[1]);
            return true;
        }

        if (availableTargets[0].equals(null) && !availableTargets[1].equals(null) && !availableTargets[2].equals(null)) {
            chooseFromTwo(availableTargets[1], availableTargets[2]);
            return true;
        }

        if (!availableTargets[0].equals(null) && availableTargets[1].equals(null) && !availableTargets[2].equals(null)) {
            chooseFromTwo(availableTargets[0], availableTargets[2]);
            return true;
        }

        if (!availableTargets[0].equals(null) && !availableTargets[1].equals(null) && !availableTargets[2].equals(null)) {
            chooseFromThree(availableTargets[0], availableTargets[1], availableTargets[2]);
            return true;
        }
        return false;
    }

    public void chooseFromTwo(Player targetA, Player targetB) {

        this.targetA = false;
        this.targetB = false;

        game.getBoardGFX().showTargets();

        while (!this.targetA || !this.targetB) {
        }

        if (this.targetA) {
            fireAt(targetA);
            return;
        }

        if (this.targetB) {
            fireAt(targetB);
            return;
        }
    }

    public void chooseFromThree(Player targetA, Player targetB, Player targetC) {

        this.targetA = false;
        this.targetB = false;
        this.targetC = false;

        game.getBoardGFX().showTargets();

        while (!this.targetA || !this.targetB || !this.targetC) {
        }

        if (this.targetA) {
            fireAt(targetA);
            return;
        }

        if (this.targetB) {
            fireAt(targetB);
            return;
        }

        if (this.targetC) {
            fireAt(targetC);
            return;
        }
    }

    public Player[] availableTargets() {

        int index = 0;
        Player[] returnTargets = new Player[3];

        for (Player player : game.getPlayers()) {

            if (player == this) {

                continue;

            }

            if (player.getCurrentSquare().getSquareType().equals(this.currentSquare.getSquareType())) {

                returnTargets[index] = player;

            } else {

                returnTargets[index] = null;
            }

            index++;
        }

        return returnTargets;
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


    // Asks gun to shoot a specified opponent. Politely.
    // Gun checks for bullets left as a precaution, but fireAt() should not be invokeable if gun is out of ammo.
    // westshootout.Game logic should permit calls to this method only if they would return true. (= option to shoot should be "greyed out" if the gun is empty)
    // Square logic should condition valid players to shoot (only others in same color of Battle Square).
    public boolean fireAt(Player player) {

        game.getBoardGFX().showBang();
        game.getBoardGFX().updateBullets();
        return gun.shoot(player);

    }


    //Handles getting shot by another player. Removes one life and checks if there are any remaining lives with checkdead().
    public void getShot() {

        currentLives--;
        game.getBoardGFX().updateLives();
        checkDead();

    }


    // Tells gun to reload, firmly.
    // westshootout.Game logic should only allow this option to be invoked if there are no bullets left in gun. Boolean return is simply for debugging.
    // reload() should only be allowed to return true. Situations where it would return false should not happen as per game logic conditions.
    public boolean reload() {

        return gun.reload();

    }

    public boolean prepareMove() {

        this.hasRolled = false;

        while (!this.hasRolled) {
        }

        setMovesLeft(game.getDice().roll());
        this.game.getBoardGFX().setDice(game.getDice().getResult());
        move();

        return true;

    }

    public boolean move() {

        while (movesLeft > 0) {

            hasMovedToA = false;
            hasMovedToB = false;

            this.game.getBoardGFX().setMovesLeft(this.movesLeft);

            while (!hasMovedToA || !hasMovedToB) {
            }
            if (hasMovedToA) {
                currentSquare = currentSquare.getNextSquareA();
            }
            if (hasMovedToB && currentSquare.getNextSquareB() != null) {
                currentSquare = currentSquare.getNextSquareB();
            }
            if (hasMovedToB && currentSquare.getNextSquareA() == null) {
                currentSquare = currentSquare.getNextSquareA();
            }

            movesLeft--;
            game.getBoardGFX().updatePositions();
        }

        currentSquare.effect(this);
        return true;
    }

    // USED FOR TELEPORTATION PURPOSES (Setting up players in starting positions, switching player positions)
    public boolean move(Square square) {

        this.currentSquare = square;
        game.getBoardGFX().updatePositions();
        return true;

    }


    //Getters
    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isCanReload() {
        return canReload;
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public boolean isDead() {
        return isDead;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    //Setters
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public void setCanReload(boolean canReload) {
        this.canReload = canReload;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public void setCurrentLives(int currentLives) {
        this.currentLives = currentLives;
    }

    public void setWillMove(boolean willMove) {
        this.willMove = willMove;
    }

    public void setWillShoot(boolean willShoot) {
        this.willShoot = willShoot;
    }

    public void setHasRolled(boolean hasRolled) {
        this.hasRolled = hasRolled;
    }

    public void setHasMovedToA(boolean hasMovedToA) {
        this.hasMovedToA = hasMovedToA;
    }

    public void setHasMovedToB(boolean hasMovedToB) {
        this.hasMovedToB = hasMovedToB;
    }

    public void setTargetA(boolean targetA) {
        this.targetA = targetA;
    }

    public void setTargetB(boolean targetB) {
        this.targetB = targetB;
    }
}
