package logic;

public interface Movable {
    enum Direction {
        LEFT, UP, RIGHT, DOWN;
    }

    void move(Direction direction);
}
