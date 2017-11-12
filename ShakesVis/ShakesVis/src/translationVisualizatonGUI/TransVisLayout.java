package translationVisualizatonGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TransVisLayout {
	
	private GridBagLayout gridBagLayout = new GridBagLayout( );

	private GridBagConstraints constraint=new GridBagConstraints();

	
	
	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}



	public void setGridBagLayout(GridBagLayout gridBagLayout) {
		this.gridBagLayout = gridBagLayout;
	}



	public GridBagConstraints getConstraint() {
		return constraint;
	}



	public void setConstraint(GridBagConstraints constraint) {
		this.constraint = constraint;
	}



	public boolean initializeGridBagLayout(int gridX, int gridY, int weightX, int weightY, Insets insets){
		setConstraint(new GridBagConstraints());
		getConstraint().gridx=gridX;
		getConstraint().gridy=gridY;
		getConstraint().weightx=weightX;
		getConstraint().weighty=weightY;
		getConstraint().fill=GridBagConstraints.BOTH;
		getConstraint().insets=insets;
		
		
		return true;
	}
	
}
