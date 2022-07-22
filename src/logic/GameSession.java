package logic;

import java.util.*;

public class GameSession extends Observable implements Movable, MergeListener {
    public enum State {
        RUNNING, WIN, LOSE;
    }

    private final GameField field;
    private int score;
    private State state;

    public GameSession() {
        field = new GameField(5);
        field.setMergeListener(this);
        field.generateCell();
        field.generateCell();
        state = State.RUNNING;
    }

    public GameField getField() {
        return field;
    }

    public int getScore() {
        return score;
    }

    public State getState() {
        return state;
    }

    @Override
    public void move(Direction direction) {
        field.move(direction);
        checkWin();
        if (field.getCellsAmount() == field.getLength() * field.getLength()) {
            checkFinish();
        }
    }

    private void checkWin() {
        for (int y = 0; y < field.getLength(); y++) {
            for (int x = 0; x < field.getLength(); x++) {
                if (field.getValueAt(new Cords(x, y)) == 2048) {
                    state = State.WIN;
                    setChanged();
                    notifyObservers();
                    return;
                }
            }
        }
    }

    private void checkFinish() {
        for (int y = 0; y < field.getLength(); y++) {
            for (int x = 0; x < field.getLength(); x++) {
                Cords currCords = new Cords(x, y);
                if (field.isCordsBelong(new Cords(x - 1, y)) &&
                        field.getValueAt(new Cords(x - 1, y)) == field.getValueAt(currCords) ||
                        field.isCordsBelong(new Cords(x, y - 1)) &&
                                field.getValueAt(new Cords(x, y - 1)) == field.getValueAt(currCords) ||
                        field.isCordsBelong(new Cords(x + 1, y)) &&
                                field.getValueAt(new Cords(x + 1, y)) == field.getValueAt(currCords) ||
                        field.isCordsBelong(new Cords(x, y + 1)) &&
                                field.getValueAt(new Cords(x, y + 1)) == field.getValueAt(currCords) ) {
                    return;
                }
            }
        }
        state = State.LOSE;
        setChanged();
        notifyObservers();
    }

    @Override
    public void updateScore(int value) {
        score += value;
        setChanged();
        notifyObservers();
    }
}