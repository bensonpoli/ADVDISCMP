import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class CRTWindow extends JFrame implements MouseListener{

	private JPanel contentPane;
	
	private JTabbedPane inputSelectorPane; 
	private JPanel txtFileInputPane;
	private JPanel systemInInputPane;
	
	private JPanel stepsPanel;
	private JScrollPane stepsScrollPane;
	
	private JButton btnOpenFile;
	
	private JScrollPane inputScrollPane;
	private JLabel lblNewLabel;
	private JTextArea inputTxtArea;
	private JButton btnCompute;
	private JButton btnClear;
	private JLabel lblInput;
	
	private CRTLogicWithSteps solver;
	private InputReader reader;
	private JTextArea stepsTxtArea;
	private JButton btnComputeFile;
	private JButton btnClearFile;
	
	private JFileChooser selector;
	private JLabel lblGetValuesHere;
	private JLabel lblFileToOpen;
	private JLabel lblFileName;

	private File toRead; 
	private JSeparator separator_1;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public CRTWindow() {
		toRead = null;
		setResizable(false);
		reader = new InputReader();
		selector = new JFileChooser();
		solver = new CRTLogicWithSteps();
		
		setTitle("Chinese Remainder Theorem Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 396);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		inputSelectorPane = new JTabbedPane(JTabbedPane.TOP);
		inputSelectorPane.setBounds(10, 27, 168, 329);
		contentPane.add(inputSelectorPane);
		
		txtFileInputPane = new JPanel();
		txtFileInputPane.setBackground(new Color(200, 221, 242));
		inputSelectorPane.addTab("via Text File", null, txtFileInputPane, null);
		txtFileInputPane.setLayout(null);
		
		btnOpenFile = new JButton("Open File");
		btnOpenFile.setBounds(10, 29, 143, 23);
		btnOpenFile.addMouseListener(this);
		txtFileInputPane.add(btnOpenFile);
		
		btnComputeFile = new JButton("Compute Input");
		btnComputeFile.setBounds(10, 152, 143, 23);
		btnComputeFile.addMouseListener(this);
		txtFileInputPane.add(btnComputeFile);
		
		btnClearFile = new JButton("Clear Input Area");
		btnClearFile.setBounds(10, 186, 143, 23);
		btnClearFile.addMouseListener(this);
		txtFileInputPane.add(btnClearFile);
		
		lblGetValuesHere = new JLabel("Get values here:");
		lblGetValuesHere.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGetValuesHere.setBounds(10, 11, 138, 20);
		txtFileInputPane.add(lblGetValuesHere);
		
		lblFileToOpen = new JLabel("File to open:");
		lblFileToOpen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFileToOpen.setBounds(10, 63, 138, 20);
		txtFileInputPane.add(lblFileToOpen);
		
		lblFileName = new JLabel("   None Selected");
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFileName.setBounds(10, 81, 138, 20);
		txtFileInputPane.add(lblFileName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 220, 163, 2);
		txtFileInputPane.add(separator);
		
		JTextArea txtSubmittedBy = new JTextArea();
		txtSubmittedBy.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtSubmittedBy.setText("Polican, Benson R.\r\nVillaluna, Winfred Louie D.\r\nADVDISC S18 ");
		txtSubmittedBy.setBounds(5, 233, 152, 57);
		txtSubmittedBy.setBackground(new Color(200, 221, 242));
		txtFileInputPane.add(txtSubmittedBy);
		
		systemInInputPane = new JPanel();
		systemInInputPane.setBackground(new Color(200, 221, 242));
		inputSelectorPane.addTab("via Input", null, systemInInputPane, null);
		systemInInputPane.setLayout(null);
		
		inputScrollPane = new JScrollPane();
		inputScrollPane.setBorder(new LineBorder(Color.WHITE, 10));
		inputScrollPane.setBounds(10, 28, 143, 113);
		systemInInputPane.add(inputScrollPane);
		
		inputTxtArea = new JTextArea();
		inputScrollPane.setViewportView(inputTxtArea);
		
		btnCompute = new JButton("Compute Input");
		btnCompute.setBounds(10, 152, 143, 23);
		btnCompute.addMouseListener(this);
		systemInInputPane.add(btnCompute);
		
		btnClear = new JButton("Clear Input Area");
		btnClear.setBounds(10, 186, 143, 23);
		btnClear.addMouseListener(this);
		systemInInputPane.add(btnClear);
		
		lblInput = new JLabel("Type values here:");
		lblInput.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInput.setBounds(10, 11, 138, 20);
		systemInInputPane.add(lblInput);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 220, 163, 2);
		systemInInputPane.add(separator_1);
		
		textArea = new JTextArea();
		textArea.setText("Polican, Benson R.\r\nVillaluna, Winfred Louie D.\r\nADVDISC S18 ");
		textArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textArea.setBackground(new Color(200, 221, 242));
		textArea.setBounds(5, 233, 152, 57);
		systemInInputPane.add(textArea);
		
		stepsPanel = new JPanel();
		stepsPanel.setBounds(188, 11, 435, 345);
		stepsPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		contentPane.add(stepsPanel);
		stepsPanel.setLayout(null);
		
		stepsScrollPane = new JScrollPane();
		stepsScrollPane.setBounds(1, 1, 433, 343);
		stepsScrollPane.setBorder(new LineBorder(Color.WHITE, 10));
		stepsPanel.add(stepsScrollPane);
		
		stepsTxtArea = new JTextArea();
		stepsTxtArea.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		stepsTxtArea.setEditable(false);
		stepsScrollPane.setViewportView(stepsTxtArea);
		
		lblNewLabel = new JLabel("Method of Input:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 5, 138, 20);
		contentPane.add(lblNewLabel);
	}
	
	public void compute(String input){
		stepsTxtArea.setText("");
		ArrayList<int[][]> toSolve = reader.StringToInt(reader.process(input)); 
			
		for(int i = 0; i < toSolve.size(); i++){
			ArrayList<String>[] steps = solver.solve(toSolve.get(i));
			for(int j = 0; j < steps.length; j++){
				for(int k = 0; k < steps[j].size(); k++){
					stepsTxtArea.append(steps[j].get(k) + "\n");
				}
				stepsTxtArea.append("\n");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(btnClear)){
			
			inputTxtArea.setText("");
			
		}else if(e.getSource().equals(btnCompute)){
			if(inputTxtArea.getText().trim().equals("")){
				stepsTxtArea.setText("Nothing to compute!");
			}else{
				compute(inputTxtArea.getText().trim());
			}
		}else if(e.getSource().equals(btnClearFile)){
			lblFileName.setText("   None Selected");
			toRead = null;
		}else if(e.getSource().equals(btnComputeFile)){
			
			if(toRead != null){
				System.out.println(toRead);
				compute(reader.read(selector.getSelectedFile().getAbsolutePath()));
			}else{
				stepsTxtArea.setText("Nothing to compute!");
			}
			
		}else if(e.getSource().equals(btnOpenFile)){
			
			int returnVal = selector.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				lblFileName.setText("   " + selector.getSelectedFile().getName());
				toRead = new File(selector.getSelectedFile().getAbsolutePath());
		    } else {
		    	lblFileName.setText("   None Selected");
		    }
			
		}
		
	}
}
