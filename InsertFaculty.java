
package homescreen;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertFaculty implements ActionListener{
    JFrame frame;
    JPanel headpanel,middlepanel,lastpanel;
    JButton back,submit,cancel;
    JTextField Name,Designation,Email,Phone;
    JPasswordField Password;
    JLabel app,personalInfo,name,designation,email,phone,password,note,research,teaching,research_student,publication,conference;
    JTextPane Research,Teaching,Research_student,Publication,Conference;
    StringBuilder faculty_id;

    InsertFaculty() {
        frame = new JFrame("Faculty Information System");
        frame.setLayout(new MigLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1368,720);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //head panel
        headpanel = new JPanel();
        //adding nizaam in head panel
        back = new JButton("Go Back");
        back.setPreferredSize(new Dimension(90,50));
        back.setFont(new Font("SansSerif",Font.BOLD,20));
        back.setBackground(new Color(224,225,225));
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        app = new JLabel("Faculty Information System");
        app.setFont( new Font("SansSerif", Font.BOLD, 50));
        
        app.setForeground(new Color(0,128,128));
        //setting layout of head panel
        headpanel.setLayout(new MigLayout());
        headpanel.add(back);
        headpanel.add(app,"gap 20%");
        headpanel.setPreferredSize(new Dimension(1368,150));

        //middle panel
        middlepanel = new JPanel();
        //adding nizaam in middle panel
        personalInfo = new JLabel("Personal Information:");
        name = new JLabel("*Name:");
        Name = new JTextField(20);
        designation = new JLabel("*Designation:");
        Designation = new JTextField(20);
        email = new JLabel("*Email:");
        Email = new JTextField(20);
        phone = new JLabel("*Phone#");
        Phone = new JTextField(20);
        password = new JLabel("*Password:");
        Password = new JPasswordField(20);

        note = new JLabel("NOTE:Separate new line in following fields with '$' sign");
        note.setFont(new Font("SansSerif",Font.BOLD,15));

        research = new JLabel("Research:");
        Research = new JTextPane();
        Research.setPreferredSize(new Dimension(300,100));
        Research.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        teaching = new JLabel("Teaching:");
        Teaching = new JTextPane();
        Teaching.setPreferredSize(new Dimension(300,120));
        Teaching.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        research_student = new JLabel("Research Students:");
        Research_student = new JTextPane();
        Research_student.setPreferredSize(new Dimension(300,120));
        Research_student.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        publication = new JLabel("Pulibcations:");
        Publication = new JTextPane();
        Publication.setPreferredSize(new Dimension(300,120));
        Publication.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        conference = new JLabel("Conference:");
        Conference = new JTextPane();
        Conference.setPreferredSize(new Dimension(300,120));
        Conference.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        //last panel
        lastpanel = new JPanel();
        //submit button
        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(70,70));
        submit.setFont( new Font("SansSerif", Font.BOLD, 20));
        submit.setBackground(new Color(224,225,225));
        submit.setForeground(Color.BLACK);
        this.submit.addActionListener(this);
        
        //cancel button
        cancel = new JButton("Reset");
        cancel.setPreferredSize(new Dimension(70,70));
        cancel.setFont( new Font("SansSerif", Font.BOLD, 20));
        cancel.setBackground(new Color(224,225,225));
        cancel.setForeground(Color.BLACK);
        this.cancel.addActionListener(this);

        //setting layout of middle panel
        middlepanel.setLayout(new MigLayout());
        middlepanel.add(personalInfo,"wrap");

        middlepanel.add(name,"gapleft 10%");
        middlepanel.add(Name);

        middlepanel.add(designation,"gapleft 5%");
        middlepanel.add(Designation);

        middlepanel.add(email,"gapleft 5%");
        middlepanel.add(Email,"wrap");

        middlepanel.add(phone,"gapleft 10%");
        middlepanel.add(Phone);

        middlepanel.add(password,"gapleft 5%");
        middlepanel.add(Password,"wrap");

        middlepanel.add(research,"gapleft 10%");
        middlepanel.add(Research);

        middlepanel.add(teaching,"gap 5%");
        middlepanel.add(Teaching,"wrap");

        middlepanel.add(research_student,"gapleft 10%");
        middlepanel.add(Research_student);

        middlepanel.add(publication,"gapleft 5%");
        middlepanel.add(Publication,"wrap");

        middlepanel.add(conference,"gapleft 10%");
        middlepanel.add(Conference,"wrap");

        //setting layout of last panel
        lastpanel.setLayout(new MigLayout());
        lastpanel.setPreferredSize(new Dimension(1368,50));
        lastpanel.add(cancel,"gapleft 50%");
        lastpanel.add(submit,"gap 10%");

        frame.add(headpanel,"wrap");
        frame.add(middlepanel,"wrap");
        frame.add(lastpanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        InsertFaculty f = new InsertFaculty();
    }
    


  
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj.equals(back)){
            try {
                ADMINUPDATEPAGE u=new ADMINUPDATEPAGE();
                //this.setVisible(false);
                this.frame.setVisible(false);
                u.setSize(1500, 750);
            } catch (SQLException ex) {
                Logger.getLogger(InsertFaculty.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(obj.equals(cancel)){
            int input = JOptionPane.showConfirmDialog(null, 
                "Are you sure?!","",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(input==0){
                InsertFaculty f=new InsertFaculty();
                this.frame.setVisible(false);
            }
        }
        else if(obj.equals(submit)){
            String name = Name.getText();
            String designation = Designation.getText();
            String email = Email.getText();
            String phone = Phone.getText();
            String pass = Password.getText();
            //reading faculty ID from db table Faculty
            String url = "jdbc:ucanaccess://D://University//5th Semester//SC(CS322)//Group#1(Faculty Information System)//Database1.accdb";
            try {
                Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select FacultyID From Faculty");
                faculty_id = new StringBuilder("");
                while(rs.next()) {
                    faculty_id.append(" " + rs.getString("FacultyID"));//appending all program from DB in String
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
            //creating a combo box and setting programs from DB in it
            String[] s = faculty_id.toString().split(" ");//converting stringbuilder to array string
            String[] f_id = faculty_id.toString().split(" ");//converting stringbuilder to array string
            //code for automatically generate ID
            String id = "E";
            Random rand = new Random();
            int rand_int = rand.nextInt(20)+1;
            id = "E" + rand_int;
            int i = 0;
            while(i < f_id.length){
                if(f_id[i].equals(id)){
                    rand_int = rand.nextInt(20);
                    id = "E" + rand_int;
                    i = -1;
                }
                i++;
            }
            
            
            //splitting research text pane into array of Strings
            String[] res = Research.getText().split("!");
            //splitting teach text pane into array of Strings
            String[] teaching = Teaching.getText().split("!");
            //splitting res student text pane into array of Strings
            String[] res_stu = Research_student.getText().split("!");
            //splitting publication text pane into array of Strings
            String[] pub = Publication.getText().split("!");
            //splitting conference text pane into array of Strings
            String[] conf = Conference.getText().split("!");
            boolean flag=false;
           
            if( name.equals("") || designation.equals("") || email.equals("") || phone.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(null,"Some Field(s) is empty!");
                flag=true;
            }
            if(!flag){
            if(!name.equals("")){
                for(int j=0;j<name.length();j++){
                    if(!(name.charAt(j) >= 'a' && name.charAt(j) < 'z') && !(name.charAt(j) >= 'A' && name.charAt(j) <= 'Z') && name.charAt(j)!= 32 && name.charAt(j) != '.'){
                        JOptionPane.showMessageDialog(null,"Invalid Name!");
                        flag=true;
                        break;
                    }
                }
            }
            }
            if(!flag){
            if(!designation.equals("")){
                for(int j=0;j<designation.length();j++){
                    if(!(designation.charAt(j) >= 'a' && designation.charAt(j) <= 'z') && !(designation.charAt(j) >= 'A' && designation.charAt(j) <= 'Z') && designation.charAt(j)!= ' '){
                        JOptionPane.showMessageDialog(null,"Invalid Designation!");
                        flag=true;
                        break;
                    }
                }
            }
            }
            if(!flag){
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
                        flag=true;
                }
            }
            }
            if(!flag){
             if(!phone.equals("")){
                for(int j=0;j<phone.length();j++){
                    if(phone.charAt(j)!='+' && phone.charAt(j)!='-' && (phone.charAt(j) <48 || phone.charAt(j) >57)){
                        JOptionPane.showMessageDialog(null,"Invalid PhoneNo!");
                        flag=true;
                        break;
                    }
                }
            }
            }
            
            if(flag){
                
            }
            else{
                //inserting in Faculty table
                try {
                    Connection con = DriverManager.getConnection(url);
                    PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Faculty(FacultyID,FacultyEmail,FacultyDesignation,FacultyName,FacultyPhoneNo) Values(?,?,?,?,?)");
                    st.setString(1, id);
                    st.setString(2, email);
                    st.setString(3, designation);
                    st.setString(4, name);
                    st.setString(5, phone);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }

                //inserting in conference table
               
                if(conf !=null){
                try {
                    Connection con = DriverManager.getConnection(url);
                    for(int j=0;j<conf.length;j++) {
                        if(!conf[j].isEmpty()){
                        PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Conference(ConferenceID,FacultyID,Conferences) Values(?,?,?)");
                        st.setString(1, Integer.toString(j));
                        st.setString(2, id);
                        st.setString(3, conf[j]);
                        st.executeUpdate();
                        }

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                }
                //inserting in login table
                try {
                    Connection con = DriverManager.getConnection(url);
                    PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Login(Email,Password) Values(?,?)");
                    st.setString(1, email);
                    st.setString(2, pass);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                //insert into Publication table
               
                if(pub!=null){
                try {
                    Connection con = DriverManager.getConnection(url);
                    for(int j=0;j<pub.length;j++) {
                        if(!pub[j].isEmpty()){
                        PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Publication(FacultyID,Publications) Values(?,?)");
                        st.setString(1, id);
                        st.setString(2, pub[j]);
                        st.executeUpdate();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                }
                //insert into Reseaech table
                
                if(res!=null){
                try {
                    Connection con = DriverManager.getConnection(url);
                    for(int j=0;j<res.length;j++) {
                        if(!res[j].isEmpty()){
                        PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Reseaech(FacultyID,Researches) Values(?,?)");
                        st.setString(1, id);
                        st.setString(2, res[j]);
                        st.executeUpdate();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                }
                //insert into Research students table
                
                if(res_stu!=null){
                try {
                    Connection con = DriverManager.getConnection(url);
                    for(int j=0;j<res_stu.length;j++) {
                        if(!res_stu[j].isEmpty()){
                        PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into ResearchStudents(FacultyID,ResearchStudents) Values(?,?)");
                        st.setString(1, id);
                        st.setString(2, res_stu[j]);
                        st.executeUpdate();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                }
                //insert into Teachings table
                
                if(teaching!=null){
                try {
                    Connection con = DriverManager.getConnection(url);
                    for(int j=0;j<teaching.length;j++) {
                        if(!teaching[j].isEmpty()){
                        PreparedStatement st = (PreparedStatement) con.prepareStatement("Insert Into Teachings(FacultyID,Teaching) Values(?,?)");
                        st.setString(1, id);
                        st.setString(2, teaching[j]);
                        st.executeUpdate();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                finally{
                    JOptionPane.showMessageDialog(null,"system updated!\n Assigned FacultyID is "+ id);
                    InsertFaculty iff=new InsertFaculty();
                    this.frame.setVisible(false);
                }
                }
            }
            
        }
    }

}