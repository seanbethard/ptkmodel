package model.systemicSimplicity;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Components
	private JButton openButton;
	private JFileChooser fc;
	private JSpinner numOfChildren;
	private JLabel labelChildren;
	private JSpinner numOfChildLearning;
	private JLabel labelChildLearning;
	private JSpinner numOfAdolLearning;
	private JLabel labelAdolLearning;
	private JButton startButton;
	
	public GUI() {
		super("Systemic Simplicity Model");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
			
		openButton = new JButton("Open an OTSoft File");
		fc = new JFileChooser();
		
		openButton.addActionListener(new Listener());
		openButton.setVisible(true);
		panel.add(openButton);
		setContentPane(panel);
		pack();
		setSize(300,60);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}

	public void createParameterWindow() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		numOfChildren = new JSpinner();
		numOfChildren.setValue(Main.numOfChildren);
		numOfChildren.setVisible(true);
		labelChildren = new JLabel("Number of Children: ");
		labelChildren.setVisible(true);
		numOfChildLearning = new JSpinner();
		numOfChildLearning.setValue(Main.numOfChildLearning);
		numOfChildLearning.setVisible(true);
		labelChildLearning = new JLabel("Cycles of Childhood Learning: ");
		labelChildLearning.setVisible(true);
		numOfAdolLearning = new JSpinner();
		numOfAdolLearning.setValue(Main.numOfAdolLearning);
		numOfAdolLearning.setVisible(true);
		labelAdolLearning = new JLabel("Cycles of Adolescent Learning: ");
		labelAdolLearning.setVisible(true);
		startButton = new JButton("Start Simulation");
		startButton.addActionListener(new Listener());
		startButton.setVisible(true);
		panel.add(labelChildren);
		panel.add(numOfChildren);
		panel.add(labelChildLearning);
		panel.add(numOfChildLearning);
		panel.add(labelAdolLearning);
		panel.add(numOfAdolLearning);
		panel.add(startButton);
		setContentPane(panel);
		pack();
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public class Listener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == openButton) {
				int returnVal = fc.showOpenDialog(GUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Main.filePath = file.getAbsolutePath();
					Main.fileName = file.getName();
					Main.fileSelected = true;
				}
			}
			if (e.getSource() == startButton)  {
				Main.numOfChildren = (Integer) numOfChildren.getValue();
				Main.numOfChildLearning = (Integer) numOfChildLearning.getValue();
				Main.numOfAdolLearning = (Integer) numOfAdolLearning.getValue();
				Main.parametersSet = true;
			}
		}
	}
}
