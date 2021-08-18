/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Help extends JFrame implements ActionListener {
   JPanel p1,p2,p3,p4;
   JLabel l1,l2,l3;
   JButton b1,b2;
   JTextField tf;
   JEditorPane ta;
   JScrollPane sc;
    boolean choice;
    Help(boolean b)
    {
        choice=b;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500,700);
        p1=new JPanel();
        l1=new JLabel();
        b2=new JButton("back");
        b2.setPreferredSize(new Dimension(100,50));
        b2.addActionListener(this);
        this.add(p1,BorderLayout.NORTH);
        l1.setText("Department of Computer Science");
        p1.add(l1);
      
        p1.add(b2);
        Font font1 = new Font("SansSerif",Font.BOLD,30);
        l1.setFont(font1);
        l1.setForeground(Color.WHITE);
        p1.setBackground(new Color(0,128,128)); 
        p2=new JPanel();
        l2=new JLabel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridy=GridBagConstraints.RELATIVE;
//        tf=new JTextField("Help",30);
//        tf.setPreferredSize(new Dimension(100,300));
        ta=new JEditorPane();
        ta.setContentType("text/html");
        ta.setText("<html>"
                + "<h1>Welcome to Faculty Infromation System</h1>"
                + "<h2>Using the FIS Help System</h2>"
                + "<p>This System provides and maintains Information about Faculty of Computer Science Department of Quaid-E-Azam University.</p></p>"
                + "<h3>For All Users</h3>"
                + "<p>You can view information of any faculty member by just clicking on the <b>Read More</b> Button.</p>"
                + "<h3>For Faculty Members</h3>"
                + "<p>Faculty members have to login to their accounts by using their <b>Username</b> and <b>Password</b> provided. "
                + "After that they have all access to their information stored on this System.<br>They can modify,update and insert "
                + "new Infromation by using <b>Update</b> Button.</p>"
                + "<h3>For Admin</h3>"
                + "<p>Admin have to login first by using his/her <b>Username</b> and <b>Password</b>.<br></p>"
                + "<h4>To Insert New Faculty</h4>"
                + "<p>Admin can Insert new Faculty by using the <b>Insert New Faculty</b> Button given on the Admin portal.</p>"
                + "<h4>To Update or Delete Faculty Information</h4>"
                + "<p>For that purpose admin can use <b> Update Delete</b> Buttons given on Admin portal. </p></html>");
        ta.setBounds(0, 0, 300, 300);
        ta.setSize(1000, 1000);
        sc=new JScrollPane(ta);
        
        b2.setBackground(new Color(224,255,255));
        //p2.add(sc);
        p2.add(sc);
        p2.setBackground(new Color(240,255,240));
        p3=new JPanel();
        p3.add(p2,c);
        p3.setBackground(new Color(0,128,128));
        p3.setLayout(new GridLayout(1,0));
        this.add(p3);
        p4=new JPanel();
        p4.setBackground(new Color(0,128,128));
        this.add(p4,BorderLayout.SOUTH);
        l3=new JLabel();
        l3.setText("QAU/CS/FACULTY/EDU/PK");
        Font font3 = new Font("Serif",Font.ITALIC,20);
        l3.setFont(font3);
        p4.add(l3);
           Border bored = BorderFactory.createLineBorder(Color.WHITE,2);
           Border bor = BorderFactory.createLineBorder(Color.BLACK,2);
           
           b2.setBorder(bored);
           p1.setBorder(bor);
           p3.setBorder(bor);
           p4.setBorder(bor);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
       Help  HC=new Help(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object obj=e.getSource();
       if(obj.equals(b2)){
           if(choice==true){
               try {
                   ViewScreen vs=new ViewScreen();
                   this.setVisible(false);
               } catch (SQLException ex) {
                   Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           else{
               HomeScreen hs=new HomeScreen();
               this.setVisible(false);
           }
       }
    }
    
}

