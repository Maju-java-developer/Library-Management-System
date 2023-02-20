package Core;

import Connectivity.MySQLConnection;
import Connectivity.NewMySQLConnection;
import DashBaordPanel.DashBoardDataBar;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Defaults {
    
    public static int generateRalationID(){
        
        int randomiseOne = new Random().nextInt(9999);
        int randomiseTwo = new Random().nextInt(9999);

        int extraRandom = new Random().nextInt(10);

        int finaRandom = (randomiseOne+randomiseTwo) + extraRandom;
        
        return finaRandom;
    }
    
    public static String iconPath(String imagepath, String filetype){
        return new File("").getAbsoluteFile() + "\\src\\Images\\" +imagepath+"."+filetype;
    }
    
    public static String currentDate(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        String dateFromFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);

        return dateFromFormat;
    }
    
    public static String convertDateToStr(Date date){
        String dateFromFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateFromFormat;
    }
    
    public static Date convertStringToDate(String dateStr){
        
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(dateStr.replaceAll("-", "/"));
        calendar.setTime(date);
        
        return calendar.getTime();
    }
    
    public static int getCountValue(String table){
        int RowCount = 0;
        String query = "SELECT * FROM " + table + ";";
        
        try {
            Connection myConn = NewMySQLConnection.GetConnection();
            
            try {
                Statement statement = myConn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        RowCount = resultSet.getRow();
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
        return RowCount;
    }

    public static int GetBookrentalCount(String status){
        int RowCount = 0;
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                
                String query = "SELECT * FROM bookrental WHERE Status = '" + status + "';";
                
                PreparedStatement preparedStatement = myConn.prepareCall(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        RowCount = resultSet.getRow();
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
        return RowCount;
    }
    
    public static ImageIcon rescaleImage(int width, int Height, String path){
        ImageIcon image = new ImageIcon(
                new ImageIcon(path)
                        .getImage()
                        .getScaledInstance(
                                width, 
                                Height,
                                Image.SCALE_SMOOTH)
    
        );
        return image;
    }
    
    public static ImageIcon rescaleImageFromBytes(int width, int Height, byte[] Imagebytes){
        ImageIcon image = new ImageIcon(
                new ImageIcon(Imagebytes)
                        .getImage()
                        .getScaledInstance(
                                width, 
                                Height,
                                Image.SCALE_SMOOTH));
        return image;
    }

    public static String currentTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp);
    }
    
    // Method For To Print Panel Contents
    public static void printRecord(JPanel panel){
        // Create PrinterJob Here
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        // Set Printer Job Name
        printerJob.setJobName("Print Record");
        // Set Printable
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Check If No Printable Content
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                // Set Graphics Translations
                // A Little Correction here Multiplication was not working so I replaced with addition
                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
                // This is a page scale. Default should be 0.3 I am using 0.5
                graphics2D.scale(0.7, 0.7);
                
                // Now paint panel as graphics2D
                panel.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
        });
        // Store printerDialog as boolean
        boolean returningResult = printerJob.printDialog();
        // check if dilog is showing
        if(returningResult){
            // Use try catch exeption for failure
            try{
                // Now call print method inside printerJob to print
                printerJob.print();
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(null, "Print Error: " + printerException.getMessage());
            }
        }
    }
    
    // Static INSTANCES
    public static DefaultTableModel bookRentalModel = null;
    public static JTable bookRentalTable = null;
    
    public static DefaultTableModel booksModel = null;
    public static JTable bookTable = null;

    public static DefaultTableModel authorsModel = null;
    public static JTable authorsTable = null;

    //Static INSTANCES 
    public static DashBoardDataBar totalMembersDataBar = null;
    public static DashBoardDataBar totalbooksDataBar = null;
    public static DashBoardDataBar totalIssuedDataBar = null;
    public static DashBoardDataBar totalReturnedDataBar = null;
    public static DashBoardDataBar totalSoldDataBar = null;
    public static DashBoardDataBar totalLostDataBar = null;
    
}