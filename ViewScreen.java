/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;

import java.awt.Dimension;
import java.awt.*;
import java.awt.ScrollPane;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.miginfocom.swing.MigLayout;

public class ViewScreen extends JFrame implements ActionListener{
    JLabel title1,title2,searchLabel,hod,faculty;
    JButton help,login,search,back;
    panel p,p2;
    JPanel hodPanel,facultyPanel,allFaculty;
    panel[] fac;
    JScrollPane sp;
    String[] names;
    String[] emails;
    String[] phone;
    String[] imgs;
    String[] ids;
    
    ViewScreen() throws SQLException{
        this.setTitle("ViewScreen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1368,720);
        this.setLayout(new GridBagLayout());
        Font BF = new Font("SansSerif",Font.BOLD,15);
        Border bored = BorderFactory.createLineBorder(Color.LIGHT_GRAY,2);
        Border bor = BorderFactory.createLineBorder(Color.BLACK,2);
        GridBagConstraints mainBag=new GridBagConstraints();
        mainBag.gridx=0;
        mainBag.gridy=0;
        mainBag.gridwidth=2;
        mainBag.weighty=1;
        mainBag.anchor=GridBagConstraints.NORTHWEST;
        
        JPanel header=new JPanel();
 
        
        JPanel titlePanel=new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints titleBag=new GridBagConstraints();
        titleBag.gridx=0;
        titleBag.gridy=0;
        titleBag.weighty=1;
        titleBag.anchor=GridBagConstraints.NORTHWEST;
        
        title1=new JLabel("Department of Computer Science");
        title1.setFont(new Font("SansSerif",Font.BOLD,25));
        title1.setForeground(Color.WHITE);
        title2=new JLabel("Quaid-i-Azam University, Islamabad");
        title2.setFont(new Font("SansSerif",Font.BOLD,25));
        title2.setForeground(Color.WHITE);
        titlePanel.add(title1,titleBag);
        titleBag.gridy=GridBagConstraints.RELATIVE;
        titlePanel.add(title2,titleBag);
      
        help=new JButton("Help");
        help.setBackground(new Color(224,255,255));
        
        help.setFont(new Font("SansSerif",Font.BOLD,15));
        help.addActionListener(this);
        login=new JButton("Login");
        login.setBackground(new Color(224,255,255));
        login.setFont(new Font("SansSerif",Font.BOLD,15));
        login.addActionListener(this);
        back=new JButton("Back");
        
        back.setFont(new Font("SansSerif",Font.BOLD,15));
        back.addActionListener(this);
        back.setBackground(new Color(224,255,255));
        search=new JButton("Search");
        search.setBackground(new Color(224,255,255));
        search.setFont(new Font("SansSerif",Font.BOLD,15));
        search.addActionListener(this);
        titlePanel.setBackground(new Color(0,128,128));
        
        header.setLayout(new MigLayout());
        header.add(titlePanel,"gapleft 0%");
        
        titleBag.gridx=1;
        titleBag.gridy=0;
        header.add(help,"gapleft 20%");
        titleBag.gridx=2;
        titleBag.gridy=0;
        header.add(login);
        titleBag.gridx=3;
        titleBag.gridy=0;

        titleBag.gridx=4;
        titleBag.gridy=0;
        header.add(back);
        titleBag.gridx=5;
        titleBag.gridy=0;
        header.add(search);
        header.setBackground(new Color(0,128,128));
        header.setPreferredSize(new Dimension(1100,100));
        //header.setLayout(new MigLayout());
        this.getContentPane().add(header,mainBag);
        
        DatabaseManager db=new DatabaseManager();
        
        names=new String[db.totalFaculty()];
        emails=new String[db.totalFaculty()];
        phone=new String[db.totalFaculty()];
        ids=new String[db.totalFaculty()];
        db.getPInfo(ids, names, emails, phone);
        
      
        hod=new JLabel("Head of Department");
        hod.setFont(new Font("SansSerif",Font.BOLD,25));
        hod.setForeground(new Color(0,128,128));
       
        hodPanel=new JPanel();
        hodPanel.add(hod);
        mainBag.gridx=1;
        mainBag.gridy=1;
        
        mainBag.gridy=GridBagConstraints.RELATIVE;
        this.getContentPane().add(hodPanel,mainBag);
        int i;
        for(i=0;ids[i]!=null;i++){
            if(ids[i].equals("E1"))
                break;
        }
        p=new panel(names[i],emails[i],phone[i],this,ids[i]);
        mainBag.gridx=1;
        mainBag.gridy=2;
        p.setLayout(new GridLayout(1,1));
        p.setPreferredSize(new Dimension(220,110));
        this.getContentPane().add(p,mainBag);
       
        faculty=new JLabel("Faculty");
        faculty.setFont(new Font("SansSerif",Font.BOLD,25));
        faculty.setForeground(new Color(0,128,128));
        facultyPanel=new JPanel();
        facultyPanel.add(faculty);
        mainBag.gridx=1;
        mainBag.gridy=3;
        this.getContentPane().add(facultyPanel,mainBag);
        allFaculty=new JPanel();
        allFaculty.setLayout(new MigLayout());
        fac=new panel[db.totalFaculty()-1];
        for(i=0;i<db.totalFaculty()-1;i++){
            fac[i]=new panel(names[i+1],emails[i+1],phone[i+1],this,ids[i+1]);
            fac[i].setPreferredSize(new Dimension(250,100));
            if( (i+1) % 3 == 0){
                allFaculty.add(fac[i],"gapleft 4%, wrap");
            }
            else{
                allFaculty.add(fac[i],"gapleft 4%");
            }
            
        }
        //allFaculty.setLayout(new GridLayout(db.totalFaculty()/3+1,3,40,10));
        
       // allFaculty.setPreferredSize(new Dimension(1000,768));
        sp = new JScrollPane(allFaculty,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainBag.gridy=4;
        sp.setPreferredSize(new Dimension(1100,300));
         this.getContentPane().add(sp,mainBag);
        
       
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object obj=e.getSource();
        if(obj.equals(login)){
            Login lg=new Login();
            lg.setSize(1500, 1500);
            this.setVisible(false);
            
        }
        else if(obj.equals(search)){
            String id=JOptionPane.showInputDialog("Search", "Id");
            if(id!=null){
                try {
                    DatabaseManager db = new DatabaseManager();
                    if(db.searchID(id)){
                        String s=db.search(id);
                        if(s!=null){
                            if(db.searchID(id)){
                                FacultyView fv;
                                fv = new FacultyView(s);
                                fv.setSize(1500,750);
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
        else if(obj.equals(back)){
            HomeScreen h=new HomeScreen();
            this.setVisible(false);
        }
        else if(obj.equals(help)){
            Help h=new Help(true);
            this.setVisible(false);
        }
    }

}

class panel extends JPanel implements ActionListener{
    ImageIcon icon;
    JLabel name,email,phone,pic,iden;
    JPanel p1,p2,p;
    JButton more;
    ViewScreen mainThis; 
    String m;
  panel(String n,String mail,String ph,ViewScreen t,String id){
      this.m=id;
      mainThis=t;
  
      p2=new JPanel();
      p=new JPanel();
      iden=new JLabel("FacultyID: "+id);
      name=new JLabel("Name: "+ n);
      email=new JLabel("Email: "+ mail);
      phone=new JLabel("Phone#: "+ ph);
      more=new JButton("Read More");
      more.addActionListener(this);
      more.setBackground(new Color(224,255,255));
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
      p2.add(more,bag);
     
      p.add(p2);
     
      this.add(p);
      
      this.setBorder(BorderFactory.createLineBorder(Color.BLACK, WIDTH, true));
      this.setSize(new Dimension(200,200));
     
   
  }
  void setbgColor(Color c){
       this.setBackground(c);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj.equals(more)){
            FacultyView fv;
            try {
                fv = new FacultyView(m);
                fv.setSize(1500,750);
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainThis.setVisible(false);
            
        }
    }

  
}

