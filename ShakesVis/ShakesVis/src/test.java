import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.*;
  
public class test extends JFrame
{
   JSlider slider;
  
Container c = getContentPane();
public test()
{
 
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
slider = new JSlider(0,100);
slider.setUI(new coloredThumbSliderUI(slider, Color.red));
slider.setMajorTickSpacing(10);
slider.setMinorTickSpacing(5);
slider.setPaintLabels(true);
slider.setPaintTrack(true);
slider.setSnapToTicks(true);
slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
c.add(slider);
setSize(400,400);
setVisible(true);
}
public static void main(String[] args)
{
test slider = new test();
}
  
class coloredThumbSliderUI extends BasicSliderUI
{
 
    Color thumbColor;
    coloredThumbSliderUI(JSlider s, Color tColor) {
        super(s);
        thumbColor=tColor;
    }
 
    public void paint( Graphics g, JComponent c ) {
        recalculateIfInsetsChanged();
        recalculateIfOrientationChanged();
        Rectangle clip = g.getClipBounds();
 
        if ( slider.getPaintTrack() && clip.intersects( trackRect ) ) {
            paintTrack( g );
        }
        if ( slider.getPaintTicks() && clip.intersects( tickRect ) ) {
            paintTicks( g );
        }
        if ( slider.getPaintLabels() && clip.intersects( labelRect ) ) {
            paintLabels( g );
        }
        if ( slider.hasFocus() && clip.intersects( focusRect ) ) {
            paintFocus( g );      
        }
        if ( clip.intersects( thumbRect ) ) {
            Color savedColor = slider.getBackground();
            slider.setBackground(thumbColor);
            paintThumb( g );
            slider.setBackground(savedColor);
        }
    }
}
}