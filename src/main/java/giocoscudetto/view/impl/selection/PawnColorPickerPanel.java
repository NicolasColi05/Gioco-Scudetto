package giocoscudetto.view.impl.selection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class PawnColorPickerPanel extends JPanel {

    public static final Color[] AVAILABLE_COLORS = {
        new Color(231, 76,  60),   //Red Color
        new Color(52,  152, 219),  //Blue Color
        new Color(46,  204, 113),  //Green Color
        new Color(241, 196, 15)    //Yellow Color
    };

    private static final String[] LABELS = {"Red", "Blue", "Green", "Yellow"};
    private static final int BTN_SIZE = 38;

    private Color selectedColor = null;
    private final List<JButton> buttons = new ArrayList<>();
    private Consumer<Color> onColorChanged;

    public PawnColorPickerPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));
        setOpaque(false);

        for (int i = 0; i < AVAILABLE_COLORS.length; i++) {
            final Color c = AVAILABLE_COLORS[i];
            final String label = LABELS[i];

            JButton btn = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                        RenderingHints.VALUE_ANTIALIAS_ON);

                    //If a color is already selected it gets darker cause it cannot be choosed form other club
                    g2.setColor(isEnabled() ? c : c.darker().darker());
                    g2.fillOval(3, 3, getWidth() - 6, getHeight() - 6);

                    //And the border become balck
                    if (c.equals(selectedColor)) {
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(3));
                    } else {
                        g2.setColor(isEnabled() ? Color.GRAY : new Color(160, 160, 160));
                        g2.setStroke(new BasicStroke(1));
                    }
                    g2.drawOval(3, 3, getWidth() - 6, getHeight() - 6);

                    //And a cross is displayed in the center of it
                    if (!isEnabled()) {
                        g2.setColor(new Color(255, 255, 255, 180));
                        g2.setStroke(new BasicStroke(2.5f));
                        int p = 10;
                        g2.drawLine(p, p, getWidth() - p, getHeight() - p);
                        g2.drawLine(getWidth() - p, p, p, getHeight() - p);
                    }

                    g2.dispose();
                }
            };

            btn.setPreferredSize(new Dimension(BTN_SIZE, BTN_SIZE));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setToolTipText(label);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> {
                if (btn.isEnabled()) {
                    selectedColor = c;
                    buttons.forEach(JButton::repaint);
                    if (onColorChanged != null) onColorChanged.accept(c);
                }
            });

            buttons.add(btn);
            add(btn);
        }
    }

    /** Disabilita i colori già scelti da altre squadre (escluso il proprio). */
    public void setTakenColors(Set<Color> takenByOthers) {
        for (int i = 0; i < AVAILABLE_COLORS.length; i++) {
            boolean isMine = AVAILABLE_COLORS[i].equals(selectedColor);
            buttons.get(i).setEnabled(!takenByOthers.contains(AVAILABLE_COLORS[i]) || isMine);
        }
        repaint();
    }

    public Color getSelectedColor() { return selectedColor; }

    /** Reset completo (usato quando cambia il numero di squadre). */
    public void reset() {
        selectedColor = null;
        buttons.forEach(btn -> btn.setEnabled(true));
        repaint();
    }

    public void setOnColorChanged(Consumer<Color> callback) {
        this.onColorChanged = callback;
    }
}