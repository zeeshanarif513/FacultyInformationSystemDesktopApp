/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;

import java.awt.*;
import java.sql.*;
import java.sql.PreparedStatement;

import java.awt.ScrollPane;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.BorderLayout;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.layout.Border;
public class Login extends JFrame implements ActionListener{
    
    JPanel main,imgPanel,contentPanel,name,pass,headingPanel,loginPanel;
    JLabel img,login,userName,password;
    JTextField nameTF;
    JPasswordField passTF;
    ImageIcon icon;
    JButton submit,back;
   
    Login(){
       this.setTitle("Faculty Information  System");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(500,500); 
       icon=new ImageIcon("D:\\University\\5th Semester\\SC(CS322)\\Group#1(Faculty Information System)\\welcome2.jpg");
       img=new JLabel(icon);
       imgPanel=new JPanel();
       imgPanel.add(img);
       contentPanel=new JPanel();
       //contentPanel.setLayout(new BorderLayout());
       login = new JLabel("Login");
       Font f=new Font("Serif",Font.BOLD,35);
       login.setFont(f);
       login.setForeground(new Color(108, 52, 131));
       headingPanel=new JPanel();
       headingPanel.setBackground(new Color(224,255,255));
       headingPanel.add(login);
       userName = new JLabel("Email:         ");
       userName.setForeground(new Color(108, 52, 131));
       nameTF=new JTextField(30);
       name=new JPanel();
       name.add(userName);
       name.add(nameTF);
       password = new JLabel("Password:");
       password.setForeground(new Color(108, 52, 131));
       passTF=new JPasswordField(30);
       pass=new JPanel();
       pass.add(password);
       pass.add(passTF);
       main=new JPanel();
       submit=new JButton("Submit");
       submit.setBackground(new Color(60,179,113));
       submit.addActionListener(this);
       loginPanel=new JPanel();
       //loginPanel.add(headingPanel);
       loginPanel.setLayout(new GridBagLayout());
       GridBagConstraints Bag=new GridBagConstraints();
        Bag.gridx=0;
        Bag.gridy=0;
        Bag.gridwidth=1;
        Bag.weighty=0;
        Bag.anchor=GridBagConstraints.NORTHWEST;
       loginPanel.add(name,Bag);
       Bag.gridy=GridBagConstraints.RELATIVE;
       loginPanel.add(pass,Bag);
       Bag.gridx=1;
       Bag.gridy=3;
       loginPanel.add(submit,Bag);
       loginPanel.setBackground(new Color(224,255,255));
       contentPanel.setLayout(new GridBagLayout());
       GridBagConstraints mainBag=new GridBagConstraints();
        mainBag.gridx=3;
        mainBag.gridy=-2;
        mainBag.gridwidth=1;
        mainBag.weighty=0;
        mainBag.anchor=GridBagConstraints.CENTER;
       
       contentPanel.add(headingPanel,mainBag);
       mainBag.gridy=GridBagConstraints.RELATIVE;
       
       contentPanel.add(loginPanel,mainBag);
       contentPanel.setBackground(new Color(224,255,255));
       //contentPanel.add(pass,BorderLayout.SOUTH);
       //contentPanel.setLayout(new GridLayout(2,1));
       //contentPanel.add(contentPanel);
       main.add(imgPanel);
       main.add(contentPanel);
       main.setBackground(new Color(0,128,128));
       main.setLayout(new GridLayout(1,2));
       this.getContentPane().add(main);
       //this.setBackground(new Color(0,128,128));
       this.setVisible(true);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj.equals(submit)){
            try {
                Border Redborder = BorderFactory.createLineBorder(Color.RED, 1);
                Border Blackborder = BorderFactory.createLineBorder(Color.BLACK, 1);
                String url="jdbc:ucanaccess://D://University//5th Semester//SC(CS322)//Group#1(Faculty Information System)//Database1.accdb";
                Connection con=DriverManager.getConnection(url);
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("Select * from login");
                String name=nameTF.getText();
                String pass=passTF.getText();
                Boolean flag,flag1;
                flag=flag1=false;
                
                while(rs.next()){
                    if(name.equals(rs.getString(2))){
                        flag=true;
                        if(pass.equals(rs.getString(1))){
                            flag1=true;
                        }
                            break;
                    }
                }  
                
                if(flag==false){
                    nameTF.setBorder(Redborder);
                }
                else{
                    nameTF.setBorder(Blackborder);
                }
                if(flag1==false){
                    passTF.setBorder(Redborder);
                }
                else{
                    passTF.setBorder(Blackborder);
                }
                
                if(flag && flag1){
                    String query="Select * from Faculty where FacultyEmail=?";
                    PreparedStatement p = con.prepareStatement(query); 
                    p.setString(1,name);
                    rs = p.executeQuery();
                    
                    //id=rs1.getString("FacultyID");
                    if(!rs.next()){
                    ADMINUPDATEPAGE u=new ADMINUPDATEPAGE();
                    this.setVisible(false);
                    u.setSize(1500, 750);
                    }
                    else{
                        FacultyUpdate fu=new FacultyUpdate(rs.getString("FacultyID"),false);
                        this.setVisible(false);
                        fu.setSize(1500, 750);
                    }
            }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}


