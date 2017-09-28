import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ComboOfCheckBox extends JFrame {

public ComboOfCheckBox() {
    begin();
}

private void begin() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();

    JTable table = new JTable(new Object[2][2], new String[]{"COL1", "COL2"});
    String[] values = new String[] {"Oh", "My", "God"};
    final JComboBox<String> comboBox = new JComboBox<String>(values) {
        @Override
        public void setPopupVisible(boolean visible){
            if (visible) {
                super.setPopupVisible(visible);
            }
        }
    };

    class CheckBoxRenderer  implements ListCellRenderer<Object> {
        private Map<String, JCheckBox> items = new HashMap<>();
        public CheckBoxRenderer(String[] items) {
            for (String item : items) {
                JCheckBox box = new JCheckBox(item);
                this.items.put(item, box);
            }

        }
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            if (items.containsKey(value)) {
                return items.get(value);
            } else {
                return null;
            }
        }

        public void setSelected(String item, boolean selected) {
            if (item.contains(item)) {
                JCheckBox cb = items.get(item);
                cb.setSelected(selected);
            }
        }
    }

    final CheckBoxRenderer renderer = new CheckBoxRenderer(values);

    comboBox.setRenderer(renderer);
    comboBox.addItemListener(e -> {
        String item = (String) e.getItem();
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            renderer.setSelected(item, false);
        } else {
            renderer.setSelected(item, true);
        }
    });

    panel.add(comboBox);

    panel.add(new JCheckBox("Another"));
    getContentPane().add(panel);
    pack();
    setVisible(true);
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            ComboOfCheckBox frame = new ComboOfCheckBox();

        }

    });
}
}