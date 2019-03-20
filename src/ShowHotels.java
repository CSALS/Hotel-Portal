import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class ShowHotels extends JFrame {

	public JPanel contentPane;
	private JLabel label_hotel1,label_hotel2,label_hotel3;
	private JButton button_hotel1,button_hotel2,button_hotel3;
	private JLabel totalprice_1,totalprice_2,totalprice_3;
	private int totalprice;
	private JLabel avgrating_1,avgrating_2,avgrating_3;
	//private AtomicInteger ref_id = new AtomicInteger();
	
	/**
	 * Launch the application.
	 */

	
	public String getDetails() {
		return JOptionPane.showInputDialog(null,new JLabel("Enter your Aadhar Number"),"Enter the details", 1);
	}
	
	
	
	public void room_availability(String hotelname,int capacity,String city,int rooms_req,String checkin_date,String checkout_date,int i,int pricepernight,long no_of_days) {
		//Begin
				//Calculating number of rooms available and displaying either rooms available,book or rooms not available,enroll in waiting list
		float av=0;
			int no_of_rooms_booked=0;
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					 stmt = con.createStatement();
					String sql = "Select rooms FROM user_booking_data where city='"+city+"' AND hotelname='"+hotelname+"' AND (  (checkin <= '"+checkin_date+"' AND checkout >= '"+checkout_date+"') OR  (checkin>='"+checkin_date+"' AND checkin<'"+checkout_date+"') OR (checkout > '"+checkin_date+"' AND checkout <= '"+checkout_date+"') )  ";
					rs = stmt.executeQuery(sql);
					
					int rooms =0;
					while(rs.next()) {
						
						rooms = rs.getInt("rooms");
						no_of_rooms_booked = no_of_rooms_booked + rooms;
						
					}	
					rs.close();
					
					//Statement stmt = con.createStatement();
					sql = "select rating from rating where hotelname = '"+hotelname+"'  ";
					rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					if(count!=0) av = sum/count;
				
					
					
				}catch(Exception e) {
					System.out.println(e);
				}finally {
					try {
						if(rs!=null) rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						if(stmt!=null)stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						if(con!=null) con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				totalprice = (int) (no_of_days * pricepernight * rooms_req);
				if(i==0) {
					
					totalprice_1.setText("Rs. "+totalprice);
					if (rooms_req > capacity) {
						label_hotel1.setText("Capacity exceeded");
						label_hotel1.setForeground(Color.RED);
						button_hotel1.setText("");
						button_hotel1.setVisible(false);
						button_hotel1.setForeground(Color.WHITE);
						button_hotel1.setBackground(new Color(59,203,232));
						avgrating_1.setText(av+"/5");
					}
					else if((capacity-no_of_rooms_booked) >= rooms_req) {
						//Rooms available
						label_hotel1.setText("Rooms available");
						label_hotel1.setForeground(Color.GREEN);
						button_hotel1.setText("BOOK");
						button_hotel1.setForeground(Color.WHITE);
						button_hotel1.setBackground(new Color(59,203,232));
						avgrating_1.setText(av+"/5");
						
						
						
						
					}
					
					else {
						//Rooms Not Available
						label_hotel1.setText("Rooms not available");
						label_hotel1.setForeground(Color.RED);
						button_hotel1.setText("Enroll in waiting list");
						button_hotel1.setForeground(Color.WHITE);
						button_hotel1.setBackground(new Color(59,203,232));
						avgrating_1.setText(av+"/5");
					}
				}
				
				else if(i==1) {
					totalprice_2.setText("Rs. "+totalprice);
					if (rooms_req > capacity) {
						label_hotel2.setText("Capacity exceeded");
						label_hotel2.setForeground(Color.RED);
						button_hotel2.setText("");
						button_hotel2.setVisible(false);
						avgrating_2.setText(av+"/5");
				
					}
					else if((capacity-no_of_rooms_booked) >= rooms_req) {
						//Rooms available
						label_hotel2.setText("Rooms available");
						label_hotel2.setForeground(Color.GREEN);
						button_hotel2.setText("BOOK");
						button_hotel2.setForeground(Color.WHITE);
						button_hotel2.setBackground(new Color(59,203,232));
						avgrating_2.setText(av+"/5");
						
					}
					else {
						//Rooms Not Available
						label_hotel2.setText("Rooms not available");
						label_hotel2.setForeground(Color.RED);
						button_hotel2.setText("Enroll in waiting list");
						button_hotel2.setForeground(Color.WHITE);
						button_hotel2.setBackground(new Color(59,203,232));
						avgrating_2.setText(av+"/5");
					}
				}
				
				else if(i==2) {
					totalprice_3.setText("Rs. "+totalprice);
					if (rooms_req > capacity) {
						label_hotel3.setText("Capacity exceeded");
						label_hotel3.setForeground(Color.RED);
						button_hotel3.setText("");
						button_hotel3.setVisible(false);
						avgrating_3.setText(av+"/5");
					}
					else if((capacity-no_of_rooms_booked) >= rooms_req) {
						//Rooms available
						label_hotel3.setText("Rooms available");
						label_hotel3.setForeground(Color.GREEN);
						button_hotel3.setText("BOOK");
						button_hotel3.setForeground(Color.WHITE);
						button_hotel3.setBackground(new Color(59,203,232));
						avgrating_3.setText(av+"/5");
						
					}
					else {
						//Rooms Not Available
						label_hotel3.setText("Rooms not available");
						label_hotel3.setForeground(Color.RED);
						button_hotel3.setText("Enroll in waiting list");
						button_hotel3.setForeground(Color.WHITE);
						button_hotel3.setBackground(new Color(59,203,232));
						avgrating_3.setText(av+"/5");
					}
				}
				}
				
				
				
				//End
	
	
	
	/**
	 * Create the frame.
	 */
	public ShowHotels(String city,String checkin_date,String checkout_date,String username,int rooms_req,int no_of_days) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 59, 993, 22);
		contentPane.add(separator);
		
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
		btnMyAccount.setBounds(904, 5, 136, 41);
		contentPane.add(btnMyAccount);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();	(new SearchHotels(username)).setVisible(true);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 21));
		btnBack.setBorder(null);
		btnBack.setBackground(new Color(59, 203, 232));
		btnBack.setBounds(89, 5, 136, 41);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(74, 82, 993, 529);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//HOTEL - 1
				JPanel hotel1 = new JPanel();
				hotel1.setBackground(Color.WHITE);
				hotel1.setBounds(217, 82, 708, 529);
				hotel1.setLayout(null);
				
				
				JButton button_2 = new JButton("2");
				button_2.setBounds(245, 491, 69, 25);
				JButton button_3 = new JButton("3");
				button_3.setBounds(326, 491, 62, 25);
			
				
				hotel1.add(button_2);
				hotel1.add(button_3);
				
				JLabel hotel1_image = new JLabel("");
				hotel1_image.setBounds(47, 73, 480, 380);
				hotel1.add(hotel1_image);
				
				JLabel name_label_1 = new JLabel("");
				name_label_1.setFont(new Font("Tahoma", Font.BOLD, 27));
				name_label_1.setBounds(173, 13, 184, 28);
				hotel1.add(name_label_1);
				
				label_hotel1 = new JLabel("");
				label_hotel1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				label_hotel1.setBounds(750, 368, 169, 16);
				hotel1.add(label_hotel1);
				
				button_hotel1 = new JButton("");
				button_hotel1.setBorder(null);
				button_hotel1.setFont(new Font("Tahoma", Font.BOLD, 21));
				button_hotel1.setBounds(715, 397, 245, 56);
				hotel1.add(button_hotel1);
				
				JLabel pernight_1 = new JLabel("");
				pernight_1.setForeground(Color.BLACK);
				pernight_1.setFont(new Font("Tahoma", Font.BOLD, 23));
				pernight_1.setBounds(750, 149, 140, 39);
				hotel1.add(pernight_1);
				
				totalprice_1 = new JLabel("");
				totalprice_1.setForeground(Color.GREEN);
				totalprice_1.setFont(new Font("Tahoma", Font.BOLD, 23));
				totalprice_1.setBounds(750, 252, 140, 39);
				hotel1.add(totalprice_1);
				
				JLabel pernight_label_1 = new JLabel("Price per night");
				pernight_label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				pernight_label_1.setBounds(759, 112, 160, 25);
				hotel1.add(pernight_label_1);
				
				JLabel totalprice_label_1 = new JLabel("Total price");
				totalprice_label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				totalprice_label_1.setBounds(770, 214, 120, 25);
				hotel1.add(totalprice_label_1);
				
				JButton rating_1 = new JButton("Rate This Hotel");
				rating_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				rating_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
				rating_1.setBounds(723, 16, 196, 39);
				hotel1.add(rating_1);
				
				JLabel givenrating_1 = new JLabel("Rating");
				givenrating_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
				givenrating_1.setBounds(568, 188, 79, 25);
				hotel1.add(givenrating_1);
				
				 avgrating_1 = new JLabel("");
				avgrating_1.setBounds(578, 223, 69, 25);
				hotel1.add(avgrating_1);
				
				
		
		//HOTEL - 2
				JPanel hotel2 = new JPanel();
				hotel2.setBackground(Color.WHITE);
				hotel2.setBounds(217, 82, 708, 529);
				hotel2.setLayout(null);
				
				JButton button_11 = new JButton("1");
				button_11.setBounds(245, 491, 69, 25);
				
				JButton button_33 = new JButton("3");
				button_33.setBounds(326, 491, 62, 25);
			
				hotel2.add(button_11);
				hotel2.add(button_33);
				
				JLabel hotel2_image = new JLabel("");
				hotel2_image.setBounds(48, 54, 480, 380);
				hotel2.add(hotel2_image);
				
				JLabel name_label_2 = new JLabel("");
				name_label_2.setFont(new Font("Tahoma", Font.BOLD, 27));
				name_label_2.setBounds(99, 13, 306, 28);
				hotel2.add(name_label_2);
				
				label_hotel2 = new JLabel("");
				label_hotel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
				label_hotel2.setBounds(750, 368, 169, 16);
				hotel2.add(label_hotel2);
				
				 button_hotel2 = new JButton("");
				button_hotel2.setBorder(null);
				button_hotel2.setFont(new Font("Tahoma", Font.BOLD, 21));
				button_hotel2.setBounds(715, 397, 245, 56);
				hotel2.add(button_hotel2);
				
				JLabel pernight_2 = new JLabel("");
				pernight_2.setForeground(Color.BLACK);
				pernight_2.setFont(new Font("Tahoma", Font.BOLD, 23));
				pernight_2.setBounds(750, 150, 140, 39);
				hotel2.add(pernight_2);
				
				totalprice_2 = new JLabel("");
				totalprice_2.setForeground(Color.GREEN);
				totalprice_2.setFont(new Font("Tahoma", Font.BOLD, 23));
				totalprice_2.setBounds(750, 252, 140, 39);
				hotel2.add(totalprice_2);
				
				JLabel pernight_label_2 = new JLabel("Price per night");
				pernight_label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				pernight_label_2.setBounds(759, 112, 120, 25);
				hotel2.add(pernight_label_2);
				
				JLabel totalprice_label_2 = new JLabel("Total price");
				totalprice_label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				totalprice_label_2.setBounds(770, 214, 120, 25);
				hotel2.add(totalprice_label_2);
				
				
				
				JButton rating_2 = new JButton("Rate This Hotel");
				rating_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
				rating_2.setBounds(723, 16, 196, 39);
				hotel2.add(rating_2);
				
				JLabel givenrating_2 = new JLabel("Rating");
				givenrating_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
				givenrating_2.setBounds(568, 188, 79, 25);
				hotel2.add(givenrating_2);
				
				 avgrating_2 = new JLabel("");
				avgrating_2.setBounds(578, 223, 69, 25);
				hotel2.add(avgrating_2);
		
		
		//HOTEL - 3
				JPanel hotel3 = new JPanel();
				hotel3.setBackground(Color.WHITE);
				hotel3.setBounds(217, 82, 708, 529);
				hotel3.setLayout(null);
				
				JButton button_111 = new JButton("1");
				button_111.setBounds(245, 491, 69, 25);
				JButton button_222 = new JButton("2");
				button_222.setBounds(326, 491, 62, 25);
				
			
				hotel3.add(button_111);
				hotel3.add(button_222);
				
				JLabel hotel3_image = new JLabel("");
				hotel3_image.setBounds(48, 54, 480, 380);
				hotel3.add(hotel3_image);
				
				JLabel name_label_3 = new JLabel("");
				name_label_3.setFont(new Font("Tahoma", Font.BOLD, 27));
				name_label_3.setBounds(126, 13, 231, 28);
				hotel3.add(name_label_3);
				
				label_hotel3 = new JLabel("");
				label_hotel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
				label_hotel3.setBounds(750, 368, 169, 16);
				hotel3.add(label_hotel3);
				
				button_hotel3 = new JButton("");
				button_hotel3.setBorder(null);
				button_hotel3.setFont(new Font("Tahoma", Font.BOLD, 21));
				button_hotel3.setBounds(715, 397, 245, 56);
				hotel3.add(button_hotel3);
				
				JLabel pernight_3 = new JLabel("");
				pernight_3.setForeground(Color.BLACK);
				pernight_3.setFont(new Font("Tahoma", Font.BOLD, 23));
				pernight_3.setBounds(750, 150, 140, 39);
				hotel3.add(pernight_3);
				
				totalprice_3 = new JLabel("");
				totalprice_3.setForeground(Color.GREEN);
				totalprice_3.setFont(new Font("Tahoma", Font.BOLD, 23));
				totalprice_3.setBounds(750, 252, 140, 39);
				hotel3.add(totalprice_3);
				
				JLabel pernight_label_3 = new JLabel("Price per night");
				pernight_label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
				pernight_label_3.setBounds(759, 112, 120, 25);
				hotel3.add(pernight_label_3);
				
				JLabel totalprice_label_3= new JLabel("Total price");
				totalprice_label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
				totalprice_label_3.setBounds(770, 214, 120, 25);
				hotel3.add(totalprice_label_3);
				
				JButton rating_3 = new JButton("Rate This Hotel");
				rating_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
				rating_3.setBounds(723, 16, 196, 39);
				hotel3.add(rating_3);
				
				JLabel givenrating_3 = new JLabel("Rating");
				givenrating_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
				givenrating_3.setBounds(568, 188, 79, 25);
				hotel3.add(givenrating_3);
				
				 avgrating_3 = new JLabel("");
				avgrating_3.setBounds(578, 223, 69, 25);
				hotel3.add(avgrating_3);
				

		CardLayout c = new CardLayout();
		panel.setLayout(c);
		panel.add(hotel1,"1");
		
		
		
		
		
		
		
		panel.add(hotel2,"2");
		panel.add(hotel3,"3");
		
		
		
		
		
		
		
		
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"2");
			}
		});
		button_222.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"2");
			}
		});
		button_111.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"1");
			}
		});
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"1");
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"3");
			}
		});
		button_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel,"3");
			}
		});
		
		if(city.equals("Hyderabad")) {
			
			
			hotel1_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/HYD/The Park Hyderabad/1.JPG"));
			name_label_1.setText("The Park");	
			
			

			hotel2_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/HYD/Trident Hyderabad/1.JPG"));
			name_label_2.setText("Trident");
			
			
			
			
			hotel3_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/HYD/FabHotel Tanisha Jubilee Hills/1.JPG"));
			name_label_3.setText("Fab Hotel");
			
			
			
			
			rating_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"') ");
					ps.setString(1, "The Park");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"The Park"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_1.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			rating_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Trident");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Trident"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_2.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			
			rating_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Fab Hotel");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Fab Hotel"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_3.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			
			pernight_1.setText("Rs. 1000");
			pernight_2.setText("Rs. 1200");
			pernight_3.setText("Rs. 1500");
			
			String s[] = {"The Park","Trident","Fab Hotel"};
			int capacity[] = {50,60,30};
			int price[]= {1000,1200,1500};
			for(int i=0;i<3;i++) {
			room_availability(s[i],capacity[i],city,rooms_req,checkin_date,checkout_date,i,price[i],no_of_days);
			}
			
			
			
		}
		
		else if(city.equals("Delhi")) {
			
			hotel1_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/DELHI/Mayank Residency/1.JPG"));
			name_label_1.setText("Mayank Residency");	

			hotel2_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/DELHI/Piccadily Hotel/2.JPG"));
			name_label_2.setText("Piccadily Hotel");
			
			hotel3_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/DELHI/Fraser Suites New Delhi/1.JPG"));
			name_label_3.setText("Fraser Suites");
			
			pernight_1.setText("Rs. 1100");
			pernight_2.setText("Rs. 1500");
			pernight_3.setText("Rs. 1000");
			
			rating_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Mayank Residency");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Mayank Residency"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_1.setText(av+"/5");*/
					
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			rating_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Piccadily Hotel");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Piccadily Hotel"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_2.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			
			rating_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Fraser Suites");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Fraser Suites"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_3.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			String s[] = {"Mayank Residency","Piccadily Hotel","Fraser Suites"};
			int capacity[] = {70,110,150};
			int price[] = {1100,1500,1000};
			for(int i=0;i<3;i++) {
				room_availability(s[i],capacity[i],city,rooms_req,checkin_date,checkout_date,i,price[i],no_of_days);
				}
			
		}
		
		else if(city.equals("Chennai")) {
			
			hotel1_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/CHENNAI/Clarion Hotel President/1.JPG"));
			name_label_1.setText("Clarion Hotel President");	

			hotel2_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/CHENNAI/The Park Chennai\\4.JPG"));
			name_label_2.setText("The Park Chennai");
			
			hotel3_image.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/Images/CHENNAI/Treebo Laxvas\\1.JPG"));
			name_label_3.setText("Treebo Laxvas");
			
			pernight_1.setText("Rs. 2500");
			pernight_2.setText("Rs. 800");
			pernight_3.setText("Rs. 10000");
			
			rating_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Clarion Hotel President");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Clarion Hotel President"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_1.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			rating_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "The Park Chennai");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"The Park Chennai"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_2.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			
			rating_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String rating_given = JOptionPane.showInputDialog(null,new JLabel("Please rate out 5"),"Give rating", 1);
					int rating = (int)Float.parseFloat(rating_given);
					Connection con = null;
					try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
					PreparedStatement ps = con.prepareStatement("INSERT into rating VALUES(?,'"+rating+"'");
					ps.setString(1, "Treebo Laxvas");
					ps.executeUpdate();
					ps.close();
					/*Statement stmt = con.createStatement();
					String sql = "select rating from rating where hotelname = '"+"Treebo Laxvas"+"'  ";
					ResultSet rs = stmt.executeQuery(sql);
					int sum=0,count=0;
					while(rs.next()) {
						int r = rs.getInt("rating");
						count++;
						sum+=r;
					}
					float av = sum/count;
					avgrating_3.setText(av+"/5");*/
					
					}catch(Exception ef) {
						ef.printStackTrace();
					}
						
				}
			});
			
			String s[] = {"Clarion Hotel President","The Park Chennai","Treebo Laxvas"};
			int price[] = {2500,800,10000};
			int capacity[] = {30,125,25};
			for(int i=0;i<3;i++) {
				room_availability(s[i],capacity[i],city,rooms_req,checkin_date,checkout_date,i,price[i],no_of_days);
				}
			
		}
		
		final String city_name = city;
		
		//action performed when user clicks button1
		button_hotel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if((button_hotel1.getText()).equals("BOOK")) {
					
					Serial_Generator ref = new Serial_Generator();
					int id = ref.serial_generator();
					
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to confirm the booking?","Confirmation",dialogButton);
					if(dialogResult == 0) //Yes 
					{
						String user_input = getDetails();
						
						if((user_input.length()==12)==false) {
							JOptionPane.showMessageDialog(null,"Please enter valid 12 digit Aadhar number","Message",2);
						}
						else {
							
							String ref_id = "TN" +new SimpleDateFormat("ddMMyyyy").format(new Date()) +id ;
							JOptionPane.showMessageDialog(null,ref_id, "Your Reference booking number", 1);
							
							Connection con = null;
							try {
								Class.forName("com.mysql.jdbc.Driver");
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
								PreparedStatement ps = con.prepareStatement("INSERT INTO user_booking_data VALUES(?,?,?,?,?,?,?,?,?)");
								ps.setString(1,username);
								ps.setString(2,city_name); //doubttttttt
								ps.setString(3,name_label_1.getText());
								ps.setString(4,checkin_date);
								ps.setString(5,checkout_date);
								ps.setInt(6,rooms_req);
								ps.setString(7,ref_id);
								ps.setString(8, "BOOKED");
								ps.setInt(9,Integer.parseInt(totalprice_1.getText().substring(4)));
								//ps.setString(10,user_input);
								//ps.setString(3, city);
								ps.executeUpdate(); //query will receive info , update manipulate/add info	
								ps.close();
								}catch(Exception e1) {
								System.out.println(e1);
								}
							//city,String checkin_date,String checkout_date,String username,int rooms_req
							
							dispose();
							(new SearchHotels(username)).setVisible(true);
							JOptionPane.showMessageDialog(null,"You have successfully booked the hotel.Thank you", "Confirmation", 1);
							
						}
					}
				}
				else if((button_hotel1.getText()).equals("Enroll in waiting list")){ //Enroll in waiting list
					
					Connection con = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
						PreparedStatement ps = con.prepareStatement("INSERT INTO waiting_list VALUES(?,?,?,?,?,?,?,?) ");
						ps.setString(1, username);
						ps.setString(2,city_name);
						ps.setString(3,name_label_1.getText());
						ps.setString(4,checkin_date);
						ps.setString(5,checkout_date);
						ps.setInt(6,rooms_req);
						ps.setString(7, "in waiting list");
						ps.setInt(8,Integer.parseInt(totalprice_1.getText().substring(4)));
						ps.executeUpdate();
						ps.close();
						
					}catch(Exception e2) {
						e2.printStackTrace();
					}
					
					dispose();
					(new SearchHotels(username)).setVisible(true);
					JOptionPane.showMessageDialog(null,"You are in waiting list.Check My Orders on your status", "Confirmation", 1);
					
					
				}
				
			}
		});
		
		
		//action performed when user clicks button2 (2nd hotel)
				button_hotel2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						if((button_hotel2.getText()).equals("BOOK")) {
							Serial_Generator ref = new Serial_Generator();
							int id = ref.serial_generator();
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to confirm the booking?","Confirmation",dialogButton);
							if(dialogResult == 0) //Yes 
							{
								String user_input = getDetails();
								if((user_input.length()==12)==false) {
									JOptionPane.showMessageDialog(null,"Please enter valid 12 digit Aadhar number","Message",2);
								}
								else {
									String ref_id = "TN" +new SimpleDateFormat("ddMMyyyy").format(new Date()) +id ;
									JOptionPane.showMessageDialog(null,ref_id, "Your Reference booking number", 1);
									
		
									
									Connection con = null;
									try {
										Class.forName("com.mysql.jdbc.Driver");
										con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
										PreparedStatement ps = con.prepareStatement("INSERT INTO user_booking_data VALUES(?,?,?,?,?,?,?,?,?)");
										ps.setString(1,username);
										ps.setString(2,city_name); //doubttttttt
										ps.setString(3,name_label_2.getText());
										ps.setString(4,checkin_date);
										ps.setString(5,checkout_date);
										ps.setInt(6,rooms_req);
										ps.setString(7,ref_id);
										ps.setString(8,"BOOKED");
										ps.setInt(9, Integer.parseInt(totalprice_2.getText().substring(4)));
										
										//ps.setString(3, city);
										ps.executeUpdate(); //query will receive info , update manipulate/add info	
										}catch(Exception e1) {
										System.out.println(e1);
										}
									//city,String checkin_date,String checkout_date,String username,int rooms_req
									
									dispose();
									(new SearchHotels(username)).setVisible(true);
									JOptionPane.showMessageDialog(null,"You have successfully booked the hotel.Thank you", "Confirmation", 1);
									
								}
							}
						}
						else if((button_hotel2.getText()).equals("Enroll in waiting list")){ //Enroll in waiting list
							
							Connection con = null;
							try {
								Class.forName("com.mysql.jdbc.Driver");
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
								PreparedStatement ps = con.prepareStatement("INSERT INTO waiting_list VALUES(?,?,?,?,?,?,?,?) ");
								ps.setString(1, username);
								ps.setString(2,city_name);
								ps.setString(3,name_label_2.getText());
								ps.setString(4,checkin_date);
								ps.setString(5,checkout_date);
								ps.setInt(6,rooms_req);
								ps.setString(7, "in waiting list");
								ps.setInt(8,Integer.parseInt(totalprice_2.getText().substring(4)));
								ps.executeUpdate();
								ps.close();
								
							}catch(Exception e2) {
								e2.printStackTrace();
							}
							
							dispose();
							(new SearchHotels(username)).setVisible(true);
							JOptionPane.showMessageDialog(null,"You are in waiting list.Check My Orders on your status", "Confirmation", 1);
							
							
						}
						
					}
				});
		
				
				//action performed when user clicks button3
				button_hotel3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Serial_Generator ref = new Serial_Generator();
						int id = ref.serial_generator();
						if((button_hotel3.getText()).equals("BOOK")) {
							
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to confirm the booking?","Confirmation",dialogButton);
							if(dialogResult == 0) //Yes 
							{
								String user_input = getDetails();
								if((user_input.length()==12)==false) {
									JOptionPane.showMessageDialog(null,"Please enter valid 12 digit Aadhar number","Message",2);
								}
								else {
									String ref_id = "TN" +new SimpleDateFormat("ddMMyyyy").format(new Date()) +id ;
									JOptionPane.showMessageDialog(null,ref_id, "Your Reference booking number", 1);
									
									
									Connection con = null;
									try {
										Class.forName("com.mysql.jdbc.Driver");
										con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
										PreparedStatement ps = con.prepareStatement("INSERT INTO user_booking_data VALUES(?,?,?,?,?,?,?,?,?)");
										ps.setString(1,username);
										ps.setString(2,city_name); //doubttttttt
										ps.setString(3,name_label_2.getText());
										ps.setString(4,checkin_date);
										ps.setString(5,checkout_date);
										ps.setInt(6,rooms_req);
										ps.setString(7,ref_id);
										ps.setString(8, "BOOKED");
										ps.setInt(9,Integer.parseInt(totalprice_3.getText().substring(4)));
										
										//ps.setString(3, city);
										ps.executeUpdate(); //query will receive info , update manipulate/add info	
										}catch(Exception e1) {
										System.out.println(e1);
										}
									//city,String checkin_date,String checkout_date,String username,int rooms_req
									
									dispose();
									(new SearchHotels(username)).setVisible(true);
									JOptionPane.showMessageDialog(null,"You have successfully booked the hotel.Thank you", "Confirmation", 1);
									
								}
							}
						}
						else if((button_hotel3.getText()).equals("Enroll in waiting list")){ //Enroll in waiting list
							
							Connection con = null;
							try {
								Class.forName("com.mysql.jdbc.Driver");
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
								PreparedStatement ps = con.prepareStatement("INSERT INTO waiting_list VALUES(?,?,?,?,?,?,?,?) ");
								ps.setString(1, username);
								ps.setString(2,city_name);
								ps.setString(3,name_label_3.getText());
								ps.setString(4,checkin_date);
								ps.setString(5,checkout_date);
								ps.setInt(6,rooms_req);
								ps.setString(7, "in waiting list");
								ps.setInt(8,Integer.parseInt(totalprice_3.getText().substring(4)));
								ps.executeUpdate();
								ps.close();
								
							}catch(Exception e2) {
								e2.printStackTrace();
							}
							
							dispose();
							(new SearchHotels(username)).setVisible(true);
							JOptionPane.showMessageDialog(null,"You are in waiting list.Check My Orders on your status", "Confirmation", 1);
							
							
						}
						
					}
				});
		
	}
}