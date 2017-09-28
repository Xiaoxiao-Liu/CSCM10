import javax.swing.*;
import java.awt.event.*;

public class Example extends JFrame{
    public JCheckBox one;

    public Example() {
        one = new JCheckBox("CT scan performed");
        one.addItemListener(new CheckBoxListener());
        setSize(300,300);
        getContentPane().add(one);
        setVisible(true);
    }

    private class CheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource()==one){
                if(one.isSelected()) {
                    System.out.println("one has been selected");
                } else {System.out.println("nothing");}
            }
        }
    }

    public static void main(String[] args) {
        new Example();
    }
}
