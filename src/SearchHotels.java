import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;



public class SearchHotels extends JFrame {

	private JPanel contentPane;
	private JTextField textfield_rooms;
	private JTextField textfield_checkin;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SearchHotels(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 686);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 60, 973, 515);
		contentPane.add(panel);
		Color trans = new Color(0,0,2,130);
		panel.setBackground(trans);
		panel.setLayout(null);
		
		JLabel search_label = new JLabel("Search your dream hotel");
		search_label.setFont(new Font("SansSerif", Font.BOLD, 28));
		search_label.setForeground(Color.WHITE);
		search_label.setBounds(293, 38, 394, 41);
		panel.add(search_label);
		
		JLabel info_label = new JLabel("Please enter following details and search");
		info_label.setForeground(Color.WHITE);
		info_label.setFont(new Font("SansSerif", Font.BOLD, 22));
		info_label.setBounds(78, 126, 578, 41);
		panel.add(info_label);
		
		JLabel label_city = new JLabel("City");
		label_city.setForeground(Color.WHITE);
		label_city.setFont(new Font("SansSerif", Font.BOLD, 24));
		label_city.setBounds(78, 230, 171, 35);
		panel.add(label_city);
		
		JLabel label_rooms = new JLabel("No. of Rooms");
		label_rooms.setForeground(Color.WHITE);
		label_rooms.setFont(new Font("SansSerif", Font.BOLD, 24));
		label_rooms.setBounds(63, 292, 214, 35);
		panel.add(label_rooms);
		
		textfield_rooms = new JTextField();
		textfield_rooms.setColumns(10);
		textfield_rooms.setBounds(262, 296, 214, 35);
		panel.add(textfield_rooms);
		
		
		
		JLabel label_checkin = new JLabel("Check in date");
		label_checkin.setForeground(Color.WHITE);
		label_checkin.setFont(new Font("SansSerif", Font.BOLD, 24));
		label_checkin.setBounds(566, 230, 191, 35);
		panel.add(label_checkin);
		
		
		
		
		
		JLabel label_checkout = new JLabel("Check out date");
		label_checkout.setForeground(Color.WHITE);
		label_checkout.setFont(new Font("SansSerif", Font.BOLD, 24));
		label_checkout.setBounds(566, 296, 201, 35);
		panel.add(label_checkout);
		
		JLabel backgroundimage_label = new JLabel("");
		backgroundimage_label.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/UI Data/Search Hotel background.png"));
		backgroundimage_label.setBounds(0, 0, 1139, 646);
		contentPane.add(backgroundimage_label);
		
		
		JButton search_button = new JButton("Search");
		
		search_button.setBorder(null);
		search_button.setFont(new Font("SansSerif", Font.BOLD, 21));
		search_button.setForeground(Color.WHITE);
		search_button.setBackground(new Color(59,203,232));
		search_button.setBounds(429, 403, 136, 41);
		panel.add(search_button);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setEditable(false);
		comboBox.setBorder(null);
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(Color.WHITE);
		
		comboBox.setBounds(262, 231, 214, 35);
		panel.add(comboBox);
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				(new MyAccount(username)).setVisible(true);
			}
		});
		btnMyAccount.setForeground(Color.WHITE);
		btnMyAccount.setFont(new Font("SansSerif", Font.BOLD, 21));
		btnMyAccount.setBorder(null);
		btnMyAccount.setBackground(new Color(59, 203, 232));
		btnMyAccount.setBounds(825, 13, 136, 41);
		panel.add(btnMyAccount);
		
		
		
		Date current_date = new Date();
		//Date selected_date = null;
		JDateChooser check_in_date = new JDateChooser();
		
		
		check_in_date.setBorder(new LineBorder(Color.DARK_GRAY));
		check_in_date.setBounds(777, 234, 136, 35);
		check_in_date.setMinSelectableDate(current_date);
		panel.add(check_in_date);
		
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current_date);
		cal.add(Calendar.DATE,1);
		Date nextday_date = cal.getTime();
		
		JDateChooser check_out_date = new JDateChooser();
		check_out_date.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selected_date = check_in_date.getDate();
				
				Calendar cal = Calendar.getInstance();
				try {
				cal.setTime(selected_date); //error point
				cal.add(Calendar.DATE,1);
				Date nextday_date = cal.getTime();
				check_out_date.setMinSelectableDate(nextday_date);
				
				}catch(NullPointerException eee) {
					JOptionPane.showMessageDialog(null, "Please enter check-in date first", "Invalid Details", 2);
				}
			
				
				
			}
		});
		check_out_date.setBorder(new LineBorder(Color.DARK_GRAY));
		check_out_date.setBounds(777, 296, 136, 35);
		check_out_date.setMinSelectableDate(nextday_date);
		panel.add(check_out_date);
		
		
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String city,rooms,checkin = null,checkout = null;
				city = (String) comboBox.getSelectedItem();
				//System.out.println(city); ///////////
				rooms = textfield_rooms.getText();
				try {
					Date cin = check_in_date.getDate();
					Date cout = check_out_date.getDate();
					long diff = cout.getTime() - cin.getTime();
					int no_of_days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					
					
					
					DateFormat df = DateFormat.getDateInstance();
					df = DateFormat.getDateInstance(DateFormat.DEFAULT,new Locale("da","DK"));
					checkin = df.format(cin);
					checkout = df.format(cout);
					
					(new ShowHotels(city,checkin,checkout,username,Integer.parseInt(rooms),no_of_days)).setVisible(true);
					dispose();
				}catch(Exception ef) {
					JOptionPane.showMessageDialog(null, "Please enter all details", "Invalid Details", 2);
				}
				
				
				
				
				
				
				
			}
		});
	
		
		/*JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBorder(new LineBorder(Color.DARK_GRAY));
		dateChooser.setBounds(773, 283, 171, 35);
		panel.add(dateChooser);*/
		
		comboBox.addItem("Hyderabad");
		comboBox.addItem("Delhi");
		comboBox.addItem("Chennai");
		
		
		
	
		
		
		
	}
}