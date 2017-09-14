
import java.awt.Adjustable;
import java.awt.FlowLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HandleScrollbarValueChangesInJScrollPane {

	private static final long serialVersionUID = 1L;

	private static void createAndShowGUI() {

		// Create and set up the window.
		final JFrame frame = new JFrame("Scroll Pane Example");

		// Display the window.
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set flow layout for the frame
		frame.getContentPane().setLayout(new FlowLayout());

		JTextArea textArea = new JTextArea(5, 5);
		JScrollPane scrollableTextArea = new JScrollPane(textArea);

		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		CustomAdjustmentListener adjustmentListener = new CustomAdjustmentListener();

		scrollableTextArea.getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);
		scrollableTextArea.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);

		frame.getContentPane().add(scrollableTextArea);

	}

	static class CustomAdjustmentListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent evt) {

			Adjustable source = evt.getAdjustable();

  // check if user is currently dragging the scrollbar's knob

  if (evt.getValueIsAdjusting()) {

return;

  }

  // get the orientation of the adjustable object

  int orient = source.getOrientation();

  if (orient == Adjustable.HORIZONTAL) {

  	System.out.println("Event from horizontal scrollbar");

  }

  else {

  	System.out.println("Event from vertical scrollbar");

  }

  // get the type of adjustment which caused the value changed event

  int type = evt.getAdjustmentType();

  switch (type) {

    case AdjustmentEvent.UNIT_INCREMENT:

  	  System.out.println("increased by one unit");

  break;

    case AdjustmentEvent.UNIT_DECREMENT:

  	  System.out.println("decreased by one unit");

  break;

    case AdjustmentEvent.BLOCK_INCREMENT:

  	  System.out.println("increased by one block");

  break;

    case AdjustmentEvent.BLOCK_DECREMENT:

  	  System.out.println("decreased by one block");

  break;

    case AdjustmentEvent.TRACK:

  	  System.out.println("knob on the scrollbar was dragged");

  break;

  }

			// get the current value in the adjustment event
			int value = evt.getValue();
			System.out.println("Current Value: " + value);

		}
	}

	public static void main(String[] args) {

  //Schedule a job for the event-dispatching thread:

  //creating and showing this application's GUI.

  javax.swing.SwingUtilities.invokeLater(new Runnable() {

public void run() {

    createAndShowGUI(); 

}

  });
    }

}