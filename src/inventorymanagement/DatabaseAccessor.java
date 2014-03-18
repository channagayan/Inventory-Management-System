/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bunny
 */
public class DatabaseAccessor {

    Statement statement=null;
    ResultSet rs=null;

   public DatabaseAccessor(String dbName) {

        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";      
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");

            statement=conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateDb(String str) {
        try {
            statement.executeUpdate(str);           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getResultSet(String str){
        try {
            rs = statement.executeQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

       return rs;
    }

    public String getDate(){

        String date=DateFormat.getInstance().format(new Date());

		

		StringTokenizer token=new StringTokenizer(date," /");

		String m,d,y;

		m=token.nextToken();
		d=token.nextToken();
		y=token.nextToken();

		date=y+"-"+m+"-"+d;

		return date;
    }

}
