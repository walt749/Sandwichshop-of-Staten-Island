import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Result;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AdminLog extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLog frame = new AdminLog();
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
	public AdminLog() throws SQLException {
		
		Connection myCon = 
		Statement myStatement = myCon.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(167, 104, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(27, 107, 89, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(27, 182, 89, 26);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("get Purchases");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String purchase = "Total Purchases:		\n";	

				String Username, password;
				Username = textField.getText();
				password = new String(passwordField.getPassword());
				
				if(Username.equals("admin") && password.equals("admin")) {
				
				Connection myCon;
				try {
					myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant", "root", "walter");
					Statement myStatement = myCon.createStatement();
					
					ResultSet res = myStatement.executeQuery("select * from cart;");
				
					while(res.next()) {
						purchase = purchase +"Cart ID:" +res.getString("cartID") + "\n"
								+"cheeseSandwich: " +res.getString("cheeseSandwich")+ "\n"
								+"chickenSandwich: " +res.getString("chickenSandwich") +"\n"
								+"turkeySandwich: " +res.getString("turkeySandwich")+"\n"
								+"eggSaladSandwich: " +res.getString("eggSaladSandwich")+"\n"
								+"HamSandwich: " +res.getString("HamSandwich")+"\n"
								+"SausageSandwich: " +res.getString("SausageSandwich")+"\n"
								+"total: $" +res.getString("total")+"\n\n"
								; 
					}
					
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, purchase);
				
			}}
		});
		btnNewButton.setBounds(200, 242, 162, 45);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 182, 147, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(1, 286, 115, 29);
		contentPane.add(btnNewButton_1);
	}
}
