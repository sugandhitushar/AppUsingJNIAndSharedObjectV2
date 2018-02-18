import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class App 
{
	public static native int calcBill(int[] params);

	static
	{
		System.loadLibrary("calc");
	}

	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				AppFrame frame = new AppFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}	
}

class AppFrame extends JFrame
{	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 400;
	
	private Hashtable<String,Integer> processors;
	private Hashtable<String,Integer> ram;
	private Hashtable<String,Integer> graphicsCard;
	JLabel resultLabel2;
		
	public AppFrame()
	{
		setTitle("Desktop Shopping Application");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		setLayout(new GridLayout(5,2));
		
		// declaring all processors and their prices
		processors = new Hashtable<String,Integer>();
		processors.put("i3",10000);
		processors.put("i5",15000);
		processors.put("i7",20000);
		
		// accepting processors from user
		JPanel panel1 = new JPanel();
		JLabel processorLabel = new JLabel("Processor : ");
		panel1.add(processorLabel);
		
		JPanel panel2 = new JPanel();
		Set<String> processorKeys = processors.keySet();
		JComboBox processorComboBox = new JComboBox(processorKeys.toArray());
		panel2.add(processorComboBox);
		
		// Declaring RAM and their prices
		ram = new Hashtable<String,Integer>();
		ram.put("2 GB",3000);
		ram.put("4 GB",5000);
		ram.put("8 GB",8000);
		
		// accepting RAM from user
		JPanel panel3 = new JPanel();
		JLabel ramLabel = new JLabel("RAM : ");
		panel3.add(ramLabel);
		
		JPanel panel4 = new JPanel();
		Set<String> ramKeys = ram.keySet();
		JComboBox ramComboBox = new JComboBox(ramKeys.toArray());
		panel4.add(ramComboBox);
		
		// Declaring Graphics Cards and their prices
		graphicsCard = new Hashtable<String,Integer>();
		graphicsCard.put("2 GB",3000);
		graphicsCard.put("4 GB",5000);
		graphicsCard.put("8 GB",8000);
		
		// accepting Graphics Card from user
		JPanel panel5 = new JPanel();
		JLabel graphicsCardLabel = new JLabel("Graphics Card : ");
		panel5.add(graphicsCardLabel);
		
		JPanel panel6 = new JPanel();
		Set<String> graphicsCardKeys = graphicsCard.keySet();
		JComboBox graphicsCardComboBox = new JComboBox(graphicsCardKeys.toArray());
		panel6.add(graphicsCardComboBox);
		
		// Calculate button
		JPanel panel7 = new JPanel();
		JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int processorPrice = processors.get((String)processorComboBox.getSelectedItem());
				int ramPrice = ram.get((String)ramComboBox.getSelectedItem());
				int graphicsCardPrice = graphicsCard.get((String)graphicsCardComboBox.getSelectedItem());
				
				int params[] = new int[3];
				params[0] = processorPrice;
				params[1] = ramPrice;
				params[2] = graphicsCardPrice;
				
				int result = App.calcBill(params);
				
				resultLabel2.setText("" + result);
			}	
		});
		
		
		panel7.add(calculateButton);
		
		JPanel panel8 = new JPanel();

		// Result
		JPanel panel9 = new JPanel();
		JLabel resultLabel = new JLabel("Result : ");
		panel9.add(resultLabel);
		
		JPanel panel10 = new JPanel();
		resultLabel2 = new JLabel("");
		panel10.add(resultLabel2);
				
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		add(panel7);
		add(panel8);
		add(panel9);
		add(panel10);
	}
}
