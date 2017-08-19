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
   /**
    * Use this method to access the PaintPanelObject
    * @return the current PaintPanelObject
    */
   public PaintPanel GetPaintPanel() {

       boolean test = true;
       if (test) System.out.println("Painter:GetPaintPanel()");
       return m_PaintPanel;
   }
   /**
    * @return TRUE on success
    */
   public boolean SetPaintPanel(PaintPanel newPaintPanel) {

       boolean test = true;
       if (test) System.out.println("Painter:SetPaintPanel()");
       m_PaintPanel = newPaintPanel;
       return true;
   }

   /**
    * Use this method to access the JFrame object
    * @return the current JFrame object
    */
   public JFrame GetJFrame() {
       boolean test = true;
       if (test) System.out.println("Painter:GetJFrame()");
       return m_Frame;
   }
   /**
    * @return TRUE on success
    */
   public boolean SetJFrame(JFrame newJFrame) {

       boolean test = true;
       if (test) System.out.println("Painter:SetJFrame()");
       m_Frame = newJFrame;
       return true;      
   }

   /**
    * Use this method to access sample data.
    * The sample data just happens to be a string in this example.
    * @return the current data
    */
   public String GetSampleData() {
       boolean test = true;
       if (test) System.out.println("Painter:GetSampleData()");
       return m_Data;
   }
   /**
    * @return TRUE on success
    */
   public boolean SetSampleData(String sampleData) {

       boolean test = true;
       if (test) System.out.println("Painter:SetSampleData()");
       m_Data = sampleData;
       return true;
      
   }

   /**
    * Painter constructor
    */
   public Painter() {
       System.out.println("Painter::Constructor()");
   }

   public static void main( String args[] ) {

      /** instantiate the painter application */
      Painter painter = new Painter();
      
      /** 
       * Create a new JFrame.
       * Most examples will show you the following line of code:
       *    JFrame application = new JFrame( "A simple paint program" );
       * We use accessor methods so we can access the JFrame anytime
       * we want.
       */
      painter.SetJFrame(new JFrame( "A simple paint program" ));

      /** 
       * Create a new paint panel.
       * Most textbooks will show you this line of code:
       *    PaintPanel paintPanel = new PaintPanel();
       * We are using something better here: accessor methods that
       * we can use anytime we like.
       */
      painter.SetPaintPanel(new PaintPanel());

      /** position it in the center 
       * Note that now we are using an accessor method
       * so we can access the paint panel easily from
       * any method.
       */
      painter.GetJFrame().add(painter.GetPaintPanel(), BorderLayout.CENTER ); //==m_Frame.add(m_PaintPanel)

      /** create a label and place it in SOUTH of BorderLayout */
      painter.GetJFrame().add( new JLabel( "Drag the mouse to draw" ), 
         BorderLayout.SOUTH );

      painter.GetJFrame().setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      /** set frame size */
      painter.GetJFrame().setSize( PaintPanel.FRAME_WIDTH, PaintPanel.FRAME_HEIGHT );
      /** display frame -won't appear without this */
      painter.GetJFrame().setVisible( true ); 

      /**
       * Because we have set up these nice accessor methods, we can
       * easily pass data to the PaintPanel object.  Here is an example
       * of doing so.
       */
      painter.SetSampleData("A string of data.");
      painter.GetPaintPanel().PassData(painter.GetSampleData());
      
   } /* end main          */

   /** a way to access the paint panel object */
   private PaintPanel m_PaintPanel;
   
   /** a way to access the JFrame object */
   private JFrame m_Frame;

   /** some sample data being passed to the PaintPanel Object */
   private String m_Data;

}    /* end class Painter */



