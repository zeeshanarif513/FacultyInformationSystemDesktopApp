/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homescreen;

import java.awt.Dimension;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DatabaseManager{

 Connection con;
 Statement st;
 ResultSet rs;
String url;
DatabaseManager() throws SQLException
{
	url="jdbc:ucanaccess://D://University//5th Semester//SC(CS322)//Group#1(Faculty Information System)//Database1.accdb";
	con=DriverManager.getConnection(url);
	st=con.createStatement();
	rs=st.executeQuery("SELECT * FROM Faculty");
       
  
	
}

public boolean getRs(String id,String[] Research,String[] ResearchSudents,String[] Teachings,String[] conference,String[] Publications,String[] personalInfo) throws SQLException
{
    

   String querey1 = "select FacultyID,FacultyName,FacultyDesignation,FacultyEmail,FacultyPhoneNo from Faculty where FacultyID = ? ";
   String querey2 = "select Researches  from Reseaech where FacultyID = ? ";
   String querey3 = "select Publications from Publication where FacultyID = ? ";
   String querey4 = "select Conferences from Conference where FacultyID = ? ";
   String querey5 = "select ResearchStudents from ResearchStudents where FacultyID = ? ";
   String querey6 = "select Teaching from Teachings where FacultyID = ? ";
   
   PreparedStatement p = con.prepareStatement(querey1); 
   p.setString(1,id);
   rs = p.executeQuery();
   
   
  // rs.beforeFirst();
         rs.next();
         personalInfo[0] = rs.getString(1);
         personalInfo[1] = rs.getString(2);
         personalInfo[2] = rs.getString(3);
         personalInfo[3] =rs.getString(4);
         personalInfo[4] =rs.getString(5);
         
   ///////////////////RESEARCH///////////////////////
        p = con.prepareStatement(querey2); 
        p.setString(1,id);
        rs = p.executeQuery();
        int i=0;
        while(rs.next())
        {
            Research[i++] = rs.getString(1);
        }
  
           
   ///////////////////PUBLICATIONS///////////////////////        
           
        p = con.prepareStatement(querey3); 
        p.setString(1,id);
        rs = p.executeQuery();
        i=0;
        while(rs.next())
        {
            Publications[i++] = rs.getString(1);
        }   
           
           
   ///////////////////CONFERENCES///////////////////////        
           
        p = con.prepareStatement(querey4); 
        p.setString(1,id);
        rs = p.executeQuery();
        i=0;
        while(rs.next())
        {
            conference[i++] = rs.getString(1);
        }   
                   
   
  ///////////////////RESEARCH STUDENTS///////////////////////        
           
        p = con.prepareStatement(querey5); 
        p.setString(1,id);
        rs = p.executeQuery();
        i=0;
        while(rs.next())
        {
            ResearchSudents[i++] = rs.getString(1);
        }   

        
           
  ///////////////////TEACHINGS///////////////////////        
           
        p = con.prepareStatement(querey6); 
        p.setString(1,id);
        rs = p.executeQuery();
        i=0;
        while(rs.next())
        {
            Teachings[i++] = rs.getString(1);
        }

 return true;
}

public String search(String id) throws SQLException{
    String querey1 = "select FacultyID from Faculty where FacultyID = ? ";
    PreparedStatement p = con.prepareStatement(querey1); 
    p.setString(1,id);
    rs = p.executeQuery();
    rs.next();
    return rs.getString("FacultyID");
}
public int totalFaculty() throws SQLException{
    st=con.createStatement();
    ResultSet rs=st.executeQuery("Select * from login");
    int count=0;
    rs.next();
    while(rs.next()){
        count++;
    }
    return count;
    
}

public void getPInfo(String[] id,String[] name,String[] mail,String[] ph) throws SQLException{
    st=con.createStatement();
    ResultSet rs=st.executeQuery("Select FacultyID,FacultyName,FacultyEmail,FacultyPhoneNo from Faculty order by facultyPriority");
    int i=0;
    while(rs.next()){
        id[i]=rs.getString("FacultyID");
        name[i]=rs.getString("FacultyName");
        mail[i]=rs.getString("FacultyEmail");
        ph[i]=rs.getString("FacultyPhoneNo");
        i++;
    }
}

public void storeResearch(String FacID,String res) throws SQLException{
    PreparedStatement s=con.prepareStatement("Insert Into Reseaech(FacultyID,Researches) Values(?,?)");
    s.setString(1, FacID);
    s.setString(2, res);
    s.executeUpdate();
}

public boolean deleteResearch(int sNo,String fID) throws SQLException{
    if(sNo==0)
        return false;
    st=con.createStatement();
    String query="Select ResearchID from Reseaech where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0)
        return false;
    String id=rs.getString("ResearchID");
    String q1="delete from Reseaech where Research=?";
    PreparedStatement ps=con.prepareStatement("delete from Reseaech where ResearchID=?");
    ps.setString(1,id);
    ps.execute();
    return true;
}

public void storeTeaching(String FacID,String t) throws SQLException{
    PreparedStatement s=con.prepareStatement("Insert Into Teachings(FacultyID,Teaching) Values(?,?)");
    s.setString(1, FacID);
    s.setString(2, t);
    s.executeUpdate();
}

public boolean deleteTeaching(int sNo,String fID) throws SQLException{
    if(sNo==0)
        return false;
    st=con.createStatement();
    String query="Select TeachingID from Teachings where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0)
        return false;
    String id=rs.getString("TeachingID");
    PreparedStatement ps=con.prepareStatement("delete from Teachings where TeachingID=?");
    ps.setString(1,id);
    ps.execute();
    return true;
}

public void storeResearchStudents(String FacID,String res) throws SQLException{
    PreparedStatement s=con.prepareStatement("Insert Into ResearchStudents(FacultyID,ResearchStudents) Values(?,?)");
    s.setString(1, FacID);
    s.setString(2, res);
    s.executeUpdate();
}

public boolean deleteResearchStudents(int sNo,String fID) throws SQLException{
    if(sNo==0)
        return false;
    st=con.createStatement();
    String query="Select StudentsID from ResearchStudents where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0)
        return false;
    String id=rs.getString("StudentsID");
    PreparedStatement ps=con.prepareStatement("delete from ResearchStudents where StudentsID=?");
    ps.setString(1,id);
    ps.execute();
    return true;
}

public void storePublication(String FacID,String pub) throws SQLException{
    PreparedStatement s=con.prepareStatement("Insert Into Publication(FacultyID,Publications) Values(?,?)");
    s.setString(1, FacID);
    s.setString(2,pub);
    s.executeUpdate();
}

public boolean deletePublication(int sNo,String fID) throws SQLException{
    if(sNo==0)
        return false;
    st=con.createStatement();
    String query="Select PublicationID from Publication where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0)
        return false;
    String id=rs.getString("PublicationID");
    PreparedStatement ps=con.prepareStatement("delete from Publication where PublicationID=?");
    ps.setString(1,id);
    ps.execute();
    return true;
}

public void storeConference(String FacID,String c) throws SQLException{
    PreparedStatement s=con.prepareStatement("Insert Into Conference(FacultyID,Conferences) Values(?,?)");
    s.setString(1, FacID);
    s.setString(2, c);
    s.executeUpdate();
}

public boolean deleteConference(int sNo,String fID) throws SQLException{
    if(sNo==0)
        return false;
    st=con.createStatement();
    String query="Select ConferenceID from Conference where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0)
        return false;
    String id=rs.getString("ConferenceID");
    PreparedStatement ps=con.prepareStatement("delete from Conference where ConferenceID=?");
    ps.setString(1,id);
    ps.execute();
    return true;
}
public void delete(String id) throws SQLException{
    PreparedStatement[] p=new PreparedStatement[7];
    p[0]=con.prepareStatement("delete from Conference where FacultyID=?");
    p[0].setString(1, id);
    p[0].execute();
    p[1]=con.prepareStatement("delete from Publication where FacultyID=?");
    p[1].setString(1, id);
    p[1].execute();
    p[2]=con.prepareStatement("delete from Reseaech where FacultyID=?");
    p[2].setString(1, id);
    p[2].execute();
    p[3]=con.prepareStatement("delete from ResearchStudents where FacultyID=?");
    p[3].setString(1, id);
    p[3].execute();
    p[4]=con.prepareStatement("delete from Teachings where FacultyID=?");
    p[4].setString(1, id);
    p[4].execute();
    
    String query="Select FacultyEmail from Faculty where FacultyID=?";
    PreparedStatement q = con.prepareStatement(query); 
    q.setString(1,id);
    rs = q.executeQuery();
    rs.next();
    
    p[5]=con.prepareStatement("delete from Login where Email=?");
    p[5].setString(1, rs.getString("facultyEmail"));
    p[5].execute();
    p[6]=con.prepareStatement("delete from Faculty where FacultyID=?");
    p[6].setString(1, id);
    p[6].execute();
}
public boolean searchID(String id) throws SQLException{
    st=con.createStatement();
    ResultSet rs=st.executeQuery("Select FacultyID from Faculty");
    while(rs.next()){
        if(rs.getString("FacultyId").equals(id))
            return true;
    }
    return false;
}
public void setName(String id,String name) throws SQLException{
    PreparedStatement preparedStmt = con.prepareStatement("update Faculty set FacultyName = ? where FacultyID=?");
    preparedStmt.setString(1, name);
    preparedStmt.setString(2, id);
    preparedStmt.executeUpdate();
}
public void setDes(String id,String des) throws SQLException{
    PreparedStatement preparedStmt = con.prepareStatement("update Faculty set FacultyDesignation = ? where FacultyID=?");
    preparedStmt.setString(1, des);
    preparedStmt.setString(2, id);
    preparedStmt.executeUpdate();
}

public void setMail(String id,String mail) throws SQLException{
    st=con.createStatement();
    String query="Select FacultyEmail from Faculty where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,id);
    rs = p.executeQuery();
    rs.next();
    PreparedStatement preparedStmt = con.prepareStatement("update Faculty set FacultyEmail = ? where FacultyID=?");
    preparedStmt.setString(1, mail);
    preparedStmt.setString(2, id);
    preparedStmt.executeUpdate();
    
    preparedStmt = con.prepareStatement("update Login set Email = ? where Email=?");
    preparedStmt.setString(1, mail);
    preparedStmt.setString(2, rs.getString(1));
    preparedStmt.executeUpdate();
}

public void setPhone(String id,String ph) throws SQLException{
    PreparedStatement preparedStmt = con.prepareStatement("update Faculty set FacultyPhoneNo = ? where FacultyID=?");
    preparedStmt.setString(1, ph);
    preparedStmt.setString(2, id);
    preparedStmt.executeUpdate();
}
public String getPass(String id) throws SQLException{
    st=con.createStatement();
    String query="Select FacultyEmail from Faculty where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,id);
    rs = p.executeQuery();
    rs.next();
    
    query="Select Password from Login where Email=?";
    p = con.prepareStatement(query); 
    p.setString(1,rs.getString(1));
    rs = p.executeQuery();
    rs.next();
    return rs.getString(1);
}

public void setPass(String pass,String id) throws SQLException{
    st=con.createStatement();
    String query="Select FacultyEmail from Faculty where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,id);
    rs = p.executeQuery();
    rs.next();
    
    PreparedStatement preparedStmt = con.prepareStatement("update Login set Password = ? where Email=?");
    preparedStmt.setString(1, pass);
    preparedStmt.setString(2, rs.getString(1));
    preparedStmt.executeUpdate();
}

public boolean editResearch(int sNo,String fID) throws SQLException{
    boolean flag=true;
    if(sNo==0){
        flag=false;
        return flag;
    }
    st=con.createStatement();
    String query="Select ResearchID,Researches from Reseaech where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0){
        flag=false;
        return flag;
    }
    String researchID=rs.getString("ResearchID");
    String research=rs.getString("Researches");
    JTextArea area=new JTextArea(research);
    area.setPreferredSize(new Dimension(70,100));
    JOptionPane op=new JOptionPane();
    int okCxl;
    okCxl = op.showConfirmDialog(null, area, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (okCxl == op.OK_OPTION){
        research = area.getText();
        if(research.equals("")){
            //deleteResearch(sNo,fID);
            String q1="delete from Reseaech where Research=?";
            PreparedStatement ps=con.prepareStatement("delete from Reseaech where ResearchID=?");
            ps.setString(1,researchID);
            ps.execute();
        }
        else{
        String id=rs.getString("ResearchID");
        String q1="update Reseaech set Researches=? where ResearchID=?";
        PreparedStatement ps=con.prepareStatement(q1);
        ps.setString(1,research);
        ps.setString(2,id);
        ps.executeUpdate();
        }
    }
    return flag;
}

public boolean editTeaching(int sNo,String fID) throws SQLException{
    boolean flag=true;
    if(sNo==0){
        flag=false;
        return flag;
    }
    st=con.createStatement();
    String query="Select TeachingID,Teaching from Teachings where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0){
        flag=false;
        return flag;
    }
    String teaching=rs.getString("Teaching");
    String id=rs.getString("TeachingID");
    JTextArea area=new JTextArea(teaching);
    area.setPreferredSize(new Dimension(70,100));
    JOptionPane op=new JOptionPane();
    int okCxl;
    okCxl = op.showConfirmDialog(null, area, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (okCxl == op.OK_OPTION){
        teaching = area.getText();
        if(teaching.equals("")){
            PreparedStatement ps=con.prepareStatement("delete from Teachings where TeachingID=?");
            ps.setString(1,id);
            ps.execute();
        }
        else{
        id=rs.getString("TeachingID");
        String q1="update Teachings set Teaching=? where TeachingID=?";
        PreparedStatement ps=con.prepareStatement(q1);
        ps.setString(1,teaching);
        ps.setString(2,id);
        ps.executeUpdate();
        }
    }
    return flag;
}

public boolean editResearchStudents(int sNo,String fID) throws SQLException{
    boolean flag=true;
    if(sNo==0){
        flag=false;
        return flag;
    }
    st=con.createStatement();
    String query="Select StudentsID,ResearchStudents from ResearchStudents where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0){
        flag=false;
        return flag;
    }
    String student=rs.getString("ResearchStudents");
    String ID=rs.getString("StudentsID");
    JTextArea area=new JTextArea(student);
    area.setPreferredSize(new Dimension(70,100));
    JOptionPane op=new JOptionPane();
    int okCxl;
    okCxl = op.showConfirmDialog(null, area, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (okCxl == op.OK_OPTION){
        student = area.getText();
        if(student.equals("")){
            PreparedStatement ps=con.prepareStatement("delete from ResearchStudents where StudentsID=?");
            ps.setString(1,ID);
            ps.execute();
        }
        else{
        String id=rs.getString("StudentsID");
        String q1="update ResearchStudents set ResearchStudents=? where StudentsID=?";
        PreparedStatement ps=con.prepareStatement(q1);
        ps.setString(1,student);
        ps.setString(2,id);
        ps.executeUpdate();
        }
    }
    return flag;
}
public boolean editPublication(int sNo,String fID) throws SQLException{
    boolean flag=true;
    if(sNo==0){
        flag=false;
        return flag;
    }
    st=con.createStatement();
    String query="Select PublicationID,Publications from Publication where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0){
        flag=false;
        return flag;
    }
    String pub=rs.getString("Publications");
    String ID=rs.getString("PublicationID");
    JTextArea area=new JTextArea(pub);
    area.setPreferredSize(new Dimension(70,100));
    JOptionPane op=new JOptionPane();
    int okCxl;
    okCxl = op.showConfirmDialog(null, area, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (okCxl == op.OK_OPTION){
        pub = area.getText();
        if(pub.equals("")){
            PreparedStatement ps=con.prepareStatement("delete from Publication where PublicationID=?");
            ps.setString(1,ID);
            ps.execute();
        }
        else{
        String id=rs.getString("PublicationID");
        String q1="update Publication set Publications=? where PublicationID=?";
        PreparedStatement ps=con.prepareStatement(q1);
        ps.setString(1,pub);
        ps.setString(2,id);
        ps.executeUpdate();
        }
    }
    return flag;
}

public boolean editConference(int sNo,String fID) throws SQLException{
    boolean flag=true;
    if(sNo==0){
        flag=false;
        return flag;
    }
    st=con.createStatement();
    String query="Select ConferenceID,Conferences from Conference where FacultyID=?";
    PreparedStatement p = con.prepareStatement(query); 
    p.setString(1,fID);
    rs = p.executeQuery();
    while(rs.next()){
        sNo--;
        if(sNo==0)
            break;
    }
    if(sNo!=0){
        flag=false;
        return flag;
    }
    String conf=rs.getString("Conferences");
    String ID=rs.getString("ConferenceID");
    JTextArea area=new JTextArea(conf);
    area.setPreferredSize(new Dimension(70,100));
    JOptionPane op=new JOptionPane();
    int okCxl;
    okCxl = op.showConfirmDialog(null, area, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (okCxl == op.OK_OPTION){
        conf = area.getText();
        if(conf.equals("")){
            PreparedStatement ps=con.prepareStatement("delete from Conference where ConferenceID=?");
            ps.setString(1,ID);
            ps.execute();
        }
        else{
        String id=rs.getString("ConferenceID");
        String q1="update Conference set Conferences=? where ConferenceID=?";
        PreparedStatement ps;
        ps = con.prepareStatement(q1);
        ps.setString(1,conf);
        ps.setString(2,id);
        ps.executeUpdate();
        }
    }
    return flag;
}
}






