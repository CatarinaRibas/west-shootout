package westshootout.gameobjects;

public class Dice {


    private int result;

    public int roll() {
        result = (int) (Math.ceil(Math.random() * 6));
        return result;
    }

    public int getResult() {
        return result;
    }
}
