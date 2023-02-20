package ReportPanel;

import Connectivity.MySQLConnection;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReportPanel extends JPanel{

    int posX;
    int posY;
    int width;
    int height;
    
    public ReportPanel(){}
    public ReportPanel(int posX, int posY, int widht, int height ){
        
        this.posX = posX;
        this.posY = posY;
        this.width = widht;
        this.height = height;
    }
    public void drawPanel(){
        
        intiComponed();
    }
    public void intiComponed(){
        
        setBounds(posX, posY, width, height);
        setLayout(null);
        
        printReportBtn.setBounds(20, 60, 200, 30);
        printReportBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reportTable.print();
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, "Printing error: " + ex.getMessage());
                }
            }
        });

        searchTxt.setBounds(230, 60, getWidth() - 300, 30);
        searchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
           
                reportTable.setModel(SearchBookrentalModel(reportTable, searchTxt.getText()));
                
            }
        });
        
        reportTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"#RollID","BookTitle","GivenToPerson","GivenToType","Email","Contact","Address","RentIssueDate","RentExpiryDate","RentReservedDate","Status"}
                
        );

        reportTableScrollPane.setBounds(20, 100, getWidth() - 80, 460);
        reportTableScrollPane.setViewportView(reportTable);
        reportTable.setModel(model);
        reportTable.setModel(GetBookrentalModel(reportTable));
        
        add(reportTableScrollPane);
        add(printReportBtn);
    
        add(searchTxt);
        
    }
    
    private JButton printReportBtn = new JButton("Print Report:");
    
    private JTextField searchTxt = new JTextField();
    
    private JTable reportTable = new JTable();
    private JScrollPane reportTableScrollPane = new JScrollPane();

    private DefaultTableModel GetBookrentalModel(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT"
                        + " GivenToRollID,"
                        + " bookrental.BookTitle,"
                        + " GivenToEmail,"
                        + " GivenToContact,"
                        + " GivenToAddress,"
                        + " RentIssueDate,"
                        + " RentExpiryDate,"
                        + " RentReservedDate,"
                        + " GivenToType, "
                        + " GivenToPerson,"
                        + " Status,"
                        + " books.BookTitle FROM bookrental JOIN books WHERE bookrental.RID = books.RID AND bookrental.BookTitle = books.BookTitle;";
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        Object object[] = {
                            resultSet.getInt("GivenToRollID"),
                            resultSet.getString("BookTitle"),
                            resultSet.getString("GivenToPerson"),
                            resultSet.getString("GivenToType"),
                            resultSet.getString("GivenToEmail"),
                            resultSet.getString("GivenToContact"),
                            resultSet.getString("GivenToAddress"),
                            resultSet.getDate("RentIssueDate"),
                            resultSet.getDate("RentExpiryDate"),
                            resultSet.getDate("RentReservedDate"),
                            resultSet.getString("Status")

                        };
                        model.addRow(object);
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
        
        return model;
    } 
    private DefaultTableModel SearchBookrentalModel(JTable table, String NameStr){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM bookrental WHERE GivenToPerson LIKE LOWER('%" + NameStr + "%') OR BookTitle LIKE LOWER('%"+ NameStr +"%');";
                    
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object object[] = {
                            resultSet.getInt("GivenToRollID"),
                            resultSet.getString("BookTitle"),
                            resultSet.getString("GivenToPerson"),
                            resultSet.getString("GivenToType"),
                            resultSet.getString("GivenToEmail"),
                            resultSet.getString("GivenToContact"),
                            resultSet.getString("GivenToAddress"),
                            resultSet.getDate("RentIssueDate"),
                            resultSet.getDate("RentExpiryDate"),
                            resultSet.getDate("RentReservedDate"),
                            resultSet.getString("Status")
                            
                        };
                        model.addRow(object);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());

        }
        return model;
    }
    
}
