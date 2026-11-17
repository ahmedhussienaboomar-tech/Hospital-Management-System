package hospital1;

    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.*;
    import java.util.logging.Level;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableRowSorter;


class Patient extends Frame implements ActionListener,WindowListener{
     
    Statement stmt;
    Connection con;
    ResultSet rs;
    
    JFrame PA=new JFrame("Patient");
    ImageIcon BG=new ImageIcon("C:\\Users\\tabarak\\Desktop\\2017-07-24-Patient-background_0922-1024x683.jpg");
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
    
    TextField T1=new  TextField();
    TextField T2=new  TextField();
    TextField T3=new  TextField();
    TextField T4=new  TextField();
    TextField T5=new  TextField();
    TextField T6=new  TextField();
    
    
    JLabel L1=new JLabel("SSN");
    JLabel L2=new JLabel("Name");
    JLabel L3=new JLabel("Ener Date");
    JLabel L4=new JLabel("Checkout Date");
    JLabel L5=new JLabel("medical insurance");
   
 Patient(){  
     ConToSql();
        
        table1=new JTable(model);
        PA.add(new JScrollPane(table1));
        PA.setSize(750, 140);
        PA.setVisible(true);
        PA.setBounds(275, 200, 750, 600);
        PA.setLayout(null);
       //search
        T1.setBounds(50, 155, 150, 30);
        PA.add(T1);
        B1.setBounds(210, 145, 100, 50);
        B1.addActionListener(this);
        PA.add(B1);
        
        //exit add update delete
        B5.setBounds(50, 425, 100, 50);
        B5.addActionListener(this);
        B5.setFont(new Font("impact",Font.PLAIN,20));
        PA.add(B5);
        
        B2.setBounds(350, 425, 100, 50);
        B2.addActionListener(this);
        B2.setFont(new Font("impact",Font.PLAIN,20));
        PA.add(B2);
        
        B3.setBounds(470, 425, 100, 50);
        B3.addActionListener(this);
        B3.setFont(new Font("impact",Font.PLAIN,20));
        PA.add(B3);
        
        B4.setBounds(590, 425, 100, 50);
        B4.addActionListener(this);
        B4.setFont(new Font("impact",Font.PLAIN,20));
        PA.add(B4);
        
        //labels and textfield
        
        L1.setBounds(50, 200, 150, 20);
        L1.setFont(new Font("impact",Font.PLAIN,20));
        L1.setForeground(Color.WHITE);
        PA.add(L1);
        T2.setBounds(50, 225, 150, 25);
        PA.add(T2);
        
        L2.setBounds(313, 200, 150, 20);
        L2.setFont(new Font("impact",Font.PLAIN,20));
        L2.setForeground(Color.WHITE);
        PA.add(L2);
        T3.setBounds(313, 225, 150, 25);
        PA.add(T3);
        
        L3.setBounds(550, 200, 150, 20);
        L3.setFont(new Font("impact",Font.PLAIN,20));
        L3.setForeground(Color.WHITE);
        PA.add(L3);
        T4.setBounds(550, 225, 150, 25);
        PA.add(T4);
        
        L4.setBounds(50, 260, 150, 20);
        L4.setFont(new Font("impact",Font.PLAIN,20));
        L4.setForeground(Color.WHITE);
        PA.add(L4);
        T5.setBounds(50, 285, 150, 25);
        PA.add(T5);
        
        L5.setBounds(313, 260, 150, 20);
        L5.setFont(new Font("impact",Font.PLAIN,20));
        L5.setForeground(Color.WHITE);
        PA.add(L5);
        T6.setBounds(313, 285, 150, 25);
        PA.add(T6);
        
        
       
        
        
        LBG.setBounds(0,100,750,501);
        PA.add(LBG);
        PA.setVisible(true);
      
     } 
 
      public void ConToSql(){
          model.addColumn("DDSN");
            model.addColumn("Name");
            model.addColumn("Ener Date");
            model.addColumn("Checkout Date");
            model.addColumn("medical insurance");
            
            
            String url="jdbc:sqlserver://localhost:1433;databaseName=Hospital";
            String user="ahmed";
            String pass="123";
            
            try{
            con= DriverManager.getConnection(url, user, pass);
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Patient");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("SSN"),rs.getString("Name"),rs.getDate("Date_admitted"),rs.getDate("Check_out"),rs.getString("Medical_insur")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
   
      } 
      
       public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Patient");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                  while(rs.next()){
            model.addRow(new Object[] {rs.getString("SSN"),rs.getString("Name"),rs.getDate("Date_admitted"),rs.getDate("Check_out"),rs.getString("Medical_insur")});
            }
                
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }  
      }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==B2){
            
            String SSN= T2.getText().toString();
            String Name=T3.getText().toString();
            String Date_admitted=T4.getText().toString();
            String Check_out=T5.getText().toString();
            String Medical_insur=T6.getText().toString();
           
            
            String query="insert into Patient  values ( '" + SSN + "' , '" + Name + "' , '" + Date_admitted + "','" + Check_out + "','" + Medical_insur + "' )";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{SSN,Name,Date_admitted,Check_out,Medical_insur});
                JOptionPane.showMessageDialog(null, "added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
            
            
            T2.setText(null);
            T3.setText(null);
            T4.setText(null);
            T5.setText(null);
            T6.setText(null);
            
            
        }
        if(e.getSource()==B3){
            
           try{ 
            String SSN= T2.getText().toString();
            String Name=T3.getText().toString();
            String Date_admitted=T4.getText().toString();
            String Check_out=T5.getText().toString();
            String Medical_insur=T6.getText().toString();
           
            
            String query="update Patient set SSN='"+SSN+"', Name='"+Name+"', Date_admitted='"+Date_admitted+"', Check_out='"+Check_out+"', Medical_insur='"+Medical_insur+"' where SSN='"+SSN+"' ";
                
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
            T5.setText(null);
            T6.setText(null);
        }
        if(e.getSource()==B4){
            String query="delete from Patient where SSN=?";
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
        if (e.getSource()==B1){
            DefaultTableModel ob=(DefaultTableModel) table1.getModel();
             TableRowSorter <DefaultTableModel> obj=new TableRowSorter<>(ob);
             table1.setRowSorter(obj);
             obj.setRowFilter(RowFilter.regexFilter(T1.getText()));
             T1.setText(null);
            
        }
        if(e.getSource()==B5){
            PA.setVisible(false);
            Secc ch=new Secc();
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

    
