package gameobjects.squares;

public abstract class Square {

    private int xPos;
    private int yPos;
    private int numNextSquares;
    private Square nextSquareA;
    private Square nextSquareB;

    public Square(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Square getNextSquareA() {
        return nextSquareA;
    }

    public Square getNextSquareB() {
        return nextSquareB;
    }

    public void setNextSquareA(Square nextSquareA) {
        this.nextSquareA = nextSquareA;
    }

    public void setNextSquareB(Square nextSquareB) {
        this.nextSquareB = nextSquareB;
    }

    public int getNumNextSquares() {
        return numNextSquares;
    }

    public void setNumNextSquares(int numNextSquares) {
        this.numNextSquares = numNextSquares;
    }
}
