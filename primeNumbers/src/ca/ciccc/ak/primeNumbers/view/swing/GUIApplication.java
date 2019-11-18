package ca.ciccc.ak.primeNumbers.view.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ca.ciccc.ak.primeNumbers.core.Prime;
import ca.ciccc.ak.primeNumbers.interfaces.View;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class GUIApplication implements View {

	private JFrame frame;
	private final Action action = new SwingAction();
	private JSpinner fNumber;
	private JLabel lblYN;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 70);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVerifyIfA = new JLabel("Verify if a number ir prime:");
		panel.add(lblVerifyIfA, BorderLayout.NORTH);
		
		fNumber = new JSpinner();
		panel.add(fNumber, BorderLayout.CENTER);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.setAction(action);
		panel.add(btnVerify, BorderLayout.EAST);
		
		lblYN = new JLabel("y / n");
		panel.add(lblYN, BorderLayout.SOUTH);
	}

	@Override
	public void start() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApplication window = new GUIApplication();
					window.frame.setVisible(true);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOutput(String text) {
		// TODO Auto-generated method stub
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
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
}
