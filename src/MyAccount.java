import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

public class MyAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textfield_firstname; 		  private String firstname;
	private JTextField textfield_lastname;  		  private String lastname;
	private JTextField textfield_email;     		  private String email;private String dob;
	private JTextField textfield_address;			  private String address;
	private JPasswordField textfield_password;		  private String password;
	private JPasswordField textfield_confirmpassword; private String confirmpassword;

	/**
	 * Launch the application.
	 */

	
	public void gettext_forallfields() {
		firstname = textfield_firstname.getText();
		lastname = textfield_lastname.getText();
		email = textfield_email.getText();
		address = textfield_address.getText();
		password = new String(textfield_password.getPassword());
		confirmpassword = new String(textfield_confirmpassword.getPassword());
	}

	/**
	 * Create the frame.
	 */
	public MyAccount(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 686);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 60, 973, 539);
		contentPane.add(panel);
		Color trans = new Color(255,255,255,200);
		panel.setBackground(trans);
		panel.setLayout(null);
		
		
		
		JLabel title_label = new JLabel("My Account");
		title_label.setFont(new Font("SansSerif", Font.BOLD, 30));
		title_label.setForeground(new Color(112, 112, 112));
		title_label.setBounds(399, 13, 212, 41);
		panel.add(title_label);
		
		JLabel info_label = new JLabel("*Leave the field empty if you don't want to change it");
		info_label.setForeground(new Color(112, 112, 112));
		info_label.setFont(new Font("SansSerif", Font.BOLD, 13));
		info_label.setBounds(554, 35, 407, 41);
		panel.add(info_label);
		
		JLabel firstname_label = new JLabel("First Name");
		firstname_label.setForeground(new Color(112, 112, 112));
		firstname_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		firstname_label.setBounds(82, 102, 167, 35);
		panel.add(firstname_label);
		
		JLabel lastname_label = new JLabel("Last Name");
		lastname_label.setForeground(new Color(112, 112, 112));
		lastname_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		lastname_label.setBounds(523, 102, 167, 35);
		panel.add(lastname_label);
		
		JLabel email_label = new JLabel("Email");
		email_label.setForeground(new Color(112, 112, 112));
		email_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		email_label.setBounds(530, 174, 137, 35);
		panel.add(email_label);
		
		JLabel dob_label = new JLabel("Date of Birth");
		dob_label.setForeground(new Color(112, 112, 112));
		dob_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		dob_label.setBounds(68, 174, 221, 35);
		panel.add(dob_label);
		
		JLabel address_label = new JLabel("Address");
		address_label.setForeground(new Color(112, 112, 112));
		address_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		address_label.setBounds(104, 379, 137, 35);
		panel.add(address_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(112, 112, 112));
		password_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		password_label.setBounds(104, 252, 137, 35);
		panel.add(password_label);
		
		JLabel confirmpassword_label = new JLabel("Confirm Password");
		confirmpassword_label.setForeground(new Color(112, 112, 112));
		confirmpassword_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		confirmpassword_label.setBounds(23, 317, 266, 35);
		panel.add(confirmpassword_label);
		
		JLabel backgroundimage_label = new JLabel("");
		backgroundimage_label.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/UI Data/My Account background.png"));
		backgroundimage_label.setBounds(0, 0, 1139, 646);
		contentPane.add(backgroundimage_label);
		
		
		JButton button_changedetails = new JButton("Change Details");
		
		button_changedetails.setBorder(null);
		button_changedetails.setFont(new Font("SansSerif", Font.BOLD, 21));
		button_changedetails.setForeground(Color.WHITE);
		button_changedetails.setBackground(new Color(59,203,232));
		button_changedetails.setBounds(255, 451, 182, 41);
		panel.add(button_changedetails);
		
		JButton button_signout = new JButton("Sign Out");
		
		button_signout.setForeground(Color.WHITE);
		button_signout.setFont(new Font("SansSerif", Font.BOLD, 21));
		button_signout.setBorder(null);
		button_signout.setBackground(new Color(59, 203, 232));
		button_signout.setBounds(554, 451, 136, 41);
		panel.add(button_signout);
		
	    textfield_firstname = new JTextField();
		textfield_firstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_firstname.setForeground(Color.DARK_GRAY);
		textfield_firstname.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_firstname.setBounds(253, 103, 243, 35);
		panel.add(textfield_firstname);
		textfield_firstname.setBackground(Color.WHITE);
		textfield_firstname.setColumns(10);
		
		textfield_lastname = new JTextField();
		textfield_lastname.setForeground(Color.DARK_GRAY);
		textfield_lastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_lastname.setColumns(10);
		textfield_lastname.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_lastname.setBackground(Color.WHITE);
		textfield_lastname.setBounds(679, 102, 243, 35);
		panel.add(textfield_lastname);
		
		textfield_email = new JTextField();
		textfield_email.setForeground(Color.DARK_GRAY);
		textfield_email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_email.setColumns(10);
		textfield_email.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_email.setBackground(Color.WHITE);
		textfield_email.setBounds(679, 174, 243, 35);
		panel.add(textfield_email);
		
		textfield_address = new JTextField();
		textfield_address.setForeground(Color.DARK_GRAY);
		textfield_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_address.setColumns(10);
		textfield_address.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_address.setBackground(Color.WHITE);
		textfield_address.setBounds(253, 379, 669, 35);
		panel.add(textfield_address);
		
		textfield_confirmpassword = new JPasswordField();	
		textfield_confirmpassword.setBounds(275, 321, 221, 35);
		textfield_confirmpassword.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_confirmpassword.setBackground(Color.WHITE);
		panel.add(textfield_confirmpassword);
		
		textfield_password = new JPasswordField();	
		textfield_password.setBounds(275, 256, 221, 35);
		textfield_password.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_password.setBackground(Color.WHITE);
		panel.add(textfield_password);
		
		JButton back_button = new JButton("Search Hotels");
		
		back_button.setForeground(Color.WHITE);
		back_button.setFont(new Font("SansSerif", Font.BOLD, 21));
		back_button.setBorder(null);
		back_button.setBackground(new Color(59, 203, 232));
		back_button.setBounds(46, 17, 195, 41);
		panel.add(back_button);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(273, 174, 205, 35);
		panel.add(dateChooser);
		
		JButton btnMyOrders = new JButton("My Orders");
		btnMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				(new MyOrders(username)).setVisible(true);
			}
		});
		btnMyOrders.setForeground(Color.WHITE);
		btnMyOrders.setFont(new Font("SansSerif", Font.BOLD, 21));
		btnMyOrders.setBorder(null);
		btnMyOrders.setBackground(new Color(59, 203, 232));
		btnMyOrders.setBounds(741, 280, 153, 41);
		panel.add(btnMyOrders);
		
		
		
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				(new SearchHotels(username)).setVisible(true);
			}
		});
		
		button_changedetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gettext_forallfields();
				if(password.equals(confirmpassword)) {
					textfield_password.setBorder(new LineBorder(new Color(112, 112, 112), 2));
					textfield_confirmpassword.setBorder(new LineBorder(new Color(112, 112, 112), 2));
					
					Connection con = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
						
						PreparedStatement ps = null;
						if(firstname.equals("")==false) {
							ps = con.prepareStatement("UPDATE user_database SET first_name = '"+firstname+"' WHERE username = '"+username+"'    ");
							ps.executeUpdate();
							ps.close();
						}
						if(lastname.equals("")==false) {
							ps = con.prepareStatement("UPDATE user_database SET last_name = '"+lastname+"' WHERE username = '"+username+"'    ");
							ps.executeUpdate();
							ps.close();
						}
						if(email.equals("")==false) {
							ps = con.prepareStatement("UPDATE user_database SET email = '"+email+"' WHERE username = '"+username+"'    ");
							ps.executeUpdate();
							ps.close();
						}
						if(address.equals("")==false) {
							ps = con.prepareStatement("UPDATE user_database SET address = '"+address+"' WHERE username = '"+username+"'    ");
							ps.executeUpdate();
							ps.close();
						}
						if(password.equals("")==false) {
							ps = con.prepareStatement("UPDATE user_database SET password = '"+password+"' WHERE username = '"+username+"'    ");
							ps.executeUpdate();
							ps.close();
						}
						
						
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					
				JOptionPane.showMessageDialog(null,"Details updated Successfully!!");
				dispose();
				(new SignIn()).setVisible(true);
				}
				else {
					textfield_password.setBorder(new LineBorder(Color.RED, 2));
					textfield_confirmpassword.setBorder(new LineBorder(Color.RED, 2));
					textfield_password.setText(""); 
					textfield_confirmpassword.setText("");
					JOptionPane.showMessageDialog(null,"Passwords don't match. Please enter the password again","Error", 2);
					
				}
			}
		});
		
		button_signout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				(new SignIn()).setVisible(true);
				JOptionPane.showMessageDialog(null,"Logged Out Successfully!!");
			}
		});
		
	}
}