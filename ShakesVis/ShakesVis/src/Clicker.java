import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Clicker extends JPanel {
    private static final int ROWS = 9; 
    private static final int COLUMNS = 9;

    public Clicker() {
        setLayout(new GridLayout(COLUMNS, ROWS));

        JLabel labels[][] = new JLabel[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            labels[i] = new JLabel[COLUMNS];    
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].addMouseListener(createMouseListener());
                add(labels[i][j]);
            }
        }
    }

    public MouseAdapter createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JLabel label = (JLabel)e.getSource();

                if (!label.isEnabled()) {
                    label.setText("");
                    label.setEnabled(true);
                } else {
                    label.setText("Clicked");
                    label.setEnabled(false);
                }
            }
        };
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Click me demo.");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Clicker(),BorderLayout.CENTER);
        frame.setVisible(true);
    }
}