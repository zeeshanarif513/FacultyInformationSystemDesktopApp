/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.lang.Math;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author bf17-42
 */
public class FacultyUpdate extends  JFrame implements ActionListener{

    
    
     JPanel header;
     JPanel center;
     JPanel detail;
     JPanel subDetail;
     JPanel h1;
     JPanel middle;
    
     JButton back,logout,editName,editDes,editMail,editPhone,changePass;
     ImageIcon icon1;
     JLabel iconLabel;
     JLabel heading;
     ImageIcon icon2;
     JLabel d[];
     JTabbedPane tabbedPane;
     JLabel footer;
     JPanel panel[];
     JTextArea ta[];
     Font font1,font2;
     GridBagConstraints gbc = new GridBagConstraints();
     JScrollPane sp;
     String getId;
     JButton add[];
     JButton delete[];
     JButton edit[];
     JPanel updatePanel[];
     JPanel taPanel[];
     boolean B;
    public void actionPerformed(ActionEvent e){
        Object obj=e.getSource();
        if(obj.equals(changePass)){
            try {
                DatabaseManager db=new DatabaseManager();
                JOptionPane p=new JOptionPane();
                String pass="";
                //String pass=p.showInputDialog("Enter current Password");
                JPasswordField pf = new JPasswordField();
                JPasswordField pf1 = new JPasswordField();
                int okCxl = p.showConfirmDialog(null, pf, "Enter current Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (okCxl == p.OK_OPTION){ 
                    pass = new String(pf.getPassword());
                    if(pass.equals(db.getPass(getId))){
                        JOptionPane p1=new JOptionPane();
                        okCxl = p.showConfirmDialog(null, pf1, "Enter new Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (okCxl == p.OK_OPTION){
                            pass = new String(pf1.getPassword());
                            db.setPass(pass,getId);
                            JOptionPane done=new JOptionPane();
                            done.showMessageDialog(null,"SuccessFul");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid Password!");
                    }
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else if(obj.equals(editName)){
            JOptionPane p=new JOptionPane();
            String name=p.showInputDialog("Input");
            boolean flag=true;
            if(!name.equals("")){
                for(int j=0;j<name.length();j++){
                    if(!(name.charAt(j) >= 'a' && name.charAt(j) < 'z') && !(name.charAt(j) >= 'A' && name.charAt(j) <= 'Z') && name.charAt(j)!= 32 && name.charAt(j) != '.'){
                        JOptionPane.showMessageDialog(null,"Invalid Name!");
                        flag=false;
                        break;
                    }
                }
            }
            if(flag){
                try {
                    DatabaseManager db=new DatabaseManager();
                    db.setName(getId,name);
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        else if(obj.equals(editDes)){
            JOptionPane p=new JOptionPane();
            String designation=p.showInputDialog("Input");
            boolean flag=true;
            if(!designation.equals("")){
                for(int j=0;j<designation.length();j++){
                    if(!(designation.charAt(j) >= 'a' && designation.charAt(j) <= 'z') && !(designation.charAt(j) >= 'A' && designation.charAt(j) <= 'Z') && designation.charAt(j)!= ' '){
                        JOptionPane.showMessageDialog(null,"Invalid Designation!");
                        flag=false;
                        break;
                    }
                }
            }
            if(flag){
                try {
                    DatabaseManager db=new DatabaseManager();
                    db.setDes(getId,designation);
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        else if(obj.equals(editMail)){
            JOptionPane p=new JOptionPane();
            String email=p.showInputDialog("Input");
            boolean flag=true;
            if(!email.equals("")){
                boolean f=false;
                for(int j=0;j<email.length();j++){
                    if(email.charAt(j)=='@'){
                        f=true;
                        break;
                    }
                }
                if(!f){
                    JOptionPane.showMessageDialog(null,"Invalid Email!");
                        flag=false;
                }
            }
            if(flag){
                try {
                    DatabaseManager db=new DatabaseManager();
                    db.setMail(getId,email);
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        else if(obj.equals(editPhone)){
            JOptionPane p=new JOptionPane();
            String phone=p.showInputDialog("Input");
            boolean flag=true;
            if(!phone.equals("")){
                for(int j=0;j<phone.length();j++){
                    if(phone.charAt(j)!='+' && phone.charAt(j)!='-' && (phone.charAt(j) <48 || phone.charAt(j) >57)){
                        JOptionPane.showMessageDialog(null,"Invalid PhoneNo!");
                        flag=false;
                        break;
                    }
                }
            }
            if(flag){
                try {
                    DatabaseManager db=new DatabaseManager();
                    db.setPhone(getId,phone);
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        if(obj.equals(back)){
            ADMINUPDATEPAGE vs;
            try {
                vs = new ADMINUPDATEPAGE();
                vs.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
            
        }
        else if(obj.equals(logout)){
            HomeScreen  h=new HomeScreen();
            this.setVisible(false);
        }
        else if(obj.equals(add[0])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Input");
                DatabaseManager db=new DatabaseManager();
                db.storeResearch(getId, s);
                JOptionPane done=new JOptionPane();
                done.showMessageDialog(null,"SuccessFul");
                FacultyUpdate again=new FacultyUpdate(getId,B); 
                again.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(obj.equals(delete[0])){
            try {
                boolean flag;
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db=new DatabaseManager();
                flag=db.deleteResearch(Integer.parseInt(s),getId);
                if(flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else if(obj.equals(edit[0])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db= new DatabaseManager();
                boolean flag=db.editResearch(Integer.parseInt(s), getId);
                if(!flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(obj.equals(add[1])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Input");
                DatabaseManager db=new DatabaseManager();
                db.storeTeaching(getId, s);
                JOptionPane done=new JOptionPane();
                done.showMessageDialog(null,"SuccessFul");
                FacultyUpdate again=new FacultyUpdate(getId,B); 
                again.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(obj.equals(delete[1])){
            try {
                boolean flag;
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db=new DatabaseManager();
                flag=db.deleteTeaching(Integer.parseInt(s),getId);
                if(flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else if(obj.equals(edit[1])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db= new DatabaseManager();
                boolean flag=db.editTeaching(Integer.parseInt(s), getId);
                if(!flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(obj.equals(add[2])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Input");
                DatabaseManager db=new DatabaseManager();
                db.storeResearchStudents(getId, s);
                JOptionPane done=new JOptionPane();
                done.showMessageDialog(null,"SuccessFul");
                FacultyUpdate again=new FacultyUpdate(getId,B); 
                again.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(obj.equals(delete[2])){
            try {
                boolean flag;
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db=new DatabaseManager();
                flag=db.deleteResearchStudents(Integer.parseInt(s),getId);
                if(flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else if(obj.equals(edit[2])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db= new DatabaseManager();
                boolean flag=db.editResearchStudents(Integer.parseInt(s), getId);
                if(!flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(obj.equals(add[3])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Input");
                DatabaseManager db=new DatabaseManager();
                db.storePublication(getId, s);
                JOptionPane done=new JOptionPane();
                done.showMessageDialog(null,"SuccessFul");
                FacultyUpdate again=new FacultyUpdate(getId,B); 
                again.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(obj.equals(delete[3])){
            try {
                boolean flag;
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db=new DatabaseManager();
                flag=db.deletePublication(Integer.parseInt(s),getId);
                if(flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else if(obj.equals(edit[3])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db= new DatabaseManager();
                boolean flag=db.editPublication(Integer.parseInt(s), getId);
                if(!flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(obj.equals(add[5])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Input");
                DatabaseManager db=new DatabaseManager();
                db.storeConference(getId, s);
                JOptionPane done=new JOptionPane();
                done.showMessageDialog(null,"SuccessFul");
                FacultyUpdate again=new FacultyUpdate(getId,B); 
                again.setSize(1500,750);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(obj.equals(delete[5])){
            try {
                boolean flag;
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db=new DatabaseManager();
                flag=db.deleteConference(Integer.parseInt(s),getId);
                if(flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else if(obj.equals(edit[5])){
            try {
                JOptionPane p=new JOptionPane();
                String s=p.showInputDialog("Enter S.No.");
                DatabaseManager db= new DatabaseManager();
                boolean flag=db.editConference(Integer.parseInt(s), getId);
                if(!flag){
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"Wrong Input");
                }
                else{
                    JOptionPane done=new JOptionPane();
                    done.showMessageDialog(null,"SuccessFul");
                    FacultyUpdate again=new FacultyUpdate(getId,B); 
                    again.setSize(1500,750);
                    this.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    FacultyUpdate(String s,boolean b) throws SQLException{
     B=b;
     getId=s;
     this.setTitle("Faculty Information System");
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setSize(700,700);
     middle = new JPanel();
     middle.setBackground(new Color(224,255,255));
   
     header = new JPanel();
     header.setBackground(new Color(0,128,128));
     h1 = new JPanel();
     h1.setBackground(new Color(0,128,128)); 

    
      header.setPreferredSize(new Dimension(100,120));
     
     
     back = new JButton("Go Back");
     back.setPreferredSize(new Dimension(100,0));
    back.setBackground(new Color(224,255,255));
     back.setForeground(Color.white);
     font2 = new Font("SansSerif", Font.BOLD, 15);
     back.setFont(font2);
     back.addActionListener(this);
     back.setVisible(b);
     
     logout = new JButton("Logout");
     logout.setPreferredSize(new Dimension(100,0));
     logout.setBackground(Color.darkGray);
     logout.setForeground(Color.white);
     logout.setFont(font2);
     logout.addActionListener(this);
     logout.setVisible(!b);
    
    

     header.setLayout(new MigLayout());
     
    
     
     
     heading = new JLabel("Department of Computer Sciences");
    font1 = new Font("SansSerif", Font.BOLD, 50);
     heading.setFont(font1);
     heading.setForeground(Color.WHITE);
     changePass=new JButton("Change Password");
     changePass.setBackground(new Color(224,255,255));
     changePass.setFont(font2);
     changePass.addActionListener(this);
     h1.add(heading);
     header.setBackground(Color.lightGray);
     header.setBackground(new Color(0,128,128));
    header.add(h1);
     
     if(b==true){
        header.add(back,"push, al right");
        back.setPreferredSize(new Dimension(100,150));
     }
     else {
         header.add(changePass,"gapleft 15%");
        header.add(logout);
        changePass.setPreferredSize(new Dimension(100,50));
        logout.setPreferredSize(new Dimension(100,50));
     }
     back.setBackground(new Color(224,255,255));
     logout.setBackground(new Color(224,255,255));
     back.setForeground(Color.BLACK);
     logout.setForeground(Color.BLACK);
     
     center = new JPanel();
     center.setPreferredSize(new Dimension(2000,250));
    
     
     detail = new JPanel();
     detail.setBackground(Color.LIGHT_GRAY);
     detail.setLayout(new GridLayout(0,2));
     detail.setPreferredSize(new Dimension(500,230));
     
     
     center.setBackground(Color.white);
     icon2 = new ImageIcon("C://Users//bf17-42//Desktop//facultyView//facultyView//src//facultyview//profile.jpg");
     
     
     d = new JLabel[6];
     d[0] = new JLabel(icon2);

   
     detail.add(d[0]);
     
  
     DatabaseManager db = new DatabaseManager();
       String[] Research  = new String[50];
       String[] ResearchSudents = new String[50];
       String[] Teachings = new String[50];
       String[] conference = new String[50];
       String[] Publications = new String[50];
       String[] personalInfo = new String[50];
    
       boolean m = db.getRs(getId,Research,ResearchSudents,Teachings,conference,Publications,personalInfo);

         d[1] = new JLabel("   "+personalInfo[0]);
           d[2] = new JLabel("   "+personalInfo[1]);
           d[3] = new JLabel("   "+personalInfo[2]);
           d[4] = new JLabel("   "+personalInfo[3]);
           d[5] = new JLabel("   "+personalInfo[4]);

    
     subDetail=new JPanel();
      subDetail = new JPanel(new MigLayout());
      editName=new JButton("Edit");
      editName.addActionListener(this);
      editName.setBackground(new Color(224,255,255));
      editDes=new JButton("Edit");
      editDes.addActionListener(this);
      editDes.setBackground(new Color(224,255,255));
      editMail=new JButton("Edit");
      editMail.addActionListener(this);
      editMail.setBackground(new Color(224,255,255));
      editPhone=new JButton("Edit");
      editPhone.addActionListener(this);
      editPhone.setBackground(new Color(224,255,255));
     
     subDetail.add(d[1],"wrap,gapy 5%");
     subDetail.add(d[2]);
     subDetail.add(editName,"push, al right,wrap,gapy 5%");
     subDetail.add(d[3]);
     subDetail.add(editDes,"push, al right,wrap,gapy 5%");
     subDetail.add(d[4]);
     subDetail.add(editMail,"push, al right,wrap,gapy 5%");
     subDetail.add(d[5]);
     subDetail.add(editPhone,"push, al right,wrap,gapy 5%");
     
     detail.add(subDetail);
     center.add(detail);
    
     tabbedPane = new JTabbedPane();
     panel = new JPanel[7];
     ta = new JTextArea[6];
     
     updatePanel = new JPanel[6];
     taPanel = new JPanel[6];
     add = new JButton[6];
     delete = new JButton[6];
       
     add  = new JButton[6];
     delete = new JButton[6];
     edit=new JButton[6];
     
    
     for(int i=0;i<6;i++){
        ta[i] = new JTextArea(18,122);  
        updatePanel[i] = new JPanel();
         taPanel[i] = new JPanel();
         add[i]  = new JButton("Add");
         add[i].addActionListener(this);
         delete[i] = new JButton("Delete");
         delete[i].addActionListener(this);
         edit[i]=new JButton("Edit");
         edit[i].addActionListener(this);
         updatePanel[i].add(edit[i]);
         updatePanel[i].add(add[i]);
         updatePanel[i].add(delete[i]);
         updatePanel[i].setBackground(new Color(0,128,128));
          add[i].setPreferredSize(new Dimension(70,40));
          delete[i].setPreferredSize(new Dimension(70,40));
          edit[i].setPreferredSize(new Dimension(70,40));
           add[i].setBackground(new Color(224,255,255));
          delete[i].setBackground(new Color(224,255,255));
          edit[i].setBackground(new Color(224,255,255));
   
     }
     
 
      
      panel[0] = new JPanel();
      panel[0].setLayout(new BorderLayout());
      panel[0].add(updatePanel[0],BorderLayout.NORTH);
      taPanel[0].add(ta[0]);
      panel[0].add(taPanel[0],BorderLayout.CENTER);
      for(int j=0;Research[j]!=null;j++)
         {
           
            ta[0].append(Integer.toString(j+1)+": "+Research[j]+"\n");
          
     
         }
     
      tabbedPane.addTab("Research",panel[0]);
      
      panel[1] = new JPanel();
      panel[1].setLayout(new BorderLayout());
      panel[1].add(updatePanel[1],BorderLayout.NORTH);
      taPanel[1].add(ta[1]);
      panel[1].add(taPanel[1],BorderLayout.CENTER);
      for(int j=0;Teachings[j]!=null;j++)
         {
           
            ta[1].append(Integer.toString(j+1)+": "+Teachings[j]+"\n");
          
     
         }
     
       tabbedPane.addTab("Teaching",panel[1]);
      
      panel[2] = new JPanel();
      panel[2].setLayout(new BorderLayout());
      panel[2].add(updatePanel[2],BorderLayout.NORTH);
      taPanel[2].add(ta[2]);
      panel[2].add(taPanel[2],BorderLayout.CENTER);
     for(int j=0;ResearchSudents[j]!=null;j++)
         {
           
            ta[2].append(Integer.toString(j+1)+": "+ResearchSudents[j]+"\n");
          
     
         }
       tabbedPane.addTab("Research Students",panel[2]);
      
      panel[3] = new JPanel();
      panel[3].setLayout(new BorderLayout());
      panel[3].add(updatePanel[3],BorderLayout.NORTH);
      taPanel[3].add(ta[3]);
      panel[3].add(taPanel[3],BorderLayout.CENTER);
      for(int j=0;Publications[j]!=null;j++)
         {
           
            ta[3].append(Integer.toString(j+1)+": "+Publications[j]+"\n");
          
     
         }
      
     
       tabbedPane.addTab("Publications",panel[3]);
      
   
      
      panel[5] = new JPanel();
      panel[5].setLayout(new BorderLayout());
      panel[5].add(updatePanel[5],BorderLayout.NORTH);
      taPanel[5].add(ta[5]);
      panel[5].add(taPanel[5],BorderLayout.CENTER);
     for(int j=0;conference[j]!=null;j++)
         {
           
            ta[5].append(Integer.toString(j+1)+": "+conference[j]+"\n");
          
     
         }
       
      tabbedPane.addTab("Conference Preceedings",panel[5]);
      for(int j=0;j<6;j++){
          if(j==4)
              continue;
          if(ta[j].getText().equals(""))
          ta[j].append("Pending");
      }
      

      for(int i=0;i<6;i++)
      {
          if(i==4)
              continue;
         sp = new JScrollPane(ta[i], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         panel[i].add(sp);
         panel[i].setBackground(new Color(0,128,128));
      }
      middle.add(center,BorderLayout.NORTH);
      
      
     
      middle.add(tabbedPane,BorderLayout.SOUTH); 
      panel[6] = new JPanel();
      footer = new JLabel("For more detail visit cs.qau.edu.pk");
      panel[6].add(footer);
      panel[6].setBackground(Color.black);
      footer.setForeground(Color.WHITE);
      footer.setBackground(new Color(0,128,128));
    
      
     
     
     
     this.getContentPane().add(header,BorderLayout.NORTH);
     this.getContentPane().add(middle,BorderLayout.CENTER);
     this.getContentPane().add(panel[6],BorderLayout.SOUTH);
    
     this.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
 
}
