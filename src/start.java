import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class start {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start window = new start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 593, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to the Staten Island");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWelcome.setBounds(80, 52, 430, 83);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblSandwichShop = new JLabel("Sandwich Shop");
		lblSandwichShop.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSandwichShop.setBounds(154, 125, 272, 83);
		frame.getContentPane().add(lblSandwichShop);
		
		JButton btnNewButton = new JButton("Begin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Login().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(56, 224, 152, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new NewCustomer().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
		});
		btnNewCustomer.setBounds(334, 224, 152, 40);
		frame.getContentPane().add(btnNewCustomer);
		
		JButton btnNewButton_1 = new JButton("Adminstrator");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new AdminLog().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(15, 16, 125, 20);
		frame.getContentPane().add(btnNewButton_1);
	}
}
