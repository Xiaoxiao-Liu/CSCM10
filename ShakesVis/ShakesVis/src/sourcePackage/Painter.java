package sourcePackage;

/**
 * @file    -Painter.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -6 Dec '10
 * @see     -Deitel and Deitel, Fig. 11.35, page 432
 *
 *  \brief A simple Java Swing Example that demonstrates
 * mouse input
 *
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Painter {

   public static void main( String args[] ) {

      /** create a new JFrame **/
      JFrame application = new JFrame( "A simple paint program" );

      /** create a new paint panel */
      PaintPanel paintPanel = new PaintPanel();
      /** position it in the center */
      application.add( paintPanel, BorderLayout.CENTER ); 

      /** create a label and place it in SOUTH of BorderLayout */
      application.add( new JLabel( "Drag the mouse to draw" ), 
         BorderLayout.SOUTH );

      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      /** set frame size */
      application.setSize( PaintPanel.FRAME_WIDTH, PaintPanel.FRAME_HEIGHT );
      /** display frame -won't appear without this */
      application.setVisible( true ); 

   } /* end main          */

}    /* end class Painter */