
package hospital1;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.*;
    import java.util.logging.Level;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableRowSorter;

   







class doctor implements ActionListener,WindowListener {
    Statement stmt;
    Connection con;
    ResultSet rs;
    
    JFrame Doc=new JFrame("Doctor");
    ImageIcon BG=new ImageIcon("C:\\Users\\tabarak\\Desktop\\422429.jpg");
    
    JLabel LBG=new JLabel("",BG,JLabel.CENTER);
    
    JTable table1;
    
    String [] Coloums={};
    Object [][] rowdata={};
    
    DefaultTableModel model=new DefaultTableModel(rowdata,Coloums);
    

    JButton B1=new JButton("Search");    
    JButton B2=new JButton("Add");   
    JButton B3=new JButton("Update");    
    JButton B4=new JButton("Delete");    
    JButton B5=new JButton("Back");
    JButton B6=new JButton("Exit");
    
    TextField T1=new  TextField();
    TextField T2=new  TextField();
    TextField T3=new  TextField();
    TextField T4=new  TextField();
    
    
    JLabel L1=new JLabel("DSSN");
    JLabel L2=new JLabel("Name");
    JLabel L3=new JLabel("Specialization");
   
 doctor(){  
     ConToSql();
        
        table1=new JTable(model);
        Doc.add(new JScrollPane(table1));
        Doc.setSize(1000, 200);
        Doc.setVisible(true);
        Doc.setBounds(900,100,1000,900);
        Doc.setLayout(null);
        //search
        T1.setBounds(50, 255, 150, 30);///////////////////////////////search
        Doc.add(T1);
        B1.setBounds(210, 245, 100, 50);
        B1.addActionListener(this);
        Doc.add(B1);
        
        //exit add update delete
        B5.setBounds(50, 725, 100, 50);/////////////////////////////////Back
        B5.addActionListener(this);
        B5.setFont(new Font("impact",Font.PLAIN,20));
        Doc.add(B5);
        
        B6.setBounds(800, 725, 100, 50);/////////////////////////////////Exit
        B6.addActionListener(this);
        B6.setFont(new Font("impact",Font.PLAIN,20));
        Doc.add(B6);
        
        B2.setBounds(350, 725, 100, 50);////////////////////////////////Add
        B2.addActionListener(this);
        B2.setFont(new Font("impact",Font.PLAIN,20));
        Doc.add(B2);
        
        B3.setBounds(470, 725, 100, 50);//////////////////////////////Update
        B3.addActionListener(this);
        B3.setFont(new Font("impact",Font.PLAIN,20));
        Doc.add(B3);
        
        B4.setBounds(590, 725, 100, 50);///////////////////////////////Delet
        B4.addActionListener(this);
        B4.setFont(new Font("impact",Font.PLAIN,20));
        Doc.add(B4);
        
        //labels and textfield
        
        L1.setBounds(145, 600, 150, 20);/////////////////////////////////DSSN
        L1.setFont(new Font("impact",Font.PLAIN,20));
        L1.setForeground(Color.WHITE);
        Doc.add(L1);
        T2.setBounds(50, 625, 150, 25);
        Doc.add(T2);
        
        L2.setBounds(323,600, 150, 20);////////////////////////////////Name
        L2.setFont(new Font("impact",Font.PLAIN,20));
        L2.setForeground(Color.WHITE);
        Doc.add(L2);
        T3.setBounds(313, 625, 150, 25);
        Doc.add(T3);
        
        L3.setBounds(550, 600, 150, 20);/////////////////////////Specialization
        L3.setFont(new Font("impact",Font.PLAIN,20));
        L3.setForeground(Color.WHITE);
        Doc.add(L3);
        T4.setBounds(550, 625, 150, 25);
        Doc.add(T4);
        
        
       
        
        
        LBG.setBounds(0,150,1200, 800);////////////////////////Image
        Doc.add(LBG);
        Doc.setVisible(true);
      
     } 
 
      public void ConToSql(){
          model.addColumn("DDSN");
            model.addColumn("Name");
            model.addColumn("Specialization");
            
            
            String url="jdbc:sqlserver://localhost:1433;databaseName=Hospital";
            String user="ahmed";
            String pass="123";
            
            try{
            con= DriverManager.getConnection(url, user, pass);
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Doctor");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("DSSN"),rs.getString("Name"),rs.getString("Specialization")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
          
          
      } 
        // do nothing
    
      
      public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Doctor");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
            
                       model.addRow(new Object[] {rs.getString("DSSN"),rs.getString("Name"),rs.getString("Specialization")});
            } 
                
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
          
          
      } 
        
 
 
 
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==B2){/////////////////////////////////////////////////////////add
            
            String DSSN= T2.getText().toString();
            String Name=T3.getText().toString();
            String Specialization=T4.getText().toString();
           
            
            String query="insert into Doctor  values ( '" + DSSN + "' , '" + Name + "' , '" + Specialization + "' )";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{DSSN,Name,Specialization});
                JOptionPane.showMessageDialog(null, "added Successfully");
                
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
            
            
            T2.setText(null);
            T3.setText(null);
            T4.setText(null);
            
            
        }
        if(e.getSource()==B3){/////////////////////////////////////////////////////////Update
            
           try{ 
                String DSSN= T2.getText().toString();
            String Name=T3.getText().toString();
            String Specialization=T4.getText().toString();
           
            
            String query="update Doctor set DSSN='"+DSSN+"', Name='"+Name+"', Specialization='"+Specialization+"' where DSSN='"+DSSN+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    UpdateTable();
                    JOptionPane.showMessageDialog(null, "updated Successfully");
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
           T2.setText(null);
            T3.setText(null);
            T4.setText(null);
            
            
        }
        if(e.getSource()==B4){//////////////////////////////////////////////////////////////delet
            String query="delete from Doctor where DSSN=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, T2.getText());
                 PS.execute();
                 UpdateTable();
                 JOptionPane.showMessageDialog(null, "deleted Successfully");
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                T2.setText(null);
                
        }
        if (e.getSource()==B1){//////////////////////////////////////////////////////search
            DefaultTableModel ob=(DefaultTableModel) table1.getModel();
             TableRowSorter <DefaultTableModel> obj=new TableRowSorter<>(ob);
             table1.setRowSorter(obj);
             obj.setRowFilter(RowFilter.regexFilter(T1.getText()));
             T1.setText(null);
            
        }
        if(e.getSource()==B5){
            Doc.setVisible(false);
            Secc ch=new Secc();
        }
        if(e.getSource()==B6)
        {
           System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        {
        System.exit(0);
        }
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