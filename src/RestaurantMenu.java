import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class RestaurantMenu extends JFrame {

	private JPanel contentPane;
	private JTextField cheeseText;
	private JTextField SausageText;
	private JTextField chickenText;
	private JTextField TotalTextField;
	private JTextField EggText;
	private JTextField TurkeyText;
	private JTextField HamText;
	private double total = 0.00;
	private double cheeseCost = 0.00, chesseAmount = 0.00;
	private double sausageCost = 0.00, sausageAmount = 0.00;
	private double chickenCost = 0.00, chickenAmount = 0.00;
	private double eggCost = 0.00, eggAmount = 0.00;
	private double turkeyCost = 0.00 , turkeyAmount = 0.00;
	private double hamCost = 0.00, hamAmount = 0.00;
	private  int cartID;

	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer customer = new Customer();
					customer.setUserName("Walter");
					RestaurantMenu frame = new RestaurantMenu(customer);
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
	public RestaurantMenu(Customer customer) throws SQLException {
		
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant", "root", "walter");
		Statement myStatement = myCon.createStatement();
		
		double cheese,chicken, turkey, sausage, eggsalad, ham;
		
		cheese = 8.00;
		chicken = 12.00;
		turkey = 16.00;
		sausage = 8.00;
		eggsalad = 7.00;
		ham = 10.00;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String name = customer.getUserName();
		JLabel lblHello = new JLabel("Hello " +name);
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblHello.setBounds(45, 38, 166, 31);
		contentPane.add(lblHello);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				JOptionPane.showMessageDialog(null, "Chesse Sandwich \nprice: " +cheese);
				
				
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\chesse0.png"));
		lblNewLabel_1.setBounds(45, 78, 132, 92);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			dispose();
				
			}
		});
		btnSignOut.setBounds(632, 392, 115, 29);
		contentPane.add(btnSignOut);
		
		JButton ChesseBtn = new JButton("add to cart");
		ChesseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chesseAmount = Double.valueOf(cheeseText.getText());
				
				cheeseCost = cheese *  chesseAmount;
				total = total + cheeseCost;
				
				TotalTextField.setText(String.valueOf(total));
				
				

				
			}
		});
		lblNewLabel_1.setLabelFor(ChesseBtn);
		ChesseBtn.setBounds(45, 175, 115, 29);
		contentPane.add(ChesseBtn);
		
		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int min = 1000;
			    int max = 9999;	
				
			  cartID = (int)Math.floor(Math.random()*(max-min+1)+min);
			    
			String chargeMessage = "Order Number: " + cartID +"\n";
			
			chargeMessage = chargeMessage +"$" +cheeseCost + " chesse Sandwiches\n"
							+"$" +sausageCost +" sausage Sandwiches \n"
							+"$" +chickenCost +" chicken Sandwiches \n"
							+"$" +eggCost +" egg sanwich Sandwiches \n"
							+"$" +turkeyCost +" turkey Sandwiches \n"
							+"$" +hamCost +" ham Sandwiches \n"
							+"_______________________________\n"
							+"$" +total + " total cost"		+ " \n\n";
					
			String sQLPurchase = "insert into  purchase (customerName, cartID) VALUES"  
					                     +"('" +customer.getUserName() +"',"
					                     +cartID +");"; 
			
			String SQLCart = "insert into  cart (cartID, cheeseSandwich,"
					+ " chickenSandwich, turkeySandwich, SausageSandwich, "
					+ "eggSaladSandwich, HamSandwich, total) VALUES " + 
						"('" +cartID +"','" +chesseAmount +"','" +chickenAmount +"','"
						+turkeyAmount +"','" +sausageAmount +"','" +eggAmount 
						+"','" + hamAmount +"','" +total +"');";
			
			
			int res = JOptionPane.showOptionDialog(new JFrame(), chargeMessage, "Checkout: "+customer.getUserName() ,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);
			
			 if (res == JOptionPane.YES_OPTION) {
		         
				 System.out.println("Selected Yes!");
				 try {
					myStatement.execute(sQLPurchase);
					myStatement.execute(SQLCart);
					
					JOptionPane.showMessageDialog(null, "Thank you for your Purchase" +customer.getUserName());
					
					dispose();
					 
				} catch (Exception e2) {
					// TODO: handle exception
				}
		         
		         
		      } else if (res == JOptionPane.NO_OPTION) {
		         System.out.println("Selected No!");
		         
		         
		      } 
			
				
				System.out.println(chesseAmount);
			}
		});
		btnNewButton.setBounds(378, 392, 115, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\chickenSanwich.jpg"));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JOptionPane.showMessageDialog(null, "Chicken Sandwich \nprice: $12.00");
			}
		});
		lblNewLabel.setBounds(272, 78, 132, 92);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("add to cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sausageAmount = Double.valueOf(SausageText.getText());
				
				sausageCost = sausage *  sausageAmount;
				total = total + sausageCost;
				
				TotalTextField.setText(String.valueOf(total));
			}
		});
		btnNewButton_1.setBounds(45, 319, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Turkey Sandwich \nPrice: $16.00");
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\TurkeySandwhich.jpg"));
		lblNewLabel_5.setBounds(508, 83, 132, 82);
		contentPane.add(lblNewLabel_5);
		
		cheeseText = new JTextField();
		cheeseText.setBounds(181, 176, 50, 26);
		contentPane.add(cheeseText);
		cheeseText.setColumns(10);
		
		SausageText = new JTextField();
		SausageText.setBounds(175, 320, 50, 26);
		contentPane.add(SausageText);
		SausageText.setColumns(10);
		
		chickenText = new JTextField();
		chickenText.setColumns(10);
		chickenText.setBounds(402, 176, 50, 26);
		contentPane.add(chickenText);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setBounds(15, 370, 69, 20);
		contentPane.add(lblNewLabel_6);
		
		TotalTextField = new JTextField();
		TotalTextField.setEditable(false);
		TotalTextField.setBounds(47, 393, 146, 26);
		contentPane.add(TotalTextField);
		TotalTextField.setColumns(10);
		TotalTextField.setText(String.valueOf(total));
		
		EggText = new JTextField();
		EggText.setColumns(10);
		EggText.setBounds(402, 320, 50, 26);
		contentPane.add(EggText);
		
		TurkeyText = new JTextField();
		TurkeyText.setColumns(10);
		TurkeyText.setBounds(638, 176, 50, 26);
		contentPane.add(TurkeyText);
		
		HamText = new JTextField();
		HamText.setColumns(10);
		HamText.setBounds(638, 320, 50, 26);
		contentPane.add(HamText);
		
		JButton button = new JButton("add to cart");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chickenAmount = Double.valueOf(chickenText.getText());
				chickenCost = chicken *  chesseAmount;
				total = total + chickenCost;
				
				TotalTextField.setText(String.valueOf(total));
				
				
			}
		});
		button.setBounds(272, 175, 115, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("add to cart");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				eggAmount = Double.valueOf(EggText.getText());
				eggCost = eggsalad *  eggAmount;
				total = total + eggCost;
				
				TotalTextField.setText(String.valueOf(total));
				
				
			}
		});
		button_1.setBounds(272, 319, 115, 29);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("add to cart");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				turkeyAmount = Double.valueOf(TurkeyText.getText());
				
				turkeyCost = turkey *  turkeyAmount;
				total = total + turkeyCost;
				
				TotalTextField.setText(String.valueOf(total));
				
			}
		});
		button_2.setBounds(508, 175, 115, 29);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("add to cart");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				hamAmount = Double.valueOf(HamText.getText());
				hamCost = ham *  hamAmount;
				total = total + hamCost;
				
				TotalTextField.setText(String.valueOf(total));
			}
		});
		button_3.setBounds(508, 319, 115, 29);
		contentPane.add(button_3);
		
		JLabel label = new JLabel("New label");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Sausage Sandwich \nprice: $8.00");
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\sausageSandwich.jpg"));
		label.setBounds(45, 218, 132, 92);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Egg Salad Sanwich, \nprice: $7.00");
			}
		});
		label_1.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\eggSalad.jpg"));
		label_1.setBounds(268, 218, 132, 92);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Ham Sandwich, \nprice: $10.00");
			}
		});
		label_2.setIcon(new ImageIcon("C:\\Users\\Santiago\\Documents\\java\\Java_WorkSpaces\\CSCFinal430\\images\\HamSandwich.jpg"));
		label_2.setBounds(508, 218, 132, 92);
		contentPane.add(label_2);
		
		JButton btnNewButton_2 = new JButton("reset cart");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				total = 0.00;
				cheeseText.setText("0");
				SausageText.setText("0");
				chickenText.setText("0");
				EggText.setText("0");
				TurkeyText.setText("0");
				HamText.setText("0");
				cheeseCost = 0.00; chesseAmount = 0.00;
				sausageCost = 0.00; sausageAmount = 0.00;
				chickenCost = 0.00; chickenAmount = 0.00;
				eggCost = 0.00; eggAmount = 0.00;
				turkeyCost = 0.00; turkeyAmount = 0.00;
				hamCost = 0.00; hamAmount = 0.00;
				TotalTextField.setText(String.valueOf(total));
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setBounds(219, 394, 80, 29);
		contentPane.add(btnNewButton_2);
	}
	
	
	
}

	
	
