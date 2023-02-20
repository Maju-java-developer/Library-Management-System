package AdminPanel;

import static AdminPanel.BookRentalDialog.BookID;
import static AdminPanel.BookRentalDialog.GetBookRID;
import static AdminPanel.BookRentalDialog.UpdateRecord;
import static AdminPanel.BookRentalDialog.addressTxt;
import static AdminPanel.BookRentalDialog.bookName;
import static AdminPanel.BookRentalDialog.bookNameTxt;
import static AdminPanel.BookRentalDialog.bookRID;
import static AdminPanel.BookRentalDialog.contactTxt;
import static AdminPanel.BookRentalDialog.displayNameLbl;
import static AdminPanel.BookRentalDialog.emailTxt;
import static AdminPanel.BookRentalDialog.givebookComboBox;
import static AdminPanel.BookRentalDialog.imageLbl;
import static AdminPanel.BookRentalDialog.rentExpiryDateCal;
import static AdminPanel.BookRentalDialog.rentReservedDateCal;
import static AdminPanel.BookRentalDialog.rollIDTxt;
import static AdminPanel.BookRentalDialog.statusBox;
import Connectivity.*;
import Core.Defaults;
import DashBaordPanel.HomePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DefaultRenderer.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import librarymanagementsystem.AppMain;

