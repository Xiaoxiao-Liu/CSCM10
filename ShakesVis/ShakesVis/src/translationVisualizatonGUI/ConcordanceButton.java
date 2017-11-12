package translationVisualizatonGUI;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ConcordanceButton extends JButton{
	

	public ConcordanceButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConcordanceButton(Action paramAction) {
		super(paramAction);
		// TODO Auto-generated constructor stub
	}

	public ConcordanceButton(Icon paramIcon) {
		super(paramIcon);
		// TODO Auto-generated constructor stub
	}

	public ConcordanceButton(String paramString, Icon paramIcon) {
		super(paramString, paramIcon);
		// TODO Auto-generated constructor stub
	}

	public ConcordanceButton(String paramString) {
		super(paramString);
		// TODO Auto-generated constructor stub
	}

	public void initialize(JScrollPane scrollPane, JFrame jFrame){
		this.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                scrollPane.setVisible(true);
                jFrame.revalidate(); 
            }
		});
	}
	
	public GridBagConstraints getConstraint() {
		GridBagConstraints constraint =new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(20,30,10,30);
		return constraint;
	}


}
