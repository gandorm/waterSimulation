package gerstner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
  
public class Arrow_Test extends JPanel implements ChangeListener {

	static final int WIND_MIN = 0;
   static final int WIND_MAX = 360;
   static final int WIND_INIT = 20;// Accumulated sum, init to 0
    double theta = 0;
    Path2D arrow = new Arrow();

    
    public int getWindDirection(){
    	return (int)theta;
    	}
    
    public void stateChanged(ChangeEvent e) {
        int value = ((JSlider) e.getSource()).getValue();
        theta = Math.toRadians(value);
        System.out.print((int)value);
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.setStroke(new BasicStroke(4));

        double x = (getWidth() - arrow.getBounds().getWidth()) / 2d;
        double y = (getHeight() - arrow.getBounds().getHeight()) / 2d;

        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        at.rotate(theta, arrow.getBounds().getWidth() / 2d, arrow.getBounds().getHeight() / 2d);
        g2d.setTransform(at);
        g2d.scale(2,2);
        g2d.draw(arrow);
        g2d.dispose();
        
    }

    JSlider getSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL,
	             WIND_MIN, WIND_MAX, WIND_INIT);
        slider.setMajorTickSpacing(60);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);	
        slider.addChangeListener(this);
        return slider;
    }

    public class Arrow extends Path2D.Double {

        public Arrow() {
            moveTo(0, 10);
            lineTo(36, 10);
            moveTo(36 - 16, 0);
            lineTo(36, 10);
            moveTo(36 - 16, 20);
            lineTo(36, 10);
        }

    }
}
