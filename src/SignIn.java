import java.awt.*;
import java.sql.*;
import java.awt.Event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField username_textfield;
	private JPasswordField password_textfield;
	private String username_string;
	private char[] password_char;
	private String password_string;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
	 */
	//@SuppressWarnings("deprecation")
	public SignIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 686);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(370, 66, 397, 497);
		contentPane.add(panel);
		panel.setBackground(new Color(255,255,255,200));
		panel.setLayout(null);
		
		JLabel singin_button = new JLabel("   Sign in");
		singin_button.setForeground(new Color(112, 112, 112));
		singin_button.setFont(new Font("SansSerif", Font.BOLD, 33));
		singin_button.setBounds(116, 30, 165, 78);
		panel.add(singin_button);
		
		JLabel username_label = new JLabel("Username");
		username_label.setForeground(new Color(112, 112, 112));
		username_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		username_label.setBounds(50, 153, 137, 35);
		panel.add(username_label);
		
		username_textfield = new JTextField();
		username_textfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username_textfield.setForeground(Color.BLACK);
		username_textfield.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		username_textfield.setBounds(60, 200, 243, 35);
		panel.add(username_textfield);
		username_textfield.setBackground(Color.WHITE);
		username_textfield.setColumns(10);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(112, 112, 112));
		password_label.setFont(new Font("SansSerif", Font.BOLD, 24));
		password_label.setBounds(50, 275, 137, 35);
		panel.add(password_label);
		
		password_textfield = new JPasswordField();	
		password_textfield.setBounds(60, 323, 243, 35);
		password_textfield.setBorder(new LineBorder(new Color(112, 112, 112), 2));
		password_textfield.setBackground(Color.WHITE);
		panel.add(password_textfield);
		
		
		
		JLabel suggestion_singup_label = new JLabel("Don't have an account?");
		suggestion_singup_label.setForeground(new Color(112, 112, 112));
		suggestion_singup_label.setFont(new Font("SansSerif", Font.BOLD, 18));
		suggestion_singup_label.setBounds(12, 448, 243, 35);
		panel.add(suggestion_singup_label);
		
		JLabel singin_label = new JLabel("Sign up");
		singin_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				(new SignUp()).setVisible(true);
			}
		});
		
		singin_label.setForeground(new Color(59,203,232));
		singin_label.setFont(new Font("SansSerif", Font.BOLD, 18));
		singin_label.setBounds(287, 447, 98, 36);
		panel.add(singin_label);
		
		JButton signin_button = new JButton("Sign in");
		signin_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username_string = username_textfield.getText();
				password_char = password_textfield.getPassword();
				password_string = new String(password_char);

				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					
					Statement stmt = con.createStatement();
					String sql = "Select * FROM user_database where username ='"+username_string+"' and password ='"+password_string+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						dispose();
						(new SearchHotels(username_string)).setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username/Password", "Invalid Credentials", 2);
						password_textfield.setText("");
					}
			
					}catch(Exception e) {
					System.out.println(e);
					}
				
			}
		});
		signin_button.setBorder(null);
		signin_button.setFont(new Font("SansSerif", Font.BOLD, 21));
		signin_button.setForeground(Color.WHITE);
		signin_button.setBackground(new Color(59,203,232));
		signin_button.setBounds(127, 390, 118, 33);
		panel.add(signin_button);
		
		JLabel backgroundimage_label = new JLabel("");
		backgroundimage_label.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/UI Data/Sign in background.png"));
		backgroundimage_label.setBounds(0, 0, 1139, 646);
		contentPane.add(backgroundimage_label);
		
	
	}
}