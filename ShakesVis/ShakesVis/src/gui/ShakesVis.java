package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;

public class ShakesVis {

//	private JFrame frame;
	private WFFrame frame = new WFFrame();
	WFPanel wfPanel=new WFPanel("D:\\dataProcess\\SHAKESPEAREbaseText.txt",50,10);


	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShakesVis window = new ShakesVis();
//					window.frame.setVisible(true);
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
	public ShakesVis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
//		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
