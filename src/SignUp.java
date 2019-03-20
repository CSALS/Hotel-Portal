
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;



public class SignUp extends JFrame  {

	private JPanel contentPane;
	private JTextField textfield_lastname;
	private JTextField textfield_email;
	private JPasswordField textfield_password;
	private JPasswordField textfield_confirmpassword;
	private JTextField textfield_address;
	private JTextField textfield_username;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 686);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 13, 973, 613);
		contentPane.add(panel);
		Color trans = new Color(255,255,255,200);
		panel.setBackground(trans);
		panel.setLayout(null);
		
		JLabel singup_label = new JLabel("Sign up");
		singup_label.setForeground(new Color(112, 112, 112));
		singup_label.setFont(new Font("SansSerif", Font.BOLD, 30));
		singup_label.setBounds(427, 13, 135, 35);
		panel.add(singup_label);
		
		JLabel firstname_label = new JLabel("First Name");
		firstname_label.setForeground(new Color(112, 112, 112));
		firstname_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		firstname_label.setBounds(104, 91, 137, 35);
		panel.add(firstname_label);
		
		JLabel lastname_label = new JLabel("Last Name");
		lastname_label.setForeground(new Color(112, 112, 112));
		lastname_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		lastname_label.setBounds(530, 91, 137, 35);
		panel.add(lastname_label);
		
		JLabel email_label = new JLabel("Email");
		email_label.setForeground(new Color(112, 112, 112));
		email_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		email_label.setBounds(104, 164, 137, 35);
		panel.add(email_label);
		
		JLabel dob_label = new JLabel("Date of Birth");
		dob_label.setForeground(new Color(112, 112, 112));
		dob_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		dob_label.setBounds(532, 164, 153, 35);
		panel.add(dob_label);
		
		JLabel address_label = new JLabel("Address");
		address_label.setForeground(new Color(112, 112, 112));
		address_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		address_label.setBounds(104, 232, 137, 35);
		panel.add(address_label);
		
		JLabel username_label = new JLabel("Username\r\n");
		username_label.setForeground(new Color(112, 112, 112));
		username_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		username_label.setBounds(104, 301, 137, 35);
		panel.add(username_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(112, 112, 112));
		password_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		password_label.setBounds(104, 368, 137, 35);
		panel.add(password_label);
		
		JLabel confirmpassword_label = new JLabel("Confirm Password");
		confirmpassword_label.setForeground(new Color(112, 112, 112));
		confirmpassword_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		confirmpassword_label.setBounds(104, 430, 212, 35);
		panel.add(confirmpassword_label);
		
		
		
		JLabel suggestion_for_signin_label = new JLabel("Already have an account?");
		suggestion_for_signin_label.setForeground(new Color(112, 112, 112));
		suggestion_for_signin_label.setFont(new Font("SansSerif", Font.BOLD, 18));
		suggestion_for_signin_label.setBounds(276, 551, 296, 35);
		panel.add(suggestion_for_signin_label);
		
		JLabel sigin_label = new JLabel("Sign in");
	
		sigin_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				(new SignIn()).setVisible(true);
			}
		});
		
		sigin_label.setForeground(new Color(59,203,232));
		sigin_label.setFont(new Font("SansSerif", Font.BOLD, 18));
		sigin_label.setBounds(546, 550, 98, 36);
		panel.add(sigin_label);
		
		
		
		
		JLabel backgroundimage_label = new JLabel("");
		backgroundimage_label.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/UI Data/Sign up background.png"));
		backgroundimage_label.setBounds(0, 0, 1139, 646);
		contentPane.add(backgroundimage_label);
		
		
		JTextField textfield_firstname = new JTextField();
		textfield_firstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_firstname.setForeground(Color.DARK_GRAY);
		textfield_firstname.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_firstname.setBounds(253, 92, 243, 35);
		panel.add(textfield_firstname);
		textfield_firstname.setBackground(Color.WHITE);
		textfield_firstname.setColumns(10);
		
		textfield_lastname = new JTextField();
		textfield_lastname.setForeground(Color.DARK_GRAY);
		textfield_lastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_lastname.setColumns(10);
		textfield_lastname.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_lastname.setBackground(new Color(255, 255, 255));
		textfield_lastname.setBounds(674, 91, 243, 35);
		panel.add(textfield_lastname);
		
		textfield_email = new JTextField();
		textfield_email.setForeground(Color.DARK_GRAY);
		textfield_email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_email.setColumns(10);
		textfield_email.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_email.setBackground(new Color(255, 255, 255));
		textfield_email.setBounds(253, 164, 243, 35);
		panel.add(textfield_email);
		
		textfield_password = new JPasswordField();	
		textfield_password.setBounds(253, 372, 243, 35);
		textfield_password.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_password.setBackground(Color.WHITE);
		panel.add(textfield_password);
		
		textfield_confirmpassword = new JPasswordField();	
		textfield_confirmpassword.setBounds(330, 430, 243, 35);
		textfield_confirmpassword.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_confirmpassword.setBackground(Color.WHITE);
		panel.add(textfield_confirmpassword);
		
		textfield_address = new JTextField();
		textfield_address.setForeground(Color.DARK_GRAY);
		textfield_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_address.setColumns(10);
		textfield_address.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_address.setBackground(new Color(255, 255, 255));
		textfield_address.setBounds(253, 232, 664, 35);
		panel.add(textfield_address);
		
		textfield_username = new JTextField();
		textfield_username.setForeground(Color.DARK_GRAY);
		textfield_username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textfield_username.setColumns(10);
		textfield_username.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		textfield_username.setBackground(Color.WHITE);
		textfield_username.setBounds(253, 301, 243, 35);
		panel.add(textfield_username);
		//dob_textfield.setForeground(Color.BLACK);
		
		JButton signup_button = new JButton("Sign up");
		
		signup_button.setBorder(null);
		signup_button.setFont(new Font("SansSerif", Font.BOLD, 21));
		signup_button.setForeground(Color.WHITE);
		signup_button.setBackground(new Color(59,203,232));
		signup_button.setBounds(421, 500, 118, 33);
		panel.add(signup_button);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(722, 164, 165, 35);
		panel.add(dateChooser);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(511, 314, 174, 16);
		panel.add(label);
		
		
		
		
		signup_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String first_name = textfield_firstname.getText();
				String last_name = textfield_lastname.getText();
				String email = textfield_email.getText();
				String address = textfield_address.getText();
				String username = textfield_username.getText();
				char[] password_char = textfield_password.getPassword();
				String pass = new String(password_char);
				char[] confirmpass_char = textfield_confirmpassword.getPassword();
				String confirmpass = new String(confirmpass_char);
				
				if( (first_name.equals("")==true || last_name.equals("")==true || email.equals("")==true || address.equals("")==true || username.equals("")==true || pass.equals("")==true ||confirmpass.equals(""))==true )
				{
					JOptionPane.showMessageDialog(null, "Please enter all details", "Invalid Credentials", 2);
				}
				else
				{
					String dob_string;
					try {
					Date dob = dateChooser.getDate();
					DateFormat df = DateFormat.getDateInstance();
					df = DateFormat.getDateInstance(DateFormat.DEFAULT,new Locale("da","DK"));
					dob_string = df.format(dob);
					if(pass.equals(confirmpass)==false)
					{
						textfield_password.setBorder(new LineBorder(Color.RED, 2));
						textfield_confirmpassword.setBorder(new LineBorder(Color.RED, 2));
						textfield_password.setText(""); 
						textfield_confirmpassword.setText("");
						JOptionPane.showMessageDialog(null,"Passwords don't match. Please enter the password again","Error", 2);
					}
					else 
					{
						Connection con = null;
						try {
							Class.forName("com.mysql.jdbc.Driver");
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
							
							Statement stmt = con.createStatement();
							String sql = "SELECT * FROM user_database WHERE username='"+username+"'   ";
							ResultSet rs =stmt.executeQuery(sql);
							if(rs.next()) {
								JOptionPane.showMessageDialog(null,"Username already taken . Please enter new one","Error", 2);
								textfield_username.setText("");
							}
							else {

								PreparedStatement ps = con.prepareStatement("INSERT INTO user_database (username,password,first_name,last_name,email,address,dob) VALUES('"+username+"','"+pass+"','"+first_name+"','"+last_name+"','"+email+"','"+address+"','"+dob_string+"')");
								ps.executeUpdate(); //query will receive info , update manipulate/add info	
								dispose();
								(new SignIn()).setVisible(true);
								ps.close();
								JOptionPane.showMessageDialog(null, "Registered Successfully", "Message", 1);
								}
							}
							catch(Exception e1) {
							System.out.println(e1);
							}
					}
				}
				catch(NullPointerException efe) {
					JOptionPane.showMessageDialog(null, "Please enter date of birth", "Invalid Credentials", 2);
				}
				}
			}
		});
				
					
				
				
			
		
	}
}
				
		
		
	
				
				
			
				
		
				
				

		
		
		
	
		
			
	
