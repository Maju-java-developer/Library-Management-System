package AdminPanel;

import Connectivity.MySQLConnection;
import Connectivity.NewMySQLConnection;
import Core.Defaults;
import DashBaordPanel.HomePanel;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import librarymanagementsystem.*;

public class AddBookPanel extends JPanel{

    int posX;
    int posY;
    int width;
    int height;
    
    public AddBookPanel(){}
    public AddBookPanel(int posX, int posY, int width, int height){
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
    }
    
    public void drawPanel(){
        initComponend();
    }

    private File bookfileUpload = null;
    private File authorFileUpload = null;
    private int gotRelationIDOfAuthor;
    private int TableSelectedRow;
    private int bookID;
    
    public void initComponend(){
        
        setBounds(posX, posY, width, height);
        setLayout(null);
    
        addbookTitleLbl.setBounds(10, 10, 200, 40);
        addbookTitleLbl.setFont(new Font("Sogoe UI Light", 0, 20));
        
        displayTotalBooksLbl.setBounds(30, 110, 200, 40);
        displayTotalBooksLbl.setFont(new Font("Sogue UI Light", 0, 20));
        displayTotalBooksLbl.setText("Total Books: " +Defaults.getCountValue("books"));

        //-------------------------------------------------
        //----------- Working On Add New Book ----------
        //-------------------------------------------------
        addbookBtn.setBounds(110, 15, 180, 30);
        addbookBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                addbookDialog = new BookDialog();
                addbookDialog.SetBtnName("Save");
                addbookDialog.drawPanel();
                
                addbookDialog.setTitle("ADD NEW BOOK");
                addbookDialog.setSize(700, 720);
                addbookDialog.setLayout(null);
                addbookDialog.setLocationRelativeTo(null);
                addbookDialog.setVisible(true);

            }
        });
        //End Work of Add New Book:
        
        //----------------------------------------------
        //------------- Working On Search --------
        //----------------------------------------------
        searchLbl.setBounds(650, 80, 200, 30);
        searchTxt.setBounds(700, 80, 250, 30);
        searchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bookTable.setModel(searchBookNameModel(searchTxt.getText(),bookTable));
            }
        });
        //End Work Of Searching:
        //-------------------------------------------------
        //------------ Working On BookTable ----------
        //-------------------------------------------------
        
        bookTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        DefaultTableModel model = new DefaultTableModel(
                new Object
                    [][]{},
                new Object[]{"#ID","BookTitle","BookType","AuthorName","Edition","Address","Contact","Copies","SubmitDate","Description"}
        );
        
        booktableScrollPane.setBounds(20, 150, getWidth() - 60, 400);
        booktableScrollPane.setViewportView(bookTable);
        bookTable.setModel(model);
        bookTable.setModel(GetBookModel(bookTable));
        bookTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                int RowID = bookTable.getSelectedRow();
                bookID = Integer.parseInt(bookTable.getValueAt(RowID, 0).toString());
                TableSelectedRow = RowID;
                
                TableDataDialog.setTitle("Book Name: " + bookTable.getValueAt(RowID, 1));
                TableDataDialog.setSize(850, 650);
                TableDataDialog.setLayout(null);
                TableDataDialog.setLocationRelativeTo(null);
                TableDataDialog.setVisible(true);

                JPanel btnPanel = new JPanel(new GridLayout(1, 1));
                
                JButton PrintBtn = new JButton("Print");
                PrintBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                 
                        Defaults.printRecord(mainPanelTableDataPanel);
                    }
                });
                JButton DeleteBtn = new JButton("Delete");
                JButton editBtn = new JButton("Edit");
                JButton canceltBtn = new JButton("Cancel");
                
                mainPanelTableDataPanel.setBounds(0, 0, TableDataDialog.getWidth(), getHeight() - 10);
                mainPanelTableDataPanel.setBackground(Color.WHITE);
                
                displayBookImagePanel.setBounds(30, 20, 160, 160);
                
                displayBookImageLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
                displayBookImageLbl.setFont(new Font("Sogoe UI Light", 0, 20));
   
                GetImage(Integer.parseInt(""+bookTable.getValueAt(RowID, 0)));
   
                displayTiltePanel.setBounds(620, 70, 150, 30);
                displayTitleLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
               
                displayDataPanel.setBounds(620, 105, 150, 30);
                displayDataPanel.setBackground(new Color(230, 230, 230));
                
                displayDataLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
                displayDataLbl.setText(""+bookTable.getValueAt(RowID, 0));
                
                displaybookTitleLbl.setBounds(120, 250, 200, 30);
                displaybookTitleLbl.setText(""+bookTable.getValueAt(RowID, 1));
                
                displaybookTypeLbl.setBounds(400, 250, 200, 30);
                displaybookTypeLbl.setText(""+bookTable.getValueAt(RowID, 2));
                
                displayAuthorNameLbl.setBounds(680, 250, 200, 30);
                displayAuthorNameLbl.setText(""+bookTable.getValueAt(RowID, 3));
                
                displayEditionLbl.setBounds(120, 350, 200, 30);
                displayEditionLbl.setText(""+bookTable.getValueAt(RowID, 4));
                
                displayAddressLbl.setBounds(400, 350, 200, 30);
                displayAddressLbl.setText(""+bookTable.getValueAt(RowID, 5));
                
                displayContactLbl.setBounds(680, 350, 200, 30);
                displayContactLbl.setText(""+bookTable.getValueAt(RowID, 6));
                
                displayCopiesLbl.setBounds(120, 450, 200, 30);
                displayCopiesLbl.setText(""+bookTable.getValueAt(RowID, 7));
                
                displaySubmitDateLbl.setBounds(680, 450, 200, 30);
                displaySubmitDateLbl.setText(""+bookTable.getValueAt(RowID, 8));
                
                for (int i = 0; i < captionPanel.length; i++) {
                    
                    captionPanel[i] = new JPanel(new GridLayout(1, 1));
                    captionPanel[i].setBounds(0, 200 + 100 * i, TableDataDialog.getWidth(), 30);
                    captionPanel[i].setBackground(new Color(230, 230, 230));
                    mainPanelTableDataPanel.add(captionPanel[i]);
                    
                }

                for (int i = 0; i < titleLbl.length; i++) {
                    
                    titleLbl[i] = new JLabel();
                    titleLbl[i].setHorizontalAlignment((int)CENTER_ALIGNMENT);
                    titleLbl[i].setText(titleArray[i]);
                    
                }
                for (int i = 0; i < 3; i++) {
                   captionPanel[0].add(titleLbl[i]);
                }
                for (int i = 3; i < 6; i++) {
                    captionPanel[1].add(titleLbl[i]);
                }
                for (int i = 6; i < 9; i++) {
                    captionPanel[2].add(titleLbl[i]);
                }
                
                btnPanel.setBounds(0, 570, mainPanelTableDataPanel.getWidth() - 15, 40);
                btnPanel.setBackground(Color.BLUE);
                
                editBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        editDailog = new BookDialog();
                        editDailog.SetBtnName("Update");
                        editDailog.drawPanel();

                        editDailog.setTitle("Edit To Book:");
                        editDailog.setSize(700, 720);
                        editDailog.setLayout(null);
                        editDailog.setLocationRelativeTo(null);
                        editDailog.setVisible(true);

                        editDailog.booktitleTxt.setText(displaybookTitleLbl.getText());
                        editDailog.booktypeBox.setSelectedItem(displaybookTypeLbl.getText());
                        editDailog.copiesTxt.setText(displayCopiesLbl.getText());
                        editDailog.editionTxt.setText(displayEditionLbl.getText());
                        editDailog.bookImageLbl.setIcon(displayBookImageLbl.getIcon());
                        editDailog.searchAuthorTxt.setText(displayAuthorNameLbl.getText());
                        editDailog.descTxt.setText(""+bookTable.getValueAt(TableSelectedRow, 9));
                    
                    }
                });
                
                DeleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int RID = getRelationID(bookID);
                        
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Deleting book ID " + bookID, JOptionPane.YES_NO_CANCEL_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
    
                                    deleteBook(bookID, RID);
                                    displayTotalBooksLbl.setText("Total Books: " +Defaults.getCountValue("books"));
                                    HomePanel.totalbooksDatabarPanel.dataLbl.setText(""+Defaults.getCountValue("books"));

                                    GetBookModel(bookTable);
                                    TableDataDialog.dispose();

                            }else {
                                JOptionPane.showMessageDialog(null, "Cancelled deleting book!");
                        }
                    }
                });
                
                canceltBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        TableDataDialog.dispose();
                    }
                });
                
                TableDataDialog.add(btnPanel);
                btnPanel.add(PrintBtn);
                btnPanel.add(editBtn);
                btnPanel.add(DeleteBtn);
                btnPanel.add(canceltBtn);
                
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
        
        Defaults.bookTable = bookTable;
        //End Work of Booktable
        
        mainPanelTableDataPanel.add(displayBookImagePanel);

        mainPanelTableDataPanel.add(displayTiltePanel);
        displayTiltePanel.add(displayTitleLbl);
        
        mainPanelTableDataPanel.add(displayDataPanel);
        displayDataPanel.add(displayDataLbl);
        
        mainPanelTableDataPanel.add(displaybookTitleLbl);
        mainPanelTableDataPanel.add(displaybookTypeLbl);
        mainPanelTableDataPanel.add(displayAuthorNameLbl);
        mainPanelTableDataPanel.add(displayEditionLbl);
        mainPanelTableDataPanel.add(displayAddressLbl);
        mainPanelTableDataPanel.add(displayContactLbl);
        mainPanelTableDataPanel.add(displayCopiesLbl);
        mainPanelTableDataPanel.add(displaySubmitDateLbl);
        
        displayBookImagePanel.add(displayBookImageLbl);
        TableDataDialog.add(mainPanelTableDataPanel);

        add(addbookTitleLbl);
        add(addbookBtn);

        add(searchLbl);
        add(searchTxt);
        
        add(booktableScrollPane);
        add(displayTotalBooksLbl);
    }

    private JLabel searchLbl = new JLabel("Search:");
    private JTextField searchTxt = new JTextField();

    private JButton addbookBtn = new JButton("AddBook");
    private BookDialog addbookDialog = null;

    private JLabel addbookTitleLbl = new JLabel("AddBook:");
    public static JLabel displayTotalBooksLbl = new JLabel();

    private JButton addAuthorBtn = new JButton("+");
    private JDialog addAuthorDialog = new JDialog();

    private JTable bookTable = new JTable();
    private JScrollPane booktableScrollPane = new JScrollPane();
   
    private JDialog TableDataDialog = new JDialog();
    private JPanel mainPanelTableDataPanel = new JPanel(null);

    private JPanel captionPanel[] = new JPanel[3];
    private JLabel titleLbl[] = new JLabel[9];
    private String titleArray[] = {"BookTitle","BookType","AuthorName","Editon","Address","Contact","Copies","","SubmitDate"};

    private JPanel displayBookImagePanel = new JPanel(new GridLayout(1, 1));
    public static JLabel displayBookImageLbl = new JLabel("PHOTO:");
    
    private JPanel displayTiltePanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayTitleLbl = new JLabel("Book ID:");
    
    private JPanel displayDataPanel = new JPanel(new GridLayout(1, 1));
    public static JLabel displayDataLbl = new JLabel("ID:");
    
    public static JLabel displaybookTitleLbl = new JLabel();
    public static JLabel displaybookTypeLbl = new JLabel();
    public static JLabel displayAuthorNameLbl = new JLabel();
    public static JLabel displayEditionLbl = new JLabel();
    public static JLabel displayContactLbl = new JLabel();
    public static JLabel displayAddressLbl = new JLabel();
    public static JLabel displayCopiesLbl = new JLabel();
    public static JLabel displaySubmitDateLbl = new JLabel();

    private BookDialog editDailog = null;

    private void GetImage(int ID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = " SELECT BookImage FROM books WHERE BID = "+ID;
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        displayBookImageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        displayBookImagePanel.getWidth(), 
                                        displayBookImagePanel.getHeight()
                                        , resultSet.getBytes("BookImage")));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
    }
    private int getRelationID(int ID){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT RID FROM books WHERE BID = " + ID + ";";
                
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        return resultSet.getInt("RID");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Erorr: "+e.getMessage());
            return 0;
        }
        return 0;
    }

    private void deleteBook(int ID, int RID){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                
                String deleteBook = "DELETE FROM books WHERE "
                        + " BID = "+ ID
                        + " AND"
                        + " RID = "+ RID +";";
                
                Statement statement = MyConn.createStatement();
                statement.executeUpdate(deleteBook);
                JOptionPane.showMessageDialog(null, "Your books has been Deleted successfully!");
                                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Erorr: "+e.getMessage());
        }
    }

    public static DefaultTableModel GetBookModel(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT "
                        +"  BID,"
                        +"  BookTitle,"
                        + " BookType,"
                        + " Copies,"
                        + " Edition,"
                        + " books.SubmitDate,"
                        + " AuthorName,"
                        + " Address,"
                        + " Contact,"
                        + " Description"
                        + " FROM books" 
                        +" JOIN authors WHERE authors.RID = books.RID;";

                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object bookObject[] = {
                            resultSet.getString("BID"),
                            resultSet.getString("BookTitle"),
                            resultSet.getString("BookType"),
                            resultSet.getString("AuthorName"),
                            resultSet.getString("Edition"),
                            resultSet.getString("Address"),
                            resultSet.getString("Contact"),
                            resultSet.getInt("Copies"),
                            resultSet.getString("SubmitDate"),
                            resultSet.getString("Description")
                                
                        }; 
                        model.addRow(bookObject);
                    }
                }
             
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
        }
        
        Defaults.booksModel = model;
        return model;
    }

    private DefaultTableModel searchBookNameModel(String bookName, JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT "
                        +"  BID,"
                        +"  BookTitle,"
                        + " BookType,"
                        + " Copies,"
                        + " Edition,"
                        + " books.SubmitDate,"
                        + " AuthorName,"
                        + " Address,"
                        + " Contact,"
                        + " Description "
                        + " FROM books " 
                        +" JOIN authors WHERE authors.RID = books.RID AND BookTitle LIKE ('%"+ bookName +"%');";
                        
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        

                        String  bookStr[] = {
                            resultSet.getString("BID"),
                            resultSet.getString("BookTitle"),
                            resultSet.getString("BookType"),
                            resultSet.getString("AuthorName"),
                            resultSet.getString("Edition"),
                            resultSet.getString("Address"),
                            resultSet.getString("Contact"),
                            resultSet.getString("Copies"),
                            resultSet.getString("SubmitDate"),
                            resultSet.getString("Description")
                                
                        }; 
                        model.addRow(bookStr);

                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
        return model;
    }

}