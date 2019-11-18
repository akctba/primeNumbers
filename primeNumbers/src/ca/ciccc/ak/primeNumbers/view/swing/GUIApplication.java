package ca.ciccc.ak.primeNumbers.view.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ca.ciccc.ak.primeNumbers.interfaces.View;

public class GUIApplication implements View {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public GUIApplication() {
		initialize();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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

}
