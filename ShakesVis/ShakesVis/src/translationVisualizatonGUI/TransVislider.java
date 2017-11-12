package translationVisualizatonGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class TransVislider extends JSlider{

	private static int orientation=SwingConstants.HORIZONTAL;
	private static int min=50; //minimum value
	private static int max=200; //maximum value
	private static int initialVar=100; //initial value
	
	public TransVislider() {
		super(orientation, min, max, initialVar);
		// TODO Auto-generated constructor stub
	}

	public boolean initialize(){
		int fontSize=11;
		int tickSpacing=10; //set tick space: 0, 10, 20...100
		Font sliderFont=new Font("Serif", Font.PLAIN, fontSize);
		this.setMajorTickSpacing(tickSpacing);
		this.setPaintTicks(true);
		this.setBackground(Color.WHITE);
		this.setFont(sliderFont);
		this.setPaintLabels(true);
		this.setPaintTicks(true);
		return true;
	}

	
}
