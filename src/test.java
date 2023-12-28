
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class test {
    public static void main(String[] args)
    {                                        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        try {
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","root");
            Statement s=con.createStatement();
            ResultSet rs;
            rs = s.executeQuery("select * from questions");
            
           
            while(rs.next())
            {
                System.out.println(rs.getString(0));
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
            }
            
                   
            con.close();
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
