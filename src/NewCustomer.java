import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.*;

public class NewCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private Customer customer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public NewCustomer() throws SQLException {
		
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant", "root", "walter");
		
		Statement myStatement = myCon.createStatement();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewCustomer = new JLabel("New Customer Sign Up");
		lblNewCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewCustomer.setBounds(46, 28, 234, 44);
		contentPane.add(lblNewCustomer);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(46, 118, 91, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(46, 167, 91, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(46, 221, 69, 20);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(232, 115, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(232, 164, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(232, 218, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(46, 273, 91, 20);
		contentPane.add(lblUsername);
		
		textField_3 = new JTextField();
		textField_3.setBounds(232, 270, 146, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] UserInfo = new String[5];
				UserInfo[0] = textField.getText(); //firstname
				UserInfo[1] = textField_1.getText(); //lastname
				UserInfo[2] = textField_2.getText(); //email
				UserInfo[3] = textField_3.getText(); //userName
				UserInfo[4] = passwordField.getText();
				
				
				String insertDataString = "insert into customer "
						+ "(firstName, lastName, userName, password, email, storeCredit) "
						+ "VALUES ("
						+"'"+UserInfo[0]  +"','" +UserInfo[1] +"','"+UserInfo[3] +"', '" 
						+UserInfo[4]+"', "
						+ "'" +UserInfo[2]  +" ', '10');";
				
				JOptionPane.showMessageDialog(null, "Welcome " +UserInfo[3] +" you have been given \n"
						+ "a bonus of $10.00 store credit");
				
				customer = new Customer(UserInfo[0], UserInfo[1], UserInfo[3], UserInfo[2], 10, UserInfo[4]);
				
				try {
					new RestaurantMenu(customer).setVisible(true);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				dispose();
				
				try {
					myStatement.executeUpdate(insertDataString);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(263, 365, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<-");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(323, 16, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(46, 324, 69, 20);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(232, 321, 146, 26);
		contentPane.add(passwordField);
	}

}