public class BookRentalPanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
   
    public BookRentalPanel(){}
    public BookRentalPanel(int posX, int posY, int width, int height){

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    
    public void drawPanel(){

        IntiComponend();
    }

    String issuedTable = "Issued Table";
    String returnedTable = "Returned Table";
    String lostTable = "Lost Table";
    String soldTable = "Sold Table";
    String sortedTable = "Sorted By";
    String TableValue = "";

    public void IntiComponend(){
        
        setBounds(posX, posY, width, height);
        setLayout(null);
        
        BookRentalTitleLbl.setBounds(20, 15, 150, 30);
        BookRentalTitleLbl.setFont(new Font("Sogoe UI Light", 0, 20));
        
        displayTotalIssueBook.setBounds(30, 110, 200, 40);
        displayTotalIssueBook.setFont(new Font("Sogue UI Light", 0, 20));
        displayTotalIssueBook.setText("Total Issued:" +Defaults.getCountValue("bookrental"));

        //------------------------------------------------
        //-------- working On Add New Rental Book ----
        //------------------------------------------------
        
        bookRentalBookBtn.setBounds(140, 15, 200, 30);
        bookRentalBookBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                addBookRentalDialog = new BookRentalDialog();
                addBookRentalDialog.drawPanel();

                BookRentalDialog.updateBtn.setEnabled(false);
                BookRentalDialog.saveBtn.setEnabled(true);

                addBookRentalDialog.setTitle("BookRental:");
                addBookRentalDialog.setSize(735, 720);
                addBookRentalDialog.setLayout(null);
                addBookRentalDialog.setLocationRelativeTo(null);
                addBookRentalDialog.setVisible(true);

                addBookRentalDialog.saveBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String rollID  = addBookRentalDialog.rollIDTxt.getText();
                    String Email = addBookRentalDialog.emailTxt.getText();
                    String contact = addBookRentalDialog.contactTxt.getText();
                    String address = addBookRentalDialog.addressTxt.getText();
                    String GiveToBook = addBookRentalDialog.givebookComboBox.getSelectedItem().toString();
                    String statusStr = addBookRentalDialog.statusBox.getSelectedItem().toString();

                    if (!addBookRentalDialog.statusBox.getSelectedItem().toString().equals("-- Select Status --")) {

                    Date expireyDate = addBookRentalDialog.rentExpiryDateCal.getCalendar().getTime();
                    Date reservedDate = addBookRentalDialog.rentReservedDateCal.getCalendar().getTime();
                    
                    String expiryDateStr = Defaults.convertDateToStr(expireyDate);
                    String reservedDateStr = Defaults.convertDateToStr(reservedDate);

                        if (addBookRentalDialog.GetBookRID(addBookRentalDialog.BookID,addBookRentalDialog. bookName)) {
                            if (!rollID.isEmpty()) {
                                if (!addBookRentalDialog.checkExactBook(bookRID,Integer.parseInt(rollID), GiveToBook)) {
                                    addBookRentalDialog.InsertReocrd(expiryDateStr, reservedDateStr, Integer.parseInt(rollID), Email, address, contact, statusStr);

                                    addBookRentalDialog.rollIDTxt.setText("");
                                    addBookRentalDialog.displayNameLbl.setText("");
                                    addBookRentalDialog.bookNameTxt.setText("");
                                    addBookRentalDialog.emailTxt.setText("");
                                    addBookRentalDialog.contactTxt.setText("");
                                    addBookRentalDialog.addressTxt.setText("");
                                    addBookRentalDialog.givebookComboBox.setSelectedItem("-- SELECT USER --");
                                    addBookRentalDialog.rentExpiryDateCal.setDate(null);
                                    addBookRentalDialog.rentReservedDateCal.setDate(null);
                                    addBookRentalDialog.statusBox.setSelectedItem("-- Select Status --");
                                    addBookRentalDialog.imageLbl.setIcon(null);

                                    displayTotalIssueBook.setText("Total Issued:" +Defaults.getCountValue("bookrental"));
                                    HomePanel.totalIssueBookDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Issued"));
                                    HomePanel.totalReturnedBookDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Returned"));
                                    HomePanel.totalSoldBooksDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Sold"));
                                    HomePanel.totalLostBooksDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Lost"));

                                    GetBookrentalModel(bookRentalTable);
                                    addBookRentalDialog.dispose();

                                }else{

                                    int result = JOptionPane.showConfirmDialog(
                                            null, "Your This Book is already Issued From Over Library!\n"
                                                + "If do you want issue it again, \n"
                                                + "so give me reason about first book Thankyou!!",
                                            "Reason Window ", JOptionPane.YES_NO_CANCEL_OPTION);
                                    if (result == JOptionPane.YES_OPTION) {

                                        addBookRentalDialog.reasonDialog.setTitle("ReasonDialog");
                                        addBookRentalDialog.reasonDialog.setSize(400, 300);
                                        addBookRentalDialog.reasonDialog.setLayout(null);
                                        addBookRentalDialog.reasonDialog.setLocationRelativeTo(null);
                                        addBookRentalDialog.reasonDialog.setVisible(true);

                                        addBookRentalDialog.ReasonTxtLbl.setBounds(30, 10, 300, 30);

                                        addBookRentalDialog.reasonLbl.setBounds(20, 50, 200, 30);
                                        addBookRentalDialog.reasonTxtScrollPane.setBounds(100, 50, 250, 120);
                                        addBookRentalDialog.reasonTxtScrollPane.setViewportView(addBookRentalDialog.reasonTxt);

                                        addBookRentalDialog.addreasonBtn.setBounds(100, 180, 250, 30);

                                        addBookRentalDialog.reasonDialog.add(addBookRentalDialog.reasonLbl);
                                        addBookRentalDialog.reasonDialog.add(addBookRentalDialog.ReasonTxtLbl);
                                        addBookRentalDialog.reasonDialog.add(addBookRentalDialog.reasonTxtScrollPane);
                                        addBookRentalDialog.reasonDialog.add(addBookRentalDialog.addreasonBtn);

                                    }
                                 }

                            }else {
                                JOptionPane.showMessageDialog(null, "Please type Roll ID to fetch data!");
                            }

                        }else {
                            JOptionPane.showMessageDialog(null, "Your ID AND Book are Unmatch!");
                       }

                    }else {
                        JOptionPane.showMessageDialog(null, "Please Select Status First!");
                    }

                    }
                });
            }
        });

        //------------------------------------------------
        //--------  Work on Searching In Table ------
        //------------------------------------------------

        ChooseTableBox.setBounds(800, 80, 200, 30);
        ChooseTableBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                
                String tableNameStr = ChooseTableBox.getSelectedItem().toString();
                
                TableValue = ChooseTableBox.getSelectedItem().toString();
                
                if (tableNameStr.equals(sortedTable)) {
                    
                    bookRentalTable.setModel(GetBookrentalModel(bookRentalTable));

                }else if (tableNameStr.equals(issuedTable)) {
                    
                    bookRentalTable.setModel(GetMixBooksModel(bookRentalTable,"Issued"));
                
                }else if (tableNameStr.equals(returnedTable)) {

                    bookRentalTable.setModel(GetMixBooksModel(bookRentalTable,"Returned"));

                } else if (tableNameStr.equals(lostTable)) {

                    bookRentalTable.setModel(GetMixBooksModel(bookRentalTable,"Lost"));

                } else if (tableNameStr.equals(soldTable)) {
                    
                    bookRentalTable.setModel(GetMixBooksModel(bookRentalTable,"Sold"));

                }

            }
        });
        searchLbl.setBounds(500, 80, 200, 30);
        searchTxt.setBounds(550, 80, 250, 30);
        searchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (ChooseTableBox.getSelectedItem().toString().equals("-- All Tables --") || TableValue.equals(sortedTable)) {

                    bookRentalTable.setModel(SearchBookrentalModel(bookRentalTable, searchTxt.getText()));
                
                } else if (!TableValue.equals("-- All Tables --") && TableValue.equals(returnedTable)) {

                    bookRentalTable.setModel(SearchMixBooksModel(bookRentalTable, searchTxt.getText(),"Returned"));
                
                }else if (!TableValue.equals("-- All Tables --") && TableValue.equals(issuedTable)) {
                    
                    bookRentalTable.setModel(SearchMixBooksModel(bookRentalTable, searchTxt.getText(),"Issued"));

                } else if (!TableValue.equals("-- All Tables --") && TableValue.equals(lostTable)) {
                    
                    bookRentalTable.setModel(SearchMixBooksModel(bookRentalTable, searchTxt.getText(),"Lost"));

                }else if (!TableValue.equals("-- All Tables --") && TableValue.equals(soldTable)) {
                    
                    bookRentalTable.setModel(SearchMixBooksModel(bookRentalTable, searchTxt.getText(),"Sold"));
                }

            }
        });
        //End Work of Searching in Table
        //---------------------------------------------
        //------- working On BookRental Table ----
        //---------------------------------------------

        bookRentalTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"#RollID","BookTitle","GivenToPerson","GivenToType","Email","Contact","Address","RentIssueDate","RentExpiryDate","RentReservedDate","Status"}
                
        );
    
        bookRentalScrollPane.setBounds(20, 150, 980, 420);
        bookRentalScrollPane.setViewportView(bookRentalTable);
        bookRentalTable.setModel(model);
        bookRentalTable.setModel(GetBookrentalModel(bookRentalTable));
        bookRentalTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
           
                int RowID = bookRentalTable.getSelectedRow();
                
                TableDataDialog.setTitle("UserName: "+bookRentalTable.getValueAt(RowID, 2));
                TableDataDialog.setSize(800, 600);
                TableDataDialog.setLayout(null);
                TableDataDialog.setLocationRelativeTo(null);
                TableDataDialog.setVisible(true);
    
                JPanel btnPanel = new JPanel(new GridLayout(1, 4));
                JButton printBtn = new JButton("Print");
                printBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        Defaults.printRecord(mainPanelTableDataDialog);
                    }
                });
                JButton deleteBtn = new JButton("Delete");
                JButton cancelBtn = new JButton("Cancel");
                JButton editBtn = new JButton("Edit");

                mainPanelTableDataDialog.setBounds(0, 0, TableDataDialog.getWidth(), getHeight() - 60);
                mainPanelTableDataDialog.setBackground(Color.WHITE);
 
                displayTitlePanel.setBounds(30, 50, 150, 30);
                
                displayTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
                displayTitleLbl.setText(bookRentalTable.getValueAt(RowID, 3) + " ID:");

                displayIDPanel.setBounds(30, 90, 150, 30);
                displayIDPanel.setBackground(new Color(230, 230, 230));
                
                displayIDLbl.setHorizontalAlignment(SwingConstants.CENTER);
                displayIDLbl.setText(""+bookRentalTable.getValueAt(RowID, 0));
               
                displayBookTitleLbl.setBounds(100, 200, 200, 30);
                displayBookTitleLbl.setText(""+bookRentalTable.getValueAt(RowID, 1));
                
                displayGivenToPersonLbl.setBounds(350, 200, 200, 30);
                displayGivenToPersonLbl.setText(""+bookRentalTable.getValueAt(RowID, 2));

                displayGivenToTypeLbl.setBounds(630, 200, 200, 30);
                displayGivenToTypeLbl.setText(""+bookRentalTable.getValueAt(RowID, 3));

                displayEmailLb.setBounds(100, 300, 200, 30);
                displayEmailLb.setText(""+bookRentalTable.getValueAt(RowID, 4));

                displayAddressLbl.setBounds(350, 300, 200, 30);
                displayAddressLbl.setText(""+bookRentalTable.getValueAt(RowID, 5));
 
                displayContactLbl.setBounds(610, 300, 200, 30);
                displayContactLbl.setText(""+bookRentalTable.getValueAt(RowID, 6));
                
                displayRentIssueDateLbl.setBounds(100, 400, 200, 30);
                displayRentIssueDateLbl.setText(""+bookRentalTable.getValueAt(RowID, 7));

                displayRentExpiryDateLbl.setBounds(350, 400, 200, 30);
                displayRentExpiryDateLbl.setText(""+bookRentalTable.getValueAt(RowID, 8));
                
                displayRentReservedDateLbl.setBounds(610, 400, 200, 30);
                displayRentReservedDateLbl.setText(""+bookRentalTable.getValueAt(RowID, 9));
                
                displayStatusTitleLbl.setBounds(30, 460, 150, 30);
                displayStatusTitleLbl.setFont(new Font("Sogue UI Bold", 0, 20));
                
                displayStatusDataLbl.setBounds(110, 460, 150, 30);
                displayStatusDataLbl.setFont(new Font("Sogue UI Bold", 0, 20));
                displayStatusDataLbl.setForeground(Color.GREEN);
                displayStatusDataLbl.setText(""+bookRentalTable.getValueAt(RowID, 10));
                
                //CaptionPanel Ja bounds Tho set Kaya 

                for (int i = 0; i < captionPanel.length; i++) {
                    
                    captionPanel[i] = new JPanel(new GridLayout(1, 3));
                    captionPanel[i].setBounds(0, 150 + 100 * i, mainPanelTableDataDialog.getWidth(), 30);
                    mainPanelTableDataDialog.add(captionPanel[i]);
                }
                //Hate Khtm CaptionPanel Ja Bounds
                
                //TitleLbl Add Tho Kaya captionpanel me
                for (int i = 0; i < titleLbl.length; i++) {
                    
                    titleLbl[i] = new JLabel();
                    titleLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
                    titleLbl[i].setText(TitleArray[i]);

                }
                
                //AddFirst There Label In captionPanel[0]
                for (int i = 0; i < 3; i++) {
                    
                    captionPanel[0].add(titleLbl[i]);
                }
                
                //AddSecond There Label In captionPanel[1]
                for (int i = 3; i < 6; i++) {
                    captionPanel[1].add(titleLbl[i]);
                }
            
                //Adding Third There Label In captionPanel[2]
                for (int i = 6; i < 9; i++) {
                    captionPanel[2].add(titleLbl[i]);
                }
                //End Here Adding OF All TitleLbl In  

                btnPanel.setBounds(0, 520, TableDataDialog.getWidth() - 15, 40);
                btnPanel.setBackground(Color.BLUE);
                
                btnPanel.add(printBtn);
                btnPanel.add(editBtn);
                btnPanel.add(deleteBtn);
                btnPanel.add(cancelBtn);

            editBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    editDialog = new BookRentalDialog();
                    editDialog.drawPanel();
                    
                    BookRentalDialog.saveBtn.setEnabled(false);
                    BookRentalDialog.updateBtn.setEnabled(true);
                    
                    //Set Text In UpdateFields:
                    editDialog.rollIDTxt.setText(displayIDLbl.getText());
                    editDialog.displayNameLbl.setText(displayGivenToPersonLbl.getText());
                    editDialog.bookNameTxt.setText(displayBookTitleLbl.getText());
                    editDialog.emailTxt.setText(displayEmailLb.getText());
                    editDialog.contactTxt.setText(displayContactLbl.getText());
                    editDialog.addressTxt.setText(displayAddressLbl.getText());
                    editDialog.givebookComboBox.setSelectedItem(displayGivenToTypeLbl.getText());
                    editDialog.statusBox.setSelectedItem(displayStatusDataLbl.getText());
                    editDialog.rentExpiryDateCal.setDate(Defaults.convertStringToDate(displayRentExpiryDateLbl.getText()));
                    editDialog.rentReservedDateCal.setDate(Defaults.convertStringToDate(displayRentIssueDateLbl.getText()));
                    fetchImage();

                    //Set INSTANCE OF EditDialog From bookrentalDialog:
                    editDialog.setTitle("Updateing ID: "+displayIDLbl.getText());
                    editDialog.setSize(735, 720);
                    editDialog.setLayout(null);
                    editDialog.setLocationRelativeTo(null);
                    editDialog.setVisible(true);

                    editDialog.updateBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                       
                        int RID = GetBookRID(displayBookTitleLbl.getText());
                                
                        if (!editDialog.statusBox.getSelectedItem().toString().equals("-- Select Status --")) {
                            if (BookRentalDialog.GetBookRID(editDialog.BookID, editDialog.bookName)) {
                                if (!editDialog.rollIDTxt.getText().isEmpty()) {
                                        
                                        editDialog.UpdateRecord(Integer.parseInt(displayIDLbl.getText()), 
                                                RID, 
                                                displayGivenToTypeLbl.getText());

                                        editDialog.rollIDTxt.setText("");
                                        editDialog.displayNameLbl.setText("");
                                        editDialog.bookNameTxt.setText("");
                                        editDialog.emailTxt.setText("");
                                        editDialog.contactTxt.setText("");
                                        editDialog.addressTxt.setText("");
                                        editDialog.givebookComboBox.setSelectedItem("-- SELECT USER --");
                                        editDialog.rentExpiryDateCal.setDate(null);
                                        editDialog.rentReservedDateCal.setDate(null);
                                        editDialog.statusBox.setSelectedItem("-- Select Status --");
                                        editDialog.imageLbl.setIcon(null);
                                        GetBookrentalModel(bookRentalTable);
                                        editDialog.dispose();

                                }else {
                                    JOptionPane.showMessageDialog(null, "Please type Roll ID to fetch data!");
                                }

                            }else {
                                JOptionPane.showMessageDialog(null, "Your ID AND Book are Unmatch!");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Please Select Status First!");
                        }

                        }
                    });
                    }
                });

                deleteBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int result = JOptionPane.showConfirmDialog(null, "Are You sure ?", "Delete Window", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            int BookRID = GetBookRID(displayBookTitleLbl.getText());
                            
                            DeleteRecord(
                                    BookRID, 
                                    Integer.parseInt(displayIDLbl.getText()), 
                                    displayGivenToTypeLbl.getText());
                            
                            displayTotalIssueBook.setText("Total Issued: " +Defaults.getCountValue("bookrental"));
                            HomePanel.totalIssueBookDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Issued"));
                            HomePanel.totalReturnedBookDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Returned"));
                            HomePanel.totalSoldBooksDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Sold"));
                            HomePanel.totalLostBooksDatabarPanel.dataLbl.setText(""+Defaults.GetBookrentalCount("Lost"));

                              GetBookrentalModel(bookRentalTable);
                            TableDataDialog.dispose();
                            
                        }else {
                            JOptionPane.showMessageDialog(null, "Canceling Delete Issued Book!");
                        }
                    }
                });
                cancelBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        TableDataDialog.dispose();
                    }
                });

                TableDataDialog.add(btnPanel);
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
        Defaults.bookRentalTable = bookRentalTable;
        //End Work On BookRental Table
        
        //------------------------------------------------
        //--------  Working On BookRental Button ------
        //------------------------------------------------

        //End Work On BookRental Button 
        
        mainPanelTableDataDialog.add(displayTitlePanel);
        mainPanelTableDataDialog.add(displayIDPanel);
        
        mainPanelTableDataDialog.add(displayBookTitleLbl);
        mainPanelTableDataDialog.add(displayGivenToPersonLbl);
        mainPanelTableDataDialog.add(displayGivenToTypeLbl);
        mainPanelTableDataDialog.add(displayEmailLb);
        mainPanelTableDataDialog.add(displayAddressLbl);
        mainPanelTableDataDialog.add(displayContactLbl);
        mainPanelTableDataDialog.add(displayRentIssueDateLbl);
        mainPanelTableDataDialog.add(displayRentExpiryDateLbl);
        mainPanelTableDataDialog.add(displayRentReservedDateLbl);
        mainPanelTableDataDialog.add(displayStatusTitleLbl);
        mainPanelTableDataDialog.add(displayStatusDataLbl);
        
        displayIDPanel.add(displayIDLbl);
        displayTitlePanel.add(displayTitleLbl);
        
        TableDataDialog.add(mainPanelTableDataDialog);
    
        add(searchLbl);
        add(searchTxt);
        
        add(BookRentalTitleLbl);
        add(bookRentalBookBtn);   
        
        add(bookRentalScrollPane);
        add(displayTotalIssueBook);

        add(ChooseTableBox);
        
    }
    
    private JLabel BookRentalTitleLbl = new JLabel("BookRental:");
    
    private JButton bookRentalBookBtn = new JButton("BookRental:");
    private BookRentalDialog addBookRentalDialog = null;

    private JDialog TableDataDialog = new JDialog();
    private JPanel captionPanel[] = new JPanel[3];

    private JLabel searchLbl = new JLabel("Search:");
    private JTextField searchTxt = new JTextField();
    
    private JTable bookRentalTable = new JTable();
    private JScrollPane bookRentalScrollPane = new JScrollPane();

    private JPanel mainPanelTableDataDialog = new JPanel(null);
    
    private JPanel displayTitlePanel = new JPanel(new GridLayout(1, 1));
    public static JLabel displayTitleLbl = new JLabel();
    
    private JPanel displayIDPanel = new JPanel(new GridLayout(1, 1));
    public static JLabel displayIDLbl = new JLabel();
    
    private JLabel titleLbl[] = new JLabel[9];
    private String TitleArray[] = {"BookTitle","GivenToPerson","GivenToType","Email","Contact",
        "Address","RentIssueDate","RentExpiryDate","RentReservedDate"};

    public static JLabel displayBookTitleLbl = new JLabel("BookTitle:");
    public static JLabel displayGivenToPersonLbl = new JLabel("GivenToPerson:");
    public static JLabel displayGivenToTypeLbl = new JLabel("GivenToType:");
    public static JLabel displayEmailLb = new JLabel("Email:");
    public static JLabel displayContactLbl = new JLabel("Contact:");
    public static JLabel displayAddressLbl = new JLabel("Address:");
    public static JLabel displayRentIssueDateLbl = new JLabel("RentIssueDate:");
    public static JLabel displayRentExpiryDateLbl = new JLabel("RentExpiryDate:");
    public static JLabel displayRentReservedDateLbl = new JLabel("RentReservedDate:");
    public static JLabel displayTotalIssueBook = new JLabel();
    
    private JLabel displayStatusTitleLbl = new JLabel("Status:");
    public static JLabel displayStatusDataLbl = new JLabel("Status:");
    
    private BookRentalDialog editDialog = null;
    
    private JComboBox ChooseTableBox = new JComboBox(new Object[]{"-- All Tables --","Sorted By","Issued Table","Returned Table","Lost Table","Sold Table"});
            
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
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
//        Defaults.bookRentalModel = model;
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

    private DefaultTableModel GetMixBooksModel(JTable table , String Status){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = " SELECT * FROM bookrental WHERE Status = '" + Status + "'";

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

    private DefaultTableModel SearchMixBooksModel(JTable table, String NameStr ,String status){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM bookrental WHERE BookTitle LIKE('%" + NameStr + "%') AND Status = '" + status + "'";

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
    
    private void DeleteRecord(int RID, int GivenToRollID, String UserType){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {

                String qurey = "DELETE FROM bookrental WHERE RID = " +RID
                        +" AND "
                        +" GivenToRollID = " +GivenToRollID
                        +" AND "
                        + "GivenToType = '" + UserType + "'";

                PreparedStatement preparedStatement = myConn.prepareStatement(qurey);
                preparedStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Your Data has been deleted Successfully!");
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    }
     
    public static int GetBookRID(String bookTitle){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                
                String query = "SELECT RID FROM books WHERE BookTitle = '" + bookTitle + "'";
                
                Statement statement = myConn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        BookRentalDialog.bookRID = resultSet.getInt("RID");
                        
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());

        }
        
        return BookRentalDialog.bookRID;
    }
    
    private void fetchImage(){
        if (!displayGivenToTypeLbl.getText().equals("-- SELECT USER --")&& displayGivenToTypeLbl.getText().equals("User")) {
            try {
                Connection myConn = MySQLConnection.getConnection();
                try {
                    String query = "SELECT UserImage FROM users WHERE UID = "+editDialog.rollIDTxt.getText();
                    
                    PreparedStatement preparedStatement = myConn.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {                            

                        editDialog.imageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        editDialog.imagePanel.getWidth(),
                                        editDialog.imagePanel.getHeight(),
                                        resultSet.getBytes("UserImage"))
                        );

                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
            
        } else if (!displayGivenToTypeLbl.getText().equals("-- SELECT USER --")&& displayGivenToTypeLbl.getText().equals("Student")) {
            try {
                Connection myConn = MySQLConnection.getConnection();
                try {

                    String query = "SELECT Image FROM students WHERE SID = "+editDialog.rollIDTxt.getText();
                    
                    PreparedStatement preparedStatement = myConn.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {                            

                        editDialog.imageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        editDialog.imagePanel.getWidth(),
                                         editDialog.imagePanel.getHeight(),
                                        resultSet.getBytes("Image"))
                        );

                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
            }
            
        } else if (!displayGivenToTypeLbl.getText().equals("-- SELECT USER --")&& displayGivenToTypeLbl.getText().equals("Teacher")) {
            try {
                Connection myConn = MySQLConnection.getConnection();
                try {

                    String query = "SELECT Image FROM teachers WHERE TID = "+editDialog.rollIDTxt.getText();
                    
                    PreparedStatement preparedStatement = myConn.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {                            

                        editDialog.imageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        editDialog.imagePanel.getWidth(),
                                         editDialog.imagePanel.getHeight(),
                                        resultSet.getBytes("Image"))
                        );

                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        }
    }
  
}