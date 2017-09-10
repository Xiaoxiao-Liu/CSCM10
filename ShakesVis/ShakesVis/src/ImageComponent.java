import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ImageComponent extends JComponent {

    final BufferedImage img;

    public ImageComponent(URL url) throws IOException {
        img = ImageIO.read(url);
        setZoom(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = getPreferredSize();
        g.drawImage(img, 0, 0, dim.width, dim.height, this);
    }

    private void setZoom(double zoom) {
        int w = (int) (zoom * img.getWidth());
        int h = (int) (zoom * img.getHeight());
        setPreferredSize(new Dimension(w, h));
        revalidate();
        repaint();
    }
    public static void main(String[] args) throws Exception {

        final URL lenna =
            new URL("http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png");

        final JSlider slider = new JSlider(0, 1000, 500);
        final ImageComponent image = new ImageComponent(lenna);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                image.setZoom(2. * slider.getValue() / slider.getMaximum());
            }
        });

        JFrame frame = new JFrame("Test");
        frame.add(slider, BorderLayout.NORTH);

        frame.add(new JScrollPane(image));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    
}