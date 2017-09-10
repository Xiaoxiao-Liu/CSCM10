import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class App extends JFrame implements MouseListener, ActionListener {
 
    int x = 0;
    int y = 0;
    File[] selectedFiles = null;
    int fileIndex = 0;
    int width = 200;
    int height = 200;
 
    public App() {
 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setResizable(false);
        getContentPane().setLayout(null);
 
        JPanel panel = new ImagePanel();
        panel.setBounds(12, 40, 370, 218);
        getContentPane().add(panel);
 
        addMouseListener(this);
        JButton btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(this);
        btnBrowse.setBounds(12, 9, 91, 21);
        getContentPane().add(btnBrowse);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new App();
    }
 
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        // 设置文件类型
        chooser.setFileFilter(filter);
        // 打开选择器面板
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFiles = chooser.getSelectedFiles();
            repaint();
        }
    }
 
    public void mouseClicked(MouseEvent e) {
 
    }
 
    public void mouseEntered(MouseEvent e) {
 
    }
 
    public void mouseExited(MouseEvent e) {
 
    }
 
    public void mousePressed(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        x = point.x;
        y = point.y;
    }
 
    public void mouseReleased(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        int thisX = point.x;
        int thisY = point.y;
        System.out.println("thisX=" + thisX + "  " + "thisY=" + thisY);
        if ((y - thisY < 20 && y - thisY > 0)
                || (y - thisY < 0 && y - thisY > -20)) {
            // Y 在20范围内移动认为是水平移动
            if (x < thisX) {
                // right
                if (selectedFiles != null
                        && fileIndex < selectedFiles.length - 1) {
                    fileIndex++;
                }
            } else {
                // left
                if (selectedFiles != null && fileIndex > 0) {
                    fileIndex--;
                }
            }
        } else {
            if (x < thisX) {
                // 右下
                width += 20;
                height += 20;
            } else {
                // 左上
                width -= 20;
                height -= 20;
            }
        }
 
        repaint();
    }
 
    class ImagePanel extends JPanel {
 
        public void paint(Graphics g) {
            super.paint(g);
 
            if (selectedFiles != null) {
                ImageIcon icon = new ImageIcon(selectedFiles[fileIndex]
                        .getPath());
                g.drawImage(icon.getImage(), 0, 0, width, height, this);
            }
        }
    }
}