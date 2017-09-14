
import java.awt.Dimension;
// ww  w.ja  va 2 s .c  om
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
  public static void main(String[] args) {
    JFrame f = new JFrame();
    final JSlider slider = new JSlider(0, 150, 0);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    slider.setPreferredSize(new Dimension(150, 30));
    
    slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        int value = slider.getValue();
        System.out.println(slider.getValue());
//        if (value == 0) {
//          System.out.println("0");
//        } else if (value > 0 && value <= 30) {
//          System.out.println("value > 0 && value <= 30");
//        } else if (value > 30 && value < 80) {
//          System.out.println("value > 30 && value < 80");
//        } else {
//          System.out.println("max");
//        }
      }
    });
    
    f.add(slider);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}