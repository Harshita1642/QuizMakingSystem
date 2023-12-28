package Connect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SWEET
 */
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class DBCon {
    Connection con;
    Statement s;
    public DBCon() {
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","1234");
            s=con.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    public int insertData(String q)
    {
        int r=0;
        try {
            r = s.executeUpdate(q);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(r>0)
            return 1;
        else
            return 0;
    }
    public ResultSet getData(String q)
    {
        ResultSet rs=null;
        try {
            rs=s.executeQuery(q);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return rs;
    }
    public void FillTable(JTable table,String query)
{
    try
    {
        DBCon con = new DBCon();
        ResultSet rs = con.getData(query);

        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
        
        
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    
}
    public String getQues(String str)
    {
        String id = "";
        ResultSet rs=getData("select * from questions where S_No='"+str+"'");
        try {
            while(rs.next())
            {
                id=rs.getString("Que");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return id;
    }
    public String getCorrectAns(String str)
    {
        String id = "";
        ResultSet rs=getData("select * from questions where S_No='"+str+"'");
        try {
            while(rs.next())
            {
                id=rs.getString("correctans");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return id;
    }
}
