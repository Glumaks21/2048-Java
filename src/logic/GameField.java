package logic;

import java.util.Observable;

public class GameField extends Observable implements Movable {
    private final int length;
    private final int[][] grid;
    private int cellsAmount;

    private MergeListener mergeListener;

    public GameField(int length) {
        this.length = length;
        grid = new int[length][length];
    }

    public int getLength() {
        return length;
    }

    public int getCellsAmount() {
        return cellsAmount;
    }

    public void generateCell() {
        if (cellsAmount < length * length) {
            int x = -1;
            int y = -1;
            boolean set = false;

            while (!set) {
                x = (int) (Math.random() * length);
                y = (int) (Math.random() * length);

                if (grid[y][x] != 0) {
                    continue;
                }
                grid[y][x] = 2;
                set = true;
            }
            cellsAmount++;
            setChanged();
            notifyObservers();
        }
    }

    public void setMergeListener(MergeListener mergeListener) {
        this.mergeListener = mergeListener;
    }

    public int getValueAt(Cords cords) {
        return grid[cords.getY()][cords.getX()];
    }

    public void move(Direction direction) {
        shift(direction);
        mergeCells(direction);
        shift(direction);
        generateCell();
    }

    public void shift(Direction direction) {
        for (int i = 0; i < length; i++) {
            switch(direction) {
                case LEFT:
                    shiftRow(i, Direction.LEFT);
                    break;
                case UP:
                    shiftColumn(i, Direction.UP);
                    break;
                case RIGHT:
                    shiftRow(i, Direction.RIGHT);
                    break;
                case DOWN:
                    shiftColumn(i, Direction.DOWN);
                    break;
            }
        }
        setChanged();
        notifyObservers();
    }

    private void shiftRow(int row, Direction direction) {
        switch (direction) {
            case LEFT:
                for (int x = 0; x < length - 1; x++) {
                    if (grid[row][x + 1] != 0) {
                        int swapX = x;
                        while(swapX >= 0 && grid[row][swapX] == 0) {
                            grid[row][swapX] = grid[row][swapX + 1];
                            grid[row][swapX + 1] = 0;
                            swapX--;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int x = length - 1; x > 0; x--) {
                    if (grid[row][x - 1] != 0) {
                        int swapX = x;
                        while (swapX < length && grid[row][swapX] == 0) {
                            grid[row][swapX] = grid[row][swapX - 1];
                            grid[row][swapX - 1] = 0;
                            swapX++;
                        }
                    }
                }
                break;
        }
    }

    private void shiftColumn(int column, Direction direction) {
        switch (direction) {
            case UP:
                for (int y = 0; y < length - 1; y++) {
                    if (grid[y + 1][column] != 0) {
                        int swapY = y;
                        while(swapY >= 0 && grid[swapY][column] == 0) {
                            grid[swapY][column] = grid[swapY + 1][column];
                            grid[swapY + 1][column] = 0;
                            swapY--;
                        }
                    }
                }
                break;
            case DOWN:
                for (int y = length - 1; y > 0; y--) {
                    if (grid[y - 1][column] != 0) {
                        int swapY = y;
                        while(swapY <= length - 1 && grid[swapY][column] == 0) {
                            grid[swapY][column] = grid[swapY - 1][column];
                            grid[swapY - 1][column] = 0;
                            swapY++;
                        }
                    }
                }
                break;
        }
    }

    public void mergeCells(Direction direction) {
        for (int i = 0; i < length; i++) {
            switch (direction) {
                case LEFT:
                    mergeRow(i, Direction.LEFT);
                    break;
                case UP:
                    mergeColumn(i, Direction.UP);
                    break;
                case RIGHT:
                    mergeRow(i, Direction.RIGHT);
                    break;
                case DOWN:
                    mergeColumn(i, Direction.DOWN);
                    break;
            }
        }
        setChanged();
        notifyObservers();
    }

    private void mergeRow(int row, Direction direction) {
        switch (direction) {
            case LEFT:
                for (int x = 0; x < length - 1; x++) {
                    if (grid[row][x] != 0 && grid[row][x + 1] != 0 &&
                            grid[row][x] == grid[row][x + 1]) {
                        grid[row][x] = grid[row][x] * 2;
                        mergeListener.updateScore(grid[row][x]);
                        grid[row][x + 1] = 0;
                        cellsAmount--;
                    }
                }
                break;
            case RIGHT:
                for (int x = length - 1; x > 0; x--) {
                    if (grid[row][x] != 0 && grid[row][x - 1] != 0 &&
                            grid[row][x] == grid[row][x - 1]) {
                        grid[row][x] =  grid[row][x] * 2;
                        mergeListener.updateScore(grid[row][x]);
                        grid[row][x - 1] = 0;
                        cellsAmount--;
                    }
                }
                break;
        }
    }

    private void mergeColumn(int column, Direction direction) {
        switch (direction) {
            case UP:
                for (int y = 0; y < length - 1; y++) {
                    if (grid[y][column] != 0 && grid[y + 1][column] != 0 &&
                            grid[y][column] == grid[y + 1][column]) {
                      grid[y][column] = grid[y][column] * 2;
                      mergeListener.updateScore(grid[y][column]);
                      grid[y + 1][column] = 0;
                      cellsAmount--;
                    }
                }
                break;
            case DOWN:
                for (int y = length - 1; y > 0; y--) {
                    if (grid[y][column] != 0 && grid[y - 1][column] != 0 &&
                            grid[y][column] == grid[y - 1][column]) {
                        grid[y][column] = grid[y][column] * 2;
                        mergeListener.updateScore(grid[y][column]);
                        grid[y - 1][column] = 0;
                        cellsAmount--;
                    }
                }
                break;

        }
    }

    public boolean isCordsBelong(Cords cords) {
        return cords.getX() >= 0 && cords.getX() < length &&
                cords.getY() >= 0 && cords.getY() < length;
    }
}