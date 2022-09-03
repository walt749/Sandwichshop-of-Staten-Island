import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.util.StringUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.*;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() throws SQLException {
		
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant", "root", "walter");
		Statement myStatement = myCon.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(98, 74, 96, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(98, 157, 96, 20);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField.setBounds(258, 71, 146, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		passwordField.setBounds(258, 157, 146, 36);
		contentPane.add(passwordField);
		
		JButton button = new JButton("<-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(385, 26, 115, 29);
		contentPane.add(button);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Username, password;
				Username = textField.getText();
				password = new String(passwordField.getPassword());
				
				//System.out.println(password);
				
				ResultSet Res;
				
				try {
					Res = myStatement.executeQuery("Select * FROM customer;");
					
					while(Res.next()){
						
						
						if(Res.getString("Username").equals(Username) 
								&& Res.getString("password").equals(password)) {
							
							JOptionPane.showMessageDialog(null, "Welcome " +Username);
							
							Customer userLog = new Customer(Res.getString("firstName"), 
									Res.getString("lastName"),
									Res.getString("userName"), Res.getString("email"),
									Res.getDouble("storeCredit"), Res.getString("password"));
							new RestaurantMenu(userLog).setVisible(true);
							
							dispose();
						}
						
						}
						
					}
					
					
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnSignIn.setBounds(98, 241, 115, 29);
		contentPane.add(btnSignIn);
	}
	
	

}
