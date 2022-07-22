package graphic;

import logic.GameSession;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ScorePanel extends JPanel implements Observer {
    private final JLabel scoreLabel;
    private final GameSession gameSession;

    public ScorePanel(GameSession gameSession) {
        this.gameSession = gameSession;
        gameSession.addObserver(this);

        setLayout(new BorderLayout());
        setBackground(new Color(187, 173, 160));

        JLabel nameLabel = new JLabel("SCORE");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        nameLabel.setForeground(new Color(238, 225, 201));

        scoreLabel = new JLabel();
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        scoreLabel.setForeground(Color.WHITE);

        add(BorderLayout.NORTH, nameLabel);
        add(BorderLayout.CENTER, scoreLabel);
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        scoreLabel.setText(Integer.toString(gameSession.getScore()));
    }
}