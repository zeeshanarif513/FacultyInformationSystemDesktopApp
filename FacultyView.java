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
/**
 *
 * @author bf17-42
 */
public class FacultyView extends  JFrame implements ActionListener{

    
    
     JPanel header;
     JPanel center;
     JPanel detail;
     JPanel subDetail;
     JPanel h1;
     JPanel middle;
    // JPanel b;
     JButton back;
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
     String getId;
     GridBagConstraints gbc = new GridBagConstraints();
   
     JScrollPane sp;
    public void actionPerformed(ActionEvent e) {
       
        Object obj=e.getSource();
        if(obj.equals(back)){
            try {
                ViewScreen vs=new ViewScreen();
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        
        
    }
    
    FacultyView(String ph) throws SQLException{
    getId = ph;
  
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
     back.addActionListener(this);
     back.setBackground(new Color(224,255,255));
     back.setForeground(Color.BLACK);
     font2 = new Font("SansSerif", Font.BOLD, 15);
     back.setFont(font2);
     
    
    

     header.setLayout(new BorderLayout());
     icon1 = new ImageIcon("C://Users//bf17-42//Desktop//facultyView//facultyView//src//facultyview//logo.png");
     iconLabel = new JLabel(icon1);
     iconLabel.setPreferredSize(new Dimension(100,100));
    
     h1.add(iconLabel);
     
     heading = new JLabel("Department of Computer Sciences");
    font1 = new Font("SansSerif", Font.BOLD, 20);
     heading.setFont(font1);
     heading.setForeground(Color.WHITE);
     
     h1.add(heading);
     header.setBackground(new Color(0,128,128)); 
     header.add(h1,BorderLayout.WEST);

     header.add(back,BorderLayout.EAST);
    
     
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

            
     
     subDetail = new JPanel(new GridLayout(5,1));
    
     
     for(int i=1;i<=5;i++)
     subDetail.add(d[i]);
     
     detail.add(subDetail);
     center.add(detail);
    
     tabbedPane = new JTabbedPane();
     panel = new JPanel[7];
     ta = new JTextArea[6];
     
     for(int i=0;i<6;i++){
        ta[i] = new JTextArea(18,122);      
     }
  
      panel[0] = new JPanel();
      panel[0].add(ta[0]);
      
      for(int j=0;Research[j]!=null;j++)
         {
           
            ta[0].append(Integer.toString(j+1)+": "+Research[j]+"\n");
          
     
         }
     
      tabbedPane.addTab("Research",panel[0]);
      
       panel[1] = new JPanel();
       panel[1].add(ta[1]);
       for(int j=0;Teachings[j]!=null;j++)
         {
           
            ta[1].append(Integer.toString(j+1)+": "+Teachings[j]+"\n");
          
     
         }
       tabbedPane.addTab("Teaching",panel[1]);
      
       panel[2] = new JPanel();
       panel[2].add(ta[2]);
           for(int j=0;ResearchSudents[j]!=null;j++)
         {
           
            ta[2].append(Integer.toString(j+1)+": "+ResearchSudents[j]+"\n");
          
     
         }
       tabbedPane.addTab("Research Students",panel[2]);
      
       panel[3] = new JPanel();
       panel[3].add(ta[3]);
           for(int j=0;Publications[j]!=null;j++)
         {
           
            ta[3].append(Integer.toString(j+1)+": "+Publications[j]+"\n");
          
     
         }
       tabbedPane.addTab("Publications",panel[3]);
      
      /*panel[4] = new JPanel();
      panel[4].add(ta[4]);
      tabbedPane.addTab("Books",panel[4]);*/
      
      panel[5] = new JPanel();
      panel[5].add(ta[5]);
            for(int j=0;conference[j]!=null;j++)
         {
           
            ta[5].append(Integer.toString(j+1)+": "+conference[j]+"\n");
          
     
         }
       ta[5].setBounds(100,100,100,100);
       
       
        
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

