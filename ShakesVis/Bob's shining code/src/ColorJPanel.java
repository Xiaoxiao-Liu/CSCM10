/**
 * @file    -ColorJPanel.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -17 Jan '11
 * @see     -Deitel and Deitel, Fig. 12.5, page 459
 *
 *  \brief A simple Java Graphics demo that renders colors
 * and bars.
 *
 * This example is quite useful for your assignment!
 * This example is difficult to understand and very error-prone if
 * we use magic numbers.
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class ColorJPanel extends JPanel {

   /* Render rectangles and Strings in different colors */
   public void paintComponent( Graphics graphics ) {

      /* call superclass's paintComponent */
      super.paintComponent( graphics );

      this.setBackground( Color.WHITE );

      /* set new drawing color using integers */
      graphics.setColor(new Color(MAX_COLOR_COMPONENT, 
                                  MIN_COLOR_COMPONENT,
                                  MIN_COLOR_COMPONENT ) );
      graphics.fillRect( FIRST_X_COORD,
                         FIRST_Y_COORD,
                         BAR_LENGTH,
                         BAR_WIDTH );
      graphics.drawString( "Current RGB: " + graphics.getColor(), TEXT_X_COORD, TEXT_Y_COORD );

      /* set new drawing color using floats */
      graphics.setColor( new Color( 0.50f, 0.75f, 0.0f ) );
      graphics.fillRect( FIRST_X_COORD, FIRST_Y_COORD+SPACING, BAR_LENGTH, BAR_WIDTH );
      graphics.drawString( "Current RGB: " + graphics.getColor(), TEXT_X_COORD, TEXT_Y_COORD+SPACING );

      /* set new drawing color using static Color objects */
      graphics.setColor( Color.BLUE );
      graphics.fillRect( FIRST_X_COORD, FIRST_Y_COORD+(2*SPACING), BAR_LENGTH, BAR_WIDTH );
      graphics.drawString( "Current RGB: " + graphics.getColor(), TEXT_X_COORD, TEXT_Y_COORD+(2*SPACING) );

      /* display individual RGB values */
      Color color = Color.MAGENTA;
      graphics.setColor( color );
      graphics.fillRect( FIRST_X_COORD, FIRST_Y_COORD+(3*SPACING), BAR_LENGTH, BAR_WIDTH );
      graphics.drawString( "RGB values: " + color.getRed() + ", " +
         color.getGreen() + ", " + color.getBlue(), TEXT_X_COORD, TEXT_Y_COORD+(3*SPACING) );
   } // end method paintComponent

   /** the minimum value of a color component */
   private final int MIN_COLOR_COMPONENT = 0;
   /** the maximum value of a color component */
   private final int MAX_COLOR_COMPONENT = 255;

   /** the left-most coordinate of each bar */
   private final int FIRST_X_COORD = 15;
   /** the upper-most coordinate of each bar */
   private final int FIRST_Y_COORD = 25;

   /** the length of each bar */
   private final int BAR_LENGTH = 100;
   /** the width of each bar */
   private final int BAR_WIDTH = 20;

   /** the left-most coordinate of each line of text */
   private final int TEXT_X_COORD = 130;
   /** the baseline coordinate of each line of text */
   private final int TEXT_Y_COORD = 40;

   /** the white space between each bar */
   private final int SPACING = 25;

} /* end class ColorJPanel */

