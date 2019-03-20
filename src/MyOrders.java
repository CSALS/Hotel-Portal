import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MyOrders extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTable booked_table;
	public int no_of_rows_deleted=0;
	private JTable waitinglist_table;

	/**
	 * Launch the application.
	 */

	
	public int cancel(String ref_id) {
		Connection con =null;
		int flag =1;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
			String temp="";
			String city = "",hotelname = "",checkin_date="",checkout_date="",username="";
			int price=0;
			int rooms = 0;
			Statement stmt = con.createStatement();
			String sql = "select * FROM user_booking_data where reference_id = '"+ref_id+"' ";
			ResultSet rs1 = stmt.executeQuery(sql);
			if(rs1.next()) {
				city = rs1.getString("city");
				hotelname = rs1.getString("hotelname");
				checkin_date = rs1.getString("checkin"); //to be cancelled date
				checkout_date = rs1.getString("checkout");
				price = rs1.getInt("price");
				
			}
			int diff=0;
			int no_of_days=0;
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			try {
				Date checkin = df.parse(checkin_date);
				Date cin = df.parse(df.format(checkin));
				Date present = new Date();
				Date today = df.parse(df.format(present));
				
				
				System.out.println(cin);
				System.out.println(today);
				
				 diff = (int) (checkin.getTime() - present.getTime()) ;
				  no_of_days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				 System.out.println(no_of_days+"  isssss");
				 System.out.println(cin.equals(today));
				  if(cin.equals(today)==false) no_of_days++;
				 System.out.println(no_of_days);
				//  System.out.println(TimeUnit.DAYS.convert((checkin.getTime() - present.getTime()), TimeUnit.MILLISECONDS));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = 1;
			if(no_of_days>=3) {
				 dialogResult = JOptionPane.showConfirmDialog(null,"No extra cancellation charges.Do you want to continue?","Confirmation",dialogButton);
			}
			else if(no_of_days == 2  || no_of_days ==1) {
				dialogResult = JOptionPane.showConfirmDialog(null,"You will be charged Rs. '"+(price/2)+"'. Do you want to continue?","Confirmation",dialogButton);
			}
			else if(no_of_days==0) {
				JOptionPane.showMessageDialog(null, "Sorry you can't cancel this order.", "Message", 2);
			}
			
			
			
			
			if(dialogResult == 0) { //yes
				//rs1.close();
				//System.out.println("yes");
				String query = "select username,cin,cout,rooms,price from waiting_list where city = '"+city+"' AND hotelname  = '"+hotelname+"' " ;
				ResultSet rs = con.createStatement().executeQuery(query);
				String checkin , checkout;
				while(rs.next()) {
					username = rs.getString("username");
					checkin = rs.getString("cin"); //required
					checkout = rs.getString("cout");
					rooms = rs.getInt("rooms");
					price = rs.getInt("price");
					
					//(checkin <= '"+checkin_date+"' AND checkout >= '"+checkout_date+"') OR  (checkin>='"+checkin_date+"' AND checkin<'"+checkout_date+"') OR (checkout > '"+checkin_date+"' AND checkout <= '"+checkout_date+"') )  ";
					
					sql = "select checkin,checkout from user_booking_data where reference_id = '"+ref_id+"'AND ( (checkin<='"+checkin+"' AND checkout>='"+checkout+"') OR (checkin>='"+checkin+"' AND checkin<'"+checkout+"') OR (checkout>'"+checkin+"' AND checkout<='"+checkout+"') )    ";
					ResultSet rs_1 = con.createStatement().executeQuery(sql);
					if(rs_1.next()) {
						
						sql = "select checkin,checkout from user_booking_data where city='"+city+"' AND reference_id != '"+ref_id+"' AND hotelname = '"+hotelname+"' AND ( (checkin<='"+checkin+"' AND checkout>='"+checkout+"') OR (checkin>='"+checkin+"' AND checkin<'"+checkout+"') OR (checkout>'"+checkin+"' AND checkout<='"+checkout+"') )     ";
						ResultSet rs_2 = con.createStatement().executeQuery(sql);
						if(rs_2.next()) {
							continue;
						}
						else {
							//guy can book
							PreparedStatement p = con.prepareStatement("DELETE from waiting_list where username='"+username+"' AND city='"+city+"' AND hotelname='"+hotelname+"' AND cin = '"+checkin+"' AND cout = '"+checkout+"' ");
							p.executeUpdate();
							p.close();
							//update these values in user_booking_data with serial generator
							Serial_Generator ref = new Serial_Generator();
							int id = ref.serial_generator();
							String r = "TN" +new SimpleDateFormat("ddMMyyyy").format(new Date()) +id ;
							p = con.prepareStatement("INSERT INTO user_booking_data VALUES(?,?,?,?,?,?,?,?,?)");
							p.setString(1, username);
							p.setString(2, city);
							p.setString(3, hotelname);
							p.setString(4, checkin);
							p.setString(5, checkout);
							p.setInt(6, rooms);
							p.setString(7, r);
							p.setString(8, "BOOKED");
							p.setInt(9,price); //////////////////
							p.executeUpdate();
							p.close();
							break;
						}
						
					}
					else {
						continue;
					}
					
					
					
				}
				
				
				PreparedStatement ps = con.prepareStatement("DELETE FROM user_booking_data where reference_id = '"+ref_id+"' ");
				ps.executeUpdate();
				//ps.close();
				temp="cancel";
				ps = con.prepareStatement("INSERT INTO reference VALUES('"+temp+"')");
				ps.executeUpdate();
				ps.close();
				
			}
			
			else if(dialogResult == 1) {
				flag = -1;
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * Create the frame.
	 */
	public MyOrders(String username) {
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
		
		JLabel title_label = new JLabel("My Orders");
		title_label.setFont(new Font("SansSerif", Font.BOLD, 30));
		title_label.setForeground(new Color(112, 112, 112));
		title_label.setBounds(392, 13, 204, 46);
		panel.add(title_label);
		
		booked_table = new JTable();
		booked_table.setBackground(new Color(255,255,255,150));
		booked_table.setBounds(93, 129, 820, 149);
		panel.add(booked_table);
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_Portal","root","");
			
			Statement stmt = con.createStatement();
			
			String sql = "Select * FROM user_booking_data where username = '"+username+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			booked_table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			//rs = stmt.executeQuery("select * from waiting_list where username = '"+username+"'");
			//table.setModel(DbUtils.resultSetToTableModel(rs));
			//rs.close();
			stmt.close();
			
			JButton cancel_button = new JButton("CANCEL");
			cancel_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String ref_id = JOptionPane.showInputDialog(null,new JLabel("Enter the booking id"),"Enter the details", 1);
					 try {
						 if(ref_id.equals("")==true) { ref_id=null; }
					 }catch(NullPointerException e) {
						 
					 }
					if(ref_id!=null) {
						//System.out.println("called1");
						int flag = cancel(ref_id);
						//System.out.println("called2");
						if(flag==1)
						{
						JOptionPane.showMessageDialog(null, "Cancelled your order", "Message", 1); }
					}
				}
			});
			cancel_button.setBounds(694, 67, 136, 41);
			panel.add(cancel_button);
			cancel_button.setForeground(Color.WHITE);
			cancel_button.setFont(new Font("SansSerif", Font.BOLD, 21));
			cancel_button.setBorder(null);
			cancel_button.setBackground(new Color(59, 203, 232));
			
			
			JButton btnBack = new JButton("BACK");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					(new MyAccount(username)).setVisible(true);
				}
			});
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("SansSerif", Font.BOLD, 21));
			btnBack.setBorder(null);
			btnBack.setBackground(new Color(59, 203, 232));
			btnBack.setBounds(93, 67, 136, 41);
			panel.add(btnBack);
			
			waitinglist_table = new JTable();
			waitinglist_table.setBackground(new Color(255, 255, 255, 150));
			waitinglist_table.setBounds(93, 322, 820, 149);
			panel.add(waitinglist_table);
			rs = con.createStatement().executeQuery("select * from waiting_list where username = '"+username+"'");
			waitinglist_table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		JLabel backgroundimage_label = new JLabel("");
		backgroundimage_label.setIcon(new ImageIcon("/home/csals/git_workspace/OOPS_Mini-Project/Hotel_Portal/Resources/UI Data/My Account background.png"));
		backgroundimage_label.setBounds(0, 0, 1139, 646);
		contentPane.add(backgroundimage_label);
	}
}
