package AdminPanel;

import static AdminPanel.BookRentalPanel.displayTotalIssueBook;
import Core.Defaults;
import com.mysql.jdbc.MySQLConnection;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BookRentalDialog extends JDialog{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public BookRentalDialog(){}
    public BookRentalDialog(int posX, int posY, int widht, int height){
    
        this.posX = posY;
        this.posY =  posY;
        this.width = widht;
        this.height = height;

    }

    public static int bookRID;
    public static int BookID;
    public static String bookName = "";
    private String userName = "";
    public static String selectedType = "";

    public void drawPanel(){
        
        intiComponeds();
    }
    
    public void intiComponeds(){
    
        SaveBtnPanel.setBounds(0, 640, 720, 40);

        //SelectUser From GiveToBookBox:
        givebooktoLbl.setBounds(450, 20, 200, 30);
        givebookComboBox.setBounds(550, 20, 150, 30);
        givebookComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedType = givebookComboBox.getSelectedItem().toString();
            }
        });

        imagePanel.setBounds(30, 40, 180, 180);
        imagePanel.setBackground(Color.WHITE);

        imageLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        imageLbl.setFont(new Font("Sogoe UI Light", 0, 20));

        //Working On FetchData From Users Table:
        
        rollIDlbl.setBounds(240, 70, 150, 30);
        rollIDTxt.setBounds(360, 70, 100, 30);
        rollIDTxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (selectedType.equals("") || selectedType.equals("-- SELECT USER --")) {
                    JOptionPane.showMessageDialog(null, "Please choose user type from left upper corner.");

                }else{
                    if (!rollIDTxt.getText().isEmpty()) {
                        fetchName();
                    }else{
                        JOptionPane.showMessageDialog(null, "Please type Roll ID to fetch data!");
                    }
                }
            }
        });
        //End FetchData:
        
        //Working On Searching Books Name & ID From List:
        displayNameLbl.setBounds(470, 70, 200, 30);
        displayNameLbl.setFont(new Font("Segoue UI Bold", 0, 14));

        bookNameLbl.setBounds(240, 120, 150, 30);
        bookNameTxt.setBounds(360, 120, 250, 30);
        bookNameTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bookNameList.setModel(setBookNameList(bookNameList, bookNameTxt.getText()));
                bookIDList.setModel(setBookIDList(bookIDList, bookNameTxt.getText()));

            }
        });
        //End Work
        
        //Working On Get BookID From BookIDList:
        bookIDlistScrollPane.setBounds(360, 160, 50, 100);
        bookIDlistScrollPane.setViewportView(bookIDList);
        bookIDList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                BookID = Integer.parseInt(bookIDList.getSelectedValue().toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        //End:
        
        //Working On Get BookName From BooksNameList:
        bookNameListSrollpane.setBounds(410, 160, 200, 100);
        bookNameListSrollpane.setViewportView(bookNameList);
        bookNameList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                bookName = bookNameList.getSelectedValue().toString();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        emailLbl.setBounds(240, 280, 150, 30);
        emailTxt.setBounds(360, 280, 250, 30);

        contactLbl.setBounds(240, 330, 150, 30);
        contactTxt.setBounds(360, 330, 250, 30);

        addressLbl.setBounds(240, 380, 150, 30);
        addressTxt.setBounds(360, 380, 250, 30);

        statusLbl.setBounds(240, 430, 150, 30);
        statusBox.setBounds(360, 430, 250, 30);
        
        rentExpiryDateLbl.setBounds(240, 480, 150, 30);
        rentExpiryDateCal.setBounds(360, 480, 250, 30);

        rentReservedDateLbl.setBounds(240, 530, 150, 30);
        rentReservedDateCal.setBounds(360, 530, 250, 30);
        
        resetBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rollIDTxt.setText("");
                displayNameLbl.setText("");
                bookNameTxt.setText("");
                emailTxt.setText("");
                contactTxt.setText("");
                addressTxt.setText("");
                givebookComboBox.setSelectedItem("-- SELECT USER --");
                statusBox.setSelectedItem("-- Select Status --");
                rentExpiryDateCal.setDate(null);
                rentReservedDateCal.setDate(null);
                imageLbl.setIcon(null);
                
            }
        });
        
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rollIDTxt.setText("");
                displayNameLbl.setText("");
                bookNameTxt.setText("");
                emailTxt.setText("");
                contactTxt.setText("");
                addressTxt.setText("");
                rentExpiryDateCal.setDate(null);
                rentReservedDateCal.setDate(null);
                imageLbl.setIcon(null);
                givebookComboBox.setSelectedItem("-- SELECT USER --");
                statusBox.setSelectedItem("-- Select Status --");
                dispose();

            }
        });

        add(imagePanel);
        imagePanel.add(imageLbl);
        
        add(givebooktoLbl);
        add(rollIDlbl);
        add(displayNameLbl);
        add(bookNameLbl);
        add(emailLbl);
        add(addressLbl);
        add(contactLbl);
        add(rentExpiryDateLbl);
        add(rentReservedDateLbl);
        add(statusLbl);
        
        add(bookNameListSrollpane);
        add(bookIDlistScrollPane);
        
        add(givebookComboBox);
        add(rollIDTxt);
        add(bookNameTxt);
        add(emailTxt);
        add(addressTxt);
        add(contactTxt);
        add(rentExpiryDateCal);
        add(rentReservedDateCal);
        add(statusBox);
        
        add(SaveBtnPanel);
        SaveBtnPanel.add(updateBtn);
        SaveBtnPanel.add(saveBtn);
        SaveBtnPanel.add(resetBtn);
        SaveBtnPanel.add(cancelBtn);

    }
    
    private JPanel SaveBtnPanel = new JPanel(new GridLayout(1, 3));

    private JLabel givebooktoLbl = new JLabel("Give Book To:");
    public static JComboBox givebookComboBox = new JComboBox(new Object[]{"-- SELECT USER --", "User","Student","Teacher"});

    public static JPanel imagePanel = new JPanel(new GridLayout(1, 1));
    public static JLabel imageLbl = new JLabel("Photo");
    
    private JLabel rollIDlbl = new JLabel("RollID:");
    public static JTextField rollIDTxt = new JTextField();

    public static JLabel displayNameLbl = new JLabel();

    private JLabel bookNameLbl = new JLabel("Book Name:");
    public static JTextField bookNameTxt = new JTextField();
    
    private JList bookIDList = new JList();
    private JScrollPane bookIDlistScrollPane = new JScrollPane();
    
    private JList bookNameList = new JList();
    private JScrollPane bookNameListSrollpane = new JScrollPane();

    private JLabel emailLbl = new JLabel("Email:");
    public static JTextField emailTxt = new JTextField();
    
    private JLabel contactLbl = new JLabel("Contact:");
    public static JTextField contactTxt = new JTextField();
    
    private JLabel addressLbl = new JLabel("Address:");
    public static JTextField addressTxt = new JTextField();

    private JLabel rentExpiryDateLbl = new JLabel("RentExpiryDate:");
    public static JDateChooser rentExpiryDateCal = new JDateChooser();
    
    private JLabel rentReservedDateLbl = new JLabel("RentReservedDate:");
    public static JDateChooser rentReservedDateCal = new JDateChooser();

    private JLabel statusLbl = new JLabel("Status:");
    public static JComboBox statusBox = new JComboBox(new Object[]{"-- Select Status --","Issued","Returned","Lost","Sold"});
            
    public static JButton saveBtn = new JButton("Save");
    public static JButton updateBtn = new JButton("Update");
    public JButton resetBtn = new JButton("Reset:");
    private JButton cancelBtn = new JButton("Cancel:");

    public JDialog reasonDialog = new JDialog();
    public JLabel reasonLbl = new JLabel("Reason:");
    public JLabel ReasonTxtLbl = new JLabel("Why Do Want Issue this book Bzc its Already have you!");

    public JScrollPane reasonTxtScrollPane = new JScrollPane();
    public JTextArea reasonTxt = new JTextArea();

    public JButton addreasonBtn = new JButton("Add");

    public void InsertReocrd(String rentExpiryDate, String rentReservedDate, int rollID, String Email, String address, String contact, String status){
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
                String query = "INSERT INTO bookrental("
                        + " RentIssueDate,"
                        + " RentExpiryDate,"
                        + " RentReservedDate,"
                        + " GivenToRollID,"
                        + " GivenToEmail,"
                        + " GivenToAddress,"
                        + " GivenToContact,"
                        + " GivenToType,"
                        + " GivenToPerson,"
                        + " SubmitDate,"
                        + " BookTitle,"
                        + " Status,"
                        + " RID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setString(1, Defaults.currentDate());
                preparedStatement.setString(2, rentExpiryDate);
                preparedStatement.setString(3, rentReservedDate);
                preparedStatement.setInt(4, rollID);
                preparedStatement.setString(5, Email);
                preparedStatement.setString(6, address);
                preparedStatement.setString(7, contact);
                preparedStatement.setString(8, givebookComboBox.getSelectedItem().toString());
                preparedStatement.setString(9, displayNameLbl.getText());
                preparedStatement.setString(10, Defaults.currentDate());
                preparedStatement.setString(11, bookName);
                preparedStatement.setString(12, status);
                preparedStatement.setInt(13, bookRID);
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your books has been Issued successfully!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {                
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    }
    public static void UpdateRecord(int givenToRollID, int BookRID, String userType){
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
                
                Date expiryDate = rentExpiryDateCal.getCalendar().getTime();
                Date reservedDate = rentExpiryDateCal.getCalendar().getTime();
                
                String expiryDateStr = Defaults.convertDateToStr(expiryDate);
                String reservedDateStr = Defaults.convertDateToStr(reservedDate);
                
                String query = "UPDATE bookrental SET GivenToType =  '"+ givebookComboBox.getSelectedItem().toString() +"',"
                    + " GivenToRollID = " + rollIDTxt.getText() + ","
                    + " GivenToPerson = '" + displayNameLbl.getText()+ "',"
                    + " GivenToEmail = '"+ emailTxt.getText()+ "',"
                    + " GivenToContact = '" + contactTxt.getText() + "',"
                    + " GivenToAddress = '" + addressTxt.getText() + "',"
                    + " RentExpiryDate = '" + expiryDateStr + "',"
                    + " RentReservedDate = '" + reservedDateStr + "',"
                    + " BookTitle = '" + bookName + "', "
                    + " Status = '" + statusBox.getSelectedItem().toString() + "',"
                    + " RID = " +bookRID + " WHERE GivenToRollID = " + givenToRollID 
                    + " AND "
                    + " GivenToType = '" + userType + "'"
                    + " AND "
                    + " RID = " +BookRID;
                    
                    Statement statement = myConn.createStatement();
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Your Data has been Updated successfully!");

                    BookRentalPanel.displayGivenToPersonLbl.setText(displayNameLbl.getText());
                    BookRentalPanel.displayIDLbl.setText(rollIDTxt.getText());
                    BookRentalPanel.displayBookTitleLbl.setText(bookName);
                    BookRentalPanel.displayGivenToTypeLbl.setText(givebookComboBox.getSelectedItem().toString());
                    BookRentalPanel.displayContactLbl.setText(contactTxt.getText());
                    BookRentalPanel.displayAddressLbl.setText(addressTxt.getText());
                    BookRentalPanel.displayEmailLb.setText(emailTxt.getText());
                    BookRentalPanel.displayRentExpiryDateLbl.setText(expiryDateStr);
                    BookRentalPanel.displayRentIssueDateLbl.setText(reservedDateStr);
                    BookRentalPanel.displayTitleLbl.setText(givebookComboBox.getSelectedItem().toString()+ " ID:");
                    BookRentalPanel.displayStatusDataLbl.setText(statusBox.getSelectedItem().toString()+":");
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
    }
    private void fetchName(){
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
                String query = "SELECT UserName, Email, Contact, Address, UserImage FROM users WHERE UID = "+ rollIDTxt.getText()+ " AND Role = '" + selectedType + "';";
                    PreparedStatement pStatement = myConn.prepareStatement(query);
                    ResultSet resultSet = pStatement.executeQuery();
                    
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            displayNameLbl.setText(resultSet.getString("UserName"));
                            emailTxt.setText(resultSet.getString("Email"));
                            contactTxt.setText(resultSet.getString("Contact"));
                            addressTxt.setText(resultSet.getString("Address"));
                            imageLbl.setIcon(
                                    Defaults.rescaleImageFromBytes(
                                            imageLbl.getWidth(),
                                            imageLbl.getHeight(),
                                            resultSet.getBytes("UserImage")
                                    )
                            );
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Your Roll ID doesn't match any record!");
                        rollIDTxt.setText("");
                        displayNameLbl.setText("No Name");
                        givebookComboBox.setSelectedItem("-- SELECT USER --");
                        emailTxt.setText("");
                        contactTxt.setText("");
                        addressTxt.setText("");
                        imageLbl.setIcon(null);
                    }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
    }
    
    public boolean checkExactBook(int BookRID, int GivenToRollID, String GivenToType){
        try {
            Connection MyConn = Connectivity.MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM bookrental WHERE GivenToRollID = " + GivenToRollID 
                        +" AND "
                        +" RID = " +BookRID
                        +" AND "
                        +" GivenToType = '" + GivenToType + "'";

                Statement statement = MyConn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        return true;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());

        }
        return false;
    }
    
    public static boolean GetBookRID(int BookID, String BookName){
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
            
                String query = "SELECT RID FROM books WHERE "
                        + " BookTitle = '" + BookName + "'"
                        + " AND "
                        + " BID = " + BookID;
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
    
                        bookRID = resultSet.getInt("RID");
                        return true;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
        return false;
    }
    
    public DefaultListModel<String> setBookNameList(JList list, String search){
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        
        String query = "SELECT BookTitle FROM books WHERE BookTitle LIKE('%"+ search +"%')";
        
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        
                        Object object = resultSet.getString("BookTitle");
                        model.addElement(object);
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " + e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage());
        }
        
        return model;
    }
    
    public DefaultListModel<String> setBookIDList(JList list, String search){
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        String query = "SELECT BID FROM books WHERE BookTitle LIKE('%"+ search +"%')";
        try {
            Connection myConn = Connectivity.MySQLConnection.getConnection();
            try {
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        Object object = resultSet.getInt("BID");
                        model.addElement(object);
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " + e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage());
        }
        
        return model;
    }

}