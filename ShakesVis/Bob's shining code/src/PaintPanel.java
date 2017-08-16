/**
 * @file    -PaintPanel.java
 * @author  -P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -6 Dec '10
 * @see     -Deitel and Deitel, Fig. 11.35, page 432
 *
 *  \brief A simple Java Swing Example that demonstrates
 * mouse input and drawing on a Java Panel
 *
 */

import java.awt.BorderLayout;
import java.awt.Point;             /* for x,y Point object */
import java.awt.Graphics;          /* for drawing          */
import java.awt.event.MouseEvent;  /* for mouse input      */
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JFrame;        /* for JFrame PaintPanel constructor */
import javax.swing.JLabel;

/**
 * The PaintPanel class is rather simple.  It simply stores points
 * to paint and listens to mouse actions.
 */
public class PaintPanel extends JPanel {

   /**
    * @return the current number of points
    */
   public int GetPointCount() {
      return m_PointCount;
   }
   /**
    * @return TRUE on success
    */
   public boolean IncrementPointCount() {//returns boolean, not void 
      m_PointCount++;
      return true;
   }
   /**
    * @return the current number of points
    */
   public Point[] GetPoints() {
      return m_Points;
   }
   /**
    * @return TRUE on success
    */
   public boolean SetPoint(Point point) {//returns boolean, not void 
      boolean test = false;
      if (test) {
          System.out.println("PaintPanel::SetPoint() - " + m_PointCount + ", " + point.toString());
      }
      m_Points[ GetPointCount() ] = point;
      return true;
   }

   /**
    * @return the current sample data
    */
   public String GetSampleData() {
      return m_SampleData;
   }
   /**
    * An accessor method demonstrating passing data to this
    * PaintPanel object
    * @return TRUE on success
    */
   public boolean SetSampleData(String newSampleData) {
      m_SampleData = newSampleData;
      return true;
   }
   /**
    * We can use accessor methods to access this object from
    * the Painter object.
    * @return TRUE on success
    */
   public boolean PassData(String newSampleData) {
       this.SetSampleData(newSampleData);
       System.out.println("PaintPanel::Hello() " + this.GetSampleData());
       return true;      
   }

   /**
    * Constructor:
    * set up GUI and register mouse event handler
    */
   public PaintPanel() {
       boolean test = false;
       if (test) {
           System.out.println("PaintPanel::constructor() ");
       }

      /* Register event handlers.  TextFieldHandler is the
       * private class implemented below.
       */

      PaintHandler handler = new PaintHandler();
      this.addMouseListener( handler );
      this.addMouseMotionListener( handler );
      /** add setHandler(handler) method. **/

   } /* end PaintPanel constructor         */
        
   /**
    * Draw an oval in a OVAL_WIDTH-by-OVAL_HEIGHT bounding box
    * at specified location on window.  This method is called
    * from PaintHandler::mouseDragged().
    */
   public void paintComponent( Graphics graphics ) {

       boolean test = false;
       if (test) {
           System.out.println("PaintPanel::paintComponent() " + graphics.toString());
       }

       /** clsisears drawing area */
       super.paintComponent( graphics );

     
       
      /** draw all points in array */
      for ( int i = 0; i < this.GetPointCount(); i++ ) {

         graphics.fillOval( GetPoints()[ i ].x, /* upper-left x coord */
                            GetPoints()[ i ].y, /* upper-left y coord */
                            OVAL_WIDTH, OVAL_HEIGHT );
      }
   } /** end method paint */


   /**
    * Private inner class for event handling.
    * It uses and implements the MouseListener and MouseMotionListener
    * interfaces.
    */
   private class PaintHandler implements MouseListener, MouseMotionListener {

      /**
       * This method is defined in MouseMotionListener.
       * store drag coordinates and repaint
       */
      public void mouseDragged(MouseEvent event) {

         boolean test = false;
         if (test) {
           System.out.println("PaintHandler::mouseDragged() " + event.toString());
         }

         if ( GetPointCount() < GetPoints().length ) {

            /* find and store point */
            SetPoint(event.getPoint());
            /* increment number of points in array **/
            IncrementPointCount();
            /* repaint JFrame */
            repaint();

         } /* end if                             */
      }    /* end method mouseDragged            */

      /**
       * This method is defined in MouseMotionListener.
       */
      public void mouseMoved(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mouseMoved() " + event.toString());
         }
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mouseEntered(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mouseEntered() " + event.toString());
         }
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mouseExited(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mouseExited() " + event.toString());
         }
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mouseClicked(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mouseClicked() " + event.toString());
         }
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mousePressed(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mousePressed() " + event.toString());
         }
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mouseReleased(MouseEvent event) {

         boolean test = true;
         if (test) {
           System.out.println("PaintHandler::mouseReleased() " + event.toString());
         }
      }

   }    /* end private inner class PaintHandler */


      /** the width of the drawing area */
   public final static int FRAME_WIDTH = 500;

   /** the height of the drawing area */
   public final static int FRAME_HEIGHT = 300;

   /** The maximum number of drawable points. */
   private final int MAX_POINTS = 10000;
// the value of MAX_POINTS is never changed no matter what.
   //unless in the situation of paintComponent, which no inside variables can be passed out of the method.
   // that was why I chose to use a global var 'barLocation'.
   // all clear?
   // all in all, follow bob and wang's coding convention. Long live Java.
   /** An oval's width */
   private final int OVAL_WIDTH = 4;

   /** An oval's height */
   private final int OVAL_HEIGHT = 4;

   /** count number of points */
   private int m_PointCount = 0;

   /** An array of MAX_POINTS java.awt.Point references */
   private Point m_Points[] = new Point[MAX_POINTS];

   /** 
    * This is used demonstrate passing data to the PaintPanelObject.
    */
   private String m_SampleData;
   
} /** end class PaintPanel */

