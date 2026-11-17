package hospital1;

    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.*;
    import java.util.logging.Level;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableRowSorter;


public class Secc extends Frame implements ActionListener,WindowListener{
        JFrame Sel=new JFrame("Hospital");
        ImageIcon BG=new ImageIcon("C:\\Users\\tabarak\\Desktop\\Screenshot (157).png");
    
    JLabel LBG=new JLabel("",BG,JLabel.CENTER);
        
        JButton B0=new JButton("TEAM_WORK'S_NAMES");
        JButton B1=new JButton("Doctors");
        JButton B2=new JButton("Patient");       
        JButton B3=new JButton("Test"); 
        JButton B7=new JButton("Back");
        JButton B8=new JButton("Exit");
        
        /*
        JButton B4=new JButton("Doctor & Patient");        
        JButton B5=new JButton("Doctor & Test");
        JButton B6=new JButton("Test & Patient");
        
        */
        
        
    Secc(){
        Sel.setLayout(null);
        Sel.setBounds(200, 200, 1100, 800);
        
      
        B0.setBounds(400, 650, 200 , 50);
        B0.addActionListener(this);
        B0.setFont(new Font("",Font.LAYOUT_LEFT_TO_RIGHT,15));
        Sel.add(B0); 
        
        B1.setBounds(100, 100, 200, 150);
        B1.addActionListener(this);
        B1.setFont(new Font("",Font.LAYOUT_LEFT_TO_RIGHT,30));
        Sel.add(B1);
        
        B2.setBounds(750, 100, 200, 150);
        B2.setFont(new Font("",Font.LAYOUT_LEFT_TO_RIGHT,30));
        B2.addActionListener(this);
        Sel.add(B2);
        
        B3.setBounds(420,350 , 200, 150);
        B3.setFont(new Font("",Font.LAYOUT_LEFT_TO_RIGHT,30));
        B3.addActionListener(this);
        Sel.add(B3);
        B7.setBounds(100, 650, 100, 50);
        B7.addActionListener(this);
        Sel.add(B7);
        B8.setBounds(850, 650, 100, 50);
        B8.addActionListener(this);
        Sel.add(B8);
        
        
      /*  B4.setBounds(300, 200, 200, 75);
        B4.addActionListener(this);
        Sel.add(B4);
        
        B5.setBounds(50, 330, 200, 75);
        B5.addActionListener(this);
        Sel.add(B5);
        
        B6.setBounds(300, 330, 200, 75);
        B6.addActionListener(this);
        Sel.add(B6);
        
        
        * */
               
        LBG.setBounds(0,0, 1200, 800);////////////////////////Image
        Sel.add(LBG);
        
        //Sel.addWindowListener(this);
        Sel.setVisible(true);
            }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==B1){
            Sel.setVisible(false);
           doctor doc=new doctor();
           JOptionPane.showMessageDialog(null, "welcome to Doctors");
        }
        if(ae.getSource()==B2){
            Sel.setVisible(false);
           Patient PA=new Patient();
           JOptionPane.showMessageDialog(null, "welcome to Patient");
        
        }
        if(ae.getSource()==B3){
            Sel.setVisible(false);
           test PA=new test();
           JOptionPane.showMessageDialog(null, "welcome to Tests");
          
        }
          if(ae.getSource()==B0){
            Sel.setVisible(false);
           team_name team=new team_name();
           JOptionPane.showMessageDialog(null, "welcome in our team ");
        }
       /* if(ae.getSource()==B4){
            Sel.setVisible(false);
           DocPat DP=new DocPat();
           JOptionPane.showMessageDialog(null, "welcome to Doctor & Patient");
        }
        if(ae.getSource()==B5){
        Sel.setVisible(false);
           DocTest DT=new DocTest();
           JOptionPane.showMessageDialog(null, "welcome to Doctor & Test");
        }
        if(ae.getSource()==B6){
        Sel.setVisible(false);
           PatTest PT=new PatTest();
           JOptionPane.showMessageDialog(null, "welcome to  Patient & Test");
        }
        if(ae.getSource()==B7){
            Sel.setVisible(false);
            Hospital1 h=new Hospital1();
        }*/
        if(ae.getSource()==B8){
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


        
    



    }

  