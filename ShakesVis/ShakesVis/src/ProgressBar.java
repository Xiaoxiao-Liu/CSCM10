import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class ProgressBar extends Thread implements ActionListener, Runnable {
	// 创建进度条，水平，最小值0，最大值100，即默认值
	JProgressBar jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
	boolean flag1 = true;// 启动和暂停标志
	boolean flag2 = false;// 重置标志

	public void run() {
		jpb.setStringPainted(true);// 进度条呈现进度字符串
		jpb.setBorderPainted(false);// 进度条不绘制其边框
		jpb.setForeground(new Color(0, 210, 40));// 设置进度条的前景色
		jpb.setBackground(new Color(188, 190, 194));// 设置进度条的背景色
		for (int t = 0; t <= 100; t++) {
			while (flag1) {// 暂停过程中重置
				if (flag2 == true) {
					t = 0;
					jpb.setValue(t);
					flag2 = false;
				}
			}
			jpb.setValue(t);// 设置进度条的值
			if (flag2 == true) {// 启动过程中重置
				t = 0;
				jpb.setValue(t);
				flag2 = false;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);// 进程结束后关闭窗口退出程序
	}

	public ProgressBar() {
		// JFrame 窗口的创建！
		JFrame frame = new JFrame("进度条");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 150);
		int WIDTH1 = Toolkit.getDefaultToolkit().getScreenSize().width;
		int HEIGHT1 = Toolkit.getDefaultToolkit().getScreenSize().height;
		int WIDTH2 = frame.getSize().width;
		int HEIGHT2 = frame.getSize().height;
		frame.setLocation((WIDTH1 - WIDTH2) / 2, (HEIGHT1 - HEIGHT2) / 2);
		frame.setVisible(true);
		// JLabel 标签组件
		JLabel jl = new JLabel("进度条控件的使用!", JLabel.CENTER);
		// JButton 按钮组件
		JButton jb1 = new JButton("启动");
		JButton jb2 = new JButton("暂停");
		JButton jb3 = new JButton("重置");
		// JPanel 面板容器,可以添加其他组件在上边，也可以绘图。
		JPanel jp = new JPanel();
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		// 用GridLayout布局管理器进行布局
		GridLayout gl = new GridLayout(3, 1);// 3行1列
		frame.setLayout(gl);
		frame.add(jl);
		frame.add(jpb);
		frame.add(jp);
		// 为按钮添加ActionListener侦听器
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// 每次捕获一个事件，即只有一个按钮有action
		String ee = e.getActionCommand();
		if (ee.equals("启动")) {
			flag1 = false;
		} else if (ee.equals("暂停")) {
			flag1 = true;
		} else if (ee.equals("重置")) {
			flag2 = true;
		}
	}

	public static void main(String[] args) {
		ProgressBar a = new ProgressBar();
		new Thread(a).start();
	}

}