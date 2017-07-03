import java.awt.EventQueue;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frame;
	
/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JTextArea textArea = new JTextArea();
		DataPreprocess dp =new DataPreprocess();
		dp.readFile();
		List<Map.Entry<String, Integer>> list =dp.sortHash();
		String abc = null;
		  for (Map.Entry<String, Integer> mapping : list) {      	 
	             abc+="map key: "+mapping.getKey() + ":\t" + mapping.getValue()+"\n";
	         }
		textArea.setText(abc);
		textArea.setBounds(49, 43, 98, 119);
		frame.getContentPane().add(textArea);
		
		
	}
}
