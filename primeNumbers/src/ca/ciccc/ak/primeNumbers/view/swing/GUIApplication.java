package ca.ciccc.ak.primeNumbers.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

import ca.ciccc.ak.primeNumbers.core.Prime;
import ca.ciccc.ak.primeNumbers.interfaces.View;
import ca.ciccc.ak.primeNumbers.tools.UtilFile;

public class GUIApplication implements View {

	private JFrame frmPrimeNumbers;
	private final Action actionVerify = new SwingActionVerify();
	private JSpinner fNumber;
	private JSpinner spLimit;
	private JLabel lblYN;
	private JLabel fieldPath;
	private JLabel lblStatus;
	private final Action actionFile = new SwingActionFile();
	private final Action actionGenerate = new SwingActionGeneratePrimes();
	
	public static final int SAVE_LIMIT = 100;
	
	private String path;

	/**
	 * Create the application.
	 */
	public GUIApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrimeNumbers = new JFrame();
		frmPrimeNumbers.setTitle("Prime Numbers");
		frmPrimeNumbers.setBounds(100, 100, 759, 296);
		frmPrimeNumbers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrimeNumbers.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBounds(6, 6, 438, 76);
		frmPrimeNumbers.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVerifyIfA = new JLabel("Verify if a number ir prime:");
		panel.add(lblVerifyIfA, BorderLayout.NORTH);
		
		fNumber = new JSpinner();
		panel.add(fNumber, BorderLayout.CENTER);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.setAction(actionVerify);
		panel.add(btnVerify, BorderLayout.EAST);
		
		lblYN = new JLabel("y / n");
		panel.add(lblYN, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLACK));
		panel_1.setBounds(6, 102, 679, 142);
		frmPrimeNumbers.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGenerateACsv = new JLabel("Generate a CSV file with prime numbers");
		lblGenerateACsv.setBounds(6, 6, 426, 16);
		panel_1.add(lblGenerateACsv);
		
		JLabel lblLimit = new JLabel("Limit:");
		lblLimit.setBounds(6, 27, 61, 16);
		panel_1.add(lblLimit);
		
		spLimit = new JSpinner();
		spLimit.setBounds(59, 22, 196, 26);
		panel_1.add(spLimit);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(6, 55, 61, 16);
		panel_1.add(lblPath);
		
		fieldPath = new JLabel(" . . .");
		fieldPath.setBounds(59, 55, 420, 16);
		panel_1.add(fieldPath);
		
		JButton btnFile = new JButton("File");
		btnFile.setAction(actionFile);
		btnFile.setBounds(480, 50, 68, 29);
		panel_1.add(btnFile);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(28, 120, 346, 16);
		panel_1.add(lblStatus);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setAction(actionGenerate);
		btnGenerate.setBounds(160, 83, 117, 29);
		panel_1.add(btnGenerate);
		
		
	}

	@Override
	public void start() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApplication window = new GUIApplication();
					window.frmPrimeNumbers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public void setTaskProgress(long progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatusText(String statusText) {
		lblStatus.setText(statusText);
		
	}

	@Override
	public void addOutput(String text) {
		lblStatus.setText(text);
		
	}
	private class SwingActionVerify extends AbstractAction {
		public SwingActionVerify() {
			putValue(NAME, "Verify");
			putValue(SHORT_DESCRIPTION, "It verifies if the number is prime using brute force algorithm");
		}
		public void actionPerformed(ActionEvent e) {
			Integer value = (Integer) fNumber.getValue(); 
			String text = "The number " + value;
			
			boolean isPrime = Prime.isPrimeBruteForce(value);
			
			text += (!isPrime?" is not prime.": " is prime.");
			lblYN.setText(text);
		}
	}
	private class SwingActionFile extends AbstractAction {
		public SwingActionFile() {
			putValue(NAME, "File");
			putValue(SHORT_DESCRIPTION, "Set the file to save prime numbers");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Choose a directory to save your file: ");
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				if (jfc.getSelectedFile().isDirectory()) {
					//System.out.println("You selected the directory: " + jfc.getSelectedFile());
					File selectedFile = jfc.getSelectedFile();
					path = selectedFile.getAbsolutePath();
					path += "/primesTo" +spLimit.getValue()+UtilFile.CSV_EXTENSION;
					
					fieldPath.setText(path);
				}
			}
			
		}
	}
	private class SwingActionGeneratePrimes extends AbstractAction {
		public SwingActionGeneratePrimes() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Generate prime numbers in the especified file");
		}
		public void actionPerformed(ActionEvent e) {
			
			if(path == null || !path.endsWith(UtilFile.CSV_EXTENSION)) {
				addOutput("Please, set the file above");
				return;
			}
			
			Integer limit = (Integer)spLimit.getValue();
			
			Date ini = new Date();

			// por partes
			int a = 2, b = (limit > SAVE_LIMIT) ? SAVE_LIMIT : limit;
			while (b <= limit) {
				List<Integer> primes = Prime.segmentedEratosthenes(a, b);
				UtilFile.saveOnFile(path, primes);
				a = b + 1;
				b = (b + SAVE_LIMIT) > limit ? limit : (b + SAVE_LIMIT);
				if(a >= limit)
					break;
			}
			
			Date end = new Date();
			long dif = end.getTime() - ini.getTime();
			//UtilFile.saveOnFile(path, dif + " milliseconds");
			addOutput("File generated in " + dif + " milliseconds");
			
		}
	}
}
