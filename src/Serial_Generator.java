import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Serial_Generator {

		int value;
		
		public int serial_generator() {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			int value=0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_data_table","root","");
				 stmt = con.createStatement();
				String sql = "Select count(reference_id)+1 FROM user_booking_data";
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					value = Integer.parseInt(rs.getString(1)); //+
				}
				rs.close();
				
				String query = "select count(dummy) from reference";
				rs=stmt.executeQuery(query);
				if(rs.next()) {
					value += Integer.parseInt(rs.getString(1));
				}
				
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
			
			return value;
		}
}