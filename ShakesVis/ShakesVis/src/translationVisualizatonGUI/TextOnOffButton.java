package translationVisualizatonGUI;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton;

public class TextOnOffButton extends JToggleButton{

	public TextOnOffButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(Action paramAction) {
		super(paramAction);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(Icon paramIcon, boolean paramBoolean) {
		super(paramIcon, paramBoolean);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(Icon paramIcon) {
		super(paramIcon);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(String paramString, boolean paramBoolean) {
		super(paramString, paramBoolean);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(String paramString, Icon paramIcon, boolean paramBoolean) {
		super(paramString, paramIcon, paramBoolean);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(String paramString, Icon paramIcon) {
		super(paramString, paramIcon);
		// TODO Auto-generated constructor stub
	}

	public TextOnOffButton(String paramString) {
		super(paramString);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Insets(double top, double right, double bottom, double left)
	 * @return
	 */
	public GridBagConstraints getConstraint() {
		GridBagConstraints constraint =new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.fill = GridBagConstraints.BOTH;
		//Insets(int top, int right, int bottom, int left)
		int top=0;
		int right=30;
		int bottom=10;
		int left=30;
		constraint.insets = new Insets(top, right, bottom, left);
		return constraint;
	}
	
	public void initialize(ConcordancePanel concordancePanel){
		this.setText("Text On");
		this.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                	 setText("Text On");//change the text on button
                	 concordancePanel.setOnAndOff(false);
                } else {
                	setText("Text Off");
                	concordancePanel.setOnAndOff(true);
                }
            }
        });
	}

	

}
