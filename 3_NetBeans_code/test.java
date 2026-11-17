
package hospital1;


    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.*;
    import java.util.logging.Level;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableRowSorter;


class test extends Frame implements ActionListener,WindowListener{
     
    Statement stmt;
    Connection con;
    ResultSet rs;
    
    JFrame TE=new JFrame("Test");
    ImageIcon BG=new ImageIcon("C:\\Users\\tabarak\\Desktop\\624111.jpg");
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
    TextField T5=new  TextField();
    
    
    
    JLabel L1=new JLabel("ID");
    JLabel L2=new JLabel("Name");
    JLabel L3=new JLabel("Result");
    JLabel L4=new JLabel("Date and time");
    
   
 test(){  
     ConToSql();
        
        table1=new JTable(model);
        TE.add(new JScrollPane(table1));
        TE.setSize(1000, 300);
        TE.setVisible(true);
        TE.setBounds(600,30,1000,1000);
        TE.setLayout(null);
        //search
        T1.setBounds(650, 410, 150, 30);
        TE.add(T1);
        B1.setBounds(850,400 , 100, 50);
        B1.addActionListener(this);
        TE.add(B1);
        
        //exit add update delete
        B5.setBounds(50, 825, 100, 50);
        B5.addActionListener(this);
        B5.setFont(new Font("impact",Font.PLAIN,20));
        TE.add(B5);
        
        B6.setBounds(850, 825, 100, 50);
        B6.addActionListener(this);
        B6.setFont(new Font("impact",Font.PLAIN,20));
        TE.add(B6);
        
        B2.setBounds(350, 825, 100, 50);
        B2.addActionListener(this);
        B2.setFont(new Font("impact",Font.PLAIN,20));
        TE.add(B2);
        
        B3.setBounds(470, 825, 100, 50);
        B3.addActionListener(this);
        B3.setFont(new Font("impact",Font.PLAIN,20));
        TE.add(B3);
        
        B4.setBounds(590, 825, 100, 50);
        B4.addActionListener(this);
        B4.setFont(new Font("impact",Font.PLAIN,20));
        TE.add(B4);
        
        //labels and textfield
        
        L1.setBounds(50, 750, 150, 20);
        L1.setFont(new Font("impact",Font.PLAIN,20));
        L1.setForeground(Color.WHITE);
        TE.add(L1);
        T2.setBounds(50, 700, 150, 25);
        TE.add(T2);
        
        L2.setBounds(313, 750, 150, 20);
        L2.setFont(new Font("impact",Font.PLAIN,20));
        L2.setForeground(Color.WHITE);
        TE.add(L2);
        T3.setBounds(313, 700, 150, 25);
        TE.add(T3);
        
        L3.setBounds(550, 750, 150, 20);
        L3.setFont(new Font("impact",Font.PLAIN,20));
        L3.setForeground(Color.WHITE);
        TE.add(L3);
        T4.setBounds(550, 700, 150, 25);
        TE.add(T4);
        
        L4.setBounds(797, 750, 150, 20);
        L4.setFont(new Font("impact",Font.PLAIN,20));
        L4.setForeground(Color.WHITE);
        TE.add(L4);
        T5.setBounds(787, 700, 150, 25);
        TE.add(T5);
        
       
        
        
       
        
        
        LBG.setBounds( 0,250,1200, 800 );
        TE.add(LBG);
        TE.setVisible(true);
      
     } 
 
      public void ConToSql(){
          model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Result");
            model.addColumn("Date and time");
           
            
            
            String url="jdbc:sqlserver://localhost:1433;databaseName=Hospital";
            String user="ahmed";
            String pass="123";
            
            try{
            con= DriverManager.getConnection(url, user, pass);
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Test");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("ID"),rs.getString("Name"),rs.getString("Result"),rs.getDate("Date_Time")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
          
          
      } 
      public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Test");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
            model.addRow(new Object[] {rs.getString("ID"),rs.getString("Name"),rs.getString("Result"),rs.getDate("Date_Time")});
            }
                
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }  
      }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==B2){
            
            String ID= T2.getText().toString();
            String Name=T3.getText().toString();
            String Result=T4.getText().toString();
            String Date_Time=T5.getText().toString();
            
           
            
            String query="insert into Test  values ( '" + ID + "' , '" + Name + "' , '" + Result + "','" + Date_Time + "' )";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{ID,Name,Result,Date_Time});
                JOptionPane.showMessageDialog(null, "added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
            
            
            T2.setText(null);
            T3.setText(null);
            T4.setText(null);
            T5.setText(null);
            
            
            
        }
        if(e.getSource()==B3){
            
           try{ 
            String ID= T2.getText().toString();
            String Name=T3.getText().toString();
            String Result=T4.getText().toString();
            String Date_Time=T5.getText().toString();
            
           
            
            String query="update Test set ID='"+ID+"', Name='"+Name+"', Result='"+Result+"', Date_Time='"+Date_Time+"' where ID='"+ID+"' ";
                
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
            
        }
        if(e.getSource()==B4){
            String query="delete from Test where ID=?";
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
            TE.setVisible(false);
            Secc ch=new Secc();
        }
        if(e.getSource()==B6)
        {
            System.exit(0);
        }
         
    }


    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
          System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
       
    }

    @Override
    public void windowIconified(WindowEvent e) {
       
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
      
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}