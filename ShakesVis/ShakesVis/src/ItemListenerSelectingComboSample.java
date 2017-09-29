import java.awt.BorderLayout;   
import java.awt.ItemSelectable;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import java.awt.event.ItemEvent;   
import java.awt.event.ItemListener;   
  
import javax.swing.JComboBox;   
import javax.swing.JFrame;   
  
public class ItemListenerSelectingComboSample {   
  static private String selectedString(ItemSelectable is) {   
    Object selected[] = is.getSelectedObjects();   
    return ((selected.length == 0) ? "null" : (String) selected[0]);   
  }   
  
  
  public static void main(String args[]) {   
    String labels[] = { "A", "B", "C", "D", "E", "F" };   
    JFrame frame = new JFrame("Selecting JComboBox");   
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
  
    JComboBox comboBox = new JComboBox(labels);   
  
    comboBox.setEditable(true);   
  
    comboBox.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        System.out.println("Selected index=" + ((JComboBox)e.getSource()).getItemCount());   
      }   
    });   
  
    frame.add(comboBox, BorderLayout.SOUTH);   
  
    ItemListener itemListener = new ItemListener() {   
      public void itemStateChanged(ItemEvent itemEvent) {   
        int state = itemEvent.getStateChange();   
        System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");   
        System.out.print("Item: " + itemEvent.getItem());   
        ItemSelectable is = itemEvent.getItemSelectable();   
        System.out.println(", Selected: " + selectedString(is));   
      }   
    };   
    comboBox.addItemListener(itemListener);   
  
    frame.setSize(400, 200);   
    frame.setVisible(true);   
  }   
}   