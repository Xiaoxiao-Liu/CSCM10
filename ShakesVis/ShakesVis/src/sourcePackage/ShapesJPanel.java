package sourcePackage;

/**
 * @file    -ShapesJPanel.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -17 Jan '11
 * @see     -Deitel and Deitel, Fig. 12.18, page 471
 *
 *  \brief A simple Java Graphics class that renders different
 * exciting shapes such as lines, rectangles, and ovals.
 *
 * Avoid the temptation of using magic numbers with rendering
 * shapes!  Magic numbers will be penalized.
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ShapesJPanel extends JPanel  {

   /**
    * This method is inherited from JComponent.
    * Render various lines, rectangles and ovals
    * @param graphics -a generic graphics object
    */
   public void paintComponent(Graphics graphics) {
       
      /* call superclass's paint method */
      super.paintComponent( graphics ); 

       this.setBackground( Color.WHITE );

      graphics.setColor( Color.RED );
      /* drawLine(int x1, int y1, int x2, int y2) */
      graphics.drawLine( SPACING, TOP_MARGIN, LINE_END_X, TOP_MARGIN );

      graphics.setColor( Color.BLUE );
      /* drawRect(int x, int y, int width, int height) */
      graphics.drawRect( SPACING,
                         TOP_MARGIN+SPACING,
                         SHAPE_WIDTH, SHAPE_HEIGHT );
      /* fillRect(int x, int y, int width, int height) */
      graphics.fillRect( (2*SPACING)+SHAPE_WIDTH, 
                          TOP_MARGIN+SPACING,
                          SHAPE_WIDTH, SHAPE_HEIGHT );

      graphics.setColor( Color.BLACK );
      /* fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) */
      graphics.fillRoundRect( (3*SPACING)+(2*SHAPE_WIDTH),
                               TOP_MARGIN+SPACING,
                               SHAPE_WIDTH, SHAPE_HEIGHT,
                               ARC_WIDTH, ARC_HEIGHT );
      graphics.drawRoundRect( (4*SPACING)+(3*SHAPE_WIDTH),
                               TOP_MARGIN+SPACING,
                               SHAPE_WIDTH, SHAPE_HEIGHT,
                               2*ARC_WIDTH, 2*ARC_HEIGHT );

      graphics.setColor( Color.YELLOW );
      /* draw3DRect(int x, int y, int width, int height, boolean raised) */
      graphics.draw3DRect( SPACING,
                           (2*SPACING)+TOP_MARGIN+SHAPE_HEIGHT,
                           SHAPE_WIDTH, SHAPE_HEIGHT,
                           true );
      graphics.fill3DRect( (2*SPACING)+SHAPE_WIDTH,
                           (2*SPACING)+TOP_MARGIN+SHAPE_HEIGHT,
                           SHAPE_WIDTH, SHAPE_HEIGHT,
                           false );

      graphics.setColor( Color.MAGENTA );
      /* drawOval(int x, int y, int width, int height) */
      graphics.drawOval( (3*SPACING)+(2*SHAPE_WIDTH), 
                         (2*SPACING)+TOP_MARGIN+SHAPE_HEIGHT,
                         SHAPE_WIDTH, SHAPE_HEIGHT );
      graphics.fillOval( (4*SPACING)+(3*SHAPE_WIDTH), 
                         (2*SPACING)+TOP_MARGIN+SHAPE_HEIGHT,
                          SHAPE_WIDTH, SHAPE_HEIGHT );

   } /* end paintComponent() */

  /* the space between the boundaries and shapes */
  private final int SPACING = 20;
  /* the space between the top boundary and a shape */
  private final int TOP_MARGIN = 30;

  /** the line ends x-coordinate */
  private final int LINE_END_X = 380;

  /** shape width */
  private final int SHAPE_WIDTH = 90;
  /** shape height */
  private final int SHAPE_HEIGHT = 55;

  /** a shape's arc width */
  private final int ARC_WIDTH = 20;
  /** a shape's arc height */
  private final int ARC_HEIGHT = 20;

} /* end class ShapesJPanel */
