/**
 * @file    -ColorDemo.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -17 Jan '11
 * @see     -Deitel and Deitel, Fig. 12.6, page 459
 *
 *  \brief A simple Java Graphics demo that renders colors.
 *
 *
 */

import javax.swing.JFrame;

public class ColorDemo {

   /* execute application */
   public static void main( String args[] ) {

     /* create frame for ColorJPanel */
      JFrame frame = new JFrame( "Color demo" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      /* create ColorJPanel */
      ColorJPanel colorJPanel = new ColorJPanel();
      /* add colorJPanel to frame */
      frame.add( colorJPanel );
      /* set frame size */
      frame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
      /* display frame */
      frame.setVisible( true );

   } /* end main */


   /** The width of the color panel */
   private static final int FRAME_WIDTH = 500;
   /** The height of the color panel */
   private static final int FRAME_HEIGHT = 180;

} /* end class ColorDemo */

