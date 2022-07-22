package graphic;

import logic.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainGui implements Observer {
    private final JFrame frame;
    private ScorePanel scorePanel;
    private FieldPanel fieldPanel;
    private GameSession gameSession;

    public MainGui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void start() {
        gameSession = new GameSession();
        gameSession.addObserver(this);

        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(205, 193, 180));
        logoPanel.setBorder(new LineBorder(new Color(187, 173, 160), 10));
        JLabel logo = new JLabel("2048");
        logo.setFont(new Font(Font.DIALOG, Font.BOLD, 90));
        logo.setForeground(new Color(119, 110, 101));
        logoPanel.add(logo);

        scorePanel = new ScorePanel(gameSession);

        northPanel.add(BorderLayout.WEST, logoPanel);
        northPanel.add(BorderLayout.CENTER, scorePanel);

        JPanel southPanel = new JPanel();
        fieldPanel = new FieldPanel(gameSession.getField());
        southPanel.add(fieldPanel);

        frame.getContentPane().add(BorderLayout.NORTH, northPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gameSession.move(Movable.Direction.LEFT);
                        break;
                    case KeyEvent.VK_UP:
                        gameSession.move(Movable.Direction.UP);
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameSession.move(Movable.Direction.RIGHT);
                        break;
                    case KeyEvent.VK_DOWN:
                        gameSession.move(Movable.Direction.DOWN);
                        break;
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (gameSession.getState() == GameSession.State.WIN) {
            System.out.println("WIN");
        } else if (gameSession.getState() == GameSession.State.LOSE) {
            System.out.println("LOSE");
        }
    }
}