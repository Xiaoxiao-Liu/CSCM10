package otherDemo;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ShareList extends JPanel {
   private static final String PROTOTYPE_CELL_VALUE = "ABCDEFGHIJKLMNOP";
   private static final int VISIBLE_ROW_COUNT = 10;
   private JTextField textField = new JTextField(10);
   private DefaultListModel<String> listModel = new DefaultListModel<>();
   private JList<String> myList = new JList<>(listModel);

   public ShareList() {
      myList.setPrototypeCellValue(PROTOTYPE_CELL_VALUE);
      myList.setVisibleRowCount(VISIBLE_ROW_COUNT);
      myList.setFocusable(false);

      JPanel buttonPanel = new JPanel();
      AddHandler addHandler = new AddHandler(listModel, this);
      textField.addActionListener(addHandler);
      buttonPanel.add(new JButton(addHandler));
      buttonPanel.add(new JButton(new ClearHandler(listModel)));

      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.add(textField, BorderLayout.NORTH);
      rightPanel.add(buttonPanel, BorderLayout.CENTER);

      setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
      add(new JScrollPane(myList));
      add(rightPanel);
   }

   public String getText() {
      textField.selectAll();
      return textField.getText();
   }

   private static void createAndShowGui() {
      JFrame frame = new JFrame("ShareList");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new ShareList());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }

}

@SuppressWarnings("serial")
class AddHandler extends AbstractAction {
   private DefaultListModel<String> listModel;
   private ShareList shareList;

   public AddHandler(DefaultListModel<String> listModel, ShareList shareList) {
      super("Add");
      putValue(MNEMONIC_KEY, KeyEvent.VK_A);
      this.listModel = listModel;
      this.shareList = shareList;
   }

   public void actionPerformed(ActionEvent e) {
      String text = shareList.getText();
      listModel.addElement(text);
   };
}

@SuppressWarnings("serial")
class ClearHandler extends AbstractAction {
   private DefaultListModel<String> listModel;

   public ClearHandler(DefaultListModel<String> listModel) {
      super("Clear");
      putValue(MNEMONIC_KEY, KeyEvent.VK_C);
      this.listModel = listModel;
   }

   public void actionPerformed(ActionEvent e) {
      listModel.clear();
   };
}