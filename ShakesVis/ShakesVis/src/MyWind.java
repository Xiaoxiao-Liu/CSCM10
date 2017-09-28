import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class MyWind extends JFrame{

    public MyWind() {
        initialize();
    }

    private void initialize() {
        setSize(300, 300);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextField field = new JTextField();
        field.setSize(200, 50);
        field.setText("              ");

        JComboBox comboBox = new JComboBox();
        comboBox.setEditable(true);
        comboBox.addItem("item1");
        comboBox.addItem("item2");

        //
        // Create an ActionListener for the JComboBox component.
        //
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("item1"))
                field.setText("30");
                else if(selected.toString().equals("item2"))
                    field.setText("40");

            }
        });
        getContentPane().add(comboBox);
        getContentPane().add(field);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyWind().setVisible(true);
            }
        });
    }
}