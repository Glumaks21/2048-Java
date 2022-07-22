package graphic;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private enum Style {
        ZERO(new Color(205, 193, 180), null, null),
        TWO(new Color(238, 228, 218), new Color(119, 110, 101), new Font(Font.DIALOG, Font.BOLD, 50)),
        FOUR(new Color(237, 224, 200), new Color(119, 110, 101), new Font(Font.DIALOG, Font.BOLD, 50)),
        EIGHT(new Color(242, 177, 121), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 50)),
        SIXTEEN(new Color(245, 149, 99), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 47)),
        THIRTY_TWO(new Color(246, 125, 95), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 47)),
        SIXTY_FOUR(new Color(246, 94, 59), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 47)),
        ONE_HUNDRED_TWENTY_EIGHT(new Color(237, 207, 114), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 45)),
        TWO_HUNDRED_FIFTY_SIX(new Color(237, 204, 97), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 45)),
        FIVE_HUNDRED_TWELVE(new Color(237, 201, 80), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 45)),
        ONE_THOUSAND_TWENTY_FOUR(new Color(237, 197, 63), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 40)),
        TWO_THOUSAND_FORTY_EIGHT(new Color(238, 194, 46), new Color(249, 246, 242), new Font(Font.DIALOG, Font.BOLD, 40));

        private Color backgroundColor;
        private Color numberColor;
        private Font font;

        Style(Color backgroundColor, Color numberColor, Font font) {
            this.backgroundColor = backgroundColor;
            this.numberColor = numberColor;
            this.font = font;
        }

        static Style getStyle(int value) {
            switch (value) {
                case 0: return ZERO;
                case 2: return TWO;
                case 4: return FOUR;
                case 8: return EIGHT;
                case 16: return SIXTEEN;
                case 32: return THIRTY_TWO;
                case 64: return SIXTY_FOUR;
                case 128: return ONE_HUNDRED_TWENTY_EIGHT;
                case 256: return TWO_HUNDRED_FIFTY_SIX;
                case 512: return FIVE_HUNDRED_TWELVE;
                case 1024: return ONE_THOUSAND_TWENTY_FOUR;
                case 2048: return TWO_THOUSAND_FORTY_EIGHT;
            }
            return null;
        }
    }

    private final JLabel valueLabel;

    public CellPanel() {
        setLayout(new BorderLayout());
        valueLabel = new JLabel();
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setVerticalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(100, 100));
        add(BorderLayout.CENTER, valueLabel);
        setValue(0);
    }

    public void setValue(int value) {
        Style style = Style.getStyle(value);
        if (style != null) {
            setBackground(style.backgroundColor);
            valueLabel.setForeground(style.numberColor);
            if (value != 0) {
                valueLabel.setFont(style.font);
                valueLabel.setText(Integer.toString(value));
            } else {
                valueLabel.setText(" ");
            }
        }
    }
}