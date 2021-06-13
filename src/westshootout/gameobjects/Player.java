package westshootout.gameobjects;

import westshootout.gameobjects.squares.Square;
import westshootout.Game;
import westshootout.gameobjects.squares.SquareType;

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
    private boolean isSkipped;
    private boolean canShoot;
    private boolean canReload;
    private boolean willShoot;
    private boolean willMove;
    private boolean willReload;
    private boolean hasRolled;
    private boolean hasMovedToA;
    private boolean hasMovedToB;
    private int target;
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

        if (currentSquare.getSquareType().equals(SquareType.COVER)) {
            this.canReload = true;
            this.canShoot = false;
        }

        if (!gun.bulletsLeft()) {
            this.canShoot = false;
            this.canReload = true;
        }

        System.out.println("Choosing action");

        if (canShoot) {

            System.out.println("Shoot or move?");

            this.game.getBoardGFX().showMoveShoot();

            while (!this.willShoot && !this.willMove) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Error in chooseAction, shoot/move loop.");
                }

            }

            if (this.willMove) {

                prepareMove();
                return;
            }

            if (this.willShoot) {

                prepareTarget();
                return;

            }
        }

        if (canReload) {

            System.out.println("Reload or move?");

            this.game.getBoardGFX().showMoveReload();

            while (!this.willMove && !this.willReload) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Error in chooseAction, reload/move loop.");
                }

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

        System.out.println("Can't shoot or reload: moving.");
        prepareMove();
        return;
    }

    public boolean prepareTarget() {

        Player[] availableTargets = availableTargets();

        if (availableTargets[0] == null && availableTargets[1] == null && availableTargets[2] == null) {
            System.out.println("Zero targets, shooting the sky");
            gun.setRemainingBullets(gun.getRemainingBullets() - 1);
            game.getBoardGFX().updateBullets();
            game.getBoardGFX().showBang();
            return true;
        }

        //Only one guy to shoot, shooting automatically.

        if (!(availableTargets[0] == null) && availableTargets[1] == null && availableTargets[2] == null) {
            System.out.println("Shooting the only guy available");
            fireAt(availableTargets[0]);
            return true;
        }

        if (availableTargets[0] == null && !(availableTargets[1] == null) && availableTargets[2] == null) {
            fireAt(availableTargets[1]);
            return true;
        }

        if (availableTargets[0] == null && availableTargets[1] == null && !(availableTargets[2] == null)) {
            fireAt(availableTargets[2]);
            return true;
        }

        // More than one possibility, proposing choice to player.

        chooseTarget(availableTargets);
        return true;

    }

    public void chooseTarget(Player[] targets) {

        this.target = 0;

        for (Player player : targets) {

            if (player == null) {
                System.out.println("null");
            } else {
                System.out.println(player.getPlayerNumber());
            }
        }

        game.getBoardGFX().showTargets(targets);

        System.out.println("Choosing target");

        while (this.target != 1 && this.target != 2 && this.target != 3 && this.target != 4) {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Error in chooseTarget, aiming loop.");
            }
        }

        if (this.target == 1) {
            fireAt(game.getPlayerByNum(1));
            return;
        }

        if (this.target == 2) {
            fireAt(game.getPlayerByNum(2));
            return;
        }

        if (this.target == 3) {
            fireAt(game.getPlayerByNum(3));
            return;
        }

        if (this.target == 4) {
            fireAt(game.getPlayerByNum(4));
            return;
        }

    }

/*    public void chooseFromTwo(Player targetA, Player targetB) {

        int this.target = 0;
        Player[] targets = new Player[2];
        targets[0] = targetA;
        targets[1] = targetB;

        game.getBoardGFX().showTargets(targets);

        while (this.target != 1 && this.target != 2 && this.target != 3 && this.target != 4) {
            System.out.println("Aiming");
        }

        if (this.target == 1) {
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
        Player[] targets = new Player[3];
        targets[0] = targetA;
        targets[1] = targetB;
        targets[2] = targetC;

        game.getBoardGFX().showTargets(targets);

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
    }*/

    public Player[] availableTargets() {

        int index = 0;
        Player[] returnTargets = new Player[3];

        for (Player player : game.getPlayers()) {

            if (player == this) {

                continue;

            }

            if (player.getCurrentSquare().getSquareType().equals(this.currentSquare.getSquareType()) && !player.isDead()) {

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
            game.getBoardGFX().buryPlayer(this);
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

        //game.getBoardGFX().getShotSound().play(true);
        game.getBoardGFX().getKillSound().play(true);
        game.getBoardGFX().showBang();
        gun.shoot(player);
        game.getBoardGFX().updateBullets();
        return true;

    }


    //Handles getting shot by another player. Removes one life and checks if there are any remaining lives with checkdead().
    public void getShot() {

        currentLives--;
        game.getBoardGFX().updateLives(this);
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
        game.getBoardGFX().askRoll();

        System.out.println("Moving, roll the dice!");

        while (!this.hasRolled) {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Error in prepareMove, hasRolled loop.");
            }

        }

        setMovesLeft(game.getDice().roll());
        this.game.getBoardGFX().setDice(game.getDice().getResult());
        move();
        return true;

    }

    public boolean move() {

        while (movesLeft > 0) {

            System.out.println("Still have moves left! 1 to go forward, 2 to turn!");

            hasMovedToA = false;
            hasMovedToB = false;

            this.game.getBoardGFX().setMovesLeft(this.movesLeft);

            while (!hasMovedToA && !hasMovedToB) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Error in move, a/b loop.");
                }

            }

            if (hasMovedToA) {
                currentSquare = currentSquare.getNextSquareA();
            }
            if (hasMovedToB && currentSquare.getNextSquareB() != null) {
                currentSquare = currentSquare.getNextSquareB();
            } else if (hasMovedToB && currentSquare.getNextSquareB() == null){
                currentSquare = currentSquare.getNextSquareA();
            }

            movesLeft--;
            game.getBoardGFX().hideMovesLeft();
            game.getBoardGFX().updatePositions(this);
        }

        currentSquare.effect(this);
        return true;
    }

    // USED FOR TELEPORTATION PURPOSES (Setting up players in starting positions, switching player positions)
    public boolean move(Square square) {

        System.out.println("Teleported!");
        this.currentSquare = square;
        game.getBoardGFX().updatePositions(this);
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

    public boolean isSkipped() {
        return isSkipped;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public Gun getGun() {
        return gun;
    }

    public int getCurrentLives() {
        return currentLives;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public Game getGame() {
        return game;
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

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    public void setSkipped(boolean skipped) {
        isSkipped = skipped;
    }

    public void setWillMove(boolean willMove) {
        this.willMove = willMove;
    }

    public void setWillShoot(boolean willShoot) {
        this.willShoot = willShoot;
    }

    public void setWillReload(boolean willReload) {
        this.willReload = willReload;
    }

    public void setHasRolled(boolean hasRolled) {
        this.hasRolled = hasRolled;
    }

    public void setHasMovedToA(boolean hasMovedToA) {
        this.hasMovedToA = hasMovedToA;
        System.out.println(hasMovedToA);
    }

    public void setHasMovedToB(boolean hasMovedToB) {
        this.hasMovedToB = hasMovedToB;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
