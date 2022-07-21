package logic;

public class Cell {
    private final int value;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Cell doubledCell() {
        return new Cell(value * 2);
    }
}