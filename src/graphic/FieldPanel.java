package graphic;

import logic.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

public class FieldPanel extends JPanel implements Observer {
    private final GameField field;
    private final CellPanel[][] grid;

    public FieldPanel(GameField field) {
        this.field = field;
        field.addObserver(this);
        grid = new CellPanel[field.getLength()][field.getLength()];

        GridLayout gridLayout = new GridLayout(field.getLength(), field.getLength());
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        setLayout(gridLayout);
        setBackground(new Color(187, 173, 160));
        setBorder(new LineBorder(new Color(187, 173, 160), 10));
        for (int y = 0; y < field.getLength(); y++) {
            for (int x = 0; x < field.getLength(); x++) {
                CellPanel cell = new CellPanel();
                grid[y][x] = cell;
                add(cell);
            }
        }
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        long startTime = System.currentTimeMillis();
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleWithFixedDelay(() -> {
            for (int y = 0; y < field.getLength(); y++) {
                for (int x = 0; x < field.getLength(); x++) {
                    grid[y][x].setValue(field.getValueAt(new Cords(x, y)));
                }
            }
            if (System.currentTimeMillis() - startTime > 100) {
                timer.shutdown();
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }
}