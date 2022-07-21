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

    public boolean equals(Object o) {
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return cell.value == value;
    }
}