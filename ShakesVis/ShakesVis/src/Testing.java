import javax.swing.*;
import java.awt.*;
class Testing
{
  public void buildGUI()
  {
    JSlider slider = new JSlider(0, 100, 0);
    JPanel p = new JPanel(){
      public void paintComponent(Graphics g){
        g.drawRect(175,125,50,50);
      }
    };
    p.setPreferredSize(new Dimension(400,300));
    JFrame f = new JFrame();
    f.getContentPane().add(slider,BorderLayout.NORTH);
    f.getContentPane().add(p,BorderLayout.CENTER);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        new Testing().buildGUI();
      }
    });
  }
}