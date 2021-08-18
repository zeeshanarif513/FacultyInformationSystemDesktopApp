/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

public class ADMINUPDATEPAGE extends JFrame implements ActionListener{
    JButton btn1,insertFacButton,btn3,btn2;
    String[] names;
    String[] emails;
    String[] phone;
    String[] ids;
    panell[] a;
    JScrollPane sp;
    ADMINUPDATEPAGE() throws SQLException{
        ImageIcon im=new ImageIcon("QAU-Logo.png");
        JLabel l1=new JLabel(im);
        this.setTitle("Admin Portal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        JScrollPane sp=new JScrollPane();
        JPanel FacultyPanel=new JPanel();
        
        
        JPanel header=new JPanel();
       

        //Header
        JPanel p1=new JPanel();
        Border bored = BorderFactory.createLineBorder(Color.LIGHT_GRAY,2);
        Border bor = BorderFactory.createLineBorder(Color.BLACK,2);
    
        header.setBackground(Color.white);
        p1.add(l1);
        JLabel l2=new JLabel("Department of Computer Science");
        Font font = new Font("Courier", Font.BOLD,50);
        l2.setForeground(Color.WHITE);
        l2.setFont(font);
     
        Font BF = new Font("SansSerif",Font.BOLD,15);
     

        btn1=new JButton("Logout");
        btn1.setFont(BF);
        btn1.setBackground(new Color(224,255,255));
        
        btn1.addActionListener(this);
        btn2=new JButton("Search");
        btn2.setFont(BF);
        
        btn2.setBackground(new Color(224,255,255));
        btn2.addActionListener(this);
        
        insertFacButton=new JButton("Insert");
        insertFacButton.setFont(BF);
        insertFacButton.setBackground(new Color(224,255,255));
      
       
        p1.add(l2);
        p1.add(btn2);
        p1.add(insertFacButton);
        insertFacButton.addActionListener(this);
        p1.add(btn1);
       
        
     
        p1.setBackground(new Color(0,128,128));
        
        JLabel label2=new JLabel("Welcome Admin!");
        Font font1 = new Font("Courier", Font.BOLD,15);
        label2.setFont(font1);

  
        p1.setLayout(new FlowLayout());
        
        header.add(p1);
        header.setPreferredSize(new Dimension(1100,100));
        header.setBorder(bored);
  
        this.getContentPane().add(header);
        
      
        
        DatabaseManager db=new DatabaseManager();
        names=new String[db.totalFaculty()];
        emails=new String[db.totalFaculty()];
        phone=new String[db.totalFaculty()];
        ids=new String[db.totalFaculty()];
        db.getPInfo(ids, names, emails, phone);
        FacultyPanel.setLayout(new MigLayout());
        a=new panell[db.totalFaculty()];
        //FacultyPanel.setPreferredSize(new Dimension(1100,700));
        for(int i=0;i<db.totalFaculty();i++){
            a[i]=new panell(ids[i],names[i],phone[i],emails[i],this);
            a[i].setPreferredSize(new Dimension(500,70));
            if( i % 2 != 0){
                FacultyPanel.add(a[i],"gapleft 1%, wrap");
            }
            else{
                FacultyPanel.add(a[i],"gapleft 1%");
            }
        }
       //FacultyPanel.setLayout(new GridLayout(db.totalFaculty()/2+1,2,20,20));
       
       
        
        
        this.setLayout(new MigLayout());
        this.setBackground(Color.WHITE);
        GridBagConstraints mainBag=new GridBagConstraints();
        mainBag.gridx=0;
        mainBag.gridy=0;
        mainBag.gridwidth=2;
        mainBag.weighty=1;
        mainBag.anchor=GridBagConstraints.NORTHWEST;
        mainBag.gridwidth=50;
        this.add(header,"gapleft 7%,wrap");
        mainBag.weighty=1;
        mainBag.gridy=GridBagConstraints.RELATIVE;
        sp = new JScrollPane(FacultyPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(1100,700));
        this.add(sp,"gapleft 7%,gapy 10%");
        
        this.setVisible(true);
        
        }
   public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj.equals(insertFacButton)){
            InsertFaculty f = new InsertFaculty();
            this.setVisible(false);
            
        }
        else if(obj.equals(btn2)){
            String id=JOptionPane.showInputDialog("Search", "Id");
            if(id!=null){
                try {
                    DatabaseManager db = new DatabaseManager();
                    if(db.searchID(id)){
                        String s=db.search(id);
                        if(s!=null){
                            if(db.searchID(id)){
                                FacultyUpdate fu;
                                fu = new FacultyUpdate(s,true);
                                fu.setSize(1500,750);
                            }
                        }
                    }
                    else{
                            JOptionPane done=new JOptionPane();
                            done.showMessageDialog(null,"Wrong Input");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ADMINUPDATEPAGE.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        else if(obj.equals(btn1)){
            HomeScreen vs=new HomeScreen();
            this.setVisible(false);
        }
        

   }
}

class panell extends JPanel implements ActionListener{
    ImageIcon icon;
    JLabel name,email,phone,pic,iden;
    JPanel p1,p2,p;
    JButton more;
    ViewScreen mainThis; 
    String m;
    JButton editbtn,deletebtn;
    ADMINUPDATEPAGE O;
  panell(String id,String n,String mail,String ph,ADMINUPDATEPAGE o){
      O=o;
      this.m=id;
      Border bored = BorderFactory.createLineBorder(Color.LIGHT_GRAY,2);
      Border bor = BorderFactory.createLineBorder(Color.BLACK,2);
      Font BF = new Font("SansSerif",Font.BOLD,15);
        
      
      p2=new JPanel();
      p=new JPanel();
      iden=new JLabel("ID: "+id);
      name=new JLabel(n);
      email=new JLabel(mail);
      phone=new JLabel(ph);

      p2.setLayout(new GridBagLayout());
      GridBagConstraints bag=new GridBagConstraints();
      bag.gridx=0;
      bag.gridy=0;
      bag.anchor=GridBagConstraints.WEST;
      p2.add(iden,bag);
      bag.gridy=GridBagConstraints.RELATIVE;
      p2.add(name,bag);
      
      p2.add(email,bag);
      p2.add(phone,bag);
      ImageIcon f1img=new ImageIcon("DrOnaiza.png");
    JLabel f1Label=new JLabel(f1img);
    JPanel imgPanel=new JPanel();
    imgPanel.add(f1Label);
    imgPanel.setLayout(new GridLayout(1,1));
    imgPanel.setBackground(Color.LIGHT_GRAY);

    editbtn=new JButton("  Edit   ");
    editbtn.addActionListener(this);
    editbtn.setFont(BF);
    editbtn.setBackground(new Color(224,255,255));
 
    deletebtn=new JButton("Delete");
    deletebtn.addActionListener(this);
    deletebtn.setFont(BF);
    deletebtn.setBackground(new Color(224,255,255));

    
    JPanel btnPanel=new JPanel();
    btnPanel.setLayout(new GridBagLayout());
    GridBagConstraints b=new GridBagConstraints();
    b.gridx=0;
    b.gridy=0;
    b.anchor=GridBagConstraints.WEST;
  
    btnPanel.add(editbtn,b);
    b.gridy=GridBagConstraints.RELATIVE;
    b.gridy=1;
    b.weighty=0;
    btnPanel.add(deletebtn,b);
    this.setBorder(bored);
      //p.add(p2);
       this.add(imgPanel);
      this.add(p2);
      this.add(btnPanel);
      this.setLayout(new GridLayout(1,3,4,4));
      
  
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj.equals(editbtn)){
            FacultyUpdate fu;
            try {
                
                fu = new FacultyUpdate(m,true);
                fu.setSize(1500,750);
                O.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(ADMINUPDATEPAGE.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
            
        }
        else if(obj.equals(deletebtn)){
            int input = JOptionPane.showConfirmDialog(null, 
                "Are you sure?!","",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(input==0){
            try {
                DatabaseManager db=new DatabaseManager();
                db.delete(m);
                ADMINUPDATEPAGE u=new ADMINUPDATEPAGE();
                O.setVisible(false);
                u.setSize(1500, 750);
            } catch (SQLException ex) {
                Logger.getLogger(panell.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
}

