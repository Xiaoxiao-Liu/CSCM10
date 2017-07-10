package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WordFreqFrame extends JFrame {

	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordFreqFrame frame = new WordFreqFrame();
					frame.setSize(500, 800);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WordFreqFrame() {
		int v;
		v=setValue();
		WFPanel wfPanel=new WFPanel("D:\\dataProcess\\SHAKESPEAREbaseText.txt",v);
		WFPanel gundolf=new WFPanel("D:\\dataProcess\\Iiii 011 gundolf.txt",v);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().add(wfPanel);
		getContentPane().add(gundolf);
		wfPanel.setSize(300, 300);
		gundolf.setBounds(310, 0, 300, 300);
		wfPanel.setVisible(true);
		gundolf.setVisible(true);
		
	}
	
	public void initialFrame(){
		
		
	}
	
	public int setValue(){
		int value=10;
		return value;
	}
	

}
