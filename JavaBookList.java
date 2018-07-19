
package javabooklist;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class JavaBookList extends JFrame implements ActionListener{

    JLabel JL_rentChegg,JL_rentCengage,JL_buyUsedChegg,JL_buyUsedCengage,
            JL_buyUsedGastonBookstore,JL_buyNewChegg,JL_buyNewCengage,
            JL_buyNewGastonBookstore,JL_ISBN;
    JTextField JT_rentChegg,JT_rentCengage,JT_buyUsedChegg,JT_buyUsedCengage,
            JT_buyUsedGastonBookstore,JT_buyNewChegg,JT_buyNewCengage,
            JT_buyNewGastonBookstore,JT_ISBN;
    JButton btn_search;
    
    public JavaBookList(){
        //add labels/text fields/locations to the UI
        super("Search Book Price List");
        JL_ISBN = new JLabel ("Enter ISBN: ");
        JL_ISBN.setBounds (20, 20, 200, 20);
        JT_ISBN = new JTextField (20);
        JT_ISBN.setBounds (130, 20, 150, 20);
        btn_search = new JButton ("Search");
        btn_search.setBounds (300, 20, 80, 20);
        btn_search.addActionListener (this);
        setVisible(true);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centers the UI
        setSize(500,350);
        
        JL_rentChegg = new JLabel("Rent Chegg: ");
        JL_rentChegg.setBounds (20,50,100,20);
        JT_rentChegg = new JTextField(20);
        JT_rentChegg.setBounds (130,50,150,20);
        
        JL_rentCengage = new JLabel("Rent Cengage: ");
        JL_rentCengage.setBounds (20,80,100,20);
        JT_rentCengage = new JTextField(20);
        JT_rentCengage.setBounds(130,80,150,20);
        
        JL_buyUsedChegg = new JLabel("Used Chegg: ");
        JL_buyUsedChegg.setBounds (20,110,100,20);
        JT_buyUsedChegg = new JTextField(20);
        JT_buyUsedChegg.setBounds(130,110,150,20);
        
        JL_buyUsedCengage = new JLabel("Used Cengage: ");
        JL_buyUsedCengage.setBounds (20,140,100,20);
        JT_buyUsedCengage = new JTextField(20);
        JT_buyUsedCengage.setBounds(130,140,150,20);
        
        JL_buyUsedGastonBookstore = new JLabel("Used Gaston: ");
        JL_buyUsedGastonBookstore.setBounds (20,170,100,20);
        JT_buyUsedGastonBookstore = new JTextField(20);
        JT_buyUsedGastonBookstore.setBounds(130,170,150,20);
        
        JL_buyNewChegg = new JLabel("New Chegg: ");
        JL_buyNewChegg.setBounds (20,200,100,20);
        JT_buyNewChegg = new JTextField(20);
        JT_buyNewChegg.setBounds(130,200,150,20);
        
        JL_buyNewCengage = new JLabel("New Cengage: ");
        JL_buyNewCengage.setBounds (20,230,100,20);
        JT_buyNewCengage = new JTextField(20);
        JT_buyNewCengage.setBounds(130,230,150,20);
        
        JL_buyNewGastonBookstore = new JLabel("New Gaston: ");
        JL_buyNewGastonBookstore.setBounds (20,260,100,20);
        JT_buyNewGastonBookstore = new JTextField(20);
        JT_buyNewGastonBookstore.setBounds(130,260,150,20);
        
        setLayout(null);
        add(btn_search);
        add(JL_ISBN);
        add(JT_ISBN);
        add(JL_rentChegg);
        add(JT_rentChegg);
        add(JL_rentCengage);
        add(JT_rentCengage);
        add(JL_buyUsedChegg);
        add(JT_buyUsedChegg);
        add(JL_buyUsedCengage);
        add(JT_buyUsedCengage);
        add(JL_buyUsedGastonBookstore);
        add(JT_buyUsedGastonBookstore);
        add(JL_buyNewChegg);
        add(JT_buyNewChegg);
        add(JL_buyNewCengage);
        add(JT_buyNewCengage);
        add(JL_buyNewGastonBookstore);
        add(JT_buyNewGastonBookstore);
        
        
    }     
  @Override
  public void actionPerformed(ActionEvent e){
      Function f = new Function();
      ResultSet rs = null;
      String rC = "rentChegg";
      String rCen = "rentCengage ";
      String bUC = "buyUsedChegg";
      String bUCen = "buyUsedCengage";
      String bUGB = "buyUsedGastonBookstore";
      String bNC = "buyNewChegg";
      String bNCen = "buyNewCengage";
      String bnGB = "buyNewGastonBookstore";
      
      rs = f.find (JT_ISBN.getText());  //FIND FROM THE RESULT SET THE ISBN THE
                                        // USER ENTERED IN THE ISBN TEXTBOX
      try{
          if(rs.next()){
              JT_rentChegg.setText (rs.getString("rentChegg"));
              JT_rentCengage.setText (rs.getString("rentCengage"));
              JT_buyUsedChegg.setText (rs.getString("buyUsedChegg"));
              JT_buyUsedCengage.setText (rs.getString("buyUsedCengage"));
              JT_buyUsedGastonBookstore.setText (rs.getString(
                      "buyUsedGastonBookstore"));
              JT_buyNewChegg.setText (rs.getString("buyNewChegg"));
              JT_buyNewCengage.setText (rs.getString("buyNewCengage"));
              JT_buyNewGastonBookstore.setText (rs.getString(
                      "buyNewGastonBookstore"));
          } else{
              JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ISBN");
          }
              
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex.getMessage());
      }
  }
          
    public class Function{
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        public ResultSet find (String s){
            try{
                con = DriverManager.getConnection(
                   "jdbc:ucanaccess://E:/CAPSTONE/GCBookList.accdb");
                ps=con.prepareStatement("SELECT * FROM Master WHERE ISBN = ?");
                ps.setString(1,s);
                rs = ps.executeQuery();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            return rs;
        }
    }
    public static void main(String[] args){ 
        new JavaBookList();
    }   
}
