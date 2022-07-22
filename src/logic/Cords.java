package logic;

public final class Cords {
    private final int x;
    private final int y;

    public Cords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cords)) return false;
        Cords cords = (Cords) o;
        return cords.x == x && cords.y == y;
    }
}