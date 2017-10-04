import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class SelectingCheckBox {
    static int number = 0;
    static AbstractButton abstractButton;
    static int numberCheckboxes = 4;
    static boolean selected;
    static ButtonModel buttonModel;
    static boolean armed;
    public static void main(String args[]) {
        JFrame frame = new JFrame("Selecting CheckBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                abstractButton = (AbstractButton) actionEvent.getSource();
                selected = abstractButton.getModel().isSelected();
            }};
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                abstractButton = (AbstractButton) changeEvent.getSource();
                buttonModel = abstractButton.getModel();
            }};
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                abstractButton = (AbstractButton) itemEvent.getSource();
                selected = abstractButton.getModel().isSelected();
                int state = itemEvent.getStateChange();
                abstractButton.setName(Integer.toString(number));
                if (state == ItemEvent.SELECTED) {
                    number = number + 1;
                    abstractButton.setName(Integer.toString(number));
                    for (int i = 0; i < numberCheckboxes; i++) {
                        if (Integer.parseInt(abstractButton.getName()) != i) {
                            abstractButton.setEnabled(false);
                        } else {
                            abstractButton.setEnabled(true);
                            System.out.println("abstractButton.getName() ="+ abstractButton.getName());
                        }}} else {
                    number = number - 1;
                }}};
        Container contentPane = frame.getContentPane();
        frame.getContentPane().setLayout(null);
        JCheckBox[] checkBoxes = new JCheckBox[numberCheckboxes];
        for (int i = 0; i < numberCheckboxes; i++) {
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setText("checkbox " + (i + 1));
            checkBoxes[i].setBounds(5, (i + 2) * 25, 100, 30);
            checkBoxes[i].setSize(100, 30);
            checkBoxes[i].addActionListener(actionListener);
            checkBoxes[i].addChangeListener(changeListener);
            checkBoxes[i].addItemListener(itemListener);
            contentPane.add(checkBoxes[i]);
        }
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(number);
            }});
        btnOk.setBounds(343, 232, 89, 23);
        frame.getContentPane().add(btnOk);
        frame.setSize(450, 300);
        frame.setBounds(100, 100, 450, 300);
        frame.setVisible(true);
    }}