/**
 * @file    -ShapesDemo.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -17 Jan '11
 * @see     -Deitel and Deitel, Fig. 12.19, page 472
 *
 *  \brief A simple Java Graphics demo that renders different
 * exciting shapes such as lines, rectangles, and ovals.
 *
 */
import java.awt.Color;
import javax.swing.JFrame;

public class ShapesDemo {

   /* execute application */
   public static void main( String args[] ) {

      /* create frame for ShapesJPanel */
      JFrame frame =
         new JFrame( "Rendering lines, rectangles and ovals" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      ShapesJPanel shapesJPanel = new ShapesJPanel();
      shapesJPanel.setBackground( Color.WHITE );
      /* add panel to frame */
      frame.add( shapesJPanel );
      /* set frame size */
      frame.setSize( WIDTH, HEIGHT );
      /* display frame */
      frame.setVisible( true );

   } /* end main */

   /** the width of the shapes panel */
   private final static int WIDTH = 450;
   /** the height of the shapes panel */
   private final static int HEIGHT = 310;

}    /* end class LinesRectsOvals */

