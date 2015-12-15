package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Interface extends Frame implements ActionListener{
 
   private JLabel lblInput;     // Declare input Label
   private JTextField tfInput;  // Declare input TextField
   private JTextField tfOutput; // Declare output TextField
   private int numberIn;       // Input number
   private int sum = 0;  
   static final int WIND_MIN = 0;
   static final int WIND_MAX = 50;
   static final int WIND_INIT = 25;// Accumulated sum, init to 0
	   
	public Interface(){
		
		class stateChanged implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				System.out.print((int)source.getValue());
			} 
		}
		
		JFrame f = new JFrame();		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());	
		
	    //Panel z sila wiatru
		
	    
		JPanel listPane = new JPanel();		
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));		
		listPane.add(Box.createRigidArea(new Dimension(0,5)));
		
		//Sila wiatru suwak		
		JPanel wind = new JPanel();
		lblInput = new JLabel("Wybierz siłę wiatru: ");	    
	    tfInput = new JTextField(10); 
	    tfInput.addActionListener(this);
		wind.setLayout(new BoxLayout(wind, BoxLayout.PAGE_AXIS));		
     
		    JSlider windSpeed = new JSlider(JSlider.HORIZONTAL,
		             WIND_MIN, WIND_MAX, WIND_INIT);
		    
			 windSpeed.setMajorTickSpacing(10);
		     windSpeed.setMinorTickSpacing(1);
		     windSpeed.setPaintTicks(true);
		     windSpeed.setPaintLabels(true);		     
		     windSpeed.addChangeListener(new stateChanged());		     
		  	     
		     wind.add(lblInput);
		     wind.add(windSpeed);
		 
	     
		 
		 //Strzalka
		 
		 JPanel arrow = new JPanel();
			arrow.setLayout(new BoxLayout(arrow, BoxLayout.PAGE_AXIS));	
			
	     Arrow_Test test = new Arrow_Test();
	     
	     arrow.setPreferredSize(new Dimension(200, 200));
	     arrow.setMinimumSize(arrow.getPreferredSize());
	     arrow.add(test);
	     arrow.add(test.getSlider(), "Last");
     
	     
	     f.getContentPane().add(arrow);	     
	     f.getContentPane().add(wind);
	     f.getContentPane().add(listPane); 
	     
	     f.setTitle("Symulator fal");  // "super" Frame sets title
	     f.setSize(800, 800);  // "super" Frame sets initial window size
	     f.pack();
	     f.setVisible(true);   // "super" Frame shows
	}
    public static void main(String[] args) {
    	new Interface();
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
       // Get the String entered into the TextField tfInput, convert to int
       numberIn = Integer.parseInt(tfInput.getText());
       sum += numberIn;      // Accumulate numbers entered into sum
       tfInput.setText("");  // Clear input TextField
       tfOutput.setText(sum + ""); // Display sum on the output TextField
                                   // convert int to String
    }
}
