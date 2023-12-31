

import Connect.DBCon;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.lang.Math;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class QuizSystem extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form QuizSystem
     */
    String username;
    int Rollno=1;
    int Rid=1;
    int marks=0;
    int RRid=1;
    JPanel p;
    Timer t;
    DBCon con=new DBCon();
    Map<Integer,String> map=new HashMap<Integer,String>();
    Map<Integer,Integer> result=new HashMap<Integer,Integer>();
    public QuizSystem(String username) throws SQLException {
        initComponents();
        this.username=username;
        footerPanel.add(new Clock());
        t=new Timer(this);
        jPanel1.add(t);
            
            String query="select * from questions";
            
            ResultSet rs=con.getData(query);
            int i=0;
            while(rs.next())
            {
                String[] questions={rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                ShowQues q=new ShowQues(questions,Integer.parseInt(rs.getString(1)),map,result);
//                RRid=Integer.parseInt(rs.getString(1));
                mainPanel.add(q);
                q.setName("card"+i);
                i++;
            }
            for(Map.Entry m:result.entrySet())
            {
                    marks+= Integer.parseInt(m.getValue().toString());
            }
//            mainPanel.add(new lastPanel(marks));
            jButton1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        headPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        footerPanel = new javax.swing.JPanel();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headPanel.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("JAVA Questions");
        headPanel.add(jLabel1);

        getContentPane().add(headPanel, java.awt.BorderLayout.NORTH);

        mainPanel.setBackground(new java.awt.Color(102, 204, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mainPanel.setForeground(new java.awt.Color(102, 204, 255));
        mainPanel.setToolTipText("");
        mainPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(mainPanel, java.awt.BorderLayout.LINE_START);

        footerPanel.setBackground(new java.awt.Color(0, 153, 255));

        prevButton.setBackground(new java.awt.Color(102, 102, 255));
        prevButton.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        prevButton.setForeground(new java.awt.Color(0, 51, 51));
        prevButton.setText("previous");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        footerPanel.add(prevButton);

        nextButton.setBackground(new java.awt.Color(102, 102, 255));
        nextButton.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        nextButton.setForeground(new java.awt.Color(0, 51, 51));
        nextButton.setText("next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        footerPanel.add(nextButton);

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jButton1.setText("submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        footerPanel.add(jButton1);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 40));
        footerPanel.add(jPanel3);

        getContentPane().add(footerPanel, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/quizImage5.jpg"))); // NOI18N
        jPanel1.add(jLabel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card=(CardLayout)mainPanel.getLayout();
        card.next(mainPanel);
        JPanel cardt = null;
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible() == true) {
                cardt = (JPanel) comp;
            }
        }
        
        //String query="insert into resultdetails values(1,1,)"
        if(cardt.getName().equals("card6"))
        {
            jButton1.setEnabled(true);
            nextButton.setEnabled(false);
            prevButton.setEnabled(false);
        }
        //System.out.println(cardt.getName());
        
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card=(CardLayout)mainPanel.getLayout();
        card.previous(mainPanel);
    }//GEN-LAST:event_prevButtonActionPerformed
    
    public void display()
    {
        t.t.stop();
        for(Map.Entry m:result.entrySet())
            {
                marks+= Integer.parseInt(m.getValue().toString());
            }            
            Random r=new Random();
            int rand=r.nextInt(100000);
            String query="insert into result(Rid,Rollno,marks,count) values("+rand+",5,'"+marks+"',10)";
            String query1="";
            System.out.println(query);
            
        con.insertData(query);
        for (Map.Entry m : map.entrySet()) 
        {
            String q1=con.getCorrectAns(String.valueOf(m.getKey()));
            int rr=0;
            if(q1.equals(m.getValue()))
            {
                rr=1;
            }
            query1="insert into resultdetails(Rid,Ques,ans,CorrectAns,marks) values("+rand+",'"+m.getKey()+"','"+m.getValue()+"','"+q1+"','"+rr+"')";
            System.out.println(query1);
            con.insertData(query1);
        }          
        
//            new lastPanel(marks);
            p=new lastPanel(marks,rand);
            p.setName("Last");
            mainPanel.add(p);
            System.out.println(marks);
            CardLayout card = (CardLayout)mainPanel.getLayout();
            card.last(mainPanel);
            
        jPanel3.add(t);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        this.display();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuizSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QuizSystem("").setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(QuizSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headPanel;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainPanel;
    public javax.swing.JButton nextButton;
    public javax.swing.JButton prevButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
