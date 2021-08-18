
package homescreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class HomeScreen extends JFrame implements ActionListener {
   JPanel p1,p2,p3,p4;
   JLabel l1,l2,l3;
   JButton b1,b2,b3;
    
    HomeScreen()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500,700);
        p1=new JPanel();
        l1=new JLabel();
        b1=new JButton("Login");
        b1.addActionListener(this);
        b2=new JButton("Help");
        b1.setPreferredSize(new Dimension(100,50));
        b2.setPreferredSize(new Dimension(100,50));
        b2.addActionListener(this);
        this.add(p1,BorderLayout.NORTH);
        l1.setText("Department of Computer Science");
        p1.add(l1);
        p1.add(b1);
        p1.add(b2);
        Font font1 = new Font("SansSerif",Font.BOLD,30);
        Font BF = new Font("SansSerif",Font.BOLD,15);
        l1.setFont(font1);
        b1.setFont(BF);
        b2.setFont(BF);
        l1.setForeground(Color.WHITE);
        p1.setBackground(new Color(0,128,128));
        p2=new JPanel();
        l2=new JLabel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        l2.setText("Welcome to Faculty Information System");
        Font font2 = new Font("SansSerif",Font.ITALIC,30);
        l2.setFont(font2);
        l2.setForeground(new Color(0,128,128));
        p2.add(l2);
        c.gridy=GridBagConstraints.RELATIVE;
        
        b3=new JButton("Explore Faculty");
        b3.setPreferredSize(new Dimension(150,50));
        b3.addActionListener(this);
        b1.setBackground(new Color(224,255,255));
        b3.setBackground(new Color(224,255,255));
        b3.setFont(BF);
        b2.setBackground(new Color(224,255,255));
        p2.add(b3,c);
        b2.setBackground(new Color(224,255,255));
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
           Border bored = BorderFactory.createLineBorder(Color.LIGHT_GRAY,2);
           Border bor = BorderFactory.createLineBorder(Color.BLACK,2);
           b1.setBorder(bored);
           b2.setBorder(bored);
           b3.setBorder(bored);
           p1.setBorder(bor);
           p3.setBorder(bor);
           p4.setBorder(bor);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
       HomeScreen  HC=new HomeScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Object obj=e.getSource();
            if(obj.equals(b3)){
                try {
                    
                  
                    ViewScreen vs=new ViewScreen();
                    this.setVisible(false);
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if(obj.equals(b1)){
                Login lg=new Login();
                lg.setSize(1500, 1500);
                this.setVisible(false);
            }
            else if(obj.equals(b2)){
                Help h=new Help(false);
                this.setVisible(false);
            }
            
    }
    
}
