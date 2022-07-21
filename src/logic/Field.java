package logic;

public class Field {
    public enum Direction {
        LEFT, UP, RIGHT, DOWN;
    }

    private final int length;
    private final Cell[][] grid;

    public Field(int length) {
        this.length = length;
        grid = new Cell[length][length];
    }

    public void generateCell() {
        boolean set = false;

        while(!set) {
            int x = (int) (Math.random() * length);
            int y = (int) (Math.random() * length);

            if (grid[y][x] != null) {
                continue;
            }
            grid[y][x] = new Cell(2);
            set = true;
        }
    }

    public void move(Direction direction) {
        for (int i = 0; i < length; i++) {
            switch(direction) {
                case LEFT:
                    moveRow(i, Direction.LEFT);
                    break;
                case UP:
                    moveColumn(i, Direction.UP);
                    break;
                case RIGHT:
                    moveRow(i, Direction.RIGHT);
                    break;
                case DOWN:
                    moveColumn(i, Direction.DOWN);
                    break;
            }
        }
    }

    public void moveRow(int row, Direction direction) {
        switch (direction) {
            case LEFT:
                for (int x = 0; x < length - 1; x++) {
                    if (grid[row][x + 1] != null) {
                        int swapX = x;
                        while(swapX >= 0 && grid[row][swapX] == null) {
                            grid[row][swapX] = grid[row][swapX + 1];
                            grid[row][swapX + 1] = null;
                            swapX--;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int x = length - 1; x > 0; x--) {
                    if (grid[row][x - 1] != null) {
                        int swapX = x;
                        while (swapX < length && grid[row][swapX] == null) {
                            grid[row][swapX] = grid[row][swapX - 1];
                            grid[row][swapX - 1] = null;
                            swapX++;
                        }
                    }
                }
                break;
        }
    }

    public void moveColumn(int column, Direction direction) {
        switch (direction) {
            case UP:
                for (int y = 0; y < length - 1; y++) {
                    if (grid[y + 1][column] != null) {
                        int swapY = y;
                        while(swapY >= 0 && grid[swapY][column] == null) {
                            grid[swapY][column] = grid[swapY + 1][column];
                            grid[swapY + 1][column] = null;
                            swapY--;
                        }
                    }
                }
                break;
            case DOWN:
                for (int y = length - 1; y > 0; y--) {
                    if (grid[y - 1][column] != null) {
                        int swapY = y;
                        while(swapY <= length - 1 && grid[swapY][column] == null) {
                            grid[swapY][column] = grid[swapY - 1][column];
                            grid[swapY - 1][column] = null;
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
    }

    public void mergeRow(int row, Direction direction) {
        switch (direction) {
            case LEFT:
                for (int x = 0; x < length - 1; x++) {
                    if (grid[row][x] != null && grid[row][x + 1] != null &&
                            grid[row][x].equals(grid[row][x + 1])) {
                        grid[row][x] = grid[row][x].doubledCell();
                        grid[row][x + 1] = null;
                    }
                }
                break;
            case RIGHT:
                for (int x = length - 1; x > 0; x--) {
                    if (grid[row][x] != null && grid[row][x - 1] != null &&
                            grid[row][x].equals(grid[row][x - 1])) {
                        grid[row][x] =  grid[row][x].doubledCell();
                        grid[row][x - 1] = null;
                    }
                }
                break;
        }
    }

    private void mergeColumn(int column, Direction direction) {
        switch (direction) {
            case UP:
                for (int y = 0; y < length - 1; y++) {
                    if (grid[y][column] != null && grid[y + 1][column] != null &&
                            grid[y][column].equals(grid[y + 1][column])) {
                      grid[y][column] = grid[y][column].doubledCell();
                      grid[y + 1][column] = null;
                    }
                }
                break;
            case DOWN:
                for (int y = length - 1; y > 0; y--) {
                    if (grid[y][column] != null && grid[y - 1][column] != null &&
                            grid[y][column].equals(grid[y - 1][column])) {
                        grid[y][column] = grid[y][column].doubledCell();
                        grid[y - 1][column] = null;
                    }
                }
                break;

        }
    }

    public void swap(Direction direction) {
        move(direction);
        mergeCells(direction);
        move(direction);
        generateCell();
    }

    public void print() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                System.out.printf("%4d", grid[y][x] == null? 0 :grid[y][x].getValue());
            }
            System.out.println();
        }
    }
}

class FieldTest {
    public static void main(String[] args) {
        Field.Direction[] dirArray = new Field.Direction[]{Field.Direction.LEFT,
                                                            Field.Direction.UP,
                                                            Field.Direction.RIGHT,
                                                            Field.Direction.DOWN};

        Field field = new Field(5);
        field.generateCell();
        field.generateCell();
        for (int i = 0; i < 1000; i++) {
            Field.Direction dir = dirArray[i % 4];
            field.swap(dir);
            field.print();
            System.out.println();
        }
    }
}